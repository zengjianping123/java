package com.fms.entity;

public class CardInfo {
	private int idI;
	private String datetime;
	private int op;
	private int remaingI;
	public CardInfo() {
		super();
	}
	public CardInfo(int idI, String datetime, int op, int remaingI) {
		super();
		this.idI = idI;
		this.datetime = datetime;
		this.op = op;
		this.remaingI = remaingI;
	}
	public int getIdI() {
		return idI;
	}
	public void setIdI(int idI) {
		this.idI = idI;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public int getOp() {
		return op;
	}
	public void setOp(int op) {
		this.op = op;
	}
	public int getRemaingI() {
		return remaingI;
	}
	public void setRemaingI(int remaingI) {
		this.remaingI = remaingI;
	}
	@Override
	public String toString() {
		return "CardInfo [idI=" + idI + ", datetime=" + datetime + ", op=" + op + ", remaingI=" + remaingI + "]";
	}
	
}
