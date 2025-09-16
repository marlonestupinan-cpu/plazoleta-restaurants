package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DishUseCaseTest {

    private IDishPersistencePort dishPersistencePort;
    private DishUseCase dishUseCase;

    @BeforeEach
    void setUp() {
        dishPersistencePort = mock(IDishPersistencePort.class);
        dishUseCase = new DishUseCase(dishPersistencePort);
    }

    @Test
    void shouldSaveDish() {
        Dish dish = new Dish();
        dish.setName("Ajiaco Santafereño");
        dish.setPrice(25000);

        dishUseCase.saveDish(dish);

        verify(dishPersistencePort, times(1)).saveDish(dish);
    }
}