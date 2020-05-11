package config;

import db.ConnectionPool;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

    //Control + O


    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> res = new HashSet<>();
        res.add(services.EchoService.class);
        res.add(services.EstudianteServices.class);
        res.add(services.MateriaServices.class);
        return res;
    }
}
