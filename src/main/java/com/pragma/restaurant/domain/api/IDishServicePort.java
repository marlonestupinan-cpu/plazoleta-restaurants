package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.Dish;

public interface IDishServicePort {
    void saveDish(Dish dish);

    Dish getById(Long id);
}
