package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishPersistencePort {
    void saveDish(Dish dish);

    Dish getById(Long id);

    Page<Dish> getByRestaurant(Long restaurantId, Pageable pageable, Long categoryId);
}
