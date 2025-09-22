package com.pragma.restaurant.infrastructure.configuration;

import com.pragma.restaurant.application.handler.ITokenGenerator;
import com.pragma.restaurant.application.handler.impl.UserHandler;
import com.pragma.restaurant.application.handler.impl.auth.jwt.JwtGenerator;
import com.pragma.restaurant.application.mapper.ICreateOrderRequestMapper;
import com.pragma.restaurant.application.mapper.IOrderDishRequestMapper;
import com.pragma.restaurant.application.mapper.impl.CreateOrderRequestMapper;
import com.pragma.restaurant.application.mapper.impl.OrderDishRequestMapper;
import com.pragma.restaurant.domain.api.ICategoryServicePort;
import com.pragma.restaurant.domain.api.IDishServicePort;
import com.pragma.restaurant.domain.api.IOrderDishServicePort;
import com.pragma.restaurant.domain.api.IOrderServicePort;
import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.api.IUserServicePort;
import com.pragma.restaurant.domain.spi.ICategoryPersistencePort;
import com.pragma.restaurant.domain.spi.IDishPersistencePort;
import com.pragma.restaurant.domain.spi.IOrderDishPersistencePort;
import com.pragma.restaurant.domain.spi.IOrderPersistencePort;
import com.pragma.restaurant.domain.spi.IRestaurantPersistencePort;
import com.pragma.restaurant.domain.usecase.CategoryUseCase;
import com.pragma.restaurant.domain.usecase.DishUseCase;
import com.pragma.restaurant.domain.usecase.OrderDishUseCase;
import com.pragma.restaurant.domain.usecase.OrderUseCase;
import com.pragma.restaurant.domain.usecase.RestaurantUseCase;
import com.pragma.restaurant.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.pragma.restaurant.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.pragma.restaurant.infrastructure.out.jpa.adapter.OrderDishJpaAdapter;
import com.pragma.restaurant.infrastructure.out.jpa.adapter.OrderJpaAdapter;
import com.pragma.restaurant.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.repository.ICategoryRepository;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IOrderDishRepository;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IOrderRepository;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    private final IOrderRepository orderRepository;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderDishEntityMapper orderDishEntityMapper;

    private final WebClient.Builder webClientUser;

    public BeanConfiguration(IRestaurantRepository restaurantRepository,
                             IRestaurantEntityMapper restaurantEntityMapper,
                             ICategoryRepository categoryRepository,
                             ICategoryEntityMapper categoryEntityMapper,
                             IDishRepository dishRepository,
                             IDishEntityMapper dishEntityMapper,
                             IOrderRepository orderRepository,
                             IOrderDishRepository orderDishRepository,
                             IOrderEntityMapper orderEntityMapper,
                             IOrderDishEntityMapper orderDishEntityMapper,
                             @Qualifier("userWebClient") WebClient.Builder webClientUser) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantEntityMapper = restaurantEntityMapper;
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
        this.dishRepository = dishRepository;
        this.dishEntityMapper = dishEntityMapper;
        this.orderRepository = orderRepository;
        this.orderDishRepository = orderDishRepository;
        this.orderEntityMapper = orderEntityMapper;
        this.orderDishEntityMapper = orderDishEntityMapper;
        this.webClientUser = webClientUser;
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IOrderDishPersistencePort orderDishPersistencePort() {
        return new OrderDishJpaAdapter(orderDishRepository, orderDishEntityMapper);
    }
    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort());
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), userServicePort());
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserHandler(webClientUser);
    }

    @Bean
    public IOrderDishServicePort orderDishServicePort() {
        return new OrderDishUseCase(orderDishPersistencePort());
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(orderPersistencePort());
    }

    @Bean
    public ITokenGenerator tokenGenerator() {
        return new JwtGenerator();
    }

    @Bean
    public ICreateOrderRequestMapper createOrderRequestMapper() {
        return new CreateOrderRequestMapper(restaurantServicePort());
    }

    @Bean
    public IOrderDishRequestMapper orderDishRequestMapper() {
        return new OrderDishRequestMapper(dishServicePort());
    }
}