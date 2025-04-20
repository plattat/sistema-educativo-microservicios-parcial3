package com.example.usuarios_servicio.config;

import com.example.usuarios_servicio.model.Usuario;
import com.example.usuarios_servicio.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsuarioSeeder {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioSeeder.class);

    @Bean
    public CommandLineRunner crearAdminSiNoExiste(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            usuarioRepository.findByUsername("admin").ifPresentOrElse(
                    user -> logger.info("Usuario 'admin' ya existe. No se crea."),
                    () -> {
                        logger.info("Usuario 'admin' no existe. Creando...");
                        Usuario admin = new Usuario();
                        admin.setNombre("Admin");
                        admin.setCorreo("admin@example.com");
                        admin.setUsername("admin");
                        admin.setPassword(passwordEncoder.encode("admin123"));
                        usuarioRepository.save(admin);
                        logger.info("Usuario 'admin' creado exitosamente.");
                    }
            );
        };
    }
}