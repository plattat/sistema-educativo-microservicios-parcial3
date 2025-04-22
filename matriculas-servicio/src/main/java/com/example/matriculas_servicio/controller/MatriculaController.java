package com.example.matriculas_servicio.controller;

import com.example.matriculas_servicio.dto.MatriculaResponse;
import com.example.matriculas_servicio.model.Matricula;
import com.example.matriculas_servicio.servicio.MatriculaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/api/matriculas")
    public Matricula crearMatricula(@RequestBody Matricula matricula) {
        return matriculaService.guardarMatricula(matricula);
    }

    @GetMapping("/completas")
    public List<MatriculaResponse> obtenerMatriculasCompletas() {
        return matriculaService.obtenerMatriculasConDetalles();
    }

    @GetMapping("/usuario/{id}")
    public List<MatriculaResponse> obtenerPorUsuario(@PathVariable String id) {
        return matriculaService.obtenerPorUsuario(id);
    }
}
