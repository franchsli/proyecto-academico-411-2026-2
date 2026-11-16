package com.uniajc.modelo;

public class Docente {
    private int id;
    private String nombre;
    private String especialidad;

    public Docente(String nombre, String especialidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del docente no puede estar vacío o nulo.");
        }
        else this.nombre = nombre;
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad del docente no puede estar vacía o nula.");
        }
        else this.especialidad = especialidad;
    }

    public Docente(int id, String nombre, String especialidad) {
        this(nombre, especialidad);
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
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
}
