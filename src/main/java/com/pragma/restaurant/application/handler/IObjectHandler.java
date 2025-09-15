package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.ObjectRequestDto;
import com.pragma.restaurant.application.dto.response.ObjectResponseDto;

import java.util.List;

public interface IObjectHandler {

    void saveObject(ObjectRequestDto objectRequestDto);

    List<ObjectResponseDto> getAllObjects();
}