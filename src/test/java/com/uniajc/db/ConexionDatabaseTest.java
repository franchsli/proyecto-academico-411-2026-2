package com.uniajc.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

class ConexionDatabaseTest {

    @BeforeEach
    void limpiarConexion() {
        ConexionDatabase.closeConnection();
    }

    @Test
    @DisplayName("getConnection() debe retornar una conexión no nula.")
    void getConnectionDebeRetornarUnaConexionNoNula() {
        Connection conexionFalsa = mock(Connection.class);

        try (MockedStatic<DriverManager> driverManager =
                     mockStatic(DriverManager.class)) {

            driverManager.when(() ->
            // anyString() intercepta la conexión real que use la clase
            // sea cual sea
                    DriverManager.getConnection(anyString()))
                    .thenReturn(conexionFalsa);

            Connection resultado = ConexionDatabase.getConnection();

            assertNotNull(resultado);
        }
    }

   @Test
   @DisplayName("closeConnection() debe dejar la conexión nula.")
    void closeConnectionDebeDejarLaConexionNula() throws Exception {
        Connection conexionFalsa = mock(Connection.class);

        try (MockedStatic<DriverManager> driverManager =
                     mockStatic(DriverManager.class)) {

            driverManager.when(() ->
                            DriverManager.getConnection(anyString()))
                    .thenReturn(conexionFalsa);

            Connection conexion = ConexionDatabase.getConnection();

            assertNotNull(conexion);

            ConexionDatabase.closeConnection();

            Field campoConnection =
                    ConexionDatabase.class.getDeclaredField("connection");

            campoConnection.setAccessible(true);

            assertNull(campoConnection.get(null));
        }
    }
}