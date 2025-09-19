package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.api.IOrderDishServicePort;
import com.pragma.restaurant.domain.api.IOrderServicePort;
import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    @Override
    public void saveOrder(Order order) {
        orderPersistencePort.saveOrder(order);
    }

    @Override
    public Order getClientActiveOrder(Long idClient) {
        return orderPersistencePort.getClientActiveOrder(idClient);
    }

    @Override
    public Page<Order> getAllRestaurantOrders(Long idRestaurant, Pageable pageable, Integer state) {
        return orderPersistencePort.getAllRestaurantOrders(idRestaurant, pageable, state);
    }

    @Override
    public Order getOrderById(Long idOrder) {
        return orderPersistencePort.getOrderById(idOrder);
    }
}
