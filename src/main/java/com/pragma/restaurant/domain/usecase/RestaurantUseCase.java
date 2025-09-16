package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.api.IUserServicePort;
import com.pragma.restaurant.domain.exception.NotOwnerException;
import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserServicePort userServicePort;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        if (!userServicePort.isOwner(restaurant.getIdOwner()))
            throw new NotOwnerException();

        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }
}
