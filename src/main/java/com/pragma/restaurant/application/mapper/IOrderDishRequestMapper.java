package com.pragma.restaurant.application.mapper;

import com.pragma.restaurant.application.dto.request.OrderDishRequestDto;
import com.pragma.restaurant.domain.model.OrderDish;

public interface IOrderDishRequestMapper {
    OrderDish toOrderDish(OrderDishRequestDto request);
}
