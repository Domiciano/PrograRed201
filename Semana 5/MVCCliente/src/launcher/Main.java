package launcher;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ConnectionWindow;

public class Main extends Application{

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ConnectionWindow connectionWindow = new ConnectionWindow();
		connectionWindow.show();
	}

}
