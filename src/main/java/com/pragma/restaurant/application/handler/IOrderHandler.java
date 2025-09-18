package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.CreateOrderRequestDto;

public interface IOrderHandler {
    void saveNewOrder(CreateOrderRequestDto order, Long idClient);
}
