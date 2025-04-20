package com.example.matriculas_servicio.repository;

import com.example.matriculas_servicio.model.Matricula;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatriculaRepository extends MongoRepository<Matricula, String> {
}
