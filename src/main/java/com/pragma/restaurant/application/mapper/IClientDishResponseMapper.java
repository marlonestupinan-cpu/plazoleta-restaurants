package com.pragma.restaurant.application.mapper;

import com.pragma.restaurant.application.dto.response.ClientDishResponseDto;
import com.pragma.restaurant.domain.model.Category;
import com.pragma.restaurant.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IClientDishResponseMapper {
    @Mapping(source = "category", target = "categoryId")
    ClientDishResponseDto toResponse(Dish dish);
    List<ClientDishResponseDto> toResponseList(List<Dish> dishes);

    default Long mapCategoryToId(Category category) {
        return category != null ? category.getId() : null;
    }
    default Page<ClientDishResponseDto> toPageResponseList(Page<Dish> page) {
        if (page == null) {
            return null;
        }

        List<ClientDishResponseDto> dtoList = toResponseList(page.getContent());

        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
