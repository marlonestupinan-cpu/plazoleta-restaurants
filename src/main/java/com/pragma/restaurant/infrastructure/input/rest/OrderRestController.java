package com.pragma.restaurant.infrastructure.input.rest;

import com.pragma.restaurant.application.dto.request.CreateOrderRequestDto;
import com.pragma.restaurant.application.handler.IOrderHandler;
import com.pragma.restaurant.infrastructure.configuration.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {
    private final IOrderHandler  orderHandler;

    @PostMapping()
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<Void> activateDish(
            @RequestBody @Valid CreateOrderRequestDto createOrderRequestDto,
            @AuthenticationPrincipal CustomUserDetails user) {
            orderHandler.saveNewOrder(createOrderRequestDto, user.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
