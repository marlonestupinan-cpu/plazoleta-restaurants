package com.pragma.restaurant.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    @Qualifier("userWebClient")
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder().baseUrl("http://localhost:8081/user");
    }
    @Bean
    @Qualifier("messageClient")
    public WebClient.Builder messageClientBuilder() {
        return WebClient.builder().baseUrl("http://localhost:8083/message");
    }
}
