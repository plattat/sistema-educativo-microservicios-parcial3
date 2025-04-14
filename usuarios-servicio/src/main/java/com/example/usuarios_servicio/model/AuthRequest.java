package com.example.usuarios_servicio.model;

public class AuthRequest {
    private String username;
    private String password;

    // Constructor vacío (requerido por Spring)
    public AuthRequest() {}

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}