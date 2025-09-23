package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.PageConfig;
import org.springframework.data.domain.Page;

public interface IDishPersistencePort {
    void saveDish(Dish dish);

    Dish getById(Long id);

    Page<Dish> getByRestaurant(Long restaurantId, PageConfig pageable, Long categoryId);
}
