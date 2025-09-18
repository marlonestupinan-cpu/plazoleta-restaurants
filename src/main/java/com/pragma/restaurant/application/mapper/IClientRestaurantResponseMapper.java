package com.pragma.restaurant.application.mapper;

import com.pragma.restaurant.application.dto.response.ClientRestaurantResponseDto;
import com.pragma.restaurant.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IClientRestaurantResponseMapper {
    List<ClientRestaurantResponseDto> toResponseList(List<Restaurant> restaurants);

    default Page<ClientRestaurantResponseDto> toPageResponseList(Page<Restaurant> page) {
        if (page == null) {
            return null;
        }

        List<ClientRestaurantResponseDto> dtoList = toResponseList(page.getContent());

        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
