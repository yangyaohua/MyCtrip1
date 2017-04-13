package com.yyh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyh.util.Constants;

public class SessionPieceServlet extends BaseServlet {

	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String piece = (String) req.getSession().getAttribute(Constants.CURRENT_TASK);
		
		System.out.println("SessionPiece = " + piece);
		PrintWriter writer = resp.getWriter();
		if (piece == null || piece.equals("")) {
			piece = "-1";
		}
		writer.write(piece);
		writer.flush();
		writer.close();
	}

}
