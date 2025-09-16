package com.pragma.restaurant.infrastructure.configuration;

import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.api.IUserServicePort;
import com.pragma.restaurant.domain.spi.IRestaurantPersistencePort;
import com.pragma.restaurant.domain.usecase.RestaurantUseCase;
import com.pragma.restaurant.domain.usecase.UserUseCase;
import com.pragma.restaurant.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final WebClient.Builder webClientBuilder;
    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }
    @Bean
    public IUserServicePort  userServicePort() {
        return new UserUseCase(webClientBuilder.build());
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), userServicePort());
    }
}