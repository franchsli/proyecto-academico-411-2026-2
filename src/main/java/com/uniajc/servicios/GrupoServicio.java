package com.uniajc.servicios;

import com.uniajc.dao.GrupoDao;
import com.uniajc.modelo.Grupo;
import java.util.List;
import java.util.Optional;

public class GrupoServicio {

    private final GrupoDao grupoDao;

    public GrupoServicio(GrupoDao grupoDao) {
        this.grupoDao = grupoDao;
    }

    public void registrarGrupo(Grupo grupo) {
        if (grupo == null) {
            throw new IllegalArgumentException("El grupo no puede estar vacío.");
        }
        if (grupo.getIdMateria() <= 0 || grupo.getIdDocente() <= 0) {
            throw new IllegalArgumentException("La materia y el docente deben tener un ID válido.");
        }
        if (grupo.getAula() == null || grupo.getAula().trim().isEmpty()) {
            throw new IllegalArgumentException("El salón es obligatorio.");
        }
        if (grupo.getHorario() == null || grupo.getHorario().trim().isEmpty()) {
            throw new IllegalArgumentException("El horario es obligatorio.");
        }

        grupoDao.guardar(grupo);
    }

    public List<Grupo> obtenerTodosLosGrupos() {
        return grupoDao.obtenerTodos();
    }

    public Optional<Grupo> buscarGrupoPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        return grupoDao.buscarPorId(id);
    }

    public void actualizarGrupo(Grupo grupo) {
        if (grupo == null || grupo.getId() <= 0) {
            throw new IllegalArgumentException("El grupo o su ID no son válidos.");
        }
        if (grupo.getIdMateria() <= 0 || grupo.getIdDocente() <= 0) {
            throw new IllegalArgumentException("La materia y el docente deben tener un ID válido.");
        }
        if (grupo.getAula() == null || grupo.getAula().trim().isEmpty()
                || grupo.getHorario() == null || grupo.getHorario().trim().isEmpty()) {
            throw new IllegalArgumentException("El salón y el horario son obligatorios.");
        }
        if (!grupoDao.buscarPorId(grupo.getId()).isPresent()) {
            throw new IllegalArgumentException("El grupo no existe.");
        }

        grupoDao.actualizar(grupo);
    }

    public void eliminarGrupo(int id) {
        if (id <= 0 || !grupoDao.buscarPorId(id).isPresent()) {
            throw new IllegalArgumentException("El grupo no existe.");
        }

        grupoDao.eliminar(id);
    }
}