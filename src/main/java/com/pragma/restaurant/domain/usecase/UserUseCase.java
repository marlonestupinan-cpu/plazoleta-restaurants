package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.api.IUserServicePort;
import com.pragma.restaurant.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {
    private final WebClient webClient;

    @Override
    public Boolean isOwner(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String token = (String) authentication.getDetails();

        return webClient.get().uri("/owner/check/{id}", id)
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
}
