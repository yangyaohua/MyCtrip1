package com.yyh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yyh.dao.UserDao;
import com.yyh.util.Constants;

public class SessionIdServlet extends BaseServlet {

	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ServletContext application = getServletContext();
		String pcID = (String) application.getAttribute(Constants.PC);
		String androidID = (String) application.getAttribute(Constants.ANDROID);
		
		String result = "pc="+pcID+";android="+androidID;
		
		PrintWriter writer = resp.getWriter();
		writer.write(result);
		writer.flush();
		writer.close();
	}
}
