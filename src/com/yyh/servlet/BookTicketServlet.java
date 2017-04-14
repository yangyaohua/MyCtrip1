package com.yyh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyh.bean.Ticket;
import com.yyh.dao.TicketDao;
import com.yyh.util.Constants;
import com.yyh.util.Convert2Json;

public class BookTicketServlet extends BaseServlet {

	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String cnumber = (String) req.getSession().getAttribute(Constants.USER_ID);
		String tid = (String) req.getSession().getAttribute(Constants.TID);
		System.out.println("BookTicketServlet JSESSIONID = " + req.getSession().getId() + "cnumber = " + cnumber + "tid = " + tid);

		boolean result = TicketDao.bookTicket(cnumber, tid);
		
		PrintWriter writer = resp.getWriter();
		writer.write(String.valueOf(result));
		writer.flush();
		writer.close();		
	}
}
