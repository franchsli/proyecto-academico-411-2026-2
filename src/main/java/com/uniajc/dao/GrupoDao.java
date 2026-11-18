package com.uniajc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.uniajc.db.ConexionDatabase;
import com.uniajc.modelo.Grupo;

public class GrupoDao {

    // 1. GUARDAR GRUPO
    public void guardar(Grupo grupo) {

        String sql = "INSERT INTO \"practica-mvc\".grupos "
                   + "(id_materia, id_docente, classroom, schedule) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, grupo.getIdMateria());
            pstmt.setInt(2, grupo.getIdDocente());
            pstmt.setString(3, grupo.getAula());
            pstmt.setString(4, grupo.getHorario());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el grupo", e);
        }
    }

    // 2. OBTENER TODOS LOS GRUPOS
    public List<Grupo> obtenerTodos() {

        List<Grupo> lista = new ArrayList<>();

        String sql = "SELECT id, id_materia, id_docente, classroom, schedule "
                   + "FROM \"practica-mvc\".grupos";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearGrupo(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar grupos", e);
        }

        return lista;
    }

    // 3. BUSCAR GRUPO POR ID
    public Optional<Grupo> buscarPorId(int id) {

        String sql = "SELECT id, id_materia, id_docente, classroom, schedule "
                   + "FROM \"practica-mvc\".grupos WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(mapearGrupo(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el grupo", e);
        }

        return Optional.empty();
    }

    // 4. ACTUALIZAR GRUPO
    public void actualizar(Grupo grupo) {

        String sql = "UPDATE \"practica-mvc\".grupos "
                   + "SET id_materia = ?, id_docente = ?, "
                   + "classroom = ?, schedule = ? WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, grupo.getIdMateria());
            pstmt.setInt(2, grupo.getIdDocente());
            pstmt.setString(3, grupo.getAula());
            pstmt.setString(4, grupo.getHorario());
            pstmt.setInt(5, grupo.getId());

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró el grupo");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el grupo", e);
        }
    }

    // 5. ELIMINAR GRUPO
    public void eliminar(int id) {

        String sql = "DELETE FROM \"practica-mvc\".grupos WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró el grupo");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el grupo", e);
        }
    }

    // 6. MAPEAR RESULTADO SQL A OBJETO GRUPO
    private Grupo mapearGrupo(ResultSet rs) throws SQLException {

        Grupo grupo = new Grupo(0,0, 0, "aula", "horario");

        grupo.setId(rs.getInt("id"));
        grupo.setIdMateria(rs.getInt("id_materia"));
        grupo.setIdDocente(rs.getInt("id_docente"));
        grupo.setAula(rs.getString("classroom"));
        grupo.setHorario(rs.getString("schedule"));

        return grupo;
    }
}