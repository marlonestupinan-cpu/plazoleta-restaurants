package com.pragma.restaurant.application.handler;


import java.util.Date;

public interface ITokenGenerator {
    String extractUsername(String token);
    String extractRole(String token);
    Long extractId(String token);
    Date extractExpiration(String token);
    boolean validateTokenExpired(String token);
}
