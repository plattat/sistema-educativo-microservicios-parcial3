package com.example.usuarios_servicio.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Aquí deberías usar un repositorio real (por ejemplo, JPA) para obtener el usuario desde la base de datos.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Supongamos que tienes un usuario "admin" con password encriptada "admin"
        if ("admin".equals(username)) {
            return new User(
                    "admin",
                    // Password encriptada con BCrypt -> admin
                    "$2a$12$68qwFCYDSWAOd9O5nzAG8ONVrVfxWxQBrQcu3sxvcD2hH2AD4IlFG",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            );
        }

        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}