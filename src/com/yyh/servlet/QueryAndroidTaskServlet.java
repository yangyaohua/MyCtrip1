package com.yyh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyh.dao.TaskDao;
import com.yyh.util.Convert2Json;
import com.yyh.bean.Task;

public class QueryAndroidTaskServlet extends BaseServlet {

	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		List<Task> queryTask = TaskDao.queryTask();
		
		String json = Convert2Json.listTaskJson(queryTask);
		
		PrintWriter writer = resp.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
