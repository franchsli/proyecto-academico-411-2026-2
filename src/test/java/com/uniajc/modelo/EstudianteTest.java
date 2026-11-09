package com.uniajc.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EstudianteTest {

    @Test
    @DisplayName("Instanciar Estudiante con nombre inválido lanza excepción.")
    void instanciarEstudianteConNombreInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Estudiante(null, "Pérez", "juan.perez@uniajc.edu.co"));
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("", "Pérez", "juan.perez@uniajc.edu.co"));
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("   ", "Pérez", "juan.perez@uniajc.edu.co"));
    }

    @Test
    @DisplayName("Instanciar Estudiante con apellido inválido lanza excepción.")
    void instanciarEstudianteConApellidoInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("Juan", null, "juan.perez@uniajc.edu.co"));
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("Juan", "", "juan.perez@uniajc.edu.co"));
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("Juan", "   ", "juan.perez@uniajc.edu.co"));
    }

    @Test
    @DisplayName("Instanciar Estudiante con email inválido lanza excepción.")
    void instanciarEstudianteConEmailInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("Juan", "Pérez", null));
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("Juan", "Pérez", ""));
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("Juan", "Pérez", "   "));
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("Juan", "Pérez", "correoInvalido"));
    }

    @Test
    @DisplayName("Instanciar Estudiante con parámetros válidos no lanza excepción.")
    void instanciarEstudianteConParametrosValidosNoLanzaExcepcion() {
        assertDoesNotThrow(() -> new Estudiante("Juan", "Pérez", "juan.perez@uniajc.edu.co"));
        assertDoesNotThrow(() -> new Estudiante(1, "Juan", "Pérez", "juan.perez@uniajc.edu.co"));
    }
}
