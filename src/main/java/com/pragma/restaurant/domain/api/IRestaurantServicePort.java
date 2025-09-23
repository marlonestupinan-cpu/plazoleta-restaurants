package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.model.Restaurant;
import org.springframework.data.domain.Page;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
    Page<Restaurant> getAllRestaurants(PageConfig pageable);
    Restaurant getRestaurantById(Long id);
    Restaurant getOwnerRestaurant(Long idOwner);
}
