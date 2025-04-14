package com.example.matriculas_servicio.model;

public class Matricula {
    private Long id;
    private Usuario usuario;
    private Asignatura asignatura;

    public Matricula() {}

    public Matricula(Long id, Usuario usuario, Asignatura asignatura) {
        this.id = id;
        this.usuario = usuario;
        this.asignatura = asignatura;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Asignatura getAsignatura() { return asignatura; }
    public void setAsignatura(Asignatura asignatura) { this.asignatura = asignatura; }
}