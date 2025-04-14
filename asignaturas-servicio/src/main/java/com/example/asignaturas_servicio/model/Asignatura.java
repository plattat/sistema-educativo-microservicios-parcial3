package com.example.asignaturas_servicio.model;

public class Asignatura {
    private Long id;
    private String nombre;
    private String profesor;

    // Constructor vacío
    public Asignatura() {}

    // Constructor con parámetros
    public Asignatura(Long id, String nombre, String profesor) {
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getProfesor() { return profesor; }
    public void setProfesor(String profesor) { this.profesor = profesor; }
}