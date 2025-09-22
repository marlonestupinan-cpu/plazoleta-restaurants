package com.pragma.restaurant.application.handler.impl;

import com.pragma.restaurant.application.dto.request.LogRequest;
import com.pragma.restaurant.application.handler.ILogService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LogService implements ILogService {
    private final WebClient.Builder webClient;

    public LogService(@Qualifier("logClient") WebClient.Builder webClient) {
        this.webClient = webClient;
    }
    @Override
    public void createLog(LogRequest logRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getDetails();

        webClient
                .build()
                .post()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(logRequest)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}
