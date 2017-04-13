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

public class PayForTicketServlet extends BaseServlet {

	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getSession().setAttribute(Constants.CURRENT_TASK, "3");

		System.out.println("PayForTicketServlet JSESSIONID = " + req.getSession().getId());

		String tid = (String) req.getSession().getAttribute(Constants.TID);
		String cnumber = (String) req.getSession().getAttribute(Constants.USER_ID);
		String phoneNumber = req.getParameter("phone_number");
		String payPassword = req.getParameter("pay_password");

		boolean payState = false;
		if (phoneNumber.equals("13672006801") && payPassword.equals("123456")) {
			payState = TicketDao.payForTicket(tid, cnumber);
		}
		String result = String.valueOf(payState);
		PrintWriter writer = resp.getWriter();
		writer.write(result);
		writer.flush();
		writer.close();		
	}

}