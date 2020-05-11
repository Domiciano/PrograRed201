package services;

import db.ConnectionPool;
import db.IcesiDatabase;
import entities.Estudiante;
import entities.Materia;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;

@Stateless
@Path("materias")
public class MateriaServices {

    @Path("insert")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Materia insertMateria(Materia materia){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        icesiDatabase.setBusy(true);
        icesiDatabase.insertMateria(materia);
        icesiDatabase.setBusy(false);
        return materia;
    }

    //Obtener lista de estudiantes
    @GET
    @Path("getall")
    @Produces("application/json")
    public ArrayList<Materia> getAllMaterias(){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        icesiDatabase.setBusy(true);
        ArrayList<Materia> materias = icesiDatabase.getAllMaterias();
        icesiDatabase.setBusy(false);
        return materias;
    }


    //Obtener un elemento por id
    @GET
    @Path("byid")
    @Produces("application/json")
    public Materia getEstudianteByID(@QueryParam("id") String id){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        icesiDatabase.setBusy(true);
        Materia materia = icesiDatabase.getMateriaByID(id);
        icesiDatabase.setBusy(false);
        return materia;
    }

    //CRUD: Create, Read, Update, Delete

    @PUT
    @Path("update")
    @Consumes("application/json")
    @Produces("application/json")
    public Materia modifyMateria(Materia materia){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        icesiDatabase.setBusy(true);
        icesiDatabase.modifyMateria(materia);
        icesiDatabase.setBusy(false);
        return materia;
    }

    @DELETE
    @Path("delete/{id}")
    public void deleteMateria(@PathParam("id") String id){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        icesiDatabase.setBusy(true);
        icesiDatabase.deleteMateriaByID(id);
        icesiDatabase.setBusy(false);
    }

    @POST
    @Path("register/{materiaID}/{estudianteID}")
    public void registerMateria(@PathParam("materiaID") String materiaID, @PathParam("estudianteID") String estudianteID){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        icesiDatabase.setBusy(true);
        icesiDatabase.registerMateriaEstudiante(materiaID,estudianteID);
        icesiDatabase.setBusy(false);
    }

    @GET
    @Path("list/{materiaID}")
    @Produces("application/json")
    public Materia getListOfEstudiantes(@PathParam("materiaID") String materiaID){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        icesiDatabase.setBusy(true);
        Materia materia = icesiDatabase.getCompleteMateriaByID(materiaID);
        icesiDatabase.setBusy(true);
        return materia;
    }

}
