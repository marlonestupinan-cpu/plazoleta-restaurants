package com.pragma.restaurant.application.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UpdateDishRequestDto {
    @NotNull
    private Long id;
    private String description;
    @Positive
    private Integer price;
}
