package com.uniajc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.uniajc.db.ConexionDatabase;
import com.uniajc.modelo.Docente;

public class Docentedao {

    // 1. GUARDAR DOCENTE
    public void guardar(Docente docente) {

        String sql = "INSERT INTO \"practica-mvc\".docentes "
                   + "(name, specialty, email) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, docente.getNombre());
            pstmt.setString(2, docente.getEspecialidad());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el docente", e);
        }
    }

    // 2. OBTENER TODOS LOS DOCENTES
    public List<Docente> obtenerTodos() {

        List<Docente> lista = new ArrayList<>();

        String sql = "SELECT id, name, specialty, email "
                   + "FROM \"practica-mvc\".docentes";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearDocente(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar docentes", e);
        }

        return lista;
    }

    // 3. BUSCAR DOCENTE POR ID
    public Optional<Docente> buscarPorId(int id) {

        String sql = "SELECT id, name, specialty, email "
                   + "FROM \"practica-mvc\".docentes WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(mapearDocente(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el docente", e);
        }

        return Optional.empty();
    }

    // 4. ACTUALIZAR DOCENTE
    public void actualizar(Docente docente) {

        String sql = "UPDATE \"practica-mvc\".docentes "
                   + "SET name = ?, specialty = ?, email = ? WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, docente.getNombre());
            pstmt.setString(2, docente.getEspecialidad());
            pstmt.setInt(4, docente.getId());

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró el docente");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el docente", e);
        }
    }

    // 5. ELIMINAR DOCENTE
    public void eliminar(int id) {

        String sql = "DELETE FROM \"practica-mvc\".docentes WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró el docente");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el docente", e);
        }
    }

    // 6. MAPEAR RESULTADO SQL A OBJETO DOCENTE
    private Docente mapearDocente(ResultSet rs) throws SQLException {

        Docente docente = new Docente("nombre" , "especialidad");

        docente.setId(rs.getInt("id"));
        docente.setNombre(rs.getString("name"));
        docente.setEspecialidad(rs.getString("specialty"));

        return docente;
    }
}