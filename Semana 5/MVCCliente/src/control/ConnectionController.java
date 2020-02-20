package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.ConnectionWindow;

public class ConnectionController {
	private ConnectionWindow view;
	
	public ConnectionController(ConnectionWindow view) {
		this.view = view;
		initView();
	}
	
	public void initView() {
		view.getBtnConnect().setOnAction(
				(e)->{
					String stringIp = view.getTfIpAddress().getText();
					String stringPort = view.getTfPort().getText();
					System.out.println(stringIp +":"+stringPort);
				}
		);
	}
	
}
