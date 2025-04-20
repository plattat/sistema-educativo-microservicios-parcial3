package com.example.usuarios_servicio.service;

import com.example.usuarios_servicio.model.Usuario;
import com.example.usuarios_servicio.model.UsuarioSecurity;
import com.example.usuarios_servicio.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Buscando usuario en base de datos: {}", username);
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.warn("Usuario no encontrado: {}", username);
                    return new UsernameNotFoundException("Usuario no encontrado");
                });
        logger.info("Usuario encontrado: {}", usuario.getUsername());
        return new UsuarioSecurity(
                usuario.getUsername(),
                usuario.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}