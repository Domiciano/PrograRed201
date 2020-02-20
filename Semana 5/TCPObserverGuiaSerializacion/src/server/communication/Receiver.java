package server.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver {
	private Socket socket;
	private BufferedReader reader;

	public Receiver(Socket socket) {
		this.socket = socket;
		init();
	}
	
	private void init() {
		try {
			InputStream is = this.socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void receiveMessage() {
		new Thread(
				()->{
					while(true) {
						try {
							String line = reader.readLine();
							observer.onMessageReceived(line);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
		).start();
	}
	
	//Patrón Observer
	private OnMessageListener observer;
	
	public void setObserver(OnMessageListener observer) {
		this.observer = observer;
	}
	
}
