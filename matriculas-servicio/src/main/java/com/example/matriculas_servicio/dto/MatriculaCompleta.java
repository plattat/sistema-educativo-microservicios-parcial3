package com.example.matriculas_servicio.dto;

import com.example.matriculas_servicio.model.Usuario;
import com.example.matriculas_servicio.model.Asignatura;

public class MatriculaCompleta {
    private Long id;
    private Usuario usuario;
    private Asignatura asignatura;

    public MatriculaCompleta() {}

    public MatriculaCompleta(Long id, Usuario usuario, Asignatura asignatura) {
        this.id = id;
        this.usuario = usuario;
        this.asignatura = asignatura;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
}