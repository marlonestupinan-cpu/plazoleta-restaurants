package com.pragma.restaurant.application.handler.impl;

import com.pragma.restaurant.domain.api.IUserServicePort;
import com.pragma.restaurant.domain.exception.UserNotFoundException;
import com.pragma.restaurant.domain.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class UserHandler implements IUserServicePort {
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
    public User getUserInfo(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getDetails();

        return webClient
                .build()
                .get()
                .uri("/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(User.class)
                .onErrorMap(WebClientResponseException.class, e -> {
                    if (e.getStatusCode().value() == 404) {
                        return new UserNotFoundException();
                    }
                    return e;
                })
                .block();
    }
}
