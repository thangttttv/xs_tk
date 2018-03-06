package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.BuocCauBean;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class BuocCauDAO {
	private String table = "soicau_buoccau";
	private Logger logger = new Logger(this.getClass().getName());

	public boolean save(BuocCauBean bean) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO "+this.table+" (cau_id,kieu_cau,b1_id,b1_dau,b1_dit,b2_id,b2_dau, " +
							" b2_dit,b3_id,b3_dau,b3_dit,b4_id, b4_dau,  " +
							"b4_dit, b5_id,b5_dau, b5_dit,b6_id,b6_dau,b6_dit, " +
							"b7_id,b7_dau,b7_dit,x,y, create_date,boso)" +
							" VALUES(?,	?, ?, ?, ?,	?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,? )");

			preStmt = conn.prepareStatement(strSQL.toString());			
			
			preStmt.setLong(1,bean.getCau_id());
			preStmt.setInt(2,bean.getKieu_cau());			
			preStmt.setLong(3,bean.getB1_id());
			preStmt.setInt(4,bean.getB1_dau());			
			preStmt.setInt(5,bean.getB1_dit());		
			preStmt.setLong(6,bean.getB2_id());
			preStmt.setInt(7,bean.getB2_dau());			
			preStmt.setInt(8,bean.getB2_dit());
			preStmt.setLong(9,bean.getB3_id());
			preStmt.setInt(10,bean.getB3_dau());
			preStmt.setInt(11,bean.getB3_dit());
			preStmt.setLong(12,bean.getB4_id());
			preStmt.setInt(13,bean.getB4_dau());			
			preStmt.setInt(14,bean.getB4_dit());				
			preStmt.setLong(15,bean.getB5_id());
			preStmt.setInt(16,bean.getB5_dau());			
			preStmt.setInt(17,bean.getB5_dit());		
			preStmt.setLong(18,bean.getB6_id());
			preStmt.setInt(19,bean.getB6_dau());			
			preStmt.setInt(20,bean.getB6_dit());			
			preStmt.setLong(21,bean.getB7_id());
			preStmt.setInt(22,bean.getB7_dau());			
			preStmt.setInt(23,bean.getB7_dit());
			preStmt.setInt(24,bean.getX());
			preStmt.setInt(25,bean.getY());
			preStmt.setTimestamp(26,bean.getCreate_date());
			preStmt.setInt(27,bean.getBoso());
			
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

	public boolean delete(long id) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		try {
			conn = C3p0VesoPool.getConnection();	
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("DELETE FROM " + table + " WHERE id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1, id);
			if (preStmt.executeUpdate() == 1) {
				result = true;
				conn.commit();
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("delete: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("deleteRow: Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("delete: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}

	public boolean delete(String ids) {

		boolean result = false;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		try {
			conn = C3p0VesoPool.getConnection();	
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("DELETE FROM " + table + " WHERE id IN("
					+ ids + ") ");
			preStmt = conn.prepareStatement(strSQL.toString());
			if (preStmt.executeUpdate() > 0) {
				result = true;
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (NoSuchElementException nse) {
			logger.error("delete: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("delete: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("delete: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public List<BuocCauBean> findAll(int start) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		StringBuffer strSQL = null;
		List<BuocCauBean> list = new ArrayList<BuocCauBean>();
		try {
			conn = C3p0VesoPool.getConnection();	
			strSQL = new StringBuffer("SELECT id,cau_id,kieu_cau,boso,b1_id,b1_dau,b1_dit,b2_id,b2_dau,b2_dit,b3_id," +
					"	b3_dau,b3_dit,b4_id,b4_dau,b4_dit,b5_id,b5_dau,	b5_dit,b6_id,b6_dau,b6_dit,	b7_id,b7_dau,b7_dit," +
					"   X,Y,create_date	FROM soicau_buoccau	where id > ? order by id ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, start);
			BuocCauBean bean = null;
			rs = preStmt.executeQuery();
			while (rs.next()) {
				bean = new BuocCauBean();
				bean.setId(rs.getLong("id"));
				bean.setCau_id(rs.getLong("cau_id"));
				bean.setKieu_cau(rs.getInt("kieu_cau"));
				bean.setBoso(rs.getInt("boso"));
				
				bean.setB1_id(rs.getLong("b1_id"));
				bean.setB1_dau(rs.getInt("b1_dau"));
				bean.setB1_dit(rs.getInt("b1_dit"));
				
				bean.setB2_id(rs.getLong("b2_id"));
				bean.setB2_dau(rs.getInt("b2_dau"));
				bean.setB2_dit(rs.getInt("b2_dit"));
				
				bean.setB3_id(rs.getLong("b3_id"));
				bean.setB3_dau(rs.getInt("b3_dau"));
				bean.setB3_dit(rs.getInt("b3_dit"));
				
				bean.setB4_id(rs.getLong("b4_id"));
				bean.setB4_dau(rs.getInt("b4_dau"));
				bean.setB4_dit(rs.getInt("b4_dit"));
				
				bean.setB5_id(rs.getLong("b5_id"));
				bean.setB5_dau(rs.getInt("b5_dau"));
				bean.setB5_dit(rs.getInt("b5_dit"));
				
				bean.setB6_id(rs.getLong("b6_id"));
				bean.setB6_dau(rs.getInt("b6_dau"));
				bean.setB6_dit(rs.getInt("b6_dit"));
				
				bean.setB7_id(rs.getLong("b7_id"));
				bean.setB7_dau(rs.getInt("b7_dau"));
				bean.setB7_dit(rs.getInt("b7_dit"));
				
				bean.setX(rs.getInt("x"));
				bean.setY(rs.getInt("y"));
				bean.setCreate_date(rs.getTimestamp("create_date"));				
				list.add(bean);
			}
		} catch (NoSuchElementException nse) {
			logger.error("findAll: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAll: Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("findAll: Error executing >>>" + e.toString());
		} finally {			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return list;
	}


}
