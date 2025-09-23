package com.pragma.restaurant.infrastructure.out.jpa.adapter;

import com.pragma.restaurant.application.mapper.IPageEntityMapper;
import com.pragma.restaurant.application.mapper.IPageListMapper;
import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.model.PageList;
import com.pragma.restaurant.domain.spi.IOrderPersistencePort;
import com.pragma.restaurant.infrastructure.exception.OrderNotFoundException;
import com.pragma.restaurant.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.CycleAvoidingMappingContext;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IPageEntityMapper pageEntityMapper;
    private final IPageListMapper pageListMapper;

    @Override
    public Order saveOrder(Order order) {
        return orderEntityMapper.toOrder(
                orderRepository
                        .save(orderEntityMapper.toEntity(order, new CycleAvoidingMappingContext())),
                new CycleAvoidingMappingContext());
    }

    @Override
    public Order getClientActiveOrder(Long idClient) {
        List<Order.OrderState> validStates = List.of(Order.OrderState.PENDING, Order.OrderState.WORKING, Order.OrderState.FINISHED);
        return orderRepository.getFirstByIdClientAndStateIsIn(idClient, validStates)
                .map((OrderEntity orderEntity) -> orderEntityMapper.toOrder(orderEntity, new CycleAvoidingMappingContext()))
                .orElse(null);
    }

    @Override
    public PageList<Order> getAllRestaurantOrders(Long idRestaurant, PageConfig paginator, Integer state) {
        Page<OrderEntity> orders;
        if (state == null) {
            orders = orderRepository.findAllByRestaurantId(idRestaurant, pageEntityMapper.toPageable(paginator));
        }
        else {
            Order.OrderState orderState = Order.OrderState.getState(state);
            orders = orderRepository.findAllByRestaurantIdAndState(idRestaurant, orderState, pageEntityMapper.toPageable(paginator));
        }

        return pageListMapper.toPageList(orderEntityMapper.toPageOrderList(orders, new CycleAvoidingMappingContext()));
    }

    @Override
    public Order getOrderById(Long idOrder) {
        return orderRepository
                .findById(idOrder)
                .map((OrderEntity orderEntity) -> orderEntityMapper.toOrder(orderEntity, new CycleAvoidingMappingContext()))
                .orElseThrow(OrderNotFoundException::new);
    }
}
