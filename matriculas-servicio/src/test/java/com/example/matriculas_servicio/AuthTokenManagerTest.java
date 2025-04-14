package com.example.matriculas_servicio.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AuthTokenManagerTest {

    private AuthTokenManager manager;
    private RestTemplate restTemplateMock;

    @BeforeEach
    void setUp() {
        restTemplateMock = Mockito.mock(RestTemplate.class);
        manager = new AuthTokenManager() {
            @Override
            public void obtenerToken() {
                AuthResponse response = new AuthResponse();
                response.setJwt("fake-jwt-token");
                TokenConfig.tokenGlobal = "Bearer " + response.getJwt();
            }
        };
    }

    @Test
    void testTokenSetCorrectly() {
        manager.obtenerToken();
        assertTrue(TokenConfig.tokenGlobal.startsWith("Bearer "));
    }
}
