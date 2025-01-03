package com.kamesh.request;

public class LoginRequest {
	private String email;
	private String passWord;
	
	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LoginRequest(String email, String passWord) {
		super();
		this.email = email;
		this.passWord = passWord;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}
