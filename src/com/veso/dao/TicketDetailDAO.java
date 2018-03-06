package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.TicketDetail;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TicketDetailDAO {
	private Logger logger = new Logger(this.getClass().getName());
	
	public List<TicketDetail> findByTicket(int ticket_id) {
		List<TicketDetail> tList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		TicketDetail ticket = null;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT id,ticket_id, boso, kieu_danh, xu, tien_thuong, create_date,type_dauduoi	FROM ticket_detail WHERE  ticket_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());		
			preStmt.setInt(1, ticket_id);
			
			rs = preStmt.executeQuery();
			tList = new ArrayList<TicketDetail>();
			while (rs.next()) {	
				ticket = new TicketDetail();
				ticket.setId(rs.getInt("id"));
				ticket.setTicket_id(rs.getInt("ticket_id"));
				ticket.setBoso(rs.getString("boso"));
				ticket.setKieu_danh(rs.getInt("kieu_danh"));
				ticket.setXu(rs.getDouble("xu"));
				ticket.setTien_thuong(rs.getDouble("tien_thuong"));		
				ticket.setType_dauduoi(rs.getInt("type_dauduoi"));
				ticket.setCreate_date(rs.getTimestamp("create_date"));
				tList.add(ticket);
			}

		} catch (NoSuchElementException nse) {
			logger.error("findByOpenDate: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findByOpenDate: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findByOpenDate: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return tList;
	}
	
	public boolean update(int id, double tien_thuong) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("UPDATE ticket_detail SET tien_thuong = ?  WHERE	id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDouble(1, tien_thuong);
			preStmt.setInt(2, id);
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("update: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("update: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("update: Error executing >>>" + e.toString());
		} finally {			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
}
