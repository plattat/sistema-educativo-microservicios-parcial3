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

    public MatriculaService(UsuarioClient usuarioClient,
                            AsignaturaClient asignaturaClient,
                            MatriculaRepository matriculaRepository) {
        this.usuarioClient = usuarioClient;
        this.asignaturaClient = asignaturaClient;
        this.matriculaRepository = matriculaRepository;
    }

    public Matricula guardarMatricula(Matricula matricula) {
        System.out.println("📥 Intentando guardar matrícula...");
        System.out.println("➡️  Usuario ID: " + matricula.getUsuarioId());
        System.out.println("➡️  Asignatura ID: " + matricula.getAsignaturaId());

        boolean yaExiste = matriculaRepository.existsByUsuarioIdAndAsignaturaId(
                matricula.getUsuarioId(), matricula.getAsignaturaId());

        if (yaExiste) {
            System.out.println("⚠️ Ya existe una matrícula previa.");
            throw new RuntimeException("Ya existe matrícula para este usuario y asignatura.");
        }

        Usuario usuario;
        try {
            usuario = usuarioClient.obtenerUsuario(matricula.getUsuarioId());
            if (usuario == null) {
                System.out.println("❌ Usuario no encontrado desde usuarios-servicio.");
                throw new RuntimeException("Usuario no encontrado");
            }
            System.out.println("✅ Usuario obtenido: " + usuario.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error al consultar usuario desde usuarios-servicio");
            e.printStackTrace();
            throw new RuntimeException("Error al obtener usuario desde usuarios-servicio: " + e.getMessage());
        }

        Asignatura asignatura;
        try {
            asignatura = asignaturaClient.obtenerAsignaturaPorId(matricula.getAsignaturaId());
            if (asignatura == null) {
                System.out.println("❌ Asignatura no encontrada desde asignaturas-servicio.");
                throw new RuntimeException("Asignatura no encontrada");
            }
            System.out.println("✅ Asignatura obtenida: " + asignatura.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error al consultar asignatura desde asignaturas-servicio");
            e.printStackTrace();
            throw new RuntimeException("Error al obtener asignatura desde asignaturas-servicio: " + e.getMessage());
        }

        System.out.println("💾 Guardando matrícula en Mongo...");
        return matriculaRepository.save(matricula);
    }

    public List<MatriculaResponse> obtenerMatriculasConDetalles() {
        List<Matricula> matriculas = matriculaRepository.findAll();
        return construirRespuestas(matriculas);
    }

    public List<MatriculaResponse> obtenerPorUsuario(String usuarioId) {
        List<Matricula> matriculas = matriculaRepository.findByUsuarioId(usuarioId);
        return construirRespuestas(matriculas);
    }

    private List<MatriculaResponse> construirRespuestas(List<Matricula> matriculas) {
        List<MatriculaResponse> lista = new ArrayList<>();

        for (Matricula m : matriculas) {
            Usuario usuario = null;
            Asignatura asignatura = null;

            try {
                usuario = usuarioClient.obtenerUsuario(m.getUsuarioId());
                if (usuario == null) {
                    System.out.println("⚠️ Usuario no encontrado para ID: " + m.getUsuarioId());
                    continue;
                }
            } catch (Exception e) {
                System.out.println("❌ Error al obtener usuario: " + m.getUsuarioId());
                continue;
            }

            try {
                asignatura = asignaturaClient.obtenerAsignaturaPorId(m.getAsignaturaId());
                if (asignatura == null) {
                    System.out.println("⚠️ Asignatura no encontrada para ID: " + m.getAsignaturaId());
                    continue;
                }
            } catch (Exception e) {
                System.out.println("❌ Error al obtener asignatura: " + m.getAsignaturaId());
                continue;
            }

            lista.add(new MatriculaResponse(
                    m.getId(),
                    usuario.getNombre(),
                    usuario.getEmail(),
                    asignatura.getNombre(),
                    asignatura.getDescripcion()
            ));
        }

        return lista;
    }
}
