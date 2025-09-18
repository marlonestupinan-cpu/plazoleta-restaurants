package com.pragma.restaurant.infrastructure.out.jpa.adapter;

import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.domain.spi.IOrderPersistencePort;
import com.pragma.restaurant.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.CycleAvoidingMappingContext;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    @Override
    public void saveOrder(Order order) {
        orderRepository.save(orderEntityMapper.toEntity(order, new CycleAvoidingMappingContext()));
    }

    @Override
    public Order getClientActiveOrder(Long idClient) {
        List<Order.OrderState> validStates = List.of(Order.OrderState.PENDING, Order.OrderState.WORKING, Order.OrderState.FINISHED);
        return orderRepository.getFirstByIdClientAndStateIsIn(idClient, validStates)
                .map((OrderEntity orderEntity) -> orderEntityMapper.toOrder(orderEntity, new CycleAvoidingMappingContext()))
                .orElse(null);
    }
}
