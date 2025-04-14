package com.example.asignaturas_servicio.controller;

import com.example.asignaturas_servicio.model.Asignatura;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AsignaturaControllerTest {

    private final AsignaturaController controller = new AsignaturaController();

    @Test
    void testObtenerAsignaturaPorId_Matematicas() {
        Asignatura asignatura = controller.obtenerAsignaturaPorId(1L);
        assertNotNull(asignatura);
        assertEquals("Matemáticas", asignatura.getNombre());
        assertEquals("Prof. García", asignatura.getProfesor());
    }

    @Test
    void testObtenerAsignaturaPorId_Desconocida() {
        Asignatura asignatura = controller.obtenerAsignaturaPorId(99L);
        assertNotNull(asignatura);
        assertEquals("Asignatura Desconocida", asignatura.getNombre());
        assertEquals("Profesor Desconocido", asignatura.getProfesor());
    }
}
