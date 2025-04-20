package com.example.usuarios_servicio.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private final long EXPIRATION_TIME = 86400000; // 1 día

    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generarToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validarToken(String token, UserDetails userDetails) {
        String username = extraerUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpirado(token);
    }

    public String extraerUsername(String token) {
        return getClaims(token).getSubject();
    }

    private boolean isTokenExpirado(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
