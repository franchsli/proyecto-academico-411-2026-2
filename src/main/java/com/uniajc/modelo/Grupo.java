package com.uniajc.modelo;

public class Grupo {
    private int id;
    private int id_materia;
    private int id_docente;
    private String aula;
    private String horario;

    public Grupo(int id_materia, int id_docente, String aula, String horario) {
        if (id_materia <= 0) {
            throw new IllegalArgumentException("El id de la materia debe ser un número positivo.");
        } else {
            this.id_materia = id_materia;
        }

        if (id_docente <= 0) {
            throw new IllegalArgumentException("El id del docente debe ser un número positivo.");
        } else {
            this.id_docente = id_docente;
        }

        if (aula == null || aula.trim().isEmpty()) {
            throw new IllegalArgumentException("El aula del grupo no puede estar vacía o nula.");
        } else {
            this.aula = aula;
        }

        if (horario == null || horario.trim().isEmpty()) {
            throw new IllegalArgumentException("El horario del grupo no puede estar vacío o nulo.");
        } else {
            this.horario = horario;
        }
    }

    public Grupo(int id, int id_materia, int id_docente, String aula, String horario) {
        this(id_materia, id_docente, aula, horario);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public int getIdMateria() {
        return id_materia;
    }

    public void setIdMateria(int id_materia) {
        this.id_materia = id_materia;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public int getIdDocente() {
        return id_docente;
    }

    public void setIdDocente(int id_docente) {
        this.id_docente = id_docente;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
