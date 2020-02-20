package control;

import javax.swing.JOptionPane;

import commucation.OnConnectionListener;
import commucation.TCPConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.ChatWindow;
import view.ConnectionWindow;

public class ConnectionController implements OnConnectionListener{
	
	private ConnectionWindow view;
	private TCPConnection connection;
	
	public ConnectionController(ConnectionWindow view) {
		this.view = view;
		connection = TCPConnection.getInstance();
		connection.setListener(this);
		initView();
	}
	
	public void initView() {
		view.getBtnConnect().setOnAction(
				(e)->{
					String stringIp = view.getTfIpAddress().getText();
					String stringPort = view.getTfPort().getText();
					int port = Integer.parseInt(stringPort);
					connection.setServerIp(stringIp);
					connection.setPuerto(port);
					connection.requestConnection();
				}
		);
	}

	
	//Se esta llamando en un hilo diferente al GUI
	@Override
	public void onConnection(String code) {
		Platform.runLater(
				()->{
					switch(code) {
					case "OK":
						connection.initReceiver();
						connection.initSender();
						connection.getReceiver().receiveMessage();
						
						ChatWindow chat = new ChatWindow();
						chat.show();
						view.close();
						break;
					case "ERROR":
						view.getLabelInstructions().setText("No se pudo hacer la conexión");
						break;
					}
				}
		);
	}
	
}
