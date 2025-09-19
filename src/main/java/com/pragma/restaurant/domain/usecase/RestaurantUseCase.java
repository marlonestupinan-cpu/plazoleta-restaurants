package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.api.IUserServicePort;
import com.pragma.restaurant.domain.exception.NotOwnerException;
import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        return restaurantPersistencePort.getAllRestaurants(pageable);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantPersistencePort.getRestaurantById(id);
    }

    @Override
    public Restaurant getOwnerRestaurant(Long idOwner) {
        return restaurantPersistencePort.getRestaurantByOwnerId(idOwner);
    }
}
