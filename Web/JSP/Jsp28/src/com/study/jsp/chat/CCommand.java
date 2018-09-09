package com.study.jsp.chat;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
