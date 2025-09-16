package com.pragma.restaurant.application.dto.request;

import lombok.Data;

@Data
public class CreateRestaurantRequestDto {
    private String name;
    private String address;
    private Long idOwner;
    private String phone;
    private String urlLogo;
    private String nit;
}
