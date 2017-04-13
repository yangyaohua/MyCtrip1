package com.yyh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.yyh.dao.UserDao;

public class ChangePasswordServlet extends BaseServlet {

	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String username = (String) req.getSession().getAttribute("username");//req.getParameter("username");
		String oldpassword = req.getParameter("old");
		String newpassword = req.getParameter("new");
		
		PrintWriter writer = resp.getWriter();
		boolean result = UserDao.changePassword(username, oldpassword,newpassword);
		writer.write(String.valueOf(result));
		writer.flush();
		writer.close();		
	}

}
