package com.uniajc.modelo;

public class Materia {
    private int id;
    private String nombre;
    private int creditos;

    public Materia(String nombre, int creditos) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia no puede estar vacío o nulo.");
        } else {
            this.nombre = nombre;
        }

        if (creditos <= 0) {
            throw new IllegalArgumentException("Los créditos de la materia deben ser mayores a cero.");
        } else {
            this.creditos = creditos;
        }
    }

    public Materia(int id, String nombre, int creditos) {
        this(nombre, creditos);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}
