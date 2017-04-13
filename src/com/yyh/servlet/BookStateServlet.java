package com.yyh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yyh.dao.TicketDao;
import com.yyh.util.Constants;

public class BookStateServlet extends BaseServlet {

	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("BookStateServlet JSESSIONID = " + req.getSession().getId());
		req.getSession().setAttribute(Constants.CURRENT_TASK, "1");

		String tid = req.getParameter("tid");
		HttpSession session = req.getSession();
		session.setAttribute(Constants.TID, tid);
		String tidState = (String) session.getAttribute(Constants.TID);
		boolean flag = (tidState != null && !tid.equals(""));
		PrintWriter writer = resp.getWriter();
		writer.write(String.valueOf(flag));
		writer.flush();
		writer.close();		
	}
}
