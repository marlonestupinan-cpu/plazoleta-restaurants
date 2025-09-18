package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
    Page<Restaurant> getAllRestaurants(Pageable pageable);
    Restaurant getRestaurantById(Long id);
}
