package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.CreateRestaurantRequestDto;
import com.pragma.restaurant.application.dto.response.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantHandler {
    void saveRestaurant(CreateRestaurantRequestDto restaurant);
    List<RestaurantResponseDto> getAllRestaurants();
}
