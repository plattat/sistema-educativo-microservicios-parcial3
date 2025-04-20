package com.example.usuarios_servicio.controller;

import com.example.usuarios_servicio.model.AuthRequest;
import com.example.usuarios_servicio.model.AuthResponse;
import com.example.usuarios_servicio.service.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        logger.info("Intentando autenticar usuario: {}", request.getUsername());
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            logger.warn("Credenciales incorrectas para usuario: {}", request.getUsername());
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        } catch (Exception e) {
            logger.error("Error inesperado durante autenticación", e);
            return ResponseEntity.status(500).body("Error interno de autenticación");
        }

        logger.info("Usuario autenticado exitosamente: {}", request.getUsername());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generarToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
