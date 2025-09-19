package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderServicePort {
    void saveOrder(Order order);

    Order getClientActiveOrder(Long idClient);

    Page<Order> getAllRestaurantOrders(Long idRestaurant, Pageable pageable, Integer state);

    Order getOrderById(Long idOrder);
}
