package com.uniajc.dao;

// Importaciones necesarias para trabajar con PostgreSQL
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importaciones para listas y búsquedas
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Conexión y modelo
import com.uniajc.config.ConexionPostgresDatabase;
import com.uniajc.modelo.Estudiante;

public class EstudianteDao {

    // 1. GUARDAR UN ESTUDIANTE
    public void guardar(Estudiante estudiante) {

        String sql = "INSERT INTO \"practica-mvc\".estudiantes "
                   + "(name, lastname, email) VALUES (?, ?, ?)";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getApellido());
            pstmt.setString(3, estudiante.getEmail());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el estudiante", e);
        }
    }

    // 2. OBTENER TODOS LOS ESTUDIANTES
    public List<Estudiante> obtenerTodos() {

        List<Estudiante> lista = new ArrayList<>();

        String sql = "SELECT id, name, lastname, email "
                   + "FROM \"practica-mvc\".estudiantes";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearEstudiante(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar estudiantes", e);
        }

        return lista;
    }

    // 3. BUSCAR UN ESTUDIANTE POR SU ID
    public Optional<Estudiante> buscarPorId(int id) {

        String sql = "SELECT id, name, lastname, email "
                   + "FROM \"practica-mvc\".estudiantes WHERE id = ?";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(mapearEstudiante(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el estudiante", e);
        }

        return Optional.empty();
    }

    // 4. ACTUALIZAR UN ESTUDIANTE
    public void actualizar(Estudiante estudiante) {

        String sql = "UPDATE \"practica-mvc\".estudiantes "
                   + "SET name = ?, lastname = ?, email = ? WHERE id = ?";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getApellido());
            pstmt.setString(3, estudiante.getEmail());
            pstmt.setInt(4, estudiante.getId());

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró el estudiante");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estudiante", e);
        }
    }

    // 5. ELIMINAR UN ESTUDIANTE
    public void eliminar(int id) {

        String sql = "DELETE FROM \"practica-mvc\".estudiantes WHERE id = ?";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró el estudiante");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el estudiante", e);
        }
    }

    // 6. CONVERTIR LOS DATOS DE SQL EN UN OBJETO ESTUDIANTE
    private Estudiante mapearEstudiante(ResultSet rs) throws SQLException {

        Estudiante estudiante = new Estudiante();

        estudiante.setId(rs.getInt("id"));
        estudiante.setNombre(rs.getString("name"));
        estudiante.setApellido(rs.getString("lastname"));
        estudiante.setEmail(rs.getString("email"));

        return estudiante;
    }
}