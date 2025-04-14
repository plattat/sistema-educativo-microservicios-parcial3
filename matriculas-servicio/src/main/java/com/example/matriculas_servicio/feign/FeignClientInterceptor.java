package com.example.matriculas_servicio.feign;

import com.example.matriculas_servicio.servicio.JwtTokenManager;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



@Configuration
public class FeignClientInterceptor implements RequestInterceptor {

    private final JwtTokenManager jwtTokenManager;

    public FeignClientInterceptor(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    @Override
    public void apply(RequestTemplate template) {
        String token = jwtTokenManager.getToken();
        if (token != null) {
            template.header("Authorization", "Bearer " + token);
        }
    }
}