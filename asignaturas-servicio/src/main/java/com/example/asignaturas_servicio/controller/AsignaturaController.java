package com.example.asignaturas_servicio.controller;

import com.example.asignaturas_servicio.model.Asignatura;
import com.example.asignaturas_servicio.service.AsignaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @GetMapping
    public List<Asignatura> obtenerTodas() {
        return asignaturaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> obtenerAsignaturaPorId(@PathVariable String id) {
        return asignaturaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Asignatura crearAsignatura(@RequestBody Asignatura asignatura) {
        return asignaturaService.guardar(asignatura);
    }
}
