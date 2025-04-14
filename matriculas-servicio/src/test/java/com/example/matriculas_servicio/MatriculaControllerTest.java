package com.example.matriculas_servicio.controller;

import com.example.matriculas_servicio.dto.MatriculaResponse;
import com.example.matriculas_servicio.servicio.MatriculaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatriculaControllerTest {

    private final MatriculaService matriculaService = Mockito.mock(MatriculaService.class);
    private final MatriculaController controller = new MatriculaController(matriculaService);

    @Test
    void testObtenerMatriculasCompletas() {
        MatriculaResponse m1 = new MatriculaResponse(1L, "Juan", "juan@email.com", "Math", "Álgebra");
        Mockito.when(matriculaService.obtenerMatriculasConDetalles()).thenReturn(List.of(m1));

        List<MatriculaResponse> result = controller.obtenerMatriculasCompletas();

        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getNombreUsuario());
    }
}
