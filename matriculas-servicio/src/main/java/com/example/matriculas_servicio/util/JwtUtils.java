package com.example.matriculas_servicio.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private Key key;

    // Expiración: 24 horas
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // Inicializa la clave una sola vez después de que se inyecta el valor
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    /**
     * Genera un token JWT válido con usuario y tiempo de expiración
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrae el nombre de usuario desde el token JWT
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Valida la estructura y firma del token JWT
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.err.println("❌ Token expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("❌ Token no soportado: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("❌ Token mal formado: " + e.getMessage());
        } catch (SecurityException e) {
            System.err.println("❌ Firma JWT inválida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Token vacío o nulo: " + e.getMessage());
        }
        return false;
    }
}
