package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.Category;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.model.PageList;
import com.pragma.restaurant.domain.model.Restaurant;

public interface IDishServicePort {
    void saveDish(Dish dish, Long ownerId);
    void saveDish(Dish dish, Long ownerId, Restaurant restaurant, Category category);
    void updateDish(Dish dish, Integer price, String description, Long ownerId);
    void changeActiveState(Dish dish, Long ownerId, Boolean newState);
    Dish getById(Long id);
    PageList<Dish> getAllDishesFromRestaurant(Long restaurantId, PageConfig pageable, Long categoryId);
}
