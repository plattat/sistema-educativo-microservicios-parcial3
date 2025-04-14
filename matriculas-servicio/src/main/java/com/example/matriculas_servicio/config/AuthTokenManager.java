package com.example.matriculas_servicio.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthTokenManager {

    private final String LOGIN_URL = "http://spring-api-usuarios-servicios/auth/login"; // URL del usuarios-servicio
    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void obtenerToken() {
        try {
            Map<String, String> credentials = new HashMap<>();
            credentials.put("username", "admin");
            credentials.put("password", "admin");

            AuthResponse response = restTemplate.postForObject(LOGIN_URL, credentials, AuthResponse.class);

            if (response != null && response.getJwt() != null) {
                TokenConfig.tokenGlobal = "Bearer " + response.getJwt();
                System.out.println("✅ Token obtenido y configurado correctamente");
            } else {
                System.err.println("❌ No se pudo obtener el token");
            }
        } catch (Exception e) {
            System.err.println("❌ Error obteniendo token: " + e.getMessage());
        }
    }

    // Clase interna para recibir la respuesta del login
    public static class AuthResponse {
        @JsonProperty("jwt")
        private String jwt;

        public String getJwt() {
            return jwt;
        }

        public void setJwt(String jwt) {
            this.jwt = jwt;
        }
    }
}