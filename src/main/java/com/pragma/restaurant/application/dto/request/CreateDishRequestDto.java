package com.pragma.restaurant.application.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateDishRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @Positive
    private Integer price;
    @NotBlank
    private String urlImage;
    @NotNull
    private Long idRestaurant;
    @NotNull
    private Long idCategory;
}
