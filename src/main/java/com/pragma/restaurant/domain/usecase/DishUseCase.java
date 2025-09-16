package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.api.IDishServicePort;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    @Override
    public void saveDish(Dish dish) {
        dishPersistencePort.saveDish(dish);
    }
}
