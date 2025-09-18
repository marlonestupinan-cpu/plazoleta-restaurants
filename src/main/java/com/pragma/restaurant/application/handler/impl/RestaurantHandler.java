package com.pragma.restaurant.application.handler.impl;

import com.pragma.restaurant.application.dto.request.CreateRestaurantRequestDto;
import com.pragma.restaurant.application.dto.response.ClientRestaurantResponseDto;
import com.pragma.restaurant.application.handler.IRestaurantHandler;
import com.pragma.restaurant.application.mapper.IClientRestaurantResponseMapper;
import com.pragma.restaurant.application.mapper.ICreateRestaurantRequestMapper;
import com.pragma.restaurant.application.mapper.IRestaurantResponseMapper;
import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {
    private final ICreateRestaurantRequestMapper createRestaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;
    private final IClientRestaurantResponseMapper clientRestaurantResponseMapper;
    private final IRestaurantServicePort restaurantService;

    @Override
    public void saveRestaurant(CreateRestaurantRequestDto requestRestaurant) {
        restaurantService.saveRestaurant(createRestaurantRequestMapper.toRestaurant(requestRestaurant));
    }

    @Override
    public Page<ClientRestaurantResponseDto> getAllRestaurants(Pageable pageable) {
        return clientRestaurantResponseMapper.toPageResponseList(restaurantService.getAllRestaurants(pageable));
    }
}
