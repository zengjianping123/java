package com.fms.entity;

public class FoodShop {
	private int dnumber;
	private int snumber;
	private int wnumber;
	public FoodShop() {
		super();
	}
	public FoodShop(int dnumber, int snumber, int wnumber) {
		super();
		this.dnumber = dnumber;
		this.snumber = snumber;
		this.wnumber = wnumber;
	}
	public int getDnumber() {
		return dnumber;
	}
	public void setDnumber(int dnumber) {
		this.dnumber = dnumber;
	}
	public int getSnumber() {
		return snumber;
	}
	public void setSnumber(int snumber) {
		this.snumber = snumber;
	}
	public int getWnumber() {
		return wnumber;
	}
	public void setWnumber(int wnumber) {
		this.wnumber = wnumber;
	}
	@Override
	public String toString() {
		return "FoodShop [dnumber=" + dnumber + ", snumber=" + snumber + ", wnumber=" + wnumber + "]";
	}	
}
