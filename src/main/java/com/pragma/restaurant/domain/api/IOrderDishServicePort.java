package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.OrderDish;

public interface IOrderDishServicePort {
    void saveOrderDish(OrderDish orderDish);
}