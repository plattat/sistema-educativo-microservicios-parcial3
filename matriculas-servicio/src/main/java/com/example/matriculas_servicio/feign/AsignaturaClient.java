package com.example.matriculas_servicio.feign;

import com.example.matriculas_servicio.config.FeignClientConfig;
import com.example.matriculas_servicio.model.Asignatura;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "asignaturas-servicio",
        configuration = FeignClientConfig.class
)
public interface AsignaturaClient {
    @GetMapping("/api/asignaturas/{id}")
    Asignatura obtenerAsignaturaPorId(@PathVariable String id);
}
