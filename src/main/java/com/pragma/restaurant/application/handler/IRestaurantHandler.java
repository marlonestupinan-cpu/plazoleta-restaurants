package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.CreateRestaurantRequestDto;
import com.pragma.restaurant.application.dto.response.ClientRestaurantResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRestaurantHandler {
    void saveRestaurant(CreateRestaurantRequestDto restaurant);
    Page<ClientRestaurantResponseDto> getAllRestaurants(Pageable pageable);

}
