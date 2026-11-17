package com.uniajc.servicios;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.uniajc.dao.EstudianteDao;
import com.uniajc.modelo.Estudiante;

public class EstudianteServicios {

    private static final Pattern NOMBRE_PATTERN = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{3,50}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final EstudianteDao estudianteDao;

    public EstudianteServicios(EstudianteDao estudiantedao) {
        this.estudianteDao = estudiantedao;
    }

    public void registrarEstudiante(Estudiante estudiante) {
        validarEstudiante(estudiante);
        estudianteDao.guardar(estudiante);
    }

    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteDao.obtenerTodos();
    }

    public Optional<Estudiante> buscarEstudiantePorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return estudianteDao.buscarPorId(id);
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        if (estudiante == null || estudiante.getId() <= 0) {
            throw new IllegalArgumentException("Estudiante inválido.");
        }

        Optional<Estudiante> existente = estudianteDao.buscarPorId(estudiante.getId());

        if (existente.isEmpty()) {
            throw new IllegalArgumentException("Estudiante no encontrado.");
        }

        validarEstudiante(estudiante);
        estudianteDao.actualizar(estudiante);
    }

    public void eliminarEstudiante(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Optional<Estudiante> existente = estudianteDao.buscarPorId(id);

        if (existente.isEmpty()) {
            throw new IllegalArgumentException("Estudiante no encontrado.");
        }

        estudianteDao.eliminar(id);
    }

    private void validarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede estar vacío.");
        }

        if (estudiante.getNombre() == null ||
                !NOMBRE_PATTERN.matcher(estudiante.getNombre()).matches()) {
            throw new IllegalArgumentException("El nombre del estudiante no es válido.");
        }

        if (estudiante.getApellido() == null ||
                !NOMBRE_PATTERN.matcher(estudiante.getApellido()).matches()) {
            throw new IllegalArgumentException("El apellido del estudiante no es válido.");
        }

        if (estudiante.getEmail() == null ||
                !EMAIL_PATTERN.matcher(estudiante.getEmail()).matches()) {
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }
    }
}