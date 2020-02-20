package client.main;

import java.util.Calendar;
import java.util.Scanner;
import java.util.UUID;

import com.google.gson.Gson;

import client.communication.MessageModel;
import client.communication.OnMessageListener;
import client.communication.TCPConnection;
import client.communication.UserModel;

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
			Gson gson = new Gson();
			switch(line) {
				case "message":
					MessageModel msg = new MessageModel(
							"Este es un mensaje de prueba", 
							Calendar.getInstance().getTime().getTime()
					);					
					String jsonMsg = gson.toJson(msg);
					conexion.getEmitter().sendMessage(jsonMsg);
					break;
				case "user":
					UserModel user= new UserModel(
							UUID.randomUUID().toString(), 
							"domi0620"
					);
					String jsonUser = gson.toJson(user);
					conexion.getEmitter().sendMessage(jsonUser);
					break;
			}
			
			
		}
		
	}

	@Override
	public void onMessageReceived(String msg) {
		System.out.println(msg);
		
	}

}
