package model;

public class User {

	private String user_id;
	private String username;
	private String emailId;
	private int contactNo;
	
	
	
	public User(String user_id, String username, String emailId, int contactNo) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.emailId = emailId;
		this.contactNo = contactNo;
	}
	public String getuser_id() {
		return user_id;
	}
	public void setuser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	
	
	
	
}