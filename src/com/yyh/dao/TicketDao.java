package com.yyh.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yyh.bean.Ticket;
import com.yyh.util.Convert2Json;
import com.yyh.util.DBUtil;

public class TicketDao {
	
	public static void main(String[] args) {
		double result = getTicketPriceByTid("10000");
		System.out.println("result = " + result);
	}

	/**
	 * 查询车票
	 * @param oAddress
	 * @param dAddress
	 * @param sDate
	 * @return
	 */
	public static List<Ticket> queryTicket(String oAddress, String dAddress, Date sDate){
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "select * from ticket where oaddress = ? and daddress = ? and sday = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = DBUtil.getConnection();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, oAddress);
			ps.setString(2, dAddress);
			ps.setDate(3,sDate);
			rs = ps.executeQuery();
			while(rs.next()) {
				Ticket ticket = new Ticket();
				ticket.setTid(rs.getInt(1));
				ticket.setSerialNumber(rs.getString(2));
				ticket.setOriginAddress(rs.getString(3));
				ticket.setDesAddress(rs.getString(4));
				ticket.setStartDay(rs.getTimestamp(5));
				ticket.setStartTime(rs.getTimestamp(6));
				ticket.setEndTime(rs.getTimestamp(7));
				ticket.setPrice(rs.getDouble(8));
				ticket.setConsume(rs.getString(9));
				ticket.setNumbers(rs.getInt(10));
				tickets.add(ticket);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(rs,ps,connection);
		}
		return tickets;
	}

	/**
	 * 预订车票
	 * @param cnumber
	 * @param tid
	 * @return
	 */
	public static boolean bookTicket(String cnumber, String tid) {
		Connection connection = DBUtil.getConnection();
		String sql = "select bstate from user_state where cnumber = ? and tid = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, cnumber);
			ps.setInt(2, Integer.valueOf(tid));
			rs = ps.executeQuery();
			if (rs.next()) {
				/*if (rs.getInt(rs.findColumn("bstate")) == 1) {
					return false;
				}*/
				sql = "update user_state set bstate = 1 where cnumber = ? and tid = ?";
				ps = connection.prepareStatement(sql);
				ps.setString(1, cnumber);
				ps.setInt(2, Integer.valueOf(tid));
				int executeUpdate = ps.executeUpdate();
				return executeUpdate > 0;
			}else {
				sql = "insert into user_state values(?,?,?,?);";
				ps = connection.prepareStatement(sql);
				ps.setString(1, cnumber);
				ps.setInt(2, Integer.valueOf(tid));
				ps.setInt(3, 1);
				ps.setInt(4, 0);
				int insert = ps.executeUpdate();
				return insert > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(rs, ps, connection);
		}
		return false;
	}
	
	public static boolean hasBookedTicket(String tid, String cnumber){
		Connection connection = DBUtil.getConnection();
		String sql = "select * from user_state where tid = ? and cnumber = ? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, cnumber);
			ps.setInt(2, Integer.valueOf(tid));
			rs = ps.executeQuery();
			if (rs.next()) {
				int result = rs.getInt(rs.findColumn("bstate"));
				return result == 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(rs, ps, connection);
		}
		return false;
	}
	
	public static boolean payForTicket(String tid, String cnumber) {
		Connection connection = DBUtil.getConnection();
		String sql = "update user_state set pstate = 1 where tid = ? and cnumber = ? ";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, tid);
			ps.setString(2, cnumber);
			int update = ps.executeUpdate();
			return update > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(null, ps, connection);
		}
		return false;
	}
	
	public static double getTicketPriceByTid(String tid) {
		Connection connection = DBUtil.getConnection();
		String sql = "select price from ticket where tid = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(tid));
			rs = ps.executeQuery();
			if (rs.next()) {
				double result = rs.getDouble(rs.findColumn("price"));
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(rs, ps, connection);
		}
		return -1;
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
