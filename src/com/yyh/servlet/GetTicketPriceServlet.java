package com.yyh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyh.dao.TicketDao;
import com.yyh.util.Constants;

public class GetTicketPriceServlet extends BaseServlet {

	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String tid = (String) req.getSession().getAttribute(Constants.TID);
		double result = TicketDao.getTicketPriceByTid(tid);
	
		PrintWriter writer = resp.getWriter();
		writer.write(String.valueOf(result));
		writer.flush();
		writer.close();
	}

}
