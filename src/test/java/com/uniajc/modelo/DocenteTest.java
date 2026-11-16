package com.uniajc.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DocenteTest {
    
    @Test
    @DisplayName("Instanciar Docente con nombre inválido lanza excepción.")
    void instanciarDocenteConNombreInvalidoLanzaExcepcion(){
        assertThrows(IllegalArgumentException.class, () -> new Docente(null, "Cátedra."));
        assertThrows(IllegalArgumentException.class, () -> new Docente("", "Cátedra."));
    }

    @Test
    @DisplayName("Instanciar Docente con especialidad inválida lanza excepción.")
    void instanciarDocenteConEspecialidadInvalidaLanzaExcepcion(){
        assertThrows(IllegalArgumentException.class, () -> new Docente("Sergio", null));
        assertThrows(IllegalArgumentException.class, () -> new Docente("Hernesto", ""));
    }

    @Test
    @DisplayName("Instanciar Docente con parámetros válidos no lanza excepción.")
    void instanciarDocenteConParametrosValidosNoLanzaExcepcion(){
        assertDoesNotThrow(() -> new Docente("Segio", "Cátedra."));
        assertDoesNotThrow(() -> new Docente(2, "Hernesto", "Cátedra."));
    }
}
