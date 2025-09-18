package com.pragma.restaurant.infrastructure.input.rest;

import com.pragma.restaurant.application.dto.request.CreateOrderRequestDto;
import com.pragma.restaurant.application.dto.response.OrderResponseDto;
import com.pragma.restaurant.application.handler.IOrderHandler;
import com.pragma.restaurant.infrastructure.configuration.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('EMPLEADO')")
    public ResponseEntity<Page<OrderResponseDto>> getAllOrders(
            @RequestParam(required = false) Integer state,
            Pageable pageable,
            @AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok(
                orderHandler.getAllOrders(user.getIdOwner(), pageable, state)
        );
    }
}
