package com.pragma.restaurant.application.handler.impl;

import com.pragma.restaurant.application.dto.request.SendCodeRequest;
import com.pragma.restaurant.application.handler.IMessageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MessageService implements IMessageService {
    private final WebClient.Builder webClient;

    public MessageService(@Qualifier("messageClient") WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    @Override
    public void sendCode(String phone, String code) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String token = (String) authentication.getDetails();

        SendCodeRequest sendCodeRequest = new SendCodeRequest(phone, code);

        webClient
                .build()
                .post()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sendCodeRequest)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}
