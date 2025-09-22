package com.pragma.restaurant.application.dto.request;

import com.pragma.restaurant.domain.model.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogRequest {
    private Order.OrderState state;
    private Long idClient;
    private Long idEmployee;
    private Long idOrder;
    private Long idRestaurant;
    private Long datetime;

    public static LogRequest buildLogWithOrder(Order order) {
        return LogRequest.builder()
                .state(order.getState())
                .idOrder(order.getId())
                .idRestaurant(order.getRestaurant().getId())
                .idClient(order.getIdClient())
                .idEmployee(order.getIdChef())
                .datetime(System.currentTimeMillis())
                .build();
    }
}
