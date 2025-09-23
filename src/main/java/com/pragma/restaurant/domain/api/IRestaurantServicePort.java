package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.model.PageList;
import com.pragma.restaurant.domain.model.Restaurant;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
    PageList<Restaurant> getAllRestaurants(PageConfig pageable);
    Restaurant getRestaurantById(Long id);
    Restaurant getOwnerRestaurant(Long idOwner);
}
