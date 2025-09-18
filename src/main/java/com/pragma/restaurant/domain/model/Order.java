package com.pragma.restaurant.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    public enum OrderState {
        PENDING,
        WORKING,
        FINISHED,
        CANCELED,
        DELIVERED;

        public static OrderState getState(Integer state) {
            var states = values();
            if (state < 0 || state >= states.length) return null;
            return states[state];
        }
    }

    private Long id;
    private Date date;
    private OrderState state;
    private Restaurant restaurant;
    private Long idClient;
    private Long idChef;
    private List<OrderDish> orderDishes;
}
