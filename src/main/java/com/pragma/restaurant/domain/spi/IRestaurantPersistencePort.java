package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.model.PageList;
import com.pragma.restaurant.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    PageList<Restaurant> getAllRestaurants(PageConfig pageConfig);
    Restaurant getRestaurantById(Long id);
    Restaurant getRestaurantByOwnerId(Long idOwner);
}
