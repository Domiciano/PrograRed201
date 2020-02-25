package commucation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import control.ChatController;

public class TCPConnection {
	
	private OnConnectionListener listener;
	
	private static TCPConnection instance;
	
	private int puerto;
	private String serverIp;
	private Socket socket;
	
	
	private Receiver receiver;
	private Emitter emitter;
	
	public static synchronized TCPConnection getInstance() {
		if(instance == null) {
			instance = new TCPConnection();
		}
		return instance;
	}
	
	public void setListener(OnConnectionListener listener) {
		this.listener = listener;
	}
	
	private TCPConnection() {}
	
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	
	public void requestConnection() {
		new Thread(
				()->{
					try {
						this.socket = new Socket(serverIp, puerto);
						System.out.println("Conexi�n establecida");
						if(listener != null) listener.onConnection("OK");
					} catch (IOException e) {
						System.out.println(e.getMessage());
						if(listener != null) listener.onConnection("ERROR");
					}
				}
		).start();
	}
	
	
	
	public void initReceiver() {
		receiver = new Receiver(socket);
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

	public Receiver getReceiver() {
		return receiver;
	}

	public Emitter getEmitter() {
		return emitter;
	}

	public void setReceiverListener(OnMessageListener listener) {
		receiver.setObserver(listener);
	}
	
	

}
