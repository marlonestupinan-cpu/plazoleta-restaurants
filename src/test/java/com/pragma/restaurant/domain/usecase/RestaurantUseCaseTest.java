package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    private IRestaurantPersistencePort restaurantPersistencePort;
    private RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setUp() {
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort);
    }

    @Test
    void shouldSaveRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurantUseCase.saveRestaurant(restaurant);

        verify(restaurantPersistencePort, times(1)).saveRestaurant(restaurant);
    }

    @Test
    void shouldReturnAllRestaurants() {
        List<Restaurant> mockRestaurants = Arrays.asList(new Restaurant(), new Restaurant());
        when(restaurantPersistencePort.getAllRestaurants()).thenReturn(mockRestaurants);

        List<Restaurant> restaurants = restaurantUseCase.getAllRestaurants();

        assertEquals(2, restaurants.size());
        verify(restaurantPersistencePort, times(1)).getAllRestaurants();
    }
}
