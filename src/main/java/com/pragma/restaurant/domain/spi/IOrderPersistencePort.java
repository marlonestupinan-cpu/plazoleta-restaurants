package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.model.PageList;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);

    Order getClientActiveOrder(Long idClient);

    PageList<Order> getAllRestaurantOrders(Long idRestaurant, PageConfig pageable, Integer state);

    Order getOrderById(Long idOrder);
}
