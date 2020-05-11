package db;

import entities.Estudiante;
import entities.Materia;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class IcesiDatabase {


    private Connection connection;
    private Statement statement;
    private boolean busy = false;

    public IcesiDatabase(){
        //Tenemos que indicar en tiempo de ejecución cuál es nuestro driver
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_11", "P09728_1_11", "ZCSaQGZU");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBusy(boolean busy){
        this.busy = busy;
    }

    public boolean isBusy() {
        return busy;
    }

    public void insertEstudiante(Estudiante estudiante) {
        String sql = "INSERT INTO estudiantes(id, nombre, codigo) VALUES ('%ID%','%NOMBRE%','%CODIGO%')";
        sql = sql.replace("%ID%",estudiante.getId());
        sql = sql.replace("%NOMBRE%",estudiante.getNombre());
        sql = sql.replace("%CODIGO%",estudiante.getCodigo());
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Estudiante> getAllEstudiantes() {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM estudiantes");
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String nombre = resultSet.getString(2);
                String codigo = resultSet.getString(3);
                Estudiante estudiante = new Estudiante(id,nombre,codigo);
                estudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudiantes;
    }

    public Estudiante getEstudianteByID(String id) {
        String sql = "SELECT * FROM estudiantes WHERE id='%ID%'";
        sql = sql.replace("%ID%", id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String estid = resultSet.getString(1);
                String nombre = resultSet.getString(2);
                String codigo = resultSet.getString(3);
                Estudiante estudiante = new Estudiante(estid, nombre, codigo);
                return estudiante;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Estudiante estudiante = new Estudiante("NO_ID", "NO_NAME", "NO_CODE");
        return estudiante;

    }

    public void insertMateria(Materia materia) {
        String sql = "INSERT INTO materias(id, nombre, NRC) VALUES ('%ID%','%NOMBRE%','%NRC%')";
        sql = sql.replace("%ID%",materia.getId());
        sql = sql.replace("%NOMBRE%",materia.getNombre());
        sql = sql.replace("%NRC%",materia.getNRC());
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Materia> getAllMaterias() {
        ArrayList<Materia> materias = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM materias");
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String nombre = resultSet.getString(2);
                String nrc = resultSet.getString(3);
                Materia materia = new Materia(id,nombre,nrc);
                materias.add(materia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materias;
    }

    public Materia getMateriaByID(String id) {
        String sql = "SELECT * FROM materias WHERE id='%ID%'";
        sql = sql.replace("%ID%", id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String estid = resultSet.getString(1);
                String nombre = resultSet.getString(2);
                String nrc = resultSet.getString(3);
                Materia materia = new Materia(estid, nombre, nrc);
                return materia;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Materia materia = new Materia("NO_ID", "NO_NAME", "NO_NRC");
        return materia;

    }

    public void modifyMateria(Materia materia) {
        String sql = "UPDATE materias SET nombre='%NOMBRE%', NRC='%NRC%' WHERE id='%ID%'";
        sql = sql.replace("%NOMBRE%",materia.getNombre());
        sql = sql.replace("%NRC%",materia.getNRC());
        sql = sql.replace("%ID%",materia.getId());
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMateriaByID(String id) {
        String sql = "DELETE FROM materias WHERE id='%ID%'";
        sql = sql.replace("%ID%", id);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void registerMateriaEstudiante(String materiaID, String estudianteID) {
        String sql = "INSERT INTO estudiantes_materias(id, estudianteID, materiaID) VALUES ('%ID%','%EST%','%MAT%')";
        sql = sql.replace("%ID%", UUID.randomUUID().toString());
        sql = sql.replace("%EST%", estudianteID);
        sql = sql.replace("%MAT%", materiaID);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Materia getCompleteMateriaByID(String materiaID) {
        //Adquirir la materia
        Materia materia = getMateriaByID(materiaID);
        //Aquirir la lista de estudiantes por materia
        ArrayList<Estudiante> estudiantes = getAllEstudiantesByMateria(materiaID);
        materia.setEstudiantes(estudiantes);
        return materia;
    }

    private ArrayList<Estudiante> getAllEstudiantesByMateria(String materiaID) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        String sql = "SELECT estudiantes.id, estudiantes.nombre, estudiantes.codigo " +
                "FROM estudiantes " +
                "INNER JOIN estudiantes_materias " +
                "ON estudiantes_materias.estudianteID = estudiantes.id " +
                "INNER JOIN materias " +
                "ON materias.id = estudiantes_materias.materiaID " +
                "WHERE materias.id = '%MATID%'";
        sql = sql.replace("%MATID%", materiaID);
        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                String id = resultSet.getString(1);
                String nombre = resultSet.getString(2);
                String codigo = resultSet.getString(3);
                Estudiante estudiante = new Estudiante(id, nombre, codigo);
                estudiantes.add(estudiante);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudiantes;
    }

    public ArrayList<Materia> getMateriasOfEstudiente(String estid) {
        ArrayList<Materia> materias = new ArrayList<>();

        String sql = "SELECT materias.id, materias.nombre, materias.NRC " +
                "FROM estudiantes " +
                "INNER JOIN estudiantes_materias " +
                "ON estudiantes_materias.estudianteID = estudiantes.id " +
                "INNER JOIN materias " +
                "ON materias.id = estudiantes_materias.materiaID " +
                "WHERE estudiantes.id = '%ESTID%'";
        sql = sql.replace("%ESTID%", estid);
        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                String id = resultSet.getString(1);
                String nombre = resultSet.getString(2);
                String NRC = resultSet.getString(3);
                Materia materia = new Materia(id, nombre, NRC);
                materias.add(materia);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materias;
    }
}
