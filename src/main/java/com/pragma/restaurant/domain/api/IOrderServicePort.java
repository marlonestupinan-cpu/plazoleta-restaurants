package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.Order;

public interface IOrderServicePort {
    void saveOrder(Order order);

    Order getClientActiveOrder(Long idClient);
}
