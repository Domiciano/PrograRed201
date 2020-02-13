package server.communication;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Emitter {
	
	private Socket socket;
	private BufferedWriter writer;

	public Emitter(Socket socket) {
		this.socket = socket;
		init();
	}
	
	private void init() {
		try {
			OutputStream os = this.socket.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String msg) {
		new Thread(
				()->{
					try {
						writer.write(msg+"\n");
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		).start();		
	}

}
