package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.TKChuKyBoSo;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TKChuKiBoSoDAO {
	private Logger logger = new Logger(this.getClass().getName());

	public boolean save(TKChuKyBoSo chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_chuky_boso(province_id,boso,start_date,end_date,length,is_special)"
							+ " VALUES (?, ?, ?,?, ?, ?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKyBoSo.province_id);
			preStmt.setString(2, chuKyBoSo.boso);
			preStmt.setDate(3, chuKyBoSo.start_date);
			preStmt.setDate(4, chuKyBoSo.end_date);
			preStmt.setLong(5, chuKyBoSo.length);
			preStmt.setInt(6, chuKyBoSo.is_special);

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
	
	public boolean updateChuKy(TKChuKyBoSo chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" UPDATE  thongke_chuky_boso SET end_date = ? , length =? WHERE id = ? ");

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
					" 	FROM thongke_chuky_boso WHERE boso = ? AND province_id = ?  ");
			
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
	
	public TKChuKyBoSo getChuKyCuoi(String boso) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKyBoSo tChuKyBoSo = null;
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,boso,start_date,end_date,length,is_special " +
					" 	FROM thongke_chuky_boso WHERE boso = ? " +
					"   ORDER BY id DESC Limit 1 ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setString(1, boso);		
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				tChuKyBoSo = new TKChuKyBoSo();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.boso = rs.getString("boso");
				tChuKyBoSo.start_date = rs.getDate("start_date");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				tChuKyBoSo.length = rs.getInt("length");
				tChuKyBoSo.is_special = rs.getInt("is_special");;
				
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
	
	public TKChuKyBoSo getChuKyCuoi(String boso,int province_id, int is_dacbiet) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKyBoSo tChuKyBoSo = null;
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,boso,start_date,end_date,length,is_special " +
					" 	FROM thongke_chuky_boso WHERE boso = ? AND province_id = ? And is_special = ?  " +
					"   ORDER BY id DESC Limit 1 ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setString(1, boso);
			preStmt.setInt(2, province_id);
			preStmt.setInt(3, is_dacbiet);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				tChuKyBoSo = new TKChuKyBoSo();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.boso = rs.getString("boso");
				tChuKyBoSo.start_date = rs.getDate("start_date");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				tChuKyBoSo.length = rs.getInt("length");
				tChuKyBoSo.is_special = rs.getInt("is_special");;
				
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
	
	
	
	public TKChuKyBoSo getChuKyCuoiDacBiet(String boso,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKyBoSo tChuKyBoSo = null;
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,boso,start_date,end_date,length,is_special " +
					" 	FROM thongke_chuky_boso WHERE boso = ? AND province_id = ? AND is_special = 1 " +
					"   ORDER BY id DESC Limit 1 ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setString(1, boso);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				tChuKyBoSo = new TKChuKyBoSo();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.boso = rs.getString("boso");
				tChuKyBoSo.start_date = rs.getDate("start_date");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				tChuKyBoSo.length = rs.getInt("length");
				tChuKyBoSo.is_special = rs.getInt("is_special");;
				
			}

		} catch (NoSuchElementException nse) {
			logger.error("getChuKyCuoiDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getChuKyCuoiDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getChuKyCuoiDacBiet: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return tChuKyBoSo;
	}
	
	
	public List<TKChuKyBoSo> findAll(int start) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKyBoSo tChuKyBoSo = null;
		List<TKChuKyBoSo> list = new ArrayList<TKChuKyBoSo>();
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,province_id,boso,start_date,end_date,length,is_special " +
					" 	FROM thongke_chuky_boso WHERE id > ? " +
					"   ORDER BY id  ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, start);		
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				tChuKyBoSo = new TKChuKyBoSo();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.boso = rs.getString("boso");
				tChuKyBoSo.start_date = rs.getDate("start_date");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				tChuKyBoSo.length = rs.getInt("length");
				tChuKyBoSo.is_special = rs.getInt("is_special");;
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
	
	public List<TKChuKyBoSo> findAllBoSoChuaVe(int so_ngay_thong_ke,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKyBoSo tChuKyBoSo = null;
		List<TKChuKyBoSo> list = new ArrayList<TKChuKyBoSo>();
		try {
			//"2014-03-20"CURRENT_DATE
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT *,DATEDIFF(CURRENT_DATE,start_date) AS so_ngay_chua_ve FROM thongke_chuky_boso" +
					" WHERE end_date IS NULL AND DATEDIFF(CURRENT_DATE,start_date)<=? AND is_special = 0 AND province_id = ? ");
			System.out.println(strSQL);
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, so_ngay_thong_ke);		
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			
			while (rs.next()) {				
				tChuKyBoSo = new TKChuKyBoSo();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.boso = rs.getString("boso");
				tChuKyBoSo.start_date = rs.getDate("start_date");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				tChuKyBoSo.length = rs.getInt("length");
				tChuKyBoSo.is_special = rs.getInt("is_special");
				tChuKyBoSo.so_ngay_chua_ve = rs.getInt("so_ngay_chua_ve");
				list.add(tChuKyBoSo);
			}

		} catch (NoSuchElementException nse) {
			logger.error("findAllBoSoChuaVe: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAllBoSoChuaVe: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findAllBoSoChuaVe: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return list;
	}
	
	public HashMap<String,TKChuKyBoSo> findAllChuKyBoSoMoiNhat(int so_ngay_thong_ke,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKyBoSo tChuKyBoSo = null;
		HashMap<String,TKChuKyBoSo> list = new HashMap<String, TKChuKyBoSo>();
		ArrayList<TKChuKyBoSo> listTkChukyboso = new ArrayList<TKChuKyBoSo>();
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT boso, MAX(end_date)  FROM thongke_chuky_boso WHERE LENGTH>0 AND DATEDIFF(CURRENT_DATE,start_date)<=? " +
					" AND is_special = 0 AND province_id = ? GROUP BY boso ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, so_ngay_thong_ke);		
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			
			while (rs.next()) {				
				tChuKyBoSo = new TKChuKyBoSo();
				tChuKyBoSo.boso = rs.getString("boso");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				listTkChukyboso.add(tChuKyBoSo);
			}
			
			///SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			for (TKChuKyBoSo tkChuKyBoSo : listTkChukyboso) {
				strSQL = new StringBuffer("SELECT *  FROM thongke_chuky_boso boso = ? And end_date = ? ");
				preStmt = conn.prepareStatement(strSQL.toString());			
				preStmt.setString(1, tkChuKyBoSo.boso);	
				//String end_date = simpleDateFormat.format(tkChuKyBoSo.end_date);
				preStmt.setDate(2, tkChuKyBoSo.end_date);
				rs = preStmt.executeQuery();
				
				if (rs.next()) {				
					tChuKyBoSo = new TKChuKyBoSo();
					tChuKyBoSo.id = rs.getInt("id");
					tChuKyBoSo.province_id = rs.getInt("province_id");
					tChuKyBoSo.boso = rs.getString("boso");
					tChuKyBoSo.start_date = rs.getDate("start_date");
					tChuKyBoSo.end_date = rs.getDate("end_date");
					tChuKyBoSo.length = rs.getInt("length");
					tChuKyBoSo.is_special = rs.getInt("is_special");
					list.put(tChuKyBoSo.boso,tChuKyBoSo);
				}
			}
			
			
			/*while (rs.next()) {				
				tChuKyBoSo = new TKChuKyBoSo();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.province_id = rs.getInt("province_id");
				tChuKyBoSo.boso = rs.getString("boso");
				tChuKyBoSo.start_date = rs.getDate("start_date");
				tChuKyBoSo.end_date = rs.getDate("end_date");
				tChuKyBoSo.length = rs.getInt("length");
				tChuKyBoSo.is_special = rs.getInt("is_special");
				list.put(tChuKyBoSo.boso,tChuKyBoSo);
			}*/

		} catch (NoSuchElementException nse) {
			logger.error("findAllChuKyBoSoMoiNhat: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAllChuKyBoSoMoiNhat: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findAllChuKyBoSoMoiNhat: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return list;
	}
	
	
	public HashMap<String,TKChuKyBoSo> findAllChuKyBoSoCucDai(int so_ngay_thong_ke,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TKChuKyBoSo tChuKyBoSo = null;
		HashMap<String,TKChuKyBoSo> list = new HashMap<String, TKChuKyBoSo>();
		try {

			conn = C3p0VesoPool.getConnection();	
			int i = 0;
			while(i<=99){
				String boso = i<10?"0"+i:i+"";
				/*strSQL = new StringBuffer("SELECT id,province_id,boso,start_date,end_date,MAX(LENGTH) AS LENGTH,is_special  FROM thongke_chuky_boso" +
						" WHERE end_date IS NOT NULL AND DATEDIFF(CURRENT_DATE,start_date)<=? AND is_special = 0 AND province_id = ? GROUP BY boso ");*/
				strSQL = new StringBuffer("SELECT * FROM thongke_chuky_boso WHERE end_date IS NOT NULL AND DATEDIFF(CURRENT_DATE,start_date)<=? AND" +
						" is_special = 0 AND province_id = ? AND boso=? AND LENGTH = (SELECT MAX(LENGTH) FROM thongke_chuky_boso" +
						" WHERE end_date IS NOT NULL AND DATEDIFF(CURRENT_DATE,start_date)<=? AND is_special = 0 AND province_id = ? AND boso=?)");
				preStmt = conn.prepareStatement(strSQL.toString());			
				preStmt.setInt(1, so_ngay_thong_ke);		
				preStmt.setInt(2, province_id);
				preStmt.setString(3, boso);
				preStmt.setInt(4, so_ngay_thong_ke);		
				preStmt.setInt(5, province_id);
				preStmt.setString(6, boso);
				rs = preStmt.executeQuery();
				
				while (rs.next()) {				
					tChuKyBoSo = new TKChuKyBoSo();
					tChuKyBoSo.id = rs.getInt("id");
					tChuKyBoSo.province_id = rs.getInt("province_id");
					tChuKyBoSo.boso = rs.getString("boso");
					tChuKyBoSo.start_date = rs.getDate("start_date");
					tChuKyBoSo.end_date = rs.getDate("end_date");
					tChuKyBoSo.length = rs.getInt("length");
					tChuKyBoSo.is_special = rs.getInt("is_special");
					list.put(tChuKyBoSo.boso, tChuKyBoSo);
				}
				i++;
			}
		} catch (NoSuchElementException nse) {
			logger.error("findAllChuKyBoSoCucDai: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAllChuKyBoSoCucDai: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findAllChuKyBoSoCucDai: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return list;
	}
	
	
}
