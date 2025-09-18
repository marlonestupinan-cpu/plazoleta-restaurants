package com.pragma.restaurant.application.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateOrderRequestDto {
    @NotNull
    private Long idRestaurant;
    @NotEmpty
    private List<OrderDishRequestDto> orderDishes;
}

