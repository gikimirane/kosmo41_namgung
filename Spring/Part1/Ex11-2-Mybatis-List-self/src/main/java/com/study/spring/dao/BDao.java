package com.study.spring.dao;

import java.util.ArrayList;

import com.study.spring.dto.BDto;

public interface BDao {
	public ArrayList<BDto> listDao();
	public ArrayList<BDto> contentview(String bId);
	
}
