package com.pragma.restaurant.infrastructure.out.jpa.mapper;

import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.infrastructure.out.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IRestaurantEntityMapper.class, ICategoryEntityMapper.class}
)
public interface IDishEntityMapper {
    DishEntity toEntity(Dish dish);
    Dish toDish(DishEntity dishEntity);
}
