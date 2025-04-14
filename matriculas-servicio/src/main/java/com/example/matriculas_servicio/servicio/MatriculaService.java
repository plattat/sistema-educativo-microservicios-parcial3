package com.example.matriculas_servicio.servicio;

import com.example.matriculas_servicio.feign.AsignaturaClient;
import com.example.matriculas_servicio.feign.UsuarioClient;
import com.example.matriculas_servicio.dto.MatriculaResponse;
import com.example.matriculas_servicio.model.Asignatura;
import com.example.matriculas_servicio.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaService {

    private final UsuarioClient usuarioClient;
    private final AsignaturaClient asignaturaClient;

    public MatriculaService(UsuarioClient usuarioClient, AsignaturaClient asignaturaClient) {
        this.usuarioClient = usuarioClient;
        this.asignaturaClient = asignaturaClient;
    }

    public List<MatriculaResponse> obtenerMatriculasConDetalles() {
        List<MatriculaResponse> lista = new ArrayList<>();

        Usuario u1 = usuarioClient.obtenerUsuarioPorId(1L);
        Asignatura a1 = asignaturaClient.obtenerAsignaturaPorId(1L);

        Usuario u2 = usuarioClient.obtenerUsuarioPorId(2L);
        Asignatura a2 = asignaturaClient.obtenerAsignaturaPorId(2L);

        lista.add(new MatriculaResponse(1L, u1.getNombre(), u1.getEmail(), a1.getNombre(), a1.getDescripcion()));
        lista.add(new MatriculaResponse(2L, u2.getNombre(), u2.getEmail(), a2.getNombre(), a2.getDescripcion()));

        return lista;
    }
}
