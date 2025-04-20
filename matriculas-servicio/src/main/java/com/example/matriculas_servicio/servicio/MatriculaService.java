package com.example.matriculas_servicio.servicio;

import com.example.matriculas_servicio.dto.MatriculaResponse;
import com.example.matriculas_servicio.feign.AsignaturaClient;
import com.example.matriculas_servicio.feign.UsuarioClient;
import com.example.matriculas_servicio.model.Asignatura;
import com.example.matriculas_servicio.model.Matricula;
import com.example.matriculas_servicio.model.Usuario;
import com.example.matriculas_servicio.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaService {

    private final UsuarioClient usuarioClient;
    private final AsignaturaClient asignaturaClient;
    private final MatriculaRepository matriculaRepository;

    public MatriculaService(UsuarioClient usuarioClient, AsignaturaClient asignaturaClient,
                            MatriculaRepository matriculaRepository) {
        this.usuarioClient = usuarioClient;
        this.asignaturaClient = asignaturaClient;
        this.matriculaRepository = matriculaRepository;
    }

    public List<MatriculaResponse> obtenerMatriculasConDetalles() {
        List<Matricula> matriculas = matriculaRepository.findAll();
        List<MatriculaResponse> lista = new ArrayList<>();

        for (Matricula matricula : matriculas) {
            Usuario usuario = usuarioClient.obtenerUsuario(matricula.getUsuarioId());
            Asignatura asignatura = asignaturaClient.obtenerAsignaturaPorId(matricula.getAsignaturaId());

            lista.add(new MatriculaResponse(
                    matricula.getId(),
                    usuario.getNombre(),
                    usuario.getEmail(),
                    asignatura.getNombre(),
                    asignatura.getDescripcion()
            ));
        }

        return lista;
    }

    public Matricula guardarMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }
}
