package com.pragma.restaurant.application.mapper;

import com.pragma.restaurant.application.dto.request.CreateOrderRequestDto;
import com.pragma.restaurant.domain.model.Order;

public interface ICreateOrderRequestMapper {
    Order toOrder(CreateOrderRequestDto createOrderRequestDto);
}
