package server.main;

import java.util.Calendar;
import java.util.Scanner;

import com.google.gson.Gson;

import server.communication.GenericModel;
import server.communication.MessageModel;
import server.communication.OnMessageListener;
import server.communication.TCPConnection;
import server.communication.UserModel;

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
		Gson g = new Gson();
		GenericModel model = g.fromJson(msg.trim(), GenericModel.class);
		System.out.println(model.getType());
		
		switch (model.getType()) {
			case "UserModel":
				UserModel user = g.fromJson(msg.trim(), UserModel.class);
				System.out.println(user.getUsername());
				break;
			case "MessageModel":
				MessageModel message = g.fromJson(msg.trim(), MessageModel.class);
				System.out.println(message.getBody());
				break;
		}
	}

}
