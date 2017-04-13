package com.yyh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.taglibs.standard.tag.el.sql.UpdateTag;

import com.yyh.util.DBUtil;

public class UserDao {
	public static void main(String[] args) {
		boolean b = login("100000", "123");
		System.out.println(b);
	}

	public static boolean login(String userId, String password){
		Connection connection = DBUtil.getConnection();
		String sql = "select * from user where uid = ? and password = ?";
		boolean flag = false;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(connection);
		}
		return flag;
	}
	
	
	public static boolean changePassword(String username, String oldpassword,
			String newpassword) {
		Connection connection = DBUtil.getConnection();
		String sql = "select * from user where uid = \'" + username + "\'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				String correctpassword = rs.getString(2);
				if (oldpassword.equals(correctpassword)) {
					sql = "update user set password = \'" + newpassword + "\'";
					ps = connection.prepareStatement(sql);
					int executeUpdate = ps.executeUpdate();
					if (executeUpdate > 0) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
