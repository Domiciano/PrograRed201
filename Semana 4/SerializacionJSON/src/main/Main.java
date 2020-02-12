package main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Carrera;
import model.Estudiante;
import model.Materia;
import model.Profesor;
import model.Universidad;

public class Main {

	public static void main(String[] args) {
		Estudiante estudiante1 = new Estudiante();
		estudiante1.setNombre("Cristian Flor");
		Estudiante estudiante2 = new Estudiante();
		estudiante2.setNombre("César Canales");
		Estudiante estudiante3 = new Estudiante();
		estudiante3.setNombre("Camilo Enriquez");
		Estudiante estudiante4 = new Estudiante();
		estudiante4.setNombre("Jesús Villota");
		Estudiante estudiante5 = new Estudiante();
		estudiante5.setNombre("Santiago Zuñiga");
		Estudiante estudiante6 = new Estudiante();
		estudiante6.setNombre("Lina Acosta");
		Estudiante estudiante7 = new Estudiante();
		estudiante7.setNombre("María Ordoñez");
		Estudiante estudiante8 = new Estudiante();
		estudiante8.setNombre("Sebatián Urbano");
		
		Profesor profesor1 = new Profesor();
		profesor1.setNombre("Andrés Navarro");
		Profesor profesor2 = new Profesor();
		profesor2.setNombre("Domiciano Rincón");
		Profesor profesor3 = new Profesor();
		profesor3.setNombre("Carlos Aponte");
		Profesor profesor4 = new Profesor();
		profesor4.setNombre("Gabriel Tamura");
		
		Materia materia1 = new Materia();
		materia1.setNombre("Señales y sistemas");
		Materia materia2 = new Materia();
		materia2.setNombre("Programación en red");
		Materia materia3 = new Materia();
		materia3.setNombre("Termodinámica");
		Materia materia4 = new Materia();
		materia4.setNombre("VIP");
		
		Carrera telematica = new Carrera();
		telematica.setNombre("Ingeniería telemática");
		
		Universidad icesi = new Universidad();
		icesi.setNombre("Universidad Icesi");
		
		ArrayList<Carrera> carreras = new ArrayList<>();
		carreras.add(telematica);
		icesi.setCarreras(carreras);
		
		ArrayList<Materia> materias = new ArrayList<>();
		materias.add(materia1);
		materias.add(materia2);
		materias.add(materia3);
		materias.add(materia4);
		telematica.setMaterias(materias);
		
		materia1.setProfesor(profesor1);
		materia2.setProfesor(profesor2);
		materia3.setProfesor(profesor3);
		materia4.setProfesor(profesor4);
		
		ArrayList<Estudiante> lista1 = new ArrayList<>();
		lista1.add(estudiante1);
		lista1.add(estudiante2);
		ArrayList<Estudiante> lista2 = new ArrayList<>();
		lista2.add(estudiante3);
		lista2.add(estudiante4);
		ArrayList<Estudiante> lista3 = new ArrayList<>();
		lista3.add(estudiante5);
		lista3.add(estudiante6);
		ArrayList<Estudiante> lista4 = new ArrayList<>();
		lista4.add(estudiante7);
		lista4.add(estudiante8);
		
		materia1.setEstudiantes(lista1);
		materia2.setEstudiantes(lista2);
		materia3.setEstudiantes(lista3);
		materia4.setEstudiantes(lista4);
		
		Gson gson = new Gson();
		String json = gson.toJson(icesi);
		System.out.println(json);
		
		Universidad icesiCopia = gson.fromJson(json, Universidad.class);
		System.out.println(icesiCopia.getCarreras().get(0).getMaterias().get(0).getEstudiantes().get(0).getNombre());

		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
