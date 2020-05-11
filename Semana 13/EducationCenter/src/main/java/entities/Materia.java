package entities;

import java.util.ArrayList;

public class Materia {

    private String id;
    private String nombre;
    private String NRC;

    private ArrayList<Estudiante> estudiantes;

    public Materia(String id, String nombre, String NRC) {
        this.id = id;
        this.nombre = nombre;
        this.NRC = NRC;
    }

    public Materia() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNRC() {
        return NRC;
    }

    public void setNRC(String NRC) {
        this.NRC = NRC;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
