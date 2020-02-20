package server.communication;

public class MessageModel {

	public String type = "MessageModel";
	private String body;
	private long date;

	public MessageModel() {}
	
	public MessageModel(String body, long date) {
		this.body = body;
		this.date = date;
	}
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	
	
	
	
}
