package com.example.usuarios_servicio.controller;

import com.example.usuarios_servicio.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioControllerTest {

    private final UsuarioController usuarioController = new UsuarioController();

    @Test
    void testObtenerUsuarioPorId() {
        Usuario usuario = usuarioController.obtenerUsuarioPorId(1L);
        assertNotNull(usuario);
        assertEquals(1L, usuario.getId());
        assertEquals("Juan Pérez", usuario.getNombre());
    }
}
