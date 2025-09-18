package com.pragma.restaurant.infrastructure.out.jpa.mapper;

import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.infrastructure.out.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IRestaurantEntityMapper.class, ICategoryEntityMapper.class}
)
public interface IDishEntityMapper {
    DishEntity toEntity(Dish dish);
    Dish toDish(DishEntity dishEntity);
    List<Dish> toDishList(List<DishEntity> dishes);

    default Page<Dish> toPageDishList(Page<DishEntity> page) {
        if (page == null) {
            return null;
        }

        List<Dish> dtoList = toDishList(page.getContent());

        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
