package com.example.matriculas_servicio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "matriculas")
public class Matricula {
    @Id
    private String id;
    private String usuarioId;
    private String asignaturaId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(String asignaturaId) {
        this.asignaturaId = asignaturaId;
    }
}
