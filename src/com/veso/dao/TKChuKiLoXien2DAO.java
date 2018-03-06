package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.TKChuKiBoXien2;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TKChuKiLoXien2DAO {
	private Logger logger = new Logger(this.getClass().getName());
	
	public boolean save(TKChuKiBoXien2 chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_chuky_boso_xien2(province_id,boso1,boso2,start_date,end_date,length,is_special)"
							+ " VALUES (?, ?, ?,?, ?, ?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKyBoSo.province_id);
			preStmt.setString(2, chuKyBoSo.boso1);
			preStmt.setString(3, chuKyBoSo.boso2);
			preStmt.setDate(4, chuKyBoSo.start_date);
			preStmt.setDate(5, chuKyBoSo.end_date);
			preStmt.setLong(6, chuKyBoSo.length);
			preStmt.setInt(7, chuKyBoSo.is_special);

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
	
	public boolean updateChuKy(TKChuKiBoXien2 chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" UPDATE  thongke_chuky_boso_xien2 SET end_date = ? , length =? WHERE id = ? ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDate(1, chuKyBoSo.end_date);
			preStmt.setLong(2, chuKyBoSo.length);
			preStmt.setInt(3, chuKyBoSo.id);

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
	
		
	public TKChuKiBoXien2 getChuKyCuoi(String boso1,String boso2,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKiBoXien2 tChuKyBoSo = null;
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,boso1,boso2,start_date,end_date,length,is_special " +
					" 	FROM thongke_chuky_boso_xien2 WHERE boso1 = ? AND boso2 = ? AND province_id = ? " +
					"   ORDER BY id DESC Limit 1 ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setString(1, boso1);
			preStmt.setString(2, boso2);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				tChuKyBoSo = new TKChuKiBoXien2();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.boso1 = rs.getString("boso1");
				tChuKyBoSo.boso2 = rs.getString("boso2");
				tChuKyBoSo.start_date = rs.getDate("start_date");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				tChuKyBoSo.length = rs.getInt("length");
				tChuKyBoSo.is_special = rs.getInt("is_special");;
				
			}

		} catch (NoSuchElementException nse) {
			logger.error("getChuKyCuoi: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getChuKyCuoi: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getChuKyCuoi: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return tChuKyBoSo;
	}
	
	public List<TKChuKiBoXien2> findAll(int start) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKiBoXien2 tChuKyBoSo = null;
		List<TKChuKiBoXien2> list = new ArrayList<TKChuKiBoXien2>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,boso1,boso2,start_date,end_date,length,is_special " +
					" 	FROM thongke_chuky_boso_xien2 WHERE id > ? " +
					"   ORDER BY id ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, start);
			rs = preStmt.executeQuery();
			
			while (rs.next()) {				
				tChuKyBoSo = new TKChuKiBoXien2();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.boso1 = rs.getString("boso1");
				tChuKyBoSo.boso2 = rs.getString("boso2");
				tChuKyBoSo.start_date = rs.getDate("start_date");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				tChuKyBoSo.length = rs.getInt("length");
				tChuKyBoSo.is_special = rs.getInt("is_special");
				list.add(tChuKyBoSo);
			}

		} catch (NoSuchElementException nse) {
			logger.error("getChuKyCuoi: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getChuKyCuoi: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getChuKyCuoi: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return list;
	}
	
}
