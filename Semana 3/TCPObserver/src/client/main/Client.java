package client.main;

import java.util.Scanner;

import client.communication.TCPConnection;

public class Client implements TCPConnection.OnMessageListener{

	public static void main(String[] args) {
		Client c = new Client();
	}
	
	TCPConnection conexion;
	
	public Client() {
		conexion = new TCPConnection("127.0.0.1", 5000);
		conexion.setObserver(this);
		conexion.requestConnection();
		conexion.initReceiver();
		conexion.initSender();
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String line = scanner.nextLine();
			conexion.sendMessage(line);
			conexion.receiveMessage();
			if(line.equals(":DIS:")) {
				conexion.close();
				break;
			}
		}
		
	}

	@Override
	public void onMessageReceived(String msg) {
		System.out.println(msg);
		
	}

}
