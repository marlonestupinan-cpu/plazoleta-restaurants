package com.pragma.restaurant.infrastructure.input.rest;

import com.pragma.restaurant.application.dto.request.CreateDishRequestDto;
import com.pragma.restaurant.application.dto.request.UpdateDishRequestDto;
import com.pragma.restaurant.application.handler.IDishHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> createDish(@RequestBody @Valid CreateDishRequestDto dish) {
        dishHandler.saveDish(dish);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateDish(@RequestBody @Valid UpdateDishRequestDto dish) {
        dishHandler.updateDish(dish);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
