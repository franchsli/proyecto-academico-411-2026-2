package com.uniajc.db;

// 1. Importar las clases necesarias de JDBC
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

// Otras importaciones necesarias para gestionar archivos de propiedades
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;

public class ConexionDatabase {
    // Conexión única (singleton)
    private static Connection connection = null;

    /**
     * Obtiene la conexión a la base de datos. Si no existe, la crea usando
     * config.properties.
     * 
     * @return Objeto Connection listo para usar.
     */
    public static Connection getConnection() {

        Properties properties = new Properties();

        if (connection == null) {
            try {

                // Cargar las propiedades desde el archivo config.properties
                properties.load(new FileInputStream(new File("config.properties")));

                // Lee la URL de conexión
                String url = properties.get("URL").toString();

                // Establecer la conexión
                connection = DriverManager.getConnection(url);
                System.out.println("Conexion exitosa.");
            } catch (SQLException error) {
                System.out.println("Failed to establish connection. " + error.getMessage());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Cierra la conexión a la base de datos si está abierta.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to close connection. " + e.getMessage());
            }
        }
    }
}
