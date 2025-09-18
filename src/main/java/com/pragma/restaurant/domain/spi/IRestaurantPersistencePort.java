package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    Page<Restaurant> getAllRestaurants(Pageable pageable);
    Restaurant getRestaurantById(Long id);
    Restaurant getRestaurantByOwnerId(Long idOwner);
}
