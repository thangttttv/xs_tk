package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.TKChuKiTongDB;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TKChuKiTongDBDAO {
	private Logger logger = new Logger(this.getClass().getName());

	public boolean save(TKChuKiTongDB chuKiDau) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_chuky_tong_db (	province_id," +
					"	tong,start_date,	end_date,length) VALUES" +
					" 	(?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKiDau.province_id);
			preStmt.setInt(2, chuKiDau.tong);
			preStmt.setDate(3, chuKiDau.start_date);
			preStmt.setDate(4, chuKiDau.end_date);
			preStmt.setLong(5, chuKiDau.length);
		

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
	
	public boolean updateChuKy(TKChuKiTongDB chuKiDau) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" UPDATE  thongke_chuky_tong_db SET end_date = ? , length =? WHERE id = ? ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDate(1, chuKiDau.end_date);
			preStmt.setLong(2, chuKiDau.length);
			preStmt.setInt(3, chuKiDau.id);

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
					" 	FROM thongke_chuky_tong_db WHERE dau = ? AND province_id = ?  ");
			
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
	
	public TKChuKiTongDB getChuKyCuoi(int tong,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKiTongDB tChuKyBoSo = null;
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,tong,start_date,end_date,length " +
					" 	FROM thongke_chuky_tong_db WHERE tong = ? AND province_id = ? " +
					"   ORDER BY id DESC Limit 1 ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, tong);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				tChuKyBoSo = new TKChuKiTongDB();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.tong = rs.getInt("tong");
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
	
	
	public List<TKChuKiTongDB> findAll(int start) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKiTongDB tChuKyBoSo = null;
		List<TKChuKiTongDB> list = new ArrayList<TKChuKiTongDB>();
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,tong,start_date,end_date,length " +
					" 	FROM thongke_chuky_tong_db WHERE id > ?  " +
					"   ORDER BY id  ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, start);			
			rs = preStmt.executeQuery();
			while(rs.next()) {				
				tChuKyBoSo = new TKChuKiTongDB();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.tong = rs.getInt("tong");
				tChuKyBoSo.start_date = rs.getDate("start_date");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				tChuKyBoSo.length = rs.getInt("length");
				list.add(tChuKyBoSo);
			}

		} catch (NoSuchElementException nse) {
			logger.error("findAll: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAll: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findAll: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);	
			C3p0VesoPool.attemptClose(conn);
		}
		return list;
	}
	
}
