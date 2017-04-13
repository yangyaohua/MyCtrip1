package com.yyh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yyh.bean.Task;
import com.yyh.util.DBUtil;

public class TaskDao {
	
	public static void main(String[] args) {
		/*Map<String, Task> map = new HashMap<String, Task>();
		Task task1 = new Task("2", "name", "yangyaohua");
		Task task2 = new Task("2", "age", "22");
		Task task3 = new Task("2", "sex", "男");
		map.put("1", task1);
		map.put("2", task2);
		map.put("3", task3);
		updateTask(map);*/
		
		List<Task> queryTask = queryTask();
		System.out.println(queryTask);
	}
	
	public static List<Task> queryTask(){
		List<Task> tasks = new ArrayList<Task>();
		Connection connection = DBUtil.getConnection();
		String sql = "select * from task";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String index = rs.getString(1);
				String key = rs.getString(2);
				String value = rs.getString(3);
				Task task = new Task(index, key, value);
				tasks.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(rs, ps, connection);
		}
		return tasks;
	}

	public static void updateTask(Map<String, Task> map){
		Connection connection = DBUtil.getConnection();
		String sql = "delete from task";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			connection.setAutoCommit(false);
			sql = "insert into task values(?,?,?);";
			ps = connection.prepareStatement(sql);
			for(String key : map.keySet()){
				Task task = map.get(key);
				ps.setString(1, task.getIndex());
				ps.setString(2, task.getKey());
				ps.setString(3, task.getValue());
				ps.addBatch();
			}
			int[] count = ps.executeBatch();
			
			connection.commit();
			for(int i : count){
				connection.rollback();
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeConnection(null, ps, connection);
		}
	}
	
	private static void closeConnection(ResultSet rs, PreparedStatement ps,
			Connection connection) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				DBUtil.closeConnection(connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
