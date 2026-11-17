package com.uniajc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.uniajc.db.ConexionDatabase;
import com.uniajc.modelo.Materia;

public class Materiadao {

    // 1. GUARDAR MATERIA
    public void guardar(Materia materia) {

        String sql = "INSERT INTO \"practica-mvc\".materias "
                   + "(name, credits) VALUES (?, ?)";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, materia.getNombre());
            pstmt.setInt(2, materia.getCreditos());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la materia", e);
        }
    }

    // 2. OBTENER TODAS LAS MATERIAS
    public List<Materia> obtenerTodos() {

        List<Materia> lista = new ArrayList<>();

        String sql = "SELECT id, name, credits "
                   + "FROM \"practica-mvc\".materias";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearMateria(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar materias", e);
        }

        return lista;
    }

    // 3. BUSCAR MATERIA POR ID
    public Optional<Materia> buscarPorId(int id) {

        String sql = "SELECT id, name, credits "
                   + "FROM \"practica-mvc\".materias WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(mapearMateria(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la materia", e);
        }

        return Optional.empty();
    }

    // 4. ACTUALIZAR MATERIA
    public void actualizar(Materia materia) {

        String sql = "UPDATE \"practica-mvc\".materias "
                   + "SET name = ?, credits = ? WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, materia.getNombre());
            pstmt.setInt(2, materia.getCreditos());
            pstmt.setInt(3, materia.getId());

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró la materia");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la materia", e);
        }
    }

    // 5. ELIMINAR MATERIA
    public void eliminar(int id) {

        String sql = "DELETE FROM \"practica-mvc\".materias WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró la materia");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la materia", e);
        }
    }

    // 6. MAPEAR RESULTADO SQL A OBJETO MATERIA
    private Materia mapearMateria(ResultSet rs) throws SQLException {

        Materia materia = new Materia("nombre", 0);

        materia.setId(rs.getInt("id"));
        materia.setNombre(rs.getString("name"));
        materia.setCreditos(rs.getInt("credits"));

        return materia;
    }
}