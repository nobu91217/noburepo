package jp.nobu.domain;

public class User {
	
	private String userId;
	private String userName;
	
	
	public User(){}
	
	public User(String userId,String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("userId:").append(userId).append(",userName:").append(userName);
		return b.toString();
	}

}
