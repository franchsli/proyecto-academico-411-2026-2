package com.uniajc.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MateriaTest {

    @Test
    @DisplayName("Instanciar Materia con nombre inválido lanza excepción.")
    void instanciarMateriaConNombreInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Materia(null, 3));
        assertThrows(IllegalArgumentException.class, () -> new Materia("", 3));
        assertThrows(IllegalArgumentException.class, () -> new Materia("   ", 3));
    }

    @Test
    @DisplayName("Instanciar Materia con créditos inválidos lanza excepción.")
    void instanciarMateriaConCreditosInvalidosLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Materia("Programación II", 0));
        assertThrows(IllegalArgumentException.class, () -> new Materia("Programación II", -2));
    }

    @Test
    @DisplayName("Instanciar Materia con parámetros válidos no lanza excepción.")
    void instanciarMateriaConParametrosValidosNoLanzaExcepcion() {
        assertDoesNotThrow(() -> new Materia("Programación II", 3));
        assertDoesNotThrow(() -> new Materia(1, "Base de Datos", 4));
    }
}
