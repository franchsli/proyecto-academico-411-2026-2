package com.uniajc.servicios;

import com.uniajc.dao.InscripcionCursoDao;
import com.uniajc.modelo.InscripcionCurso;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InscripcionCursoServicio {

    private final InscripcionCursoDao inscripcionCursoDao;

    public InscripcionCursoServicio(InscripcionCursoDao inscripcionCursoDao) {
        this.inscripcionCursoDao = inscripcionCursoDao;
    }

    public void registrarInscripcion(InscripcionCurso inscripcion) {
        if (inscripcion == null) {
            throw new IllegalArgumentException("La inscripción no puede estar vacía.");
        }
        if (inscripcion.getIdEstudiante() <= 0 || inscripcion.getIdGrupo() <= 0) {
            throw new IllegalArgumentException("El estudiante y el grupo deben tener un ID válido.");
        }
        if (inscripcion.getEstado() == null ||
                !inscripcion.getEstado().matches("ACTIVO|APROBADO|REPROBADO|CANCELADO")) {
            throw new IllegalArgumentException("El estado de la inscripción no es válido.");
        }
        if (inscripcion.getNotaFinal() < 0 || inscripcion.getNotaFinal() > 5) {
            throw new IllegalArgumentException("La nota final debe estar entre 0 y 5.");
        }

        inscripcionCursoDao.guardar(inscripcionCursoDao);
    }

    public List<InscripcionCurso> obtenerTodasLasInscripciones() {
        return inscripcionCursoDao.obtenerTodos()
                .stream()
                .map(inscripcion -> (InscripcionCurso) (Object) inscripcion)
                .collect(Collectors.toList());
    }

    public Optional<InscripcionCurso> buscarInscripcionPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        return inscripcionCursoDao.buscarPorId(id)
                .map(inscripcion -> (InscripcionCurso) (Object) inscripcion);
    }

    public void actualizarInscripcion(InscripcionCurso inscripcion) {
        if (inscripcion == null || inscripcion.getId() <= 0) {
            throw new IllegalArgumentException("La inscripción o su ID no son válidos.");
        }
        if (inscripcion.getIdEstudiante() <= 0 || inscripcion.getIdGrupo() <= 0) {
            throw new IllegalArgumentException("El estudiante y el grupo deben tener un ID válido.");
        }
        if (inscripcion.getEstado() == null ||
                !inscripcion.getEstado().matches("ACTIVO|APROBADO|REPROBADO|CANCELADO")) {
            throw new IllegalArgumentException("El estado de la inscripción no es válido.");
        }
        if (inscripcion.getNotaFinal() < 0 || inscripcion.getNotaFinal() > 5) {
            throw new IllegalArgumentException("La nota final debe estar entre 0 y 5.");
        }
        if (!inscripcionCursoDao.buscarPorId(inscripcion.getId()).isPresent()) {
            throw new IllegalArgumentException("La inscripción no existe.");
        }

        inscripcionCursoDao.actualizar(inscripcionCursoDao);
    }

    public void eliminarInscripcion(int id) {
        if (id <= 0 || !inscripcionCursoDao.buscarPorId(id).isPresent()) {
            throw new IllegalArgumentException("La inscripción no existe.");
        }

        inscripcionCursoDao.eliminar(id);
    }
}