package com.pragma.restaurant.infrastructure.out.jpa.adapter;

import com.pragma.restaurant.application.mapper.IPageEntityMapper;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.spi.IDishPersistencePort;
import com.pragma.restaurant.infrastructure.exception.DishNoFoundException;
import com.pragma.restaurant.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IPageEntityMapper pageEntityMapper;

    @Override
    public void saveDish(Dish dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public Dish getById(Long id) {
        return dishRepository.findById(id)
                .map(dishEntityMapper::toDish)
                .orElseThrow(DishNoFoundException::new);
    }

    @Override
    public Page<Dish> getByRestaurant(Long restaurantId, PageConfig pageable, Long categoryId) {
        Page<DishEntity> dishes;
        if (categoryId == null) {
            dishes = dishRepository.findByRestaurantIdAndActiveIsTrue(restaurantId, pageEntityMapper.toPageable(pageable));
        } else {
            dishes = dishRepository.findByRestaurantIdAndCategoryIdAndActiveIsTrue(restaurantId, categoryId, pageEntityMapper.toPageable(pageable));
        }

        return dishEntityMapper.toPageDishList(dishes);
    }
}
