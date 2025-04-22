package com.example.usuarios_servicio.controller;

import com.example.usuarios_servicio.model.Usuario;
import com.example.usuarios_servicio.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        logger.info(">>> GET /api/usuarios ejecutado correctamente");
        return usuarioRepository.findAll();
    }

    @PostMapping("/test-hash")
    public String probarHash(@RequestBody Usuario user) {
        logger.info("Verificando hash para usuario: {}", user.getUsername());
        Usuario encontrado = usuarioRepository.findByUsername(user.getUsername()).orElse(null);
        if (encontrado == null) {
            return "Usuario no encontrado en Mongo";
        }

        logger.info("Hash en Mongo: {}", encontrado.getPassword());
        logger.info("Contraseña enviada: {}", user.getPassword());
        boolean match = passwordEncoder.matches(user.getPassword(), encontrado.getPassword());
        return match ? "Contraseña válida (BCrypt match)" : "Contraseña inválida";
    }

    @GetMapping("/conexion-mongo")
    public String testMongoConnection() {
        try {
            long count = usuarioRepository.count();
            return "Conexión exitosa a Mongo. Total usuarios: " + count;
        } catch (Exception e) {
            logger.error("Error al conectar a MongoDB", e);
            return "Error de conexión a Mongo: " + e.getMessage();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            Usuario saved = usuarioRepository.save(usuario);
            return ResponseEntity.ok(saved); // 200 OK + JSON usuario
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear usuario: " + e.getMessage());
        }
    }



    @GetMapping("/test-ui")
    public String ui() {
        return """
            <html>
            <head><title>Verificación Mongo + BCrypt</title></head>
            <body style='font-family:sans-serif;'>
                <h2>Verificación Mongo + BCrypt</h2>
                <p><strong>Conexión Mongo:</strong> <a href='/api/usuarios/conexion-mongo'>/conexion-mongo</a></p>
                <p><strong>Probar contraseña:</strong> usar Postman → <code>POST /api/usuarios/test-hash</code></p>
                <pre>{
  \"username\": \"admin\",
  \"password\": \"admin123\"
}</pre>
            </body>
            </html>
        """;
    }
}
