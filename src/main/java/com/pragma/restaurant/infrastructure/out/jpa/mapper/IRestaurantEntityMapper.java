package com.pragma.restaurant.infrastructure.out.jpa.mapper;

import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.infrastructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantEntityMapper {
    RestaurantEntity toEntity(Restaurant restaurant);

    Restaurant toRestaurant(RestaurantEntity restaurantEntity);

    List<Restaurant> toRestaurantList(List<RestaurantEntity> restaurants);

    default Page<Restaurant> toPageRestaurantList(Page<RestaurantEntity> page) {
        if (page == null) {
            return null;
        }

        List<Restaurant> dtoList = toRestaurantList(page.getContent());

        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
