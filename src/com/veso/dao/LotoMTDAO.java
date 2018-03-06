package com.veso.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import com.veso.bean.BangTanSuatLotoBean;
import com.veso.bean.LotoBean;
import com.veso.bean.ProvinceBean;
import com.veso.bean.TanSuatBoSoBean;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.DateProc;
import com.veso.util.Logger;

public class LotoMTDAO {
	private String table = "thongke_loto_mienbac";
	private Logger logger = new Logger(this.getClass().getName());
	
	private String boso[] =  {"00","55","01","10","02","20","03","30","04","40","05","50","06","60","07","70","08","80",
			"09","90","11","66","12","21","13","31","14","41","15","51","16","61","17","71","18","81","19","91","22","77","23",
			"32","24","42","25","52","26","62","27","72","28","82","29","92","33","88","34","43","35","53","36","63","37","73",
			"38","83","39","93","44","99","45","54","46","64","47","74","48","84","49","94","56","65","57","75","58","85","59",
			"95","67","76","68","86","69","96","78","87","79","97","89","98"};
	
	public LotoMTDAO(String table)
	{
		this.table = table;
	}
	
	public boolean save(LotoBean lotoBean) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO "+this.table+" (ketqua_id,	province_id, boso, thu, is_dacbiet, is_tongchan, is_tongle," +
					"	is_bochanle, is_bolechan, is_bochanchan, is_bolele,	is_bokep, is_bosatkep, tan_so, dau_so, dit_so,	tong_bo," +
					"   giai, ngay_quay, create_date, create_user, modify_date, modify_user ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
					"    ?,?,?,?,?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1, lotoBean.getKetqua_id());
			preStmt.setInt(2,lotoBean.getProvince_id());
			preStmt.setString(3,lotoBean.getBoso());
			preStmt.setInt(4,lotoBean.getThu());
			
			preStmt.setInt(5,lotoBean.getIs_dacbiet());
			preStmt.setInt(6,lotoBean.getIs_tongchan());
			preStmt.setInt(7,lotoBean.getIs_tongle());
			preStmt.setInt(8,lotoBean.getIs_bochanle());
			preStmt.setInt(9,lotoBean.getIs_bolechan());
			preStmt.setInt(10,lotoBean.getIs_bochanchan());
			preStmt.setInt(11,lotoBean.getIs_bolele());
			preStmt.setInt(12,lotoBean.getIs_bokep());
			preStmt.setInt(13,lotoBean.getIs_bosatkep());
			
			preStmt.setInt(14,lotoBean.getTan_so());
			preStmt.setInt(15,lotoBean.getDau_so());
			preStmt.setInt(16,lotoBean.getDit_so());
			preStmt.setInt(17,lotoBean.getTong_bo());
			
			preStmt.setString(18,lotoBean.getGiai());
			preStmt.setDate(19, lotoBean.getNgay_quay());
			preStmt.setTimestamp(20, lotoBean.getCreate_date());
			preStmt.setString(21, lotoBean.getCreate_user());
			
			preStmt.setTimestamp(22, lotoBean.getModify_date());
			preStmt.setString(23, lotoBean.getModify_user());
			
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
	
	
	
	public boolean checkLotoMienBac(Date open_date,String boso) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_mienbac WHERE ngay_quay = ? AND boso = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkLotoMienBac: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkLotoMienBac: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkLotoMienBac: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public boolean checkDeMienBac(Date open_date,String boso) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_mienbac WHERE ngay_quay = ? AND boso = ? AND is_dacbiet = 1 ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkDeMienBac: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkDeMienBac: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkDeMienBac: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public boolean checkDeMienNam(Date open_date,String boso,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_miennam WHERE ngay_quay = ? AND boso = ? AND is_dacbiet = 1 AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkDeMienBac: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkDeMienBac: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkDeMienBac: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public boolean checkDeMienTrung(Date open_date,String boso,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_mientrung WHERE ngay_quay = ? AND boso = ? AND is_dacbiet = 1 AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkDeMienTrung: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkDeMienTrung: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkDeMienTrung: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public boolean checkLoto2NhayMienBac(Date open_date,String boso) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT count(boso) as sl FROM thongke_loto_mienbac WHERE ngay_quay = ? AND boso = ? group by boso ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				int sl = rs.getInt("sl");
				if(sl>=2)
					kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkLoto2NhayMienBac: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkLoto2NhayMienBac: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkLoto2NhayMienBac: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	

	public boolean checkLoto2NhayMienNam(Date open_date,String boso,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT count(boso) as sl FROM thongke_loto_miennam WHERE ngay_quay = ? AND boso = ? AND province_id = ? group by boso ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3,province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				int sl = rs.getInt("sl");
				if(sl>=2)
					kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkLoto2NhayMienNam: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkLoto2NhayMienNam: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkLoto2NhayMienNam: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public boolean checkLoto2NhayMienTrung(Date open_date,String boso,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT count(boso) as sl FROM thongke_loto_mientrung WHERE ngay_quay = ? AND boso = ? AND province_id = ? group by boso ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3,province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				int sl = rs.getInt("sl");
				if(sl>=2)
					kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkLoto2NhayMienTrung: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkLoto2NhayMienTrung: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkLoto2NhayMienTrung: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public boolean checkLotoMienNam(Date open_date,String boso, int province_id) {
	//	System.out.println(boso);
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_miennam WHERE ngay_quay = ? AND boso = ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3, province_id);
			
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkLotoMienNam: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkLotoMienNam: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkLotoMienNam: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	
	public boolean checkLotoMienTrung(Date open_date,String boso, int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_mientrung WHERE ngay_quay = ? AND boso = ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3, province_id);
			
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkLotoMienTrung: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkLotoMienTrung: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkLotoMienTrung: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public HashMap<String,Integer> tkTanSoLanVe(Date open_date) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso, COUNT(boso) AS sl FROM thongke_loto_mienbac WHERE ngay_quay = ? GROUP BY boso ORDER BY sl DESC");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setDate(1, open_date);
			rs = preStmt.executeQuery();
			kqList = new HashMap<String,Integer>();
			while (rs.next()) {				
				kqList.put(rs.getString("boso"), rs.getInt("sl"));
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
		return kqList;
	}
	
		
	public HashMap<String,Integer> tkTanSoLanVe(Date open_date,int province_id) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso, COUNT(boso) AS sl FROM "+table+" WHERE ngay_quay = ? AND province_id = ? GROUP BY boso ORDER BY sl DESC");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setDate(1, open_date);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			kqList = new HashMap<String,Integer>();
			while (rs.next()) {				
				kqList.put(rs.getString("boso"), rs.getInt("sl"));
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
		return kqList;
	}
	
	public HashMap<String,Integer> tkTanSoLanVeDB(Date open_date) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso, COUNT(boso) AS sl FROM thongke_loto_mienbac WHERE ngay_quay = ? AND is_dacbiet = 1 GROUP BY boso ORDER BY sl DESC");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setDate(1, open_date);
			rs = preStmt.executeQuery();
			kqList = new HashMap<String,Integer>();
			while (rs.next()) {				
				kqList.put(rs.getString("boso"), rs.getInt("sl"));
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
		return kqList;
	}
	
	
	public HashMap<String,Integer> tkTanSoLanVeDB(Date open_date, int province_id) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso, COUNT(boso) AS sl FROM "+table+" WHERE ngay_quay = ? AND is_dacbiet = 1 AND province_id = ? GROUP BY boso ORDER BY sl DESC");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDate(1, open_date);
			preStmt.setInt(1, province_id);
			rs = preStmt.executeQuery();
			kqList = new HashMap<String,Integer>();
			while (rs.next()) {				
				kqList.put(rs.getString("boso"), rs.getInt("sl"));
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
		return kqList;
	}
	
	public HashMap<String,Integer> tkTanSoLanVe(Date fromDate, Date toDate) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso, COUNT(boso) AS sl FROM thongke_loto_mienbac WHERE ngay_quay BETWEEN ? AND ? GROUP BY boso ORDER BY sl DESC");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setDate(1, fromDate);
			preStmt.setDate(2, toDate);
			
			rs = preStmt.executeQuery();
			kqList = new HashMap<String,Integer>();
			while (rs.next()) {				
				kqList.put(rs.getString("boso"), rs.getInt("sl"));
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
		return kqList;
	}
	
	public HashMap<String,Integer> tkTanSoLanVeByProvince(Date fromDate, Date toDate,int provinceId) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();
			;
			strSQL = new StringBuffer(
					"SELECT boso, COUNT(boso) AS sl FROM "+table+" WHERE (ngay_quay BETWEEN ? AND ? ) AND province_id = ? GROUP BY boso ORDER BY sl DESC");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setDate(1, fromDate);
			preStmt.setDate(2, toDate);
			preStmt.setInt(3, provinceId);
			
			rs = preStmt.executeQuery();
			kqList = new HashMap<String,Integer>();
			while (rs.next()) {				
				kqList.put(rs.getString("boso"), rs.getInt("sl"));
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
		return kqList;
	}

	public HashMap<String,Integer> tkTanSoNgayVe(Date fromDate, Date toDate) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso,COUNT(*) AS sl  FROM (SELECT boso, ngay_quay FROM thongke_loto_mienbac WHERE ngay_quay " +
					"BETWEEN ? AND ? GROUP BY boso, ngay_quay) AS tk GROUP BY boso ORDER BY sl desc");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setDate(1, fromDate);
			preStmt.setDate(2, toDate);
			
			rs = preStmt.executeQuery();
			kqList = new HashMap<String,Integer>();
			while (rs.next()) {				
				kqList.put(rs.getString("boso"),rs.getInt("sl"));
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
		return kqList;
	}
	
	public String createTableChuKy(Date bien_ngay,int solan_quay)
	{
		long miliSecondDay = 86400000;		
		String table="<table width='90%' cellspacing='0' cellpadding='0' border='0'>";
		long fromDate =  bien_ngay.getTime()-solan_quay*miliSecondDay;
		long toDate = bien_ngay.getTime();
		HashMap<String, Integer> listKQLoto = null;
		HashMap<String, Integer> listTansoLoto = new HashMap<String, Integer>();
		table +="<tr><td><strong>Ngày</strong></td>";
		int i = 0;
		while(i<boso.length)
		{
			table +="<td align=center>"+boso[i]+"</td>";
			listTansoLoto.put(boso[i], 0);
			i++;
		}
		table +="</tr>";
		DateFormat format = new SimpleDateFormat("dd/MM");
		String tsloto="";
		int tongTanSo =0;
		while(fromDate<=toDate)
		{			
			listKQLoto = this.tkTanSoLanVe(new Date(fromDate));
			table +="<tr><td >"+format.format(new Date(fromDate))+"</td>";
			 i = 0;
				while(i<boso.length)
				{
					tsloto=listKQLoto.containsKey(boso[i])?listKQLoto.get(boso[i])+"":"";
					tongTanSo =0;
					tongTanSo += listKQLoto.containsKey(boso[i])?listKQLoto.get(boso[i]):0;
					listTansoLoto.put(boso[i], listTansoLoto.get(boso[i])+tongTanSo);
					table +="<td align=center><strong>"+ tsloto +"</strong></td>";
					i++;
				}
			table +="</tr>";
			fromDate = fromDate+miliSecondDay;
		}
		
		table +="<tr><td>TK Số</td>";
		i = 0;
		while(i<boso.length)
		{
			table +="<td align=center><strong>"+listTansoLoto.get(boso[i])+"</strong></td>";			
			i++;
		}
		table +="</tr>";
		i = 0;tongTanSo =0;
		
		table +="<tr><td>TK_Cặp</td>";
		String tanso="";
		while(i<boso.length)
		{
			tongTanSo += listTansoLoto.get(boso[i]);
			tanso = i%2==0?"":tongTanSo+"";
			table +="<td align=center><strong>"+tanso+"</strong></td>";	
			tongTanSo = i%2!=0?0:tongTanSo;
			i++;
		}
		table +="</tr>";
		table +="</table>";
		return table;
	}
	
	public String createTableChuKyDB(Date bien_ngay,int solan_quay)
	{
		long miliSecondDay = 86400000;		
		String table="<table width='90%' cellspacing='0' cellpadding='0' border='0'>";
		long fromDate =  bien_ngay.getTime()-solan_quay*miliSecondDay;
		long toDate = bien_ngay.getTime();
		HashMap<String, Integer> listKQLoto = null;
		HashMap<String, Integer> listTansoLoto = new HashMap<String, Integer>();
		table +="<tr><td><strong>Ngày</strong></td>";
		int i = 0;
		while(i<boso.length)
		{
			table +="<td align=center><strong>"+boso[i]+"</strong></td>";
			listTansoLoto.put(boso[i], 0);
			i++;
		}
		table +="</tr>";
		DateFormat format = new SimpleDateFormat("dd/MM");
		String tsloto="";
		int tongTanSo =0;
		while(fromDate<=toDate)
		{			
			listKQLoto = this.tkTanSoLanVeDB(new Date(fromDate));
			table +="<tr><td >"+format.format(new Date(fromDate))+"</td>";
			 i = 0;
				while(i<boso.length)
				{
					tsloto=listKQLoto.containsKey(boso[i])?listKQLoto.get(boso[i])+"":"";
					tongTanSo =0;
					tongTanSo += listKQLoto.containsKey(boso[i])?listKQLoto.get(boso[i]):0;
					listTansoLoto.put(boso[i], listTansoLoto.get(boso[i])+tongTanSo);
					table +="<td align=center><strong>"+ tsloto +"</strong></td>";
					i++;
				}
			table +="</tr>";
			fromDate = fromDate+miliSecondDay;
		}
		
		table +="<tr><td>TK Số</td>";
		i = 0;
		while(i<boso.length)
		{
			table +="<td align=center><strong>"+listTansoLoto.get(boso[i])+"</strong></td>";			
			i++;
		}
		table +="</tr>";
		i = 0;tongTanSo =0;
		
		table +="<tr><td>TK_Cặp</td>";
		String tanso="";
		while(i<boso.length)
		{
			tongTanSo += listTansoLoto.get(boso[i]);
			tanso = i%2==0?"":tongTanSo+"";
			table +="<td align=center><strong>"+tanso+"</strong></td>";	
			tongTanSo = i%2!=0?0:tongTanSo;
			i++;
		}
		table +="</tr>";
		table +="</table>";
		return table;
	}
	
	public String createTableChuKyByProvince(Date bien_ngay,int solan_quay,int province_id)
	{
		long miliSecondDay = 86400000;		
		String table="<table  width='90%' cellspacing='0' cellpadding='0' border='0'>";
		long fromDate =  bien_ngay.getTime()-solan_quay*miliSecondDay;
		long toDate = bien_ngay.getTime();
		HashMap<String, Integer> listKQLoto = null;
		HashMap<String, Integer> listTansoLoto = new HashMap<String, Integer>();
		table +="<tr><td><strong>Ngày</strong></td>";
		int i = 0;
		while(i<boso.length)
		{
			table +="<td align=center><strong>"+boso[i]+"</strong></td>";
			listTansoLoto.put(boso[i], 0);
			i++;
		}
		table +="</tr>";
		DateFormat format = new SimpleDateFormat("dd/MM");
		String tsloto="";
		int tongTanSo =0;
		while(fromDate<=toDate)
		{			
			listKQLoto = this.tkTanSoLanVe(new Date(fromDate),province_id);
			table +="<tr><td >"+format.format(new Date(fromDate))+"</td>";
			 i = 0;
				while(i<boso.length)
				{
					tsloto=listKQLoto.containsKey(boso[i])?listKQLoto.get(boso[i])+"":"";
					tongTanSo =0;
					tongTanSo += listKQLoto.containsKey(boso[i])?listKQLoto.get(boso[i]):0;
					listTansoLoto.put(boso[i], listTansoLoto.get(boso[i])+tongTanSo);
					table +="<td align=center><strong>"+ tsloto +"</strong></td>";
					i++;
				}
			table +="</tr>";
			fromDate = fromDate+miliSecondDay;
		}
		
		table +="<tr><td>TK Số</td>";
		i = 0;
		while(i<boso.length)
		{
			table +="<td align=center><strong>"+listTansoLoto.get(boso[i])+"</strong></td>";			
			i++;
		}
		table +="</tr>";
		i = 0;tongTanSo =0;
		
		table +="<tr><td>TK_Cặp</td>";
		String tanso="";
		while(i<boso.length)
		{
			tongTanSo += listTansoLoto.get(boso[i]);
			tanso = i%2==0?"":tongTanSo+"";
			table +="<td align=center><strong>"+tanso+"</strong></td>";	
			tongTanSo = i%2!=0?0:tongTanSo;
			i++;
		}
		table +="</tr>";
		table +="</table>";
		return table;
	}
	
	public HashMap<String,Integer> tkTanSoNgayVeByProvince(Date fromDate, Date toDate, int province_id) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso,COUNT(*) AS sl  FROM (SELECT boso, ngay_quay FROM "+table+" WHERE (ngay_quay " +
					"BETWEEN ? AND ?) AND province_id = ? GROUP BY boso, ngay_quay) AS tk GROUP BY boso ORDER BY sl desc");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setDate(1, fromDate);
			preStmt.setDate(2, toDate);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			kqList = new HashMap<String,Integer>();
			while (rs.next()) {				
				kqList.put(rs.getString("boso"),rs.getInt("sl"));
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
		return kqList;
	}

	
	

	public static void main_ts_nb(String[] args) {
		//2011-04-18 15:05:35
		//2009-01-01
		//10 ngay
		int sn = 500;
		long miliSecondDay = 86400000;
		long bienngay = sn*miliSecondDay;
		
		long endDate = Date.valueOf("2011-05-31").getTime();
		long beginDate = Date.valueOf("2009-01-01").getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(endDate-miliSecondDay);
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(Calendar.MONTH));
		System.out.println(calendar.get(Calendar.YEAR));
		
		LotoMTDAO lotoDAO = new LotoMTDAO("thongke_loto_mienbac");
		TanSuatBoSoDAO tanSuatBoSoDAO = new TanSuatBoSoDAO("thongke_tansuat_loto_mienbac");
		Date fromDate = null,toDate=null;
		HashMap<String, Integer> lisTS = null;
		HashMap<String, Integer> lisNV = null;
		TanSuatBoSoBean tanSuatBoSoBean = null;
		int i = 0;String boso="00";
		while(beginDate<endDate)
		{
			toDate = new Date(endDate);
			fromDate = new Date(endDate-bienngay);
			
			lisTS = lotoDAO.tkTanSoLanVe(fromDate, toDate);
			lisNV = lotoDAO.tkTanSoNgayVe(fromDate, toDate);
			i = 0;
			while(i<99)
			{
				boso=String.valueOf(i).length()==1?"0"+i:i+"";
				if(lisTS.containsKey(boso))
				{
					
					tanSuatBoSoBean = new TanSuatBoSoBean();
					tanSuatBoSoBean.setBoso(boso);
					tanSuatBoSoBean.setCreate_date(DateProc.createTimestamp());
					tanSuatBoSoBean.setKhoang_thoigian(sn);
					
					System.out.println((double)lisTS.get(boso)/sn);
					
					tanSuatBoSoBean.setP_so_lan_ve(((double)lisTS.get(boso)/(sn*27))*100);					
					tanSuatBoSoBean.setSo_lan_ve(lisTS.get(boso));
					
					tanSuatBoSoBean.setSo_ngay_ve(lisNV.get(boso));
					tanSuatBoSoBean.setP_so_ngay_ve(((double)lisNV.get(boso)/sn)*100);
					
					tanSuatBoSoBean.setP_so_lan_ngay(((double)lisTS.get(boso)/lisNV.get(boso))*100);
					tanSuatBoSoBean.setProvince_id(0);
					tanSuatBoSoBean.setBien_ngay(toDate);
					tanSuatBoSoDAO.save(tanSuatBoSoBean);
				}
				i++;
			}
			
			
			calendar = Calendar.getInstance();
			calendar.setTimeInMillis(fromDate.getTime());
			System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
			System.out.println(calendar.get(Calendar.MONTH));
			System.out.println(calendar.get(Calendar.YEAR));
			
			calendar = Calendar.getInstance();
			calendar.setTimeInMillis(toDate.getTime());
			System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
			System.out.println(calendar.get(Calendar.MONTH));
			System.out.println(calendar.get(Calendar.YEAR));
			
			endDate = endDate - miliSecondDay;
			
		}
		
	}
	
	
	public static void main(String[] args) {
		//2011-04-18 15:05:35
		//2009-01-01
		//10 ngay
		int sn = 20;
		long miliSecondDay = 86400000;
		long bienngay = sn*miliSecondDay;
		
		long endDate = Date.valueOf("2010-01-29").getTime();
		long beginDate = Date.valueOf("2009-01-01").getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(endDate-miliSecondDay);
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(Calendar.MONTH));
		System.out.println(calendar.get(Calendar.YEAR));
		
		LotoMTDAO lotoDAO = new LotoMTDAO("thongke_loto_mientrung");
		ProvinceDAO provinceDAO = new ProvinceDAO();
		List<ProvinceBean> listProvince = provinceDAO.findAll(2);
		TanSuatBoSoDAO tanSuatBoSoDAO = new TanSuatBoSoDAO("thongke_tansuat_loto_mientrung");
		Date fromDate = null,toDate=null;
		HashMap<String, Integer> lisTS = null;
		HashMap<String, Integer> lisNV = null;
		TanSuatBoSoBean tanSuatBoSoBean = null;
		int i = 0;String boso="00";int j=0;
		ProvinceBean provinceBean = null;
		while(beginDate<endDate)
		{
			toDate = new Date(endDate);
			fromDate = new Date(endDate-bienngay);
			j=0;
			while(j<listProvince.size())
			{			
			provinceBean = listProvince.get(j);
			System.out.println(provinceBean.getName());
			lisTS = lotoDAO.tkTanSoLanVeByProvince(fromDate, toDate,provinceBean.getId());
			lisNV = lotoDAO.tkTanSoNgayVeByProvince(fromDate, toDate,provinceBean.getId());
				i = 0;
				if(lisTS.size()>0)
				{
					while(i<99)
					{
						boso=String.valueOf(i).length()==1?"0"+i:i+"";
						if(lisTS.containsKey(boso))
						{
							
							tanSuatBoSoBean = new TanSuatBoSoBean();
							tanSuatBoSoBean.setBoso(boso);
							tanSuatBoSoBean.setCreate_date(DateProc.createTimestamp());
							tanSuatBoSoBean.setKhoang_thoigian(sn);
							
							System.out.println((double)lisTS.get(boso)/sn);
							
							tanSuatBoSoBean.setP_so_lan_ve(((double)lisTS.get(boso)/(sn*27))*100);					
							tanSuatBoSoBean.setSo_lan_ve(lisTS.get(boso));
							
							tanSuatBoSoBean.setSo_ngay_ve(lisNV.get(boso));
							tanSuatBoSoBean.setP_so_ngay_ve(((double)lisNV.get(boso)/sn)*100);
							
							tanSuatBoSoBean.setP_so_lan_ngay(((double)lisTS.get(boso)/lisNV.get(boso))*100);
							tanSuatBoSoBean.setProvince_id(provinceBean.getId());
							tanSuatBoSoBean.setBien_ngay(toDate);
							tanSuatBoSoDAO.save(tanSuatBoSoBean);
						}
						i++;
					}
				}
				j++;
			}
			
			calendar = Calendar.getInstance();
			calendar.setTimeInMillis(fromDate.getTime());
			System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
			System.out.println(calendar.get(Calendar.MONTH));
			System.out.println(calendar.get(Calendar.YEAR));
			
			calendar = Calendar.getInstance();
			calendar.setTimeInMillis(toDate.getTime());
			System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
			System.out.println(calendar.get(Calendar.MONTH));
			System.out.println(calendar.get(Calendar.YEAR));
			
			endDate = endDate - miliSecondDay;
			
		}
		
	}
	
	public static void main_chuki(String[] args) {
		LotoMTDAO lotoDAO = new LotoMTDAO("thongke_loto_mienbac");
		BangTanSuatLotoDAO bangTanSuatLotoDAO = new BangTanSuatLotoDAO();
		BangTanSuatLotoBean bean = new BangTanSuatLotoBean();
		
		bean.setProvince_id(1);
		bean.setContent(lotoDAO.createTableChuKy(Date.valueOf("2011-04-18"), 10));
		bean.setNgay_bien(Date.valueOf("2011-04-18"));
		bean.setCreate_date(DateProc.createTimestamp());
		bean.setSo_ngay(10);
		
		bangTanSuatLotoDAO.save(bean);
		
	}
	
	public static void main7(String[] args) {
		LotoMTDAO lotoDAO = new LotoMTDAO("thongke_loto_mienbac");
		BangTanSuatLotoDAO bangTanSuatLotoDAO = new BangTanSuatLotoDAO();
		bangTanSuatLotoDAO.setTable("thongke_bang_tansuatdacbiet");
		BangTanSuatLotoBean bean = new BangTanSuatLotoBean();
		
		bean.setProvince_id(1);
		bean.setContent(lotoDAO.createTableChuKyDB(Date.valueOf("2011-04-18"), 30));
		bean.setNgay_bien(Date.valueOf("2011-04-18"));
		bean.setCreate_date(DateProc.createTimestamp());
		bean.setSo_ngay(30);
		
		bangTanSuatLotoDAO.save(bean);
		
	}
	
	public static void main8(String[] args) {
		LotoMTDAO lotoDAO = new LotoMTDAO("thongke_loto_miennam");
		ProvinceDAO provinceDAO = new ProvinceDAO();
		Vector<ProvinceBean> listPro = provinceDAO.findAll(3);
		BangTanSuatLotoDAO bangTanSuatLotoDAO = new BangTanSuatLotoDAO();
		
		for (ProvinceBean provinceBean : listPro) {
			BangTanSuatLotoBean bean = new BangTanSuatLotoBean();		
			bean.setProvince_id(0);
			bean.setContent(lotoDAO.createTableChuKyByProvince(Date.valueOf("2011-04-18"), 10,provinceBean.getId()));
			bean.setNgay_bien(Date.valueOf("2011-04-18"));
			bean.setCreate_date(DateProc.createTimestamp());
			bean.setSo_ngay(10);		
			bangTanSuatLotoDAO.save(bean);
		}
		
		
	}
	
	public static void main9(String[] args) {
		LotoMTDAO lotoDAO = new LotoMTDAO("thongke_loto_mientrung");
		ProvinceDAO provinceDAO = new ProvinceDAO();
		Vector<ProvinceBean> listPro = provinceDAO.findAll(2);
		BangTanSuatLotoDAO bangTanSuatLotoDAO = new BangTanSuatLotoDAO();
		for (ProvinceBean provinceBean : listPro) {
			BangTanSuatLotoBean bean = new BangTanSuatLotoBean();		
			bean.setProvince_id(0);
			bean.setContent(lotoDAO.createTableChuKyByProvince(Date.valueOf("2011-04-18"), 30,provinceBean.getId()));
			bean.setNgay_bien(Date.valueOf("2011-04-18"));
			bean.setCreate_date(DateProc.createTimestamp());
			bean.setSo_ngay(30);		
			bangTanSuatLotoDAO.save(bean);
		}
		
		
	}
}
