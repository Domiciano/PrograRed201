package server.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

import server.communication.Emitter;
import server.communication.Receiver;
import server.main.Server;

public class TCPConnection {
	
	private static TCPConnection instance;
	
	private ServerSocket socketDispatcher;
	private int puerto;
	private Server server;
	private ArrayList<Connection> connections;
	
	public static synchronized TCPConnection getInstance() {
		if(instance == null) {
			instance = new TCPConnection();
		}
		return instance;
	}
	
	private TCPConnection() {
		connections = new ArrayList<>();
	}
	
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	public void waitForConnection() {
		new Thread(
				()->{
					try {
						socketDispatcher = new ServerSocket(this.puerto);
						
						while(true) {
							System.out.println("Esperando...");
							Socket socket = socketDispatcher.accept();
							System.out.println("Conexión establecida");
							Connection connection = new Connection(socket);
							connection.setId(UUID.randomUUID().toString());
							connections.add(connection);
							connection.setListener(server);
							connection.initSender();
							connection.initReceiver();
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		).start();
		
	}
	
	public void setObserver(Server server) {
		this.server = server;
	}

	public void sendBroadcast(String msg) {
		for(int i=0 ; i<connections.size() ; i++) {
			connections.get(i).getEmitter().sendMessage(msg);;
		}
	}

}
