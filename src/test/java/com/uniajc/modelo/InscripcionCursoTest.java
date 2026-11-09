package com.uniajc.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InscripcionCursoTest {

    @Test
    @DisplayName("Instanciar InscripcionCurso con id_estudiante inválido lanza excepción.")
    void instanciarInscripcionCursoConIdEstudianteInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new InscripcionCurso(0, 1, 4.5f, "ACTIVO"));
        assertThrows(IllegalArgumentException.class, () -> new InscripcionCurso(-1, 1, 4.5f, "ACTIVO"));
    }

    @Test
    @DisplayName("Instanciar InscripcionCurso con id_grupo inválido lanza excepción.")
    void instanciarInscripcionCursoConIdGrupoInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new InscripcionCurso(1, 0, 4.5f, "ACTIVO"));
        assertThrows(IllegalArgumentException.class, () -> new InscripcionCurso(1, -2, 4.5f, "ACTIVO"));
    }

    @Test
    @DisplayName("Instanciar InscripcionCurso con nota_final inválida lanza excepción.")
    void instanciarInscripcionCursoConNotaFinalInvalidaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new InscripcionCurso(1, 1, -0.1f, "ACTIVO"));
        assertThrows(IllegalArgumentException.class, () -> new InscripcionCurso(1, 1, 5.1f, "ACTIVO"));
    }

    @Test
    @DisplayName("Instanciar InscripcionCurso con estado inválido lanza excepción.")
    void instanciarInscripcionCursoConEstadoInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new InscripcionCurso(1, 1, 4.0f, null));
        assertThrows(IllegalArgumentException.class, () -> new InscripcionCurso(1, 1, 4.0f, ""));
        assertThrows(IllegalArgumentException.class, () -> new InscripcionCurso(1, 1, 4.0f, "   "));
    }

    @Test
    @DisplayName("Instanciar InscripcionCurso con parámetros válidos no lanza excepción.")
    void instanciarInscripcionCursoConParametrosValidosNoLanzaExcepcion() {
        assertDoesNotThrow(() -> new InscripcionCurso(1, 1, 4.5f, "ACTIVO"));
        assertDoesNotThrow(() -> new InscripcionCurso(10, 1, 2, 0.0f, "ACTIVO"));
        assertDoesNotThrow(() -> new InscripcionCurso(11, 2, 3, 5.0f, "APROBADO"));
    }
}
