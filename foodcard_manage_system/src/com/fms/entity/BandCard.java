package com.fms.entity;

public class BandCard {
	private int id;
	private int number;
	private String username;
	private int pwd;
	private int remaning;
	public BandCard() {
		super();
	}
	public BandCard(int id, int number, String username, int pwd, int remaning) {
		super();
		this.id = id;
		this.number = number;
		this.username = username;
		this.pwd = pwd;
		this.remaning = remaning;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPwd() {
		return pwd;
	}
	public void setPwd(int pwd) {
		this.pwd = pwd;
	}
	public int getRemaning() {
		return remaning;
	}
	public void setRemaning(int remaning) {
		this.remaning = remaning;
	}
	@Override
	public String toString() {
		return "BandCard [id=" + id + ", number=" + number + ", username=" + username + ", pwd=" + pwd + ", remaning="
				+ remaning + "]";
	}
	
}
