package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.Dish;

public interface IDishPersistencePort {
    void saveDish(Dish dish);
}
