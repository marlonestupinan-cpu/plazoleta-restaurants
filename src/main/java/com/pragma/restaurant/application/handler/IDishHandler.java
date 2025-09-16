package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.CreateDishRequestDto;

public interface IDishHandler {
    void saveDish(CreateDishRequestDto createDishRequestDto);
}
