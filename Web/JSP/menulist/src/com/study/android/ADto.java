package com.study.android;

import java.sql.Timestamp;

public class ADto {
	private String code;
	private String menu;
	private String price;
	private String clientno;
	private String status;
	private Timestamp date;
	
	public ADto(String code, String menu, String price, String clientno, String status, Timestamp date) {
		
		this.code = code;
		this.menu = menu;
		this.price = price;
		this.clientno = clientno;
		this.status = status;
		this.date = date;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getClientno() {
		return clientno;
	}
	public void setClientno(String clientno) {
		this.clientno = clientno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}
