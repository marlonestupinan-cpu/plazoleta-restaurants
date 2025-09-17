package com.pragma.restaurant.infrastructure.input.rest;

import com.pragma.restaurant.application.dto.request.CreateDishRequestDto;
import com.pragma.restaurant.application.dto.request.UpdateDishRequestDto;
import com.pragma.restaurant.application.handler.IDishHandler;
import com.pragma.restaurant.infrastructure.configuration.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {
    private final IDishHandler dishHandler;

    @PostMapping
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<Void> createDish(
            @RequestBody @Valid CreateDishRequestDto dish,
            @AuthenticationPrincipal CustomUserDetails user) {
        dishHandler.saveDish(dish, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<Void> updateDish(
            @RequestBody @Valid UpdateDishRequestDto dish,
            @AuthenticationPrincipal CustomUserDetails user) {
        dishHandler.updateDish(dish, user.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
