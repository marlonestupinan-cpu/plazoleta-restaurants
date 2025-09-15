package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
}
