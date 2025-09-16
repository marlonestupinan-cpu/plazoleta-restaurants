package com.pragma.restaurant.domain.model;

import com.pragma.restaurant.domain.exception.DomainException;
import lombok.Data;

@Data
public class Dish {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String urlImage;
    private Boolean active = true;
    private Restaurant restaurant;
    private Category category;

    public void setPrice(Integer price) {
        if (price <= 0) {
            throw new DomainException("El precio debe ser un entero positivo");
        }
        this.price = price;
    }
}
