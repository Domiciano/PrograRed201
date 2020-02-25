package server.communication;

import java.io.IOException;
import java.net.Socket;

public class Connection {
	
	private String id;
	private Socket socket;
	private Receiver receiver;
	private Emitter emitter;
	private OnMessageListener listener;
	
	public Connection(Socket socket) {
		this.socket = socket;
	}
	
	public void setListener(OnMessageListener listener) {
		this.listener = listener;
	}
	
	public void initReceiver() {
		receiver = new Receiver(this);
		receiver.setObserver(listener);
		receiver.receiveMessage();
	}
	
	public void initSender() {
		emitter = new Emitter(socket);
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

	public void setId(String id) {
		this.id = id;
	}

	public Socket getSocket() {
		return this.socket;
	}
	
	public String getId() {
		return this.id;
	}

}
