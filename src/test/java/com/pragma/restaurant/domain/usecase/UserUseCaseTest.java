package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.exception.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private WebClient webClient;

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    private MockedStatic<SecurityContextHolder> mockedContextHolder;

    @BeforeEach
    void setUp() {
        mockedContextHolder = mockStatic(SecurityContextHolder.class);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(Long.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    }

    @AfterEach
    void tearDown(){
        mockedContextHolder.close();
    }

    @Test
    void shouldReturnTrueWhenApiReturnsTrue() {
        String fakeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn(fakeToken);

        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(true));
        Boolean result = userUseCase.isOwner(1L);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenApiReturnsFalse() {
        String fakeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn(fakeToken);

        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(false));

        Boolean result = userUseCase.isOwner(2L);

        assertFalse(result);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenApiReturns404() {
        String fakeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn(fakeToken);

        WebClientResponseException mockException = mock(WebClientResponseException.class);
        when(mockException.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);

        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.error(mockException));

        assertThrows(UserNotFoundException.class, () -> userUseCase.isOwner(99L));
    }

    @Test
    void shouldThrowWebClientResponseExceptionForOtherErrors() {
        String fakeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn(fakeToken);

        WebClientResponseException mockException = mock(WebClientResponseException.class);
        when(mockException.getStatusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.error(mockException));

        assertThrows(WebClientResponseException.class, () -> userUseCase.isOwner(100L));
    }
}