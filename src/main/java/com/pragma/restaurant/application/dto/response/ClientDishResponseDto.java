package com.pragma.restaurant.application.dto.response;

import lombok.Data;

@Data
public class ClientDishResponseDto {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String urlImage;
    private Long categoryId;
}
