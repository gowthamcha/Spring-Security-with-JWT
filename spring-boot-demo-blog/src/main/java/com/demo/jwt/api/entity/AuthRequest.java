package com.demo.jwt.api.entity;

/**
 * 
 * AuthRequest class is used for authentication Purpose
 * 
 * @author Gowtham
 *
 */
public class AuthRequest {

    private String userName;
    private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AuthRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public AuthRequest() {
		super();
	}
    
}
