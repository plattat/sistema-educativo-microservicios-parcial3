package com.example.matriculas_servicio.feign;

import com.example.matriculas_servicio.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios-servicio")
public interface UsuarioClient {
    @GetMapping("/api/usuarios/{id}")
    Usuario obtenerUsuario(@PathVariable String id);
}
