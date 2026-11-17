package com.uniajc.servicios;

import com.uniajc.dao.MateriaDao;
import com.uniajc.modelo.Materia;
import java.util.List;
import java.util.Optional;

public class MateriaServicio {

    private final MateriaDao materiaDao;

    public MateriaServicio(MateriaDao materiaDao) {
        this.materiaDao = materiaDao;
    }

    public void registrarMateria(Materia materia) {
        if (materia == null) {
            throw new IllegalArgumentException("La materia no puede estar vacía.");
        }
        if (materia.getNombre() == null || materia.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia es obligatorio.");
        }
        if (materia.getCreditos() < 1 || materia.getCreditos() > 10) {
            throw new IllegalArgumentException("Los créditos deben estar entre 1 y 10.");
        }

        materiaDao.guardar(materia);
    }

    public List<Materia> obtenerTodasLasMaterias() {
        return materiaDao.obtenerTodos();
    }

    public Optional<Materia> buscarMateriaPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        return materiaDao.buscarPorId(id);
    }

    public void actualizarMateria(Materia materia) {
        if (materia == null || materia.getId() <= 0) {
            throw new IllegalArgumentException("La materia o su ID no son válidos.");
        }
        if (materia.getNombre() == null || materia.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia es obligatorio.");
        }
        if (materia.getCreditos() < 1 || materia.getCreditos() > 10) {
            throw new IllegalArgumentException("Los créditos deben estar entre 1 y 10.");
        }
        if (!materiaDao.buscarPorId(materia.getId()).isPresent()) {
            throw new IllegalArgumentException("La materia no existe.");
        }

        materiaDao.actualizar(materia);
    }

    public void eliminarMateria(int id) {
        if (id <= 0 || !materiaDao.buscarPorId(id).isPresent()) {
            throw new IllegalArgumentException("La materia no existe.");
        }

        materiaDao.eliminar(id);
    }
}