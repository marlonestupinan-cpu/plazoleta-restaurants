package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.OrderDish;

public interface IOrderDishPersistencePort {
    void saveOrderDish(OrderDish orderDish);
}
