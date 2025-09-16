package com.pragma.restaurant.infrastructure.configuration;

import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.spi.IRestaurantPersistencePort;
import com.pragma.restaurant.domain.usecase.RestaurantUseCase;
import com.pragma.restaurant.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort());
    }
}