package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderPersistencePort {
    void saveOrder(Order order);

    Order getClientActiveOrder(Long idClient);

    Page<Order> getAllRestaurantOrders(Long idRestaurant, Pageable pageable, Integer state);

    Order getOrderById(Long idOrder);
}
