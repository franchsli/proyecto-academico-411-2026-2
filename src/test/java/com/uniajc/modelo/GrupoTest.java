package com.uniajc.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GrupoTest {

    @Test
    @DisplayName("Instanciar Grupo con id_materia inválido lanza excepción.")
    void instanciarGrupoConIdMateriaInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Grupo(0, 1, "A-101", "Lunes 07:00-10:00"));
        assertThrows(IllegalArgumentException.class, () -> new Grupo(-1, 1, "A-101", "Lunes 07:00-10:00"));
    }

    @Test
    @DisplayName("Instanciar Grupo con id_docente inválido lanza excepción.")
    void instanciarGrupoConIdDocenteInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Grupo(1, 0, "A-101", "Lunes 07:00-10:00"));
        assertThrows(IllegalArgumentException.class, () -> new Grupo(1, -5, "A-101", "Lunes 07:00-10:00"));
    }

    @Test
    @DisplayName("Instanciar Grupo con aula inválida lanza excepción.")
    void instanciarGrupoConAulaInvalidaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Grupo(1, 1, null, "Lunes 07:00-10:00"));
        assertThrows(IllegalArgumentException.class, () -> new Grupo(1, 1, "", "Lunes 07:00-10:00"));
        assertThrows(IllegalArgumentException.class, () -> new Grupo(1, 1, "   ", "Lunes 07:00-10:00"));
    }

    @Test
    @DisplayName("Instanciar Grupo con horario inválido lanza excepción.")
    void instanciarGrupoConHorarioInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Grupo(1, 1, "A-101", null));
        assertThrows(IllegalArgumentException.class, () -> new Grupo(1, 1, "A-101", ""));
        assertThrows(IllegalArgumentException.class, () -> new Grupo(1, 1, "A-101", "   "));
    }

    @Test
    @DisplayName("Instanciar Grupo con parámetros válidos no lanza excepción.")
    void instanciarGrupoConParametrosValidosNoLanzaExcepcion() {
        assertDoesNotThrow(() -> new Grupo(1, 2, "A-101", "Lunes 07:00-10:00"));
        assertDoesNotThrow(() -> new Grupo(10, 1, 2, "Laboratorio 2", "Viernes 14:00-18:00"));
    }
}
