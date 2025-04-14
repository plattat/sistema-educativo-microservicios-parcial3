package com.example.matriculas_servicio.servicio;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenManager {

    private String token;

    public String getToken() {
        return token;
    }

    @PostConstruct
    public void init() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://spring-api-usuarios-servicios:8082/auth/login";

        Map<String, String> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "admin");

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(url, body, AuthResponse.class);
        this.token = response.getBody().getJwt();

        System.out.println("✅ Token JWT obtenido: " + token);
    }

    public static class AuthResponse {
        private String jwt;
        public String getJwt() { return jwt; }
        public void setJwt(String jwt) { this.jwt = jwt; }
    }
}
