package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.Order;

public interface IOrderPersistencePort {
    void saveOrder(Order order);

    Order getClientActiveOrder(Long idClient);
}
