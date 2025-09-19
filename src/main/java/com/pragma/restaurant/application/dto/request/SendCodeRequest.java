package com.pragma.restaurant.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendCodeRequest {
    private String phoneNumber;
    private String code;
}
