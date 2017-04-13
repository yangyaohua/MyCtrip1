package com.yyh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyh.dao.TicketDao;
import com.yyh.util.Constants;

public abstract class BaseServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost");  
		resp.addHeader("Access-Control-Allow-Methods",  
	            "GET, POST, PUT, DELETE, OPTIONS");  
		resp.addHeader("Access-Control-Allow-Headers",  
	            "origin, content-type, accept, x-requested-with, sid, mycustom, smuser"); 
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.addHeader("Connection", "keep-alive");
		if (req != null) {
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for(Cookie cookie : cookies){
					cookie.setHttpOnly(false);
					cookie.setSecure(false);
					
					
					String name = cookie.getName();
					if (name.equals("JSESSIONID")) {
						String jsessionid = cookie.getValue();
						Cookie jsessionidCookie = new Cookie("JSESSIONID", jsessionid);
						jsessionidCookie.setHttpOnly(false);
						resp.addCookie(jsessionidCookie);
					}
					
				}
			}
		}
		dispatchTask(req,resp);
		
	}

	protected abstract void dispatchTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException ;
}
