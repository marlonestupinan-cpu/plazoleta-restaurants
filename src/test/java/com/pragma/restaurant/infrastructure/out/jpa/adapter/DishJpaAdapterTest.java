package com.pragma.restaurant.infrastructure.out.jpa.adapter;

import com.pragma.restaurant.domain.model.Category;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IDishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
class DishJpaAdapterTest {

    private IDishRepository dishRepository;
    @Autowired
    private IDishEntityMapper dishEntityMapper;
    private DishJpaAdapter dishJpaAdapter;

    @BeforeEach
    void setUp() {
        dishRepository = mock(IDishRepository.class);
        dishJpaAdapter = new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Test
    void shouldSaveDishSuccessfully() {
        Dish dish = createDishMock();
        DishEntity expectedDishEntity = dishEntityMapper.toEntity(dish);

        dishJpaAdapter.saveDish(dish);

        verify(dishRepository).save(expectedDishEntity);
    }

    private Dish createDishMock() {
        Dish dish = new Dish();
        dish.setId(1L);
        dish.setName("Bandeja Paisa");
        dish.setPrice(32000);
        dish.setDescription("Plato típico de la región antioqueña.");
        dish.setUrlImage("bandeja.png");
        dish.setActive(true);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(10L);
        dish.setRestaurant(restaurant);

        Category category = new Category();
        category.setId(5L);
        dish.setCategory(category);

        return dish;
    }
}