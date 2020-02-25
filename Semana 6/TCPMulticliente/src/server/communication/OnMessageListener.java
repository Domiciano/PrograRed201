package server.communication;

public interface OnMessageListener{
	void onMessageReceived(String id, String msg);
}
