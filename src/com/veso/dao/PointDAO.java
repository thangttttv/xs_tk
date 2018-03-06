package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.Chot;
import com.veso.bean.UserPoint;
import com.veso.bean.UserPointLog;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class PointDAO {
	
	private Logger logger = new Logger(this.getClass().getName());
	
	public boolean saveUserPoint(UserPoint userPoint) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO x_user_point (user_id,point) VALUES (?,?)");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setInt(1,userPoint.user_id);
			preStmt.setDouble(2,userPoint.point);
			
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("save: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("save: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("save: Error executing >>>" + e.toString());
		} finally {
			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
			
		}
		return result;
	}
	
	public boolean changePoint(int user_id, double point) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" UPDATE  x_user_point SET point = point + ? Where user_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setInt(1,user_id);
			preStmt.setDouble(2,point);
			
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("save: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("save: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("save: Error executing >>>" + e.toString());
		} finally {
			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
			
		}
		return result;
	}
	
	public boolean updateChot(int chot_id, String loto,String lotoDB) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" UPDATE  x_chot SET loto = ? , loto_dac_biet = ?  Where id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setString(1,loto);
			preStmt.setString(2,lotoDB);
			preStmt.setInt(3,chot_id);
			
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("save: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("save: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("save: Error executing >>>" + e.toString());
		} finally {
			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
			
		}
		return result;
	}
	
	public boolean checkUserPoint(int user_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					"Select  * From x_user_point Where user_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setInt(1,user_id);
			ResultSet rs= preStmt.executeQuery();
			
			if (rs.next()) {
				
				result = true;
			} 
		} catch (NoSuchElementException nse) {
			logger.error("save: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("save: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("save: Error executing >>>" + e.toString());
		} finally {
			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
			
		}
		return result;
	}
	
	
	
	public boolean saveUserPointLog(UserPointLog userPoint) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO x_user_point_log (user_id,point,reference_id,description) VALUES (?,?,?,?)");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setInt(1,userPoint.user_id);
			preStmt.setDouble(2,userPoint.point);
			preStmt.setInt(3,userPoint.reference_id);
			preStmt.setString(4,userPoint.description);
			
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("save: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("save: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("save: Error executing >>>" + e.toString());
		} finally {
			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
			
		}
		return result;
	}
	
	
	public List<Chot> getChotByNgayQuay(String ngay_quay, int province_id) {		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		String table = "x_chot";
		List<Chot> list = new ArrayList<Chot>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT* from	"+table+" WHERE  ngay_quay = ? And province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setString(1, ngay_quay);
			preStmt.setInt(2, province_id);
			
			rs = preStmt.executeQuery();		
		
			while (rs.next()) {
				int id  = rs.getInt("id");
				int user_id  = rs.getInt("user_id");
				String loto = rs.getString("loto");
				String loto_dac_biet = rs.getString("loto_dac_biet");
				Chot chot = new Chot(id,user_id,province_id,loto,loto_dac_biet,ngay_quay);
				list.add(chot);
			}

		} catch (NoSuchElementException nse) {
			logger.error("getChotByNgayQuay: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getChotByNgayQuay: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getChotByNgayQuay: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return list;
	}

	
}
