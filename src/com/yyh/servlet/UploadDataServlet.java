package com.yyh.servlet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yyh.bean.Task;
import com.yyh.dao.TaskDao;
import com.yyh.util.Constants;
import com.yyh.util.FileHelper;

public class UploadDataServlet extends BaseServlet {

	@Override
	protected void dispatchTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletInputStream inputStream = req.getInputStream();
		byte[] buffer = new byte[512];
		int numberRead = 0;
		FileOutputStream outputStream = new FileOutputStream(Constants.DATA_FILE_PATH);
		while((numberRead = inputStream.read(buffer)) != -1){
			outputStream.write(buffer,0,numberRead);
		}
		inputStream.close();
		outputStream.close();
		
		String data = FileHelper.txt2String(Constants.DATA_FILE_PATH);
		Gson gson = new Gson();
		Map<String, Task> tasks = gson.fromJson(data, new TypeToken<Map<String, Task>>(){}.getType());
		TaskDao.updateTask(tasks);
	}
	
	
}
