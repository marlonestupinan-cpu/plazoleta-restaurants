package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.Category;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishServicePort {
    void saveDish(Dish dish, Long ownerId);
    void saveDish(Dish dish, Long ownerId, Restaurant restaurant, Category category);
    void updateDish(Dish dish, Integer price, String description, Long ownerId);
    void changeActiveState(Dish dish, Long ownerId, Boolean newState);
    Dish getById(Long id);

    Page<Dish> getAllDishesFromRestaurant(Long restaurantId, Pageable pageable, Long categoryId);
}
