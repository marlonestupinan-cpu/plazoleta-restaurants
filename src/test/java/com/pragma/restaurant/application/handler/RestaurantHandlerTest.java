package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.CreateRestaurantRequestDto;
import com.pragma.restaurant.application.dto.response.RestaurantResponseDto;
import com.pragma.restaurant.application.handler.impl.RestaurantHandler;
import com.pragma.restaurant.application.mapper.ICreateRestaurantRequestMapper;
import com.pragma.restaurant.application.mapper.IRestaurantResponseMapper;
import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RestaurantHandlerTest {

    private IRestaurantServicePort restaurantService;
    private final ICreateRestaurantRequestMapper createRestaurantRequestMapper = Mappers.getMapper(ICreateRestaurantRequestMapper.class);
    private final IRestaurantResponseMapper restaurantResponseMapper = Mappers.getMapper(IRestaurantResponseMapper.class);
    private RestaurantHandler restaurantHandler;

    @BeforeEach
    void setUp() {
        restaurantService = mock(IRestaurantServicePort.class);
        restaurantHandler = new RestaurantHandler(
                createRestaurantRequestMapper,
                restaurantResponseMapper,
                restaurantService
        );
    }

    @Test
    void shouldSaveRestaurant() {
        CreateRestaurantRequestDto dto = new CreateRestaurantRequestDto();
        dto.setName("El Corral Gourmet");
        dto.setNit("123456789");
        dto.setAddress("Calle Falsa 123");
        dto.setPhone("+573004567890");
        dto.setUrlLogo("http://logo.url/img.png");
        dto.setIdOwner(1L);

        Restaurant expectedRestaurant = createRestaurantRequestMapper.toRestaurant(dto);

        restaurantHandler.saveRestaurant(dto);

        ArgumentCaptor<Restaurant> restaurantCaptor = ArgumentCaptor.forClass(Restaurant.class);
        verify(restaurantService).saveRestaurant(restaurantCaptor.capture());
        Restaurant capturedRestaurant = restaurantCaptor.getValue();

        assertEquals(expectedRestaurant, capturedRestaurant);
    }

    @Test
    void shouldGetAllRestaurants() {
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(1L);
        restaurant1.setName("Restaurante A");

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2L);
        restaurant2.setName("Restaurante B");

        List<Restaurant> mockRestaurantList = Arrays.asList(restaurant1, restaurant2);
        List<RestaurantResponseDto> expectedResponseList = restaurantResponseMapper.toResponseList(mockRestaurantList);

        when(restaurantService.getAllRestaurants()).thenReturn(mockRestaurantList);

        List<RestaurantResponseDto> result = restaurantHandler.getAllRestaurants();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedResponseList, result);
        verify(restaurantService).getAllRestaurants();
    }
}