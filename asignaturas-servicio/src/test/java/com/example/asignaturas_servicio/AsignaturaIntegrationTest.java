package com.example.asignaturas_servicio;

import com.example.asignaturas_servicio.controller.AsignaturaController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AsignaturaController.class)
public class AsignaturaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAsignaturaPorId() throws Exception {
        mockMvc.perform(get("/api/asignaturas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Matemáticas"))
                .andExpect(jsonPath("$.profesor").value("Prof. García"));
    }
}
