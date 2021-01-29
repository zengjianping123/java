package com.fms.entity;

public class Consumer {
	private int number;
	private String username;
	private String sex;
	private boolean role;
	public Consumer() {
		super();
	}
	
	public Consumer(int number, String username, String sex, boolean role) {
		super();
		this.number = number;
		this.username = username;
		this.sex = sex;
		this.role = role;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public boolean isRole() {
		return role;
	}
	public void setRole(boolean role) {
		this.role = role;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Consumer [number=" + number + ", username=" + username + ", sex=" + sex + ", role=" + role + "]";
	}

	
}
