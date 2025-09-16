package com.pragma.restaurant.infrastructure.out.jpa.mapper;

import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.infrastructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantEntityMapper {
    RestaurantEntity toEntity(Restaurant user);

    Restaurant toRestaurant(RestaurantEntity userEntity);

    List<Restaurant> toRestaurantList(List<RestaurantEntity> userEntityList);
}
