package com.example.matriculas_servicio.dto;

public class MatriculaResponse {
    private String idMatricula;
    private String nombreUsuario;
    private String correoUsuario;
    private String nombreAsignatura;
    private String descripcionAsignatura;

    public MatriculaResponse(String idMatricula, String nombreUsuario, String correoUsuario,
                             String nombreAsignatura, String descripcionAsignatura) {
        this.idMatricula = idMatricula;
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.nombreAsignatura = nombreAsignatura;
        this.descripcionAsignatura = descripcionAsignatura;
    }

    public String getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(String idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getDescripcionAsignatura() {
        return descripcionAsignatura;
    }

    public void setDescripcionAsignatura(String descripcionAsignatura) {
        this.descripcionAsignatura = descripcionAsignatura;
    }
}
