package com.yyh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yyh.dao.UserDao;
import com.yyh.util.Constants;

public class LoginServlet extends BaseServlet {
	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");

		HttpSession session = req.getSession();
		session.setAttribute(Constants.USER_ID, userId);
		System.out.println("LoginServlet JSESSIONID = " + session.getId());
		
		PrintWriter writer = resp.getWriter();
		boolean login = UserDao.login(userId, password);
		
		if (login) {
			Cookie userIdCookie = new Cookie("uid", userId);
			userIdCookie.setMaxAge(Integer.MAX_VALUE);
			resp.addCookie(userIdCookie);
		}
		writer.write(String.valueOf(login));
		writer.flush();
		writer.close();		
	}
}
