package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.api.IDishServicePort;
import com.pragma.restaurant.domain.exception.NotRestaurantOwnerException;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    @Override
    public void saveDish(Dish dish, Long id) {
        if (!dish.getRestaurant().getIdOwner().equals(id)) {
            throw new NotRestaurantOwnerException();
        }
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public Dish getById(Long id) {
        return dishPersistencePort.getById(id);
    }
}
