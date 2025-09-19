package com.pragma.restaurant.application.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
}
