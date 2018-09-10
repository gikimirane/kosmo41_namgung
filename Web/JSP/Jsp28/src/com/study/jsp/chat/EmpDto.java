package com.study.jsp.chat;

public class EmpDto {
	String name;
	int room;
	String status;
	int invi_num;
	
	public EmpDto(String name, int room, String status, int invi_num) {
		this.name = name;
		this.room = room;
		this.status = status;
		this.invi_num = invi_num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getInvi_num() {
		return invi_num;
	}
	public void setInvi_num(int invi_num) {
		this.invi_num = invi_num;
	}
	
}
