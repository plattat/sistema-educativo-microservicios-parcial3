package com.example.asignaturas_servicio.repository;

import com.example.asignaturas_servicio.model.Asignatura;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AsignaturaRepository extends MongoRepository<Asignatura, String> {
}