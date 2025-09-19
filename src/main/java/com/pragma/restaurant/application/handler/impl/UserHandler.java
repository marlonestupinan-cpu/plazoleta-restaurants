package com.pragma.restaurant.application.handler.impl;

import com.pragma.restaurant.application.dto.response.UserResponse;
import com.pragma.restaurant.application.handler.IUserHandler;
import com.pragma.restaurant.domain.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class UserHandler implements IUserHandler {
    private final WebClient.Builder webClient;

    public UserHandler(@Qualifier("userWebClient") WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    @Override
    public Boolean isOwner(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getDetails();

        return webClient
                .build()
                .get()
                .uri("/owner/check/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorMap(WebClientResponseException.class, e -> {
                    if (e.getStatusCode().value() == 404) {
                        return new UserNotFoundException();
                    }
                    return e;
                })
                .block();
    }

    @Override
    public UserResponse getUserInfo(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getDetails();

        return webClient
                .build()
                .get()
                .uri("/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .onErrorMap(WebClientResponseException.class, e -> {
                    if (e.getStatusCode().value() == 404) {
                        return new UserNotFoundException();
                    }
                    return e;
                })
                .block();
    }
}
