package com.example.usuarios_servicio.controller;


import com.example.usuarios_servicio.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        // Simulación de base de datos
        if (id == 1L) return new Usuario(1L, "Juan Pérez", "juan@example.com");
        if (id == 2L) return new Usuario(2L, "María Gómez", "maria@example.com");
        return new Usuario(id, "Desconocido", "sin-correo@example.com");
    }
}