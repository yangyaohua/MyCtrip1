package com.yyh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yyh.util.Constants;

public class IsLoginServlet extends BaseServlet {
	
	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String user_Id = (String) session.getAttribute(Constants.USER_ID);
		
		boolean flag = !(user_Id == null || user_Id.equals(""));
		System.out.println("IsLoginServlet JSESSIONID = " + session.getId() + "login_state = " + flag);
		PrintWriter writer = resp.getWriter();
		writer.write(String.valueOf(flag));
		writer.flush();
		writer.close();		
	}
}
