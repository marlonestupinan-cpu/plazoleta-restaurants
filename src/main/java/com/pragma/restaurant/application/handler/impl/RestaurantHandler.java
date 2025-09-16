package com.pragma.restaurant.application.handler.impl;

import com.pragma.restaurant.application.dto.request.CreateRestaurantRequestDto;
import com.pragma.restaurant.application.dto.response.RestaurantResponseDto;
import com.pragma.restaurant.application.handler.IRestaurantHandler;
import com.pragma.restaurant.application.mapper.ICreateRestaurantRequestMapper;
import com.pragma.restaurant.application.mapper.IRestaurantResponseMapper;
import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {
    private final ICreateRestaurantRequestMapper createRestaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;
    private final IRestaurantServicePort restaurantService;

    @Override
    public void saveRestaurant(CreateRestaurantRequestDto requestRestaurant) {
        restaurantService.saveRestaurant(createRestaurantRequestMapper.toRestaurant(requestRestaurant));
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantResponseMapper.toResponseList(restaurantService.getAllRestaurants());
    }
}
