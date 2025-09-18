package com.pragma.restaurant.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDish {
    @ToString.Exclude
    private Order order;
    private Dish dish;
    private Integer quantity;
}
