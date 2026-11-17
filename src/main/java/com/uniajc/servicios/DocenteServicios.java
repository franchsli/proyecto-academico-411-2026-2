package com.uniajc.servicios;

import com.uniajc.dao.DocenteDao;
import com.uniajc.modelo.Docente;
import java.util.List;
import java.util.Optional;

public class DocenteServicios {

    private final DocenteDao docenteDao;

    public DocenteServicios(DocenteDao docenteDao) {
        this.docenteDao = docenteDao;
    }

    public void registrarDocente(Docente docente) {
        if (docente == null) {
            throw new IllegalArgumentException("El docente no puede estar vacío.");
        }
        if (docente.getNombre() == null || docente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (docente.getEspecialidad() == null || docente.getEspecialidad().trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria.");
        }

        docenteDao.guardar(docente);
    }

    public List<Docente> obtenerTodosLosDocentes() {
        return docenteDao.obtenerTodos();
    }

    public Optional<Docente> buscarDocentePorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        return docenteDao.buscarPorId(id);
    }

    public void actualizarDocente(Docente docente) {
        if (docente == null || docente.getId() <= 0) {
            throw new IllegalArgumentException("El docente o su ID no son válidos.");
        }
        if (docente.getNombre() == null || docente.getNombre().trim().isEmpty()
                || docente.getEspecialidad() == null || docente.getEspecialidad().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre y la especialidad son obligatorios.");
        }
        if (!docenteDao.buscarPorId(docente.getId()).isPresent()) {
            throw new IllegalArgumentException("El docente no existe.");
        }

        docenteDao.actualizar(docente);
    }

    public void eliminarDocente(int id) {
        if (id <= 0 || !docenteDao.buscarPorId(id).isPresent()) {
            throw new IllegalArgumentException("El docente no existe.");
        }

        docenteDao.eliminar(id);
    }
}