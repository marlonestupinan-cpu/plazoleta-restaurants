package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.api.IUserServicePort;
import com.pragma.restaurant.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {
    private final WebClient webClient;

    @Override
    public Boolean isOwner(Long id) {
        return webClient.get().uri("/owner/check/{id}", id)
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
