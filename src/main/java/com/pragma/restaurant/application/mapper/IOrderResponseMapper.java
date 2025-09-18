package com.pragma.restaurant.application.mapper;

import com.pragma.restaurant.application.dto.response.OrderResponseDto;
import com.pragma.restaurant.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {
    OrderResponseDto toResponse(Order order);
    List<OrderResponseDto> toResponseList(List<Order> order);

    default Page<OrderResponseDto> toPageResponseList(Page<Order> page) {
        if (page == null) {
            return null;
        }

        List<OrderResponseDto> dtoList = toResponseList(page.getContent());

        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
