package com.example.asignaturas_servicio.service;

import com.example.asignaturas_servicio.model.Asignatura;
import com.example.asignaturas_servicio.repository.AsignaturaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;

    public AsignaturaService(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    public Optional<Asignatura> obtenerPorId(String id) {
        return asignaturaRepository.findById(id);
    }

    public Asignatura guardar(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }
}