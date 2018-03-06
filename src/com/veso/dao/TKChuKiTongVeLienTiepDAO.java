package com.veso.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.TKChuKiTongDBVeLienTiep;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TKChuKiTongVeLienTiepDAO {
	private Logger logger = new Logger(this.getClass().getName());

	public boolean save(TKChuKiTongDBVeLienTiep chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_tong_db_ve_lientiep (province_id, tong, start_date, end_date, length" +
					"	) VALUES (	?,	?,	?,	?,	? )");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKyBoSo.province_id);
			preStmt.setInt(2, chuKyBoSo.tong);
			preStmt.setDate(3, chuKyBoSo.start_date);
			preStmt.setDate(4, chuKyBoSo.end_date);
			preStmt.setLong(5, chuKyBoSo.length);
			
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
	
	public TKChuKiTongDBVeLienTiep getChuKyCuoi(int boso,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKiTongDBVeLienTiep dauSoVeLienTiep = null;
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,tong,start_date,end_date,length " +
					" 	FROM thongke_tong_db_ve_lientiep WHERE tong = ? AND province_id = ? " +
					"   ORDER BY id DESC Limit 1 ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, boso);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				dauSoVeLienTiep = new TKChuKiTongDBVeLienTiep();
				dauSoVeLienTiep.id = rs.getInt("id");
				dauSoVeLienTiep.province_id = rs.getInt("province_id");
				dauSoVeLienTiep.tong = rs.getInt("tong");
				dauSoVeLienTiep.start_date = rs.getDate("start_date");
				dauSoVeLienTiep.end_date = rs.getDate("end_date");
				dauSoVeLienTiep.length = rs.getInt("length");
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
		return dauSoVeLienTiep;
	}
	
	public List<TKChuKiTongDBVeLienTiep> getChuKyCanXoa(int province_id,Date open_date) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKiTongDBVeLienTiep boSoVeLienTiep = null;
		List<TKChuKiTongDBVeLienTiep> chukis = new ArrayList<TKChuKiTongDBVeLienTiep>();
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,tong,start_date,end_date,length " +
					" 	FROM thongke_tong_db_ve_lientiep WHERE  province_id = ? AND  end_date < ? AND length = 1 " +
					"   ORDER BY id DESC ");
			
			preStmt = conn.prepareStatement(strSQL.toString());		
			preStmt.setInt(1, province_id);
			preStmt.setDate(2, open_date);
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				boSoVeLienTiep = new TKChuKiTongDBVeLienTiep();
				boSoVeLienTiep.id = rs.getInt("id");
				boSoVeLienTiep.province_id = rs.getInt("province_id");
				boSoVeLienTiep.tong = rs.getInt("tong");
				boSoVeLienTiep.start_date = rs.getDate("start_date");
				boSoVeLienTiep.end_date = rs.getDate("end_date");
				boSoVeLienTiep.length = rs.getInt("length");
				chukis.add(boSoVeLienTiep);
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
		return chukis;
	}
	
	public boolean updateLengthChuKy(TKChuKiTongDBVeLienTiep chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" UPDATE  thongke_tong_db_ve_lientiep " +
					" SET  length = length + 1, end_date = ? WHERE id = ? ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDate(1, chuKyBoSo.end_date);
			preStmt.setInt(2, chuKyBoSo.id);

			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("updateLengthChuKy: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("updateLengthChuKy: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("updateLengthChuKy: Error executing >>>" + e.toString());
		} finally {
			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
	public boolean deleteChuKy(int id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" DELETE FROM  thongke_tong_db_ve_lientiep WHERE id = ? ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, id);

			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("deleteChuKy: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("deleteChuKy: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("deleteChuKy: Error executing >>>" + e.toString());
		} finally {
			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
	public boolean dongChuKi(TKChuKiTongDBVeLienTiep chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" UPDATE  thongke_tong_db_ve_lientiep SET end_date = ?  WHERE id = ? ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDate(1, chuKyBoSo.end_date);			
			preStmt.setInt(2, chuKyBoSo.id);

			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("dongChuKi: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("dongChuKi: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("dongChuKi: Error executing >>>" + e.toString());
		} finally {
			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public List<TKChuKiTongDBVeLienTiep> findAll(int start) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKiTongDBVeLienTiep dauSoVeLienTiep = null;
		List<TKChuKiTongDBVeLienTiep>  list = new ArrayList<TKChuKiTongDBVeLienTiep>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,tong,start_date,end_date,length " +
					" 	FROM thongke_tong_db_ve_lientiep WHERE id > ? " +
					"   ORDER BY id  ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, start);
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				dauSoVeLienTiep = new TKChuKiTongDBVeLienTiep();
				dauSoVeLienTiep.id = rs.getInt("id");
				dauSoVeLienTiep.province_id = rs.getInt("province_id");
				dauSoVeLienTiep.tong = rs.getInt("tong");
				dauSoVeLienTiep.start_date = rs.getDate("start_date");
				dauSoVeLienTiep.end_date = rs.getDate("end_date");
				dauSoVeLienTiep.length = rs.getInt("length");
				list.add(dauSoVeLienTiep);
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
