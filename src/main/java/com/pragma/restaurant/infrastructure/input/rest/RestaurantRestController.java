package com.pragma.restaurant.infrastructure.input.rest;

import com.pragma.restaurant.application.dto.request.CreateRestaurantRequestDto;
import com.pragma.restaurant.application.dto.response.RestaurantResponseDto;
import com.pragma.restaurant.application.handler.IRestaurantHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {
    private final IRestaurantHandler  restaurantHandler;
    @PostMapping()
    public ResponseEntity<Void> createRestaurant(@RequestBody @Valid CreateRestaurantRequestDto user) {
        restaurantHandler.saveRestaurant(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants());
    }

}
