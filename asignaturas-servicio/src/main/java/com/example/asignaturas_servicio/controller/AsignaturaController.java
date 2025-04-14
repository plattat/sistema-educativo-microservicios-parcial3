package com.example.asignaturas_servicio.controller;

import com.example.asignaturas_servicio.model.Asignatura;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    @GetMapping("/{id}")
    public Asignatura obtenerAsignaturaPorId(@PathVariable Long id) {
        // Simulación de base de datos
        if (id == 1L) return new Asignatura(1L, "Matemáticas", "Prof. García");
        if (id == 2L) return new Asignatura(2L, "Física", "Prof. López");
        return new Asignatura(id, "Asignatura Desconocida", "Profesor Desconocido");
    }
}