package com.yyh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends BaseServlet {

	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String jsessionid = req.getSession().getId();
		//Cookie jsessionidCookie = new Cookie("JSESSIONID", jsessionid);
		System.out.println("jsessionid=" + jsessionid);
		//resp.addCookie(jsessionidCookie);
	}

}
