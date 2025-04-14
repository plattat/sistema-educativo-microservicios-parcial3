package com.example.usuarios_servicio.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        userDetails = new User("admin", "password", new java.util.ArrayList<>());
    }

    @Test
    void testGenerarToken() {
        String token = jwtUtil.generarToken(userDetails);
        assertNotNull(token);
    }

    @Test
    void testExtraerUsername() {
        String token = jwtUtil.generarToken(userDetails);
        String username = jwtUtil.extraerUsername(token);
        assertEquals("admin", username);
    }

    @Test
    void testValidarToken() {
        String token = jwtUtil.generarToken(userDetails);
        assertTrue(jwtUtil.validarToken(token, userDetails));
    }
}
