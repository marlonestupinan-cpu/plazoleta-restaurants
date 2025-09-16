package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.CreateDishRequestDto;
import com.pragma.restaurant.application.dto.request.UpdateDishRequestDto;

public interface IDishHandler {
    void saveDish(CreateDishRequestDto createDishRequestDto);
    void updateDish(UpdateDishRequestDto updateDishRequestDto);
}
