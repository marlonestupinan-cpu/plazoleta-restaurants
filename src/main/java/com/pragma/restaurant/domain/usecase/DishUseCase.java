package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.api.IDishServicePort;
import com.pragma.restaurant.domain.exception.NotRestaurantOwnerException;
import com.pragma.restaurant.domain.model.Category;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;

    @Override
    public void saveDish(Dish dish, Long ownerId) {
        saveDish(dish, ownerId, dish.getRestaurant(), dish.getCategory());
    }

    @Override
    public void saveDish(Dish dish, Long ownerId, Restaurant restaurant, Category category) {
        if (!dish.getRestaurant().getIdOwner().equals(ownerId)) {
            throw new NotRestaurantOwnerException();
        }

        dish.setRestaurant(restaurant);
        dish.setCategory(category);

        dishPersistencePort.saveDish(dish);
    }

    @Override
    public void updateDish(Dish dish, Integer price, String description, Long ownerId) {
        if (price != null)
            dish.setPrice(price);
        if (description != null)
            dish.setDescription(description);
        saveDish(dish, ownerId);
    }

    @Override
    public void changeActiveState(Dish dish, Long ownerId, Boolean newState) {
        dish.setActive(newState);
        saveDish(dish, ownerId);
    }

    @Override
    public Dish getById(Long id) {
        return dishPersistencePort.getById(id);
    }

    @Override
    public Page<Dish> getAllDishesFromRestaurant(Long restaurantId, PageConfig pageable, Long categoryId) {
        return dishPersistencePort.getByRestaurant(restaurantId, pageable, categoryId);
    }
}
