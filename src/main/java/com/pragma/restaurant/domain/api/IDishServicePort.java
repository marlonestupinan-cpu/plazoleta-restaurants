package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishServicePort {
    void saveDish(Dish dish, Long ownerId);

    Dish getById(Long id);

    Page<Dish> getAllDishesFromRestaurant(Long restaurantId, Pageable pageable, Long categoryId);
}
