package com.pragma.restaurant.application.dto.response;

import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.domain.model.Restaurant;
import lombok.Data;

import java.util.Date;
@Data
public class OrderResponseDto {
    private Long id;
    private Date date;
    private Order.OrderState state;
    private Restaurant restaurant;
    private Long idClient;
    private Long idChef;
}
