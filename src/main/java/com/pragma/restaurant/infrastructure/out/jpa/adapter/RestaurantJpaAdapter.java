package com.pragma.restaurant.infrastructure.out.jpa.adapter;

import com.pragma.restaurant.application.mapper.IPageEntityMapper;
import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.domain.spi.IRestaurantPersistencePort;
import com.pragma.restaurant.infrastructure.exception.NoDataFoundException;
import com.pragma.restaurant.infrastructure.exception.RestaurantNotFoundException;
import com.pragma.restaurant.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IPageEntityMapper pageEntityMapper;
    @Override
    public void saveRestaurant(Restaurant restaurant) {

        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public Page<Restaurant> getAllRestaurants(PageConfig pageConfig) {

        Page<RestaurantEntity> restaurants = restaurantRepository.findAll(pageEntityMapper.toPageable(pageConfig));
        if (restaurants.isEmpty()) {
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toPageRestaurantList(restaurants);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository
                .findById(id)
                .map(restaurantEntityMapper::toRestaurant)
                .orElseThrow(RestaurantNotFoundException::new);
    }

    @Override
    public Restaurant getRestaurantByOwnerId(Long idOwner) {
        return restaurantRepository
                .findByIdOwner(idOwner)
                .map(restaurantEntityMapper::toRestaurant)
                .orElseThrow(RestaurantNotFoundException::new);
    }
}
