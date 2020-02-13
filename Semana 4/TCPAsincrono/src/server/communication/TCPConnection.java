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

import server.communication.Emitter;
import server.communication.Receiver;
import server.main.Server;

public class TCPConnection {
	
	private static TCPConnection instance;
	
	private int puerto;
	private Socket socket;
	
	private Server server;
	private Receiver receiver;
	private Emitter emitter;
	
	public static synchronized TCPConnection getInstance() {
		if(instance == null) {
			instance = new TCPConnection();
		}
		return instance;
	}
	
	private TCPConnection() {
		
	}
	
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	public void waitForConnection() {
		try {
			ServerSocket server = new ServerSocket(this.puerto);
			System.out.println("Esperando...");
			this.socket = server.accept();
			System.out.println("Conexión establecida");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initReceiver() {
		receiver = new Receiver(socket);
		receiver.setObserver(server);
	}
	
	
	
	public void initSender() {
		emitter = new Emitter(socket);
	}
	
	public void setObserver(Server server) {
		this.server = server;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public Emitter getEmitter() {
		return emitter;
	}

	public void close() {
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
