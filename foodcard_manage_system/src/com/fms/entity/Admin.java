package com.fms.entity;

public class Admin {
	private String username;
	private String password;
	private String phone;
	
	public Admin() {
		super();
	}
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + ", phone=" + phone + "]";
	}	
}
