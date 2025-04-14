package com.example.matriculas_servicio.controller;

import com.example.matriculas_servicio.dto.MatriculaResponse;
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

    @GetMapping("/completas")
    public List<MatriculaResponse> obtenerMatriculasCompletas() {
        return matriculaService.obtenerMatriculasConDetalles();
    }
}