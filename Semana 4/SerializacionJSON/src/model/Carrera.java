package model;

import java.util.ArrayList;

public class Carrera {

	private String nombre;
	private ArrayList<Materia> materias;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(ArrayList<Materia> materias) {
		this.materias = materias;
	}
	
}
