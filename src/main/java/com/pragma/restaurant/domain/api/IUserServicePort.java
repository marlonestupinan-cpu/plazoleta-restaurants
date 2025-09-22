package com.pragma.restaurant.domain.api;


import com.pragma.restaurant.domain.model.User;

public interface IUserServicePort {
    Boolean isOwner(Long id);
    User getUserInfo(Long id);
}
