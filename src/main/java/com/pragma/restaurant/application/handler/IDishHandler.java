package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.CreateDishRequestDto;
import com.pragma.restaurant.application.dto.request.UpdateDishRequestDto;
import com.pragma.restaurant.application.dto.response.ClientDishResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishHandler {
    void saveDish(CreateDishRequestDto createDishRequestDto, Long ownerId);
    void updateDish(UpdateDishRequestDto updateDishRequestDto, Long ownerId);
    void setState(Long idDish, Long idOwner, boolean state);
    Page<ClientDishResponseDto> getAllDishesFromRestaurant(Long restaurantId, Pageable pageable, Long categoryId);
}
