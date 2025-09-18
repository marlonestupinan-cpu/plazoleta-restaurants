package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.exception.NotOwnerException;
import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RestaurantUseCaseTest {

    private IRestaurantPersistencePort restaurantPersistencePort;
    private RestaurantUseCase restaurantUseCase;
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        userUseCase = mock(UserUseCase.class);
        restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, userUseCase);
    }

    @Test
    void shouldSaveRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setIdOwner(1L);

        when(userUseCase.isOwner(anyLong())).thenReturn(true);

        restaurantUseCase.saveRestaurant(restaurant);

        verify(restaurantPersistencePort, times(1)).saveRestaurant(restaurant);
    }

    @Test
    void shouldNotSaveRestaurantIfUserIsNotOwner() {
        Restaurant restaurant = new Restaurant();
        restaurant.setIdOwner(1L);

        when(userUseCase.isOwner(anyLong())).thenReturn(false);

        assertThrows(NotOwnerException.class,  () -> restaurantUseCase.saveRestaurant(restaurant));
    }
}
