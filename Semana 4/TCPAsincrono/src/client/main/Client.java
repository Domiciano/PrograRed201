package client.main;

import java.util.Scanner;

import client.communication.OnMessageListener;
import client.communication.TCPConnection;

public class Client implements OnMessageListener{

	public static void main(String[] args) {
		Client c = new Client();
	}
	
	TCPConnection conexion;
	
	public Client() {
		conexion = TCPConnection.getInstance();
		conexion.setPuerto(5000);
		conexion.setServerIp("127.0.0.1");
		conexion.setObserver(this);
		conexion.requestConnection();
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
