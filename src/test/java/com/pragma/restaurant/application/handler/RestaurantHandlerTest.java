package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.CreateRestaurantRequestDto;
import com.pragma.restaurant.application.handler.impl.RestaurantHandler;
import com.pragma.restaurant.application.mapper.IClientRestaurantResponseMapper;
import com.pragma.restaurant.application.mapper.ICreateRestaurantRequestMapper;
import com.pragma.restaurant.application.mapper.IRestaurantResponseMapper;
import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RestaurantHandlerTest {

    private IRestaurantServicePort restaurantService;
    private final ICreateRestaurantRequestMapper createRestaurantRequestMapper = Mappers.getMapper(ICreateRestaurantRequestMapper.class);
    private final IRestaurantResponseMapper restaurantResponseMapper = Mappers.getMapper(IRestaurantResponseMapper.class);
    private final IClientRestaurantResponseMapper clientRestaurantResponseMapper = Mappers.getMapper(IClientRestaurantResponseMapper.class);
    private RestaurantHandler restaurantHandler;

    @BeforeEach
    void setUp() {
        restaurantService = mock(IRestaurantServicePort.class);
        restaurantHandler = new RestaurantHandler(
                createRestaurantRequestMapper,
                restaurantResponseMapper,
                clientRestaurantResponseMapper,
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
}