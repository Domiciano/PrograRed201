package db;

import java.util.ArrayList;

public class ConnectionPool {

    public static ArrayList<IcesiDatabase> connections = new ArrayList<>();

    public static IcesiDatabase getAvailableConnection(){
        for(int i=0 ; i<connections.size() ; i++){
            if(!connections.get(i).isBusy()){
                return connections.get(i);
            }
        }
        IcesiDatabase instance = new IcesiDatabase();
        connections.add(instance);
        return instance;
    }

}
