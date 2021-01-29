package com.fms.entity;

public class Card {
	private int id;
	private int cNumber;
	private int remaing;
	private int pwd;
	private boolean flag;
	private int bandId;
	public Card(int id, int cNumber, int remaing, int pwd, boolean flag, int bandId) {
		super();
		this.id = id;
		this.cNumber = cNumber;
		this.remaing = remaing;
		this.pwd = pwd;
		this.flag = flag;
		this.bandId = bandId;
	}
	public Card() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getcNumber() {
		return cNumber;
	}
	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}
	public int getRemaing() {
		return remaing;
	}
	public void setRemaing(int remaing) {
		this.remaing = remaing;
	}
	public int getPwd() {
		return pwd;
	}
	public void setPwd(int pwd) {
		this.pwd = pwd;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int getBandId() {
		return bandId;
	}
	public void setBandId(int bandId) {
		this.bandId = bandId;
	}
	@Override
	public String toString() {
		return "Card [id=" + id + ", cNumber=" + cNumber + ", remaing=" + remaing + ", pwd=" + pwd + ", flag=" + flag
				+ ", bandId=" + bandId + "]";
	}
	
}
