package com.pragma.restaurant.application.dto.request;

import com.pragma.restaurant.domain.model.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogRequest {
    private Order.OrderState oldState;
    private Order.OrderState newState;
    private Long idClient;
    private Long idEmployee;
    private Long idOrder;
    private Long idRestaurant;
    private Long datetime;
    private String employeeEmail;

    public static LogRequest buildLogWithOrder(Order order, String employeeEmail, Order.OrderState oldState) {
        return LogRequest.builder()
                .oldState(oldState)
                .newState(order.getState())
                .idOrder(order.getId())
                .idRestaurant(order.getRestaurant().getId())
                .idClient(order.getIdClient())
                .idEmployee(order.getIdChef())
                .datetime(System.currentTimeMillis())
                .employeeEmail(employeeEmail)
                .build();
    }
}
