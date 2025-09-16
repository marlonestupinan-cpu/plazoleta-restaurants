package com.pragma.restaurant.application.dto.response;

import lombok.Data;

@Data
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private Long idOwner;
    private String phone;
    private String urlLogo;
    private String nit;
}
