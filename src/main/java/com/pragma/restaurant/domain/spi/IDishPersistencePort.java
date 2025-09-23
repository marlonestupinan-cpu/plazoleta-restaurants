package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.model.PageList;

public interface IDishPersistencePort {
    void saveDish(Dish dish);

    Dish getById(Long id);

    PageList<Dish> getByRestaurant(Long restaurantId, PageConfig pageable, Long categoryId);
}
