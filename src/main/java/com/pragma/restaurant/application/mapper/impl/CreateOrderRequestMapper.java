package com.pragma.restaurant.application.mapper.impl;

import com.pragma.restaurant.application.dto.request.CreateOrderRequestDto;
import com.pragma.restaurant.application.mapper.ICreateOrderRequestMapper;
import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class CreateOrderRequestMapper implements ICreateOrderRequestMapper {
    private final IRestaurantServicePort restaurantService;

    @Override
    public Order toOrder(CreateOrderRequestDto createOrderRequestDto) {
        Restaurant restaurant = restaurantService.getRestaurantById(createOrderRequestDto.getIdRestaurant());

        return Order.builder()
                .date(new Date())
                .restaurant(restaurant)
                .build();
    }
}
