package com.example.matriculas_servicio.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            ServletRequestAttributes attributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String token = request.getHeader(HttpHeaders.AUTHORIZATION);
                if (token != null) {
                    template.header(HttpHeaders.AUTHORIZATION, token);
                    System.out.println("✅ Token reenviado desde Feign interceptor: " + token);
                } else {
                    System.out.println("⚠️ No se encontró token en la cabecera");
                }
            } else {
                System.out.println("⚠️ No hay contexto de request activo");
            }
        };
    }
}