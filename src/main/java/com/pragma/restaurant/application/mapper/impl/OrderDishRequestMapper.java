package com.pragma.restaurant.application.mapper.impl;

import com.pragma.restaurant.application.dto.request.OrderDishRequestDto;
import com.pragma.restaurant.application.mapper.IOrderDishRequestMapper;
import com.pragma.restaurant.domain.api.IDishServicePort;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.OrderDish;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderDishRequestMapper implements IOrderDishRequestMapper {
    private final IDishServicePort dishService;

    @Override
    public OrderDish toOrderDish(OrderDishRequestDto request) {
        Dish dish = dishService.getById(request.getIdDish());
        return OrderDish.builder()
                .dish(dish)
                .quantity(request.getQuantity())
                .build();
    }
}
