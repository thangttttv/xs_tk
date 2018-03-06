package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.TKChuKiDuoi;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TKChuKiDuoiDAO {
	
	private Logger logger = new Logger(this.getClass().getName());

	public boolean save(TKChuKiDuoi chuKiDuoi) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_chuky_duoi (province_id," +
					"	duoi,start_date,end_date,length) VALUES" +
					" 	(?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKiDuoi.province_id);
			preStmt.setString(2, chuKiDuoi.duoi);
			preStmt.setDate(3, chuKiDuoi.start_date);
			preStmt.setDate(4, chuKiDuoi.end_date);
			preStmt.setLong(5, chuKiDuoi.length);
			

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
	
	public boolean updateChuKy(TKChuKiDuoi chuKiDuoi) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("UPDATE thongke_chuky_duoi SET end_date = ? , length =? WHERE id = ? ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDate(1, chuKiDuoi.end_date);
			preStmt.setLong(2, chuKiDuoi.length);
			preStmt.setInt(3, chuKiDuoi.id);

			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("updateChuKy: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("updateChuKy: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("updateChuKy: Error executing >>>" + e.toString());
		} finally {
			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
	public boolean checkBoso(String boso,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id " +
					" 	FROM thongke_chuky_duoi WHERE duoi = ? AND province_id = ?  ");
			
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setString(1, boso);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkBoso: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkBoso: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkBoso: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public TKChuKiDuoi getChuKyCuoi(String boso,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKiDuoi tChuKyBoSo = null;
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,duoi,start_date,end_date,length " +
					" 	FROM thongke_chuky_duoi WHERE duoi = ? AND province_id = ? " +
					"   ORDER BY id DESC Limit 1 ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setString(1, boso);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				tChuKyBoSo = new TKChuKiDuoi();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.duoi = rs.getString("duoi");
				tChuKyBoSo.start_date = rs.getDate("start_date");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				tChuKyBoSo.length = rs.getInt("length");
				
				
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkChuKyDaDong: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkChuKyDaDong: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkChuKyDaDong: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return tChuKyBoSo;
	}
	
	
	public List<TKChuKiDuoi> findAll(int start) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKiDuoi tChuKyBoSo = null;
		List<TKChuKiDuoi> list = new ArrayList<TKChuKiDuoi>();
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,duoi,start_date,end_date,length " +
					" 	FROM thongke_chuky_duoi WHERE  id > ?  " +
					"   ORDER BY id  ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, start);
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				tChuKyBoSo = new TKChuKiDuoi();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.duoi = rs.getString("duoi");
				tChuKyBoSo.start_date = rs.getDate("start_date");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				tChuKyBoSo.length = rs.getInt("length");
				list.add(tChuKyBoSo);
			}
		} catch (NoSuchElementException nse) {
			logger.error("checkChuKyDaDong: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkChuKyDaDong: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkChuKyDaDong: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return list;
	}
	
	
	
}
