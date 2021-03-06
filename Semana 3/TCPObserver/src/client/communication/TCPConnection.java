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

public class TCPConnection {
	
	private int puerto;
	private String serverIp;
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public TCPConnection(String serverIp, int puerto) {
		this.serverIp = serverIp;
		this.puerto = puerto;
	}
	
	public void requestConnection() {
		try {
			this.socket = new Socket(serverIp, puerto);
			System.out.println("Conexi�n establecida");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initReceiver() {
		try {
			InputStream is = this.socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initSender() {
		try {
			OutputStream os = this.socket.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receiveMessage() {
		try {
			String line = reader.readLine();
			observer.onMessageReceived(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String msg) {
		try {
			writer.write(msg+"\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private OnMessageListener observer;
	
	public void setObserver(OnMessageListener observer) {
		this.observer = observer;
	}
	
	public interface OnMessageListener{
		void onMessageReceived(String msg);
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
