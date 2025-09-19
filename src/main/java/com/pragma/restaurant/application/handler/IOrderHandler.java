package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.CreateOrderRequestDto;
import com.pragma.restaurant.application.dto.response.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderHandler {
    void saveNewOrder(CreateOrderRequestDto order, Long idClient);

    Page<OrderResponseDto> getAllOrders(Long idOwner, Pageable pageable, Integer state);

    void assignOrder(Long idOrder, Long idEmployee, Long idOwner);

    void finishOrder(Long idOrder, Long idEmployee);

    void deliverOrder(Long idOrder, String securityCode, Long idEmployee);

    void cancelOrder(Long idOrder, Long idClient);
}
