package com.example.matriculas_servicio.repository;

import com.example.matriculas_servicio.model.Matricula;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatriculaRepository extends MongoRepository<Matricula, String> {
    boolean existsByUsuarioIdAndAsignaturaId(String usuarioId, String asignaturaId);
    List<Matricula> findByUsuarioId(String usuarioId);
}
