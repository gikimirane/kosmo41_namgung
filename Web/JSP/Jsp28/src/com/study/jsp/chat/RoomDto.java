package com.study.jsp.chat;

public class RoomDto {
	String rno;
	String user_limit;
	String open_type;
	String pwd;
	String room_owner;
	
	public RoomDto(String rno, String user_limit, String open_type, String pwd, String room_owner) {
		this.rno = rno;
		this.user_limit = user_limit;
		this.open_type = open_type;
		this.pwd = pwd;
		this.room_owner = room_owner;
	}
	
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public String getUser_limit() {
		return user_limit;
	}
	public void setUser_limit(String user_limit) {
		this.user_limit = user_limit;
	}
	public String getOpen_type() {
		return open_type;
	}
	public void setOpen_type(String open_type) {
		this.open_type = open_type;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRoom_owner() {
		return room_owner;
	}
	public void setRoom_owner(String room_owner) {
		this.room_owner = room_owner;
	}
}
