package com.example.usuarios_servicio.model;

public class Usuario {
    private Long id;
    private String nombre;
    private String correo;

    // Constructor vacío
    public Usuario() {}

    // Constructor con parámetros
    public Usuario(Long id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}
