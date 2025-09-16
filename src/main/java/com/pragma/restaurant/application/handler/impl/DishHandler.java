package com.pragma.restaurant.application.handler.impl;

import com.pragma.restaurant.application.dto.request.CreateDishRequestDto;
import com.pragma.restaurant.application.handler.IDishHandler;
import com.pragma.restaurant.application.mapper.ICreateDishRequestMapper;
import com.pragma.restaurant.domain.api.ICategoryServicePort;
import com.pragma.restaurant.domain.api.IDishServicePort;
import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.model.Category;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {
    private final ICreateDishRequestMapper createDishRequestMapper;
    private final IDishServicePort  dishServicePort;
    private final IRestaurantServicePort  restaurantServicePort;
    private final ICategoryServicePort  categoryServicePort;

    @Override
    public void saveDish(CreateDishRequestDto createDishRequestDto) {
        Dish dish = createDishRequestMapper.toDish(createDishRequestDto);
        Restaurant restaurant = restaurantServicePort.getRestaurantById(createDishRequestDto.getIdRestaurant());
        Category category = categoryServicePort.getCategoryById(createDishRequestDto.getIdCategory());

        dish.setRestaurant(restaurant);
        dish.setCategory(category);

        dishServicePort.saveDish(dish);
    }
}
