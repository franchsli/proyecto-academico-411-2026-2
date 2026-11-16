package com.uniajc.modelo;

public class InscripcionCurso {
    private int id;
    private int id_estudiante;
    private int id_grupo;
    private float nota_final;
    private String estado;

    public InscripcionCurso(int id_estudiante, int id_grupo, float nota_final, String estado) {
        if (id_estudiante <= 0) {
            throw new IllegalArgumentException("El id del estudiante debe ser un número positivo.");
        } else {
            this.id_estudiante = id_estudiante;
        }

        if (id_grupo <= 0) {
            throw new IllegalArgumentException("El id del grupo debe ser un número positivo.");
        } else {
            this.id_grupo = id_grupo;
        }

        if (nota_final < 0.0f || nota_final > 5.0f) {
            throw new IllegalArgumentException("La nota final debe estar entre 0.0 y 5.0.");
        } else {
            this.nota_final = nota_final;
        }

        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de la inscripción no puede estar vacío o nulo.");
        } else {
            this.estado = estado;
        }
    }

    public InscripcionCurso(int id, int id_estudiante, int id_grupo, float nota_final, String estado) {
        this(id_estudiante, id_grupo, nota_final, estado);
        this.id = id;
    }

    public InscripcionCurso(int id_estudiante, int id_grupo, double nota_final, String estado) {
        this(id_estudiante, id_grupo, (float) nota_final, estado);
    }

    public InscripcionCurso(int id, int id_estudiante, int id_grupo, double nota_final, String estado) {
        this(id, id_estudiante, id_grupo, (float) nota_final, estado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getIdEstudiante() {
        return id_estudiante;
    }

    public void setIdEstudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getIdGrupo() {
        return id_grupo;
    }

    public void setIdGrupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public float getNota_final() {
        return nota_final;
    }

    public void setNota_final(float nota_final) {
        this.nota_final = nota_final;
    }

    public void setNota_final(double nota_final) {
        this.nota_final = (float) nota_final;
    }

    public float getNotaFinal() {
        return nota_final;
    }

    public void setNotaFinal(float nota_final) {
        this.nota_final = nota_final;
    }

    public void setNotaFinal(double nota_final) {
        this.nota_final = (float) nota_final;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
