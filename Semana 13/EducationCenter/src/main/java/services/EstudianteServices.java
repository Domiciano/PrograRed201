package services;


import db.ConnectionPool;
import db.IcesiDatabase;
import entities.Estudiante;
import entities.Materia;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;

@Stateless
@Path("estudiante")
public class EstudianteServices {

    @Path("insert")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Estudiante insertEstudiante(Estudiante estudiante){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        icesiDatabase.setBusy(true);
        icesiDatabase.insertEstudiante(estudiante);
        icesiDatabase.setBusy(false);
        return estudiante;
    }

    //Obtener lista de estudiantes
    @GET
    @Path("getall")
    @Produces("application/json")
    public ArrayList<Estudiante> getAllEstudiantes(){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        icesiDatabase.setBusy(true);
        ArrayList<Estudiante> estudiantes = icesiDatabase.getAllEstudiantes();
        icesiDatabase.setBusy(false);
        return estudiantes;
    }


    //Obtener un elemento por id
    @GET
    @Path("byid/{id}")
    @Produces("application/json")
    public Estudiante getEstudianteByID(@PathParam("id") String id){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        icesiDatabase.setBusy(true);
        Estudiante estudiante = icesiDatabase.getEstudianteByID(id);
        icesiDatabase.setBusy(false);
        return estudiante;
    }

    @GET
    @Path("list/{estid}")
    @Produces("application/json")
    public ArrayList<Materia> getMateriasListByEstudianteID(@PathParam("estid") String id){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        icesiDatabase.setBusy(true);
        ArrayList<Materia> materias = icesiDatabase.getMateriasOfEstudiente(id);
        icesiDatabase.setBusy(false);
        return materias;
    }

}
