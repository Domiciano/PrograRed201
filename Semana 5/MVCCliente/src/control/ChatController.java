package control;

import commucation.OnMessageListener;
import commucation.TCPConnection;
import view.ChatWindow;

public class ChatController implements OnMessageListener{
	
	private ChatWindow view;
	private TCPConnection connection;
	
	public ChatController(ChatWindow view) {
		this.view = view;
		connection = TCPConnection.getInstance();
		initView();
	}

	private void initView() {
		
	}

	@Override
	public void onMessageReceived(String msg) {
		// TODO Auto-generated method stub
	}
	
}
