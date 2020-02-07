package client.main;

import java.util.Scanner;

import client.communication.TCPConnection;

public class Client implements TCPConnection.OnMessageListener{

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
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String line = scanner.nextLine();
			if(line.equals(":DIS:")) {
				conexion.close();
				break;
			}else if(line.equals("RTT")) {
				
				byte[] buff = new byte[1024];
				for(int i=0 ; i<buff.length ; i++) {
					buff[i] = 65;
				}
				String palabra1024 = new String(buff);
				System.out.println(palabra1024);
				conexion.sendMessage(palabra1024);
				conexion.receiveMessage();
				
			}
		}
		
	}

	@Override
	public void onMessageReceived(String msg) {
		System.out.println(msg);
		
	}

}
