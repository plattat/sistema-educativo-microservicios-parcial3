package com.example.usuarios_servicio.service;

import com.example.usuarios_servicio.model.Usuario;
import com.example.usuarios_servicio.model.UsuarioSecurity;
import com.example.usuarios_servicio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new UsuarioSecurity(usuario.getUsername(), usuario.getPassword());
    }
}