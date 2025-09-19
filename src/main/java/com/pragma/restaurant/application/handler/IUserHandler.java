package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.response.UserResponse;

public interface IUserHandler {
    Boolean isOwner(Long id);
    UserResponse getUserInfo(Long id);
}
