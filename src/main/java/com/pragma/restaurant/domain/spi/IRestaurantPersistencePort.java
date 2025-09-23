package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.model.Restaurant;
import org.springframework.data.domain.Page;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    Page<Restaurant> getAllRestaurants(PageConfig pageConfig);
    Restaurant getRestaurantById(Long id);
    Restaurant getRestaurantByOwnerId(Long idOwner);
}
