package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.CreateDishRequestDto;
import com.pragma.restaurant.application.handler.impl.DishHandler;
import com.pragma.restaurant.application.mapper.ICreateDishRequestMapper;
import com.pragma.restaurant.domain.api.ICategoryServicePort;
import com.pragma.restaurant.domain.api.IDishServicePort;
import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.model.Category;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DishHandlerTest {

    private final ICreateDishRequestMapper createDishRequestMapper = Mappers.getMapper(ICreateDishRequestMapper.class);
    private IDishServicePort dishServicePort;
    private IRestaurantServicePort restaurantServicePort;
    private ICategoryServicePort categoryServicePort;
    private DishHandler dishHandler;

    @BeforeEach
    void setUp() {
        dishServicePort = mock(IDishServicePort.class);
        restaurantServicePort = mock(IRestaurantServicePort.class);
        categoryServicePort = mock(ICategoryServicePort.class);
        dishHandler = new DishHandler(
                createDishRequestMapper,
                dishServicePort,
                restaurantServicePort,
                categoryServicePort
        );
    }

    @Test
    void shouldSaveDish() {
        CreateDishRequestDto expectedDish = new CreateDishRequestDto();
        expectedDish.setName("Cazuela de Mariscos");
        expectedDish.setPrice(35000);
        expectedDish.setDescription("Deliciosa sopa con frutos del mar");
        expectedDish.setUrlImage("cazuela.png");
        expectedDish.setIdRestaurant(1L);
        expectedDish.setIdCategory(2L);

        Restaurant mockRestaurant = new Restaurant();
        mockRestaurant.setId(1L);
        mockRestaurant.setName("El Sabor del Mar");

        Category mockCategory = new Category();
        mockCategory.setId(2L);
        mockCategory.setName("Sopas");

        when(restaurantServicePort.getRestaurantById(1L)).thenReturn(mockRestaurant);
        when(categoryServicePort.getCategoryById(2L)).thenReturn(mockCategory);

        dishHandler.saveDish(expectedDish);

        ArgumentCaptor<Dish> dishCaptor = ArgumentCaptor.forClass(Dish.class);
        verify(dishServicePort).saveDish(dishCaptor.capture());
        Dish realDish = dishCaptor.getValue();

        assertEquals("Cazuela de Mariscos", realDish.getName());
        assertEquals(mockRestaurant, realDish.getRestaurant());
        assertEquals(mockCategory, realDish.getCategory());

        verify(restaurantServicePort, times(1)).getRestaurantById(1L);
        verify(categoryServicePort, times(1)).getCategoryById(2L);
    }
}