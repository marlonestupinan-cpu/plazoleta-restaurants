package com.pragma.restaurant.infrastructure.out.jpa.adapter;

import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
class RestaurantJpaAdapterTest {

    private IRestaurantRepository restaurantRepository;
    @Autowired
    private IRestaurantEntityMapper restaurantEntityMapper;
    private RestaurantJpaAdapter restaurantJpaAdapter;

    @BeforeEach
    void setUp() {
        restaurantRepository = mock(IRestaurantRepository.class);
        restaurantJpaAdapter = new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Test
    void shouldSaveRestaurantSuccessfully() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("El Sabor Costeño");
        restaurant.setNit("123456789");
        restaurant.setAddress("Avenida Siempre Viva 742");
        restaurant.setPhone("+573101234567");
        restaurant.setUrlLogo("logo.png");
        restaurant.setIdOwner(1L);

        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);

        restaurantJpaAdapter.saveRestaurant(restaurant);

        verify(restaurantRepository).save(restaurantEntity);
    }
}