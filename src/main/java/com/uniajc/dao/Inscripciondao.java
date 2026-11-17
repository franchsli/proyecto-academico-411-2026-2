package com.uniajc.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.uniajc.db.ConexionDatabase;

public class Inscripciondao {

    private int id;
    private int idEstudiante;
    private int idGrupo;
    private BigDecimal notaFinal;
    private String estado;

    public Inscripciondao() {
    }

    public Inscripciondao(int id, int idEstudiante, int idGrupo, BigDecimal notaFinal, String estado) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.idGrupo = idGrupo;
        this.notaFinal = notaFinal;
        this.estado = estado;
    }

    // 1. GUARDAR INSCRIPCION
    public void guardar(Inscripciondao inscripcion) {

        String sql = "INSERT INTO \"practica-mvc\".inscripciones "
                   + "(id_estudiante, id_grupo, final_grade, status) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, inscripcion.getIdEstudiante());
            pstmt.setInt(2, inscripcion.getIdGrupo());
            pstmt.setBigDecimal(3, inscripcion.getNotaFinal());
            pstmt.setString(4, inscripcion.getEstado());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la inscripción", e);
        }
    }

    // 2. OBTENER TODAS LAS INSCRIPCIONES
    public List<Inscripciondao> obtenerTodos() {

        List<Inscripciondao> lista = new ArrayList<>();

        String sql = "SELECT id, id_estudiante, id_grupo, final_grade, status "
                   + "FROM \"practica-mvc\".inscripciones";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearInscripcion(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar inscripciones", e);
        }

        return lista;
    }

    // 3. BUSCAR INSCRIPCION POR ID
    public Optional<Inscripciondao> buscarPorId(int id) {

        String sql = "SELECT id, id_estudiante, id_grupo, final_grade, status "
                   + "FROM \"practica-mvc\".inscripciones WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(mapearInscripcion(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la inscripción", e);
        }

        return Optional.empty();
    }

    // 4. ACTUALIZAR INSCRIPCION
    public void actualizar(Inscripciondao inscripcion) {

        String sql = "UPDATE \"practica-mvc\".inscripciones "
                   + "SET id_estudiante = ?, id_grupo = ?, "
                   + "final_grade = ?, status = ? WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, inscripcion.getIdEstudiante());
            pstmt.setInt(2, inscripcion.getIdGrupo());
            pstmt.setBigDecimal(3, inscripcion.getNotaFinal());
            pstmt.setString(4, inscripcion.getEstado());
            pstmt.setInt(5, inscripcion.getId());

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró la inscripción");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la inscripción", e);
        }
    }

    // 5. ELIMINAR INSCRIPCION
    public void eliminar(int id) {

        String sql = "DELETE FROM \"practica-mvc\".inscripciones WHERE id = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró la inscripción");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la inscripción", e);
        }
    }

    // 6. MAPEAR RESULTADO SQL A OBJETO INSCRIPCION
    private Inscripciondao mapearInscripcion(ResultSet rs) throws SQLException {

        Inscripciondao inscripcion = new Inscripciondao();

        inscripcion.setId(rs.getInt("id"));
        inscripcion.setIdEstudiante(rs.getInt("id_estudiante"));
        inscripcion.setIdGrupo(rs.getInt("id_grupo"));
        inscripcion.setNotaFinal(rs.getBigDecimal("final_grade"));
        inscripcion.setEstado(rs.getString("status"));

        return inscripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public BigDecimal getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(BigDecimal notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdInscripcion() {
        return id;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.id = idInscripcion;
    }
}