package com.pragma.restaurant.application.dto.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CreateRestaurantRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotNull
    private Long idOwner;
    @NotBlank
    @Size(min = 1, max = 13)
    @Pattern(regexp = "^\\+?\\d+$", message = "Debe contener solo números o el simboló \"+\"")
    private String phone;
    @NotBlank
    private String urlLogo;
    @NotBlank
    @Pattern(regexp = "^\\d+$", message = "Debe contener solo números")
    private String nit;
}
