package client.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import client.main.Client;

public class TCPConnection {
	
	private static TCPConnection instance;
	
	private int puerto;
	private String serverIp;
	private Socket socket;
	
	private Client client;
	private Receiver receiver;
	private Emitter emitter;
	
	public static synchronized TCPConnection getInstance() {
		if(instance == null) {
			instance = new TCPConnection();
		}
		return instance;
	}
	
	private TCPConnection() {}
	
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	
	public void requestConnection() {
		try {
			this.socket = new Socket(serverIp, puerto);
			System.out.println("Conexión establecida");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void initReceiver() {
		receiver = new Receiver(socket);
		receiver.setObserver(client);
	}
	
	
	
	public void initSender() {
		emitter = new Emitter(socket);
	}


	public void close() {
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setObserver(Client client) {
		this.client = client;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public Emitter getEmitter() {
		return emitter;
	}
	
	

}
