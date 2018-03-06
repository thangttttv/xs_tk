package com.veso.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.Ticket;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TicketDAO {
	
	private Logger logger = new Logger(this.getClass().getName());
	
	public List<Ticket> findByOpenDate(Date open_date) {
		List<Ticket> tList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		Ticket ticket = null;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT id,	user_id, ngay_quay,	province_id, total_xu, tien_thuong," +
					" kieu_danh,create_date FROM ticket WHERE ngay_quay = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());		
			preStmt.setDate(1, open_date);
			
			rs = preStmt.executeQuery();
			tList = new ArrayList<Ticket>();
			while (rs.next()) {	
				ticket = new Ticket();
				ticket.setId(rs.getInt("id"));
				ticket.setUser_id(rs.getInt("user_id"));
				ticket.setNgay_quay(rs.getDate("ngay_quay"));
				ticket.setProvince_id(rs.getInt("province_id"));
				ticket.setTotal_xu(rs.getDouble("total_xu"));
				ticket.setTien_thuong(rs.getDouble("tien_thuong"));
				ticket.setKieu_danh(rs.getInt("kieu_danh"));
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
	
	public List<Ticket> findTicketTransaction(Date open_date) {
		List<Ticket> tList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		Ticket ticket = null;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT id,	user_id, ngay_quay,	province_id, total_xu, tien_thuong," +
					" kieu_danh,create_date FROM ticket WHERE ngay_quay = ? AND has_payment = 0 AND tien_thuong > 0 ");
			preStmt = conn.prepareStatement(strSQL.toString());		
			preStmt.setDate(1, open_date);
				
			rs = preStmt.executeQuery();
			tList = new ArrayList<Ticket>();
			while (rs.next()) {	
				ticket = new Ticket();
				ticket.setId(rs.getInt("id"));
				ticket.setUser_id(rs.getInt("user_id"));
				ticket.setNgay_quay(rs.getDate("ngay_quay"));
				ticket.setProvince_id(rs.getInt("province_id"));
				ticket.setTotal_xu(rs.getDouble("total_xu"));
				ticket.setTien_thuong(rs.getDouble("tien_thuong"));
				ticket.setKieu_danh(rs.getInt("kieu_danh"));
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
	
	
	public List<Ticket> findTicketStatisticBonus(Date open_date) {
		List<Ticket> tList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		Ticket ticket = null;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT t.id,	t.user_id, t.ngay_quay,	t.province_id, t.total_xu, t.tien_thuong," +
					" t.kieu_danh,t.create_date,u.username,u.mobile FROM ticket t INNER JOIN user_veso u ON t.user_id = u.id  WHERE t.ngay_quay = ?  AND t.tien_thuong > 0 ");
			preStmt = conn.prepareStatement(strSQL.toString());		
			preStmt.setDate(1, open_date);
				
			rs = preStmt.executeQuery();
			tList = new ArrayList<Ticket>();
			while (rs.next()) {	
				ticket = new Ticket();
				ticket.setId(rs.getInt("id"));
				ticket.setUser_id(rs.getInt("user_id"));
				ticket.setMobile(rs.getString("mobile"));
				ticket.setUsername(rs.getString("username"));
				ticket.setNgay_quay(rs.getDate("ngay_quay"));
				ticket.setProvince_id(rs.getInt("province_id"));
				ticket.setTotal_xu(rs.getDouble("total_xu"));
				ticket.setTien_thuong(rs.getDouble("tien_thuong"));
				ticket.setKieu_danh(rs.getInt("kieu_danh"));
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

	
	public List<Ticket> findByProvince(Date open_date, int province_id) {
		List<Ticket> tList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		Ticket ticket = null;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT id,	user_id, ngay_quay,	province_id, total_xu, tien_thuong," +
					" kieu_danh,create_date FROM ticket WHERE ngay_quay = ? AND  province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());		
			preStmt.setDate(1, open_date);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			tList = new ArrayList<Ticket>();
			while (rs.next()) {	
				ticket = new Ticket();
				ticket.setId(rs.getInt("id"));
				ticket.setUser_id(rs.getInt("user_id"));
				ticket.setNgay_quay(rs.getDate("ngay_quay"));
				ticket.setProvince_id(rs.getInt("province_id"));
				ticket.setTotal_xu(rs.getDouble("total_xu"));
				ticket.setTien_thuong(rs.getDouble("tien_thuong"));
				ticket.setKieu_danh(rs.getInt("kieu_danh"));
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
			strSQL = new StringBuffer("UPDATE ticket SET tien_thuong = ?  WHERE	id = ? ");
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
	
	public boolean update(int id, double tien_thuong,int has_payment) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("UPDATE ticket SET tien_thuong = ? , has_payment = ?  WHERE	id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDouble(1, tien_thuong);
			preStmt.setInt(2, has_payment);
			preStmt.setInt(3, id);
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
	
	public boolean update(int id, int has_payment) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("UPDATE ticket SET has_payment = ?  WHERE	id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, has_payment);
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
