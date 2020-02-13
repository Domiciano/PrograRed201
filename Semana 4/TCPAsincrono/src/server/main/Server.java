package server.main;

import java.util.Calendar;
import java.util.Scanner;

import server.communication.OnMessageListener;
import server.communication.TCPConnection;

public class Server implements OnMessageListener{

	public static void main(String[] args) {
		Server server = new Server();
	}

	TCPConnection conexion;
	
	public Server() {
		conexion = TCPConnection.getInstance();
		conexion.setPuerto(5000);
		conexion.setObserver(this);
		conexion.waitForConnection();
		conexion.initReceiver();
		conexion.initSender();
		conexion.getReceiver().receiveMessage();
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String line = scanner.nextLine();
			conexion.getEmitter().sendMessage(line);
		}
	}

	@Override
	public void onMessageReceived(String msg) {
		System.out.println(msg);
	}

}
