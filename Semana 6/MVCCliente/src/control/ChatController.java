package control;

import commucation.OnMessageListener;
import commucation.TCPConnection;
import javafx.application.Platform;
import view.ChatWindow;

public class ChatController implements OnMessageListener{
	
	private ChatWindow view;
	private TCPConnection connection;
	
	public ChatController(ChatWindow view) {
		this.view = view;
		connection = TCPConnection.getInstance();
		
		connection.initReceiver();
		connection.initSender();
		connection.setReceiverListener(this);
		connection.getReceiver().receiveMessage();
		
		initView();
	}

	private void initView() {
		view.getBtnSend().setOnAction(
				(event)->{
					String txt = view.getTfMessage().getText();
					connection.getEmitter().sendMessage(txt);
				}
		);
	}

	@Override
	public void onMessageReceived(String msg) {
		Platform.runLater(
				()->{
					view.getTaMessages().appendText(msg+"\n");
					System.out.println("ChatController>"+msg);
				}
		);
	}
	
}
