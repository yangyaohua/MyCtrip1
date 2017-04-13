package com.yyh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyh.bean.Ticket;
import com.yyh.dao.TicketDao;
import com.yyh.util.Constants;
import com.yyh.util.Convert2Json;

public class QueryTicketServlet extends BaseServlet {

	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("QueryTicketServlet JSESSIONID = " + req.getSession().getId());
		
		String name = (String) req.getSession().getAttribute("username");
		String oAddress = req.getParameter("oaddress");
		String dAddress = req.getParameter("daddress");
		String sDateString = req.getParameter("sday");
		Date sDate = Date.valueOf(sDateString);
		List<Ticket> tickets = TicketDao.queryTicket(oAddress, dAddress, sDate);
		String result = Convert2Json.listTicketJson(tickets);
		
		PrintWriter writer = resp.getWriter();
		writer.write(result);
		writer.flush();
		writer.close();		
	}
}
