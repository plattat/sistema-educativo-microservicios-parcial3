package com.example.matriculas_servicio.servicio;

import com.example.matriculas_servicio.model.Asignatura;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "asignaturas-servicio")
public interface AsignaturaClient {
    @GetMapping("/api/asignaturas/{id}")
    Asignatura obtenerAsignaturaPorId(@PathVariable Long id);
}
