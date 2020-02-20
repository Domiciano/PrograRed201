package view;

import control.ConnectionController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConnectionWindow extends Stage{
	
	private Scene scene;
	private Label labelInstructions;
	private TextField tfIpAddress;
	private TextField tfPort;
	private Button btnConnect; 
	private ConnectionController controller;
	
	public ConnectionWindow() {
		btnConnect = new Button("Conectar");
		tfPort = new TextField("5000");
		tfIpAddress = new TextField("127.0.0.1");
		labelInstructions = new Label("Por favor ingrese su dirección IP y el puerto para "
				+ "establece la conexión tipo TCP");
		
		VBox vBox = new VBox();
		vBox.getChildren().add(labelInstructions);
		vBox.getChildren().add(tfIpAddress);
		vBox.getChildren().add(tfPort);
		vBox.getChildren().add(btnConnect);
		
		scene = new Scene(vBox, 400, 400);
		this.setScene(scene);
		
		controller = new ConnectionController(this);
	}

	public Button getBtnConnect() {
		return btnConnect;
	}

	public TextField getTfIpAddress() {
		return tfIpAddress;
	}

	public TextField getTfPort() {
		return tfPort;
	}
	
	

}
