package server.communication;

public class UserModel {
	public String type = "UserModel";
	private String id;
	private String username;
	
	public UserModel() {}
	
	public UserModel(String id, String username) {
		this.id = id;
		this.username = username;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
