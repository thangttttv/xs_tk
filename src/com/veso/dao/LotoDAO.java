package com.veso.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.veso.bean.BangTanSuatLotoBean;
import com.veso.bean.BoSoThongKe;
import com.veso.bean.LotoBean;
import com.veso.bean.ProvinceBean;
import com.veso.bean.TKLotoGanCucDai;
import com.veso.bean.TKSMSContent;
import com.veso.bean.TanSuatBoSoBean;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.DateProc;
import com.veso.util.Logger;

public class LotoDAO {
	private String table = "thongke_loto_mienbac";
	private Logger logger = new Logger(this.getClass().getName());
	
	private String boso[] =  {"00","55","01","10","02","20","03","30","04","40","05","50","06","60","07","70","08","80",
			"09","90","11","66","12","21","13","31","14","41","15","51","16","61","17","71","18","81","19","91","22","77","23",
			"32","24","42","25","52","26","62","27","72","28","82","29","92","33","88","34","43","35","53","36","63","37","73",
			"38","83","39","93","44","99","45","54","46","64","47","74","48","84","49","94","56","65","57","75","58","85","59",
			"95","67","76","68","86","69","96","78","87","79","97","89","98"};
	
	public LotoDAO(String table)
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
	
	
	public boolean checkLotoMienBacByNgayQuay(Date open_date) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_mienbac WHERE ngay_quay = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
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
	
	public boolean checkLotoMienNamByNgayQuay(Date open_date) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_miennam WHERE ngay_quay = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
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
	
	public boolean checkLotoMienNamByNgayQuay(Date open_date,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_miennam WHERE ngay_quay = ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setInt(2,province_id);
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
	
	public boolean checkLotoMienTrungByNgayQuay(Date open_date) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_mientrung WHERE ngay_quay = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
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
	
	
	public boolean checkLotoMienTrungByNgayQuay(Date open_date,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_mientrung WHERE ngay_quay = ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setInt(2, province_id);
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
	
	
	public int checkLotoMienBac(Date open_date,String boso) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int kq = 0;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT count(id) FROM thongke_loto_mienbac WHERE ngay_quay = ? AND boso = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = rs.getInt(1);
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
	
	public int checkLotoMienBacBachThu(Date open_date,String boso) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int kq = 0;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT count(id) FROM thongke_loto_mienbac WHERE ngay_quay = ? AND boso = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = rs.getInt(1);
				if(kq>1) kq = 0;
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
	
	
	public boolean checkDauDeMienBac(Date open_date,String dau_boso) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_mienbac WHERE ngay_quay = ? AND boso like ? AND is_dacbiet = 1 ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, dau_boso+"%");
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkDeMienBac: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkDeMienBac: Error executing SQL >>>" + strSQL.toString()+ se.toString());
		} catch (Exception e) {
			logger.error("checkDeMienBac: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public boolean checkDuoiDeMienBac(Date open_date,String dau_boso) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_mienbac WHERE ngay_quay = ? AND boso like ? AND is_dacbiet = 1 ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2,"%"+dau_boso);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkDuoiDeMienBac: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkDuoiDeMienBac: Error executing SQL >>>" + strSQL.toString()+ se.toString());
		} catch (Exception e) {
			logger.error("checkDuoiDeMienBac: Error executing >>>" + e.toString());
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
			logger.error("checkDeMienNam: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkDeMienNam: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkDeMienNam: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	
	public boolean checkBosoG8MienNam(Date open_date,String boso,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_miennam WHERE ngay_quay = ? AND boso = ? AND giai = 'G8' AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkBosoG8MienNam: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkBosoG8MienNam: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkBosoG8MienNam: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	
	public boolean checkBo3soG7MienNam(Date open_date,String bo3so,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT * FROM ketqua_miennam WHERE ngay_quay = ? AND giai_bay = ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, bo3so);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkBo3soG7MienNam: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkBo3soG7MienNam: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkBo3soG7MienNam: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public boolean checkBo3soG7MienTrung(Date open_date,String bo3so,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT * FROM ketqua_mientrung WHERE ngay_quay = ? AND giai_bay = ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, bo3so);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkBo3soG7MienNam: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkBo3soG7MienNam: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkBo3soG7MienNam: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public boolean checkBo3soGDBMienNam(Date open_date,String bo3so,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT * FROM ketqua_miennam WHERE ngay_quay = ? AND giai_dacbiet like ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, "%"+bo3so);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkBo3soGDBMienNam: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkBo3soGDBMienNam: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkBo3soGDBMienNam: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	
	public boolean checkBo3soGDBMienTrung(Date open_date,String bo3so,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT * FROM ketqua_mientrung WHERE ngay_quay = ? AND giai_dacbiet like ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, "%"+bo3so);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkBo3soGDBMienTrung: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkBo3soGDBMienTrung: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkBo3soGDBMienTrung: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	
	public boolean checkSoCuoiDBMienTrung(Date open_date,String socuoi,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT * FROM ketqua_mientrung WHERE ngay_quay = ? AND giai_dacbiet like ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, "%"+socuoi);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkSoCuoiDBMienTrung: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkSoCuoiDBMienTrung: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkSoCuoiDBMienTrung: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	
	public boolean checkSoCuoiDBMienNam(Date open_date,String socuoi,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT * FROM ketqua_miennam WHERE ngay_quay = ? AND giai_dacbiet like ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, "%"+socuoi);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkSoCuoiDBMienNam: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkSoCuoiDBMienNam: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkSoCuoiDBMienNam: Error executing >>>" + e.toString());
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
	
	public boolean checkBosoG8MienTrung(Date open_date,String boso,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso FROM thongke_loto_mientrung WHERE ngay_quay = ? AND boso = ? AND giai = 'G8' AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3, province_id);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkBosoG8MienTrung: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkBosoG8MienTrung: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkBosoG8MienTrung: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	public boolean checkLotoTruotMienBac(Date open_date,String[] bosos) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = true;
		String str_boso= "";
		for (String string : bosos) {
			str_boso +=string+",";
		}
		str_boso = str_boso.substring(0, str_boso.length()-1);
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT boso FROM thongke_loto_mienbac WHERE ngay_quay = ? AND boso in (?) ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, str_boso);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = false;
			}
		} catch (NoSuchElementException nse) {
			logger.error("checkLotoTruotMienBac: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkLotoTruotMienBac: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkLotoTruotMienBac: Error executing >>>" + e.toString());
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
	
	public int checkLotoMienNam(Date open_date,String boso, int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int kq = 0;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT count(id) FROM thongke_loto_miennam WHERE ngay_quay = ? AND boso = ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3, province_id);
			
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = rs.getInt(1);
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
	
	
	public int checkLotoMienNamBachThu(Date open_date,String boso, int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int kq = 0;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT count(id) FROM thongke_loto_miennam WHERE ngay_quay = ? AND boso = ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3, province_id);
			
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = rs.getInt(1);
				if(kq>1) kq = 0;
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
	
	
	public int checkLotoMienTrung(Date open_date,String boso, int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int kq = 0;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT count(id) FROM thongke_loto_mientrung WHERE ngay_quay = ? AND boso = ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3, province_id);
			
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = rs.getInt(1);
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
	
	public int checkLotoMienTrungBachThu(Date open_date,String boso, int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int kq = 0;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT count(id) FROM thongke_loto_mientrung WHERE ngay_quay = ? AND boso = ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			
			preStmt.setDate(1, open_date);
			preStmt.setString(2, boso);
			preStmt.setInt(3, province_id);
			
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = rs.getInt(1);
				if(kq>1) kq = 0;
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
			logger.error("tkTanSoLanVeDB: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("tkTanSoLanVeDB: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("tkTanSoLanVeDB: Error executing >>>" + e.toString());
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
			logger.error("tkTanSoLanVeDB: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("tkTanSoLanVeDB: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("tkTanSoLanVeDB: Error executing >>>" + e.toString());
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
			logger.error("tkTanSoLanVe: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("tkTanSoLanVe: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("tkTanSoLanVe: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kqList;
	}
	
	
	public HashMap<String,Integer> tkTanSoLanVeDacBiet(Date fromDate, Date toDate) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso, COUNT(boso) AS sl FROM thongke_loto_mienbac WHERE ngay_quay BETWEEN ? AND ? And is_dacbiet = 1 GROUP BY boso ORDER BY sl DESC");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setDate(1, fromDate);
			preStmt.setDate(2, toDate);
			
			rs = preStmt.executeQuery();
			kqList = new HashMap<String,Integer>();
			while (rs.next()) {				
				kqList.put(rs.getString("boso"), rs.getInt("sl"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("tkTanSoLanVeDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("tkTanSoLanVeDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("tkTanSoLanVeDacBiet: Error executing >>>" + e.toString());
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
			logger.error("tkTanSoLanVeByProvince: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("tkTanSoLanVeByProvince: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("tkTanSoLanVeByProvince: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kqList;
	}
	
	public HashMap<String,Integer> tkTanSoLanVeByProvinceDacBiet(Date fromDate, Date toDate,int provinceId) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();
			;
			strSQL = new StringBuffer(
					"SELECT boso, COUNT(boso) AS sl FROM "+table+" WHERE (ngay_quay BETWEEN ? AND ? ) AND province_id = ? And is_dacbiet = 1 GROUP BY boso ORDER BY sl DESC");
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
			logger.error("tkTanSoLanVeByProvinceDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("tkTanSoLanVeByProvinceDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("tkTanSoLanVeByProvinceDacBiet: Error executing >>>" + e.toString());
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
			logger.error("tkTanSoNgayVe: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("tkTanSoNgayVe: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("tkTanSoNgayVe: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kqList;
	}
	
	public HashMap<String,Integer> tkTanSoNgayVeDacBiet(Date fromDate, Date toDate) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso,COUNT(*) AS sl  FROM (SELECT boso, ngay_quay FROM thongke_loto_mienbac WHERE ngay_quay " +
					"BETWEEN ? AND ? And is_dacbiet = 1  GROUP BY boso, ngay_quay) AS tk GROUP BY boso ORDER BY sl desc");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setDate(1, fromDate);
			preStmt.setDate(2, toDate);
			
			rs = preStmt.executeQuery();
			kqList = new HashMap<String,Integer>();
			while (rs.next()) {				
				kqList.put(rs.getString("boso"),rs.getInt("sl"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("tkTanSoNgayVeDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("tkTanSoNgayVeDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("tkTanSoNgayVeDacBiet: Error executing >>>" + e.toString());
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
			logger.error("tkTanSoNgayVeByProvince: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("tkTanSoNgayVeByProvince: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("tkTanSoNgayVeByProvince: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kqList;
	}

	public HashMap<String,Integer> tkTanSoNgayVeByProvinceDacBiet(Date fromDate, Date toDate, int province_id) {

		HashMap<String,Integer> kqList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT boso,COUNT(*) AS sl  FROM (SELECT boso, ngay_quay FROM "+table+" WHERE (ngay_quay " +
					"BETWEEN ? AND ?) AND province_id = ? And is_dacbiet = 1 GROUP BY boso, ngay_quay)  AS tk GROUP BY boso ORDER BY sl desc");
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
			logger.error("tkTanSoNgayVeByProvinceDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("tkTanSoNgayVeByProvinceDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("tkTanSoNgayVeByProvinceDacBiet: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kqList;
	}
	

	public static void main2121(String[] args) {
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
		
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
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
	
	
	public static void main_nm(String[] args) {
		//2011-04-18 15:05:35
		//2009-01-01
		//10 ngay
		int sn = 20;
		long miliSecondDay = 86400000;
		long bienngay = sn*miliSecondDay;
		
		long endDate = Date.valueOf("2009-10-06").getTime();
		long beginDate = Date.valueOf("2009-01-01").getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(endDate-miliSecondDay);
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(Calendar.MONTH));
		System.out.println(calendar.get(Calendar.YEAR));
		
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_miennam");
		ProvinceDAO provinceDAO = new ProvinceDAO();
		List<ProvinceBean> listProvince = provinceDAO.findAll(3);
		TanSuatBoSoDAO tanSuatBoSoDAO = new TanSuatBoSoDAO("thongke_tansuat_loto_miennam");
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
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
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
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
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
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_miennam");
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
	
	public List<String> getLoto(Date open_date) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> bosos = new ArrayList<String>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT distinct boso FROM "+this.table+" WHERE ngay_quay = ?  ORDER BY boso ASC ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos.add(rs.getString("boso"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("getLoto: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLoto: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLoto: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return bosos;
	}
	
	public List<String> getLotoDacBiet(Date open_date) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> bosos = new ArrayList<String>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT distinct boso FROM "+this.table+" WHERE ngay_quay = ? AND is_dacbiet = 1 ORDER BY boso ASC ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos.add(rs.getString("boso"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("getLotoDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLotoDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLotoDacBiet: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return bosos;
	}
	
	public List<String> getLoto(Date open_date,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> bosos = new ArrayList<String>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT distinct boso FROM "+this.table+" WHERE ngay_quay = ? AND province_id = ? ORDER BY boso ASC ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos.add(rs.getString("boso"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("getLoto: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLoto: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLoto: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return bosos;
	}
	
	public List<String> getLotoDacBiet(Date open_date,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> bosos = new ArrayList<String>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT distinct boso FROM "+this.table+" WHERE ngay_quay = ? AND province_id = ? AND is_dacbiet = 1 ORDER BY boso ASC ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos.add(rs.getString("boso"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("getLotoDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLotoDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLotoDacBiet: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return bosos;
	}
	
	public List<String> getDauSoDacBiet(Date open_date) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> bosos = new ArrayList<String>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT distinct dau_so FROM "+this.table+" WHERE ngay_quay = ?  AND  is_dacbiet = 1  ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);			
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos.add(rs.getString("dau_so"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("getDauSoDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getDauSoDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getDauSoDacBiet: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return bosos;
	}
	
	public List<Integer> getTongDacBiet(Date open_date) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<Integer> bosos = new ArrayList<Integer>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT distinct tong_bo FROM "+this.table+" WHERE ngay_quay = ?  AND  is_dacbiet = 1  ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);			
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos.add(rs.getInt("tong_bo"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("getTongDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getTongDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getTongDacBiet: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return bosos;
	}
	
	public List<Integer> getTongDacBiet(Date open_date,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<Integer> bosos = new ArrayList<Integer>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT distinct tong_bo FROM "+this.table+" WHERE ngay_quay = ?  AND  is_dacbiet = 1  AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos.add(rs.getInt("tong_bo"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("getTongDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getTongDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getTongDacBiet: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return bosos;
	}
	
	public List<String> getDauSoDacBiet(Date open_date,int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> bosos = new ArrayList<String>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT distinct dau_so FROM "+this.table+" WHERE ngay_quay = ?  AND  is_dacbiet = 1 AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos.add(rs.getString("dau_so"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("getDauSoDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getDauSoDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getDauSoDacBiet: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return bosos;
	}
	
	public List<String> getDuoiSoDacBiet(Date open_date, int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> bosos = new ArrayList<String>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT distinct dit_so FROM "+this.table+" WHERE ngay_quay = ?  AND  is_dacbiet = 1 AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos.add(rs.getString("dit_so"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("getDuoiSoDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getDuoiSoDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getDuoiSoDacBiet: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return bosos;
	}
	
	
	
	public List<String> getDuoiSoDacBiet(Date open_date) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> bosos = new ArrayList<String>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT distinct dit_so FROM "+this.table+" WHERE ngay_quay = ?  AND  is_dacbiet = 1 ");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setDate(1, open_date);		
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos.add(rs.getString("dit_so"));
			}

		} catch (NoSuchElementException nse) {
			logger.error("getDuoiSoDacBiet: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getDuoiSoDacBiet: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getDuoiSoDacBiet: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return bosos;
	}
	
	
	
	public List<LotoBean> getLoto(int start_id,String table) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		LotoBean lotoBean = null;
		List<LotoBean> list = new ArrayList<LotoBean>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT id,ketqua_id,province_id,boso,thu,is_dacbiet,is_tongchan,is_tongle,is_bochanle,is_bolechan,is_bochanchan," +
					" 	is_bolele,is_bokep,is_bosatkep,tan_so,dau_so,dit_so,tong_bo,giai,ngay_quay,create_date,create_user,modify_date," +
					"   modify_user FROM "+table+" Where id > ?  Order by id  ");
			
			preStmt = conn.prepareStatement(strSQL.toString());	
			preStmt.setInt(1,start_id);
			rs = preStmt.executeQuery();
			while (rs.next()) {		
				lotoBean = new LotoBean();
				lotoBean.setId(rs.getLong("id"));
				lotoBean.setKetqua_id(rs.getLong("ketqua_id"));
				lotoBean.setProvince_id(rs.getInt("province_id"));
				lotoBean.setBoso(rs.getString("boso"));
				lotoBean.setThu(rs.getInt("thu"));
				lotoBean.setIs_dacbiet(rs.getInt("is_dacbiet"));
				lotoBean.setIs_tongchan(rs.getInt("is_tongchan"));
				lotoBean.setIs_tongle(rs.getInt("is_tongle"));
				lotoBean.setIs_bochanle(rs.getInt("is_bochanle"));
				lotoBean.setIs_bolechan(rs.getInt("is_bolechan"));
				lotoBean.setIs_bochanchan(rs.getInt("is_bochanchan"));
				lotoBean.setIs_bolele(rs.getInt("is_bolele"));
				lotoBean.setIs_bokep(rs.getInt("is_bokep"));
				lotoBean.setIs_bosatkep(rs.getInt("is_bosatkep"));
				
				lotoBean.setTan_so(rs.getInt("tan_so"));
				lotoBean.setDau_so(rs.getInt("dau_so"));
				lotoBean.setDit_so(rs.getInt("dit_so"));
				lotoBean.setTong_bo(rs.getInt("tong_bo"));
				lotoBean.setGiai(rs.getString("giai"));
				
				lotoBean.setNgay_quay(rs.getDate("ngay_quay"));
				lotoBean.setCreate_date(rs.getTimestamp("create_date"));
				lotoBean.setCreate_user(rs.getString("create_user"));
				
				lotoBean.setModify_date(rs.getTimestamp("modify_date"));
				lotoBean.setModify_user(rs.getString("modify_user"));
				list.add(lotoBean);
			}

		} catch (NoSuchElementException nse) {
			logger.error("getLoto: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLoto: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLoto: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return list;
	}
	
	
	public List<BoSoThongKe> getBoSoVeNhieu(int province_id,int so_ngay_kt) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<BoSoThongKe> bosos = new ArrayList<BoSoThongKe>();
		 String pattern = "yyyy-MM-dd";
		 SimpleDateFormat format = new SimpleDateFormat(pattern);
		    
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT boso, COUNT(boso) AS sola FROM   "+table
					+ " WHERE ngay_quay >= DATE_SUB(NOW(),INTERVAL "+so_ngay_kt+" DAY) AND province_id = "+province_id+" "
					+ " GROUP BY boso ORDER BY sola DESC");
			System.out.println(strSQL);
			preStmt = conn.prepareStatement(strSQL.toString());			
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				BoSoThongKe boSoThongKe = new BoSoThongKe();
				boSoThongKe.boso = rs.getString("boso");
				boSoThongKe.solan = rs.getInt("sola");
				boSoThongKe.ngay_thongke = format.format(Calendar.getInstance().getTime());
				System.out.println(boSoThongKe.boso+"_"+boSoThongKe.solan);
				bosos.add(boSoThongKe);
			}

		} catch (NoSuchElementException nse) {
			logger.error("getBoSoVeNhieu: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getBoSoVeNhieu: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getBoSoVeNhieu: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return bosos;
	}
	
	public String getLastOpenDate(int province_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		String ngay_quay = "";
		    
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT ngay_quay FROM "+table+" Where province_id = "+province_id+" ORDER BY ngay_quay DESC LIMIT 1");
			System.out.println(strSQL);
			preStmt = conn.prepareStatement(strSQL.toString());			
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				ngay_quay =  rs.getString("ngay_quay");
			}
			
		} catch (NoSuchElementException nse) {
			logger.error("getLastOpenDate: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLastOpenDate: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLastOpenDate: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return ngay_quay;
	}
	
	public String getLotoTheoNgay(int province_id,String ngay_quay) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		String lotos = "";
		    
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT boso FROM "+table+" WHERE province_id ="+province_id+" AND ngay_quay = '"+ngay_quay+"'");
			System.out.println(strSQL);
			preStmt = conn.prepareStatement(strSQL.toString());			
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				lotos += rs.getString("boso")+",";
			}
			System.out.println(lotos);
			lotos = lotos.substring(0,lotos.length()-1);
			System.out.println(lotos);
		} catch (NoSuchElementException nse) {
			logger.error("getLotoTheoNgay: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLotoTheoNgay: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLotoTheoNgay: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return lotos;
	}
	
	
	public String getLotoDacBietTheoNgay(int province_id,String ngay_quay) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		String lotos = "";
		    
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT boso FROM "+table+" WHERE is_dacbiet = 1 AND province_id ="+province_id+" AND ngay_quay = '"+ngay_quay+"'");
			System.out.println(strSQL);
			preStmt = conn.prepareStatement(strSQL.toString());			
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				lotos += rs.getString("boso")+",";
			}
			System.out.println(lotos);
			lotos = lotos.substring(0,lotos.length()-1);
			System.out.println(lotos);
		} catch (NoSuchElementException nse) {
			logger.error("getLotoTheoNgay: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLotoTheoNgay: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLotoTheoNgay: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return lotos;
	}
	
	public String getTongDacBietTheoNgay(int province_id,String ngay_quay) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		String lotos = "";
		    
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT tong_bo FROM "+table+" WHERE is_dacbiet = 1 AND province_id ="+province_id+" AND ngay_quay = '"+ngay_quay+"'");
			System.out.println(strSQL);
			preStmt = conn.prepareStatement(strSQL.toString());			
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				lotos += rs.getString("tong_bo")+",";
			}
			System.out.println(lotos);
			lotos = lotos.substring(0,lotos.length()-1);
			System.out.println(lotos);
		} catch (NoSuchElementException nse) {
			logger.error("getLotoTheoNgay: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLotoTheoNgay: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLotoTheoNgay: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return lotos;
	}
	
	public String getChamDacBietTheoNgay(int province_id,String ngay_quay) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		String lotos = "";
		    
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT dau_so,dit_so FROM "+table+" WHERE is_dacbiet = 1 AND province_id ="+province_id+" AND ngay_quay = '"+ngay_quay+"'");
			System.out.println(strSQL);
			preStmt = conn.prepareStatement(strSQL.toString());			
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				lotos += rs.getString("dau_so")+","+rs.getString("dit_so")+",";
			}
			System.out.println(lotos);
			lotos = lotos.substring(0,lotos.length()-1);
			System.out.println(lotos);
		} catch (NoSuchElementException nse) {
			logger.error("getLotoTheoNgay: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLotoTheoNgay: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLotoTheoNgay: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return lotos;
	}
	
	
	
	public List<TKLotoGanCucDai> getLotoGan(int province_id,String ngay_quay) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> listLotoGan = new ArrayList<String>();
		List<TKLotoGanCucDai> lotoGanCucDai = new ArrayList<TKLotoGanCucDai>();
		
		// Get List Loto Gan
		String lotos = getLotoTheoNgay( province_id, ngay_quay);
		String arrLoto[] = lotos.split(",");
		int i = 0;
		while(i<boso.length){
			int j = 0;
			// check loto
			boolean kt = false;
			while(j<arrLoto.length){
				if(boso[i].equalsIgnoreCase(arrLoto[j]))
				{
					kt = true;
					break;
				}
				j++;
			}
			
			if(!kt) listLotoGan.add(boso[i]);
				
			i++;
		}
		
		
		try {
			conn = C3p0VesoPool.getConnection();	
			for (String boso : listLotoGan) {
				TKLotoGanCucDai loCucDai = new TKLotoGanCucDai();
				strSQL = new StringBuffer("SELECT  boso, ngay_quay FROM "+table+" WHERE province_id ="+province_id+" AND "
						+ "boso = '"+boso+"' ORDER BY ngay_quay DESC LIMIT 1");
				//System.out.println(strSQL);
				preStmt = conn.prepareStatement(strSQL.toString());			
				rs = preStmt.executeQuery();
				if (rs.next()) {				
					loCucDai.boso = boso;
					loCucDai.ngay_quay =  rs.getString("ngay_quay");
					long solanquay = ( Calendar.getInstance().getTimeInMillis() - rs.getDate("ngay_quay").getTime())/ (24*60*60*1000);
					loCucDai.lanquay_chuave = (int)solanquay;
					
					if(province_id==14||province_id==24) loCucDai.lanquay_chuave = ((int)solanquay/7)*2;
					else if(province_id==0||province_id==1) loCucDai.lanquay_chuave = (int)solanquay;
					else loCucDai.lanquay_chuave = (int)(solanquay/7);
					//System.out.println(boso+"-->"+solanquay);
					loCucDai.lanquay_chuave = loCucDai.lanquay_chuave -1;
					lotoGanCucDai.add(loCucDai);
				}
			}
			
		} catch (NoSuchElementException nse) {
			logger.error("getLastDateLotoGan: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLastDateLotoGan: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLastDateLotoGan: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		
		Collections.sort(lotoGanCucDai, new Comparator<TKLotoGanCucDai>() {
            @Override
            public int compare(TKLotoGanCucDai lhs, TKLotoGanCucDai rhs) {
                return lhs.lanquay_chuave > rhs.lanquay_chuave ? -1 : (lhs.lanquay_chuave < rhs.lanquay_chuave ) ? 1 : 0;
            }
        });
		
		return lotoGanCucDai;
	}
	
	
	public List<TKLotoGanCucDai> getLotoDacBietGan(int province_id,String ngay_quay) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> listLotoGan = new ArrayList<String>();
		List<TKLotoGanCucDai> lotoGanCucDai = new ArrayList<TKLotoGanCucDai>();
		
		// Get List Loto Gan
		String lotos = getLotoDacBietTheoNgay( province_id, ngay_quay);
		String arrLoto[] = lotos.split(",");
		int i = 0;
		while(i<boso.length){
			int j = 0;
			// check loto
			boolean kt = false;
			while(j<arrLoto.length){
				if(boso[i].equalsIgnoreCase(arrLoto[j]))
				{
					kt = true;
					break;
				}
				j++;
			}
			
			if(!kt) listLotoGan.add(boso[i]);
				
			i++;
		}
		
		
		try {
			conn = C3p0VesoPool.getConnection();	
			for (String boso : listLotoGan) {
				TKLotoGanCucDai loCucDai = new TKLotoGanCucDai();
				strSQL = new StringBuffer("SELECT  boso, ngay_quay FROM "+table+" WHERE is_dacbiet = 1 AND  province_id ="+province_id+" AND "
						+ "boso = '"+boso+"' ORDER BY ngay_quay DESC LIMIT 1");
				//System.out.println(strSQL);
				preStmt = conn.prepareStatement(strSQL.toString());			
				rs = preStmt.executeQuery();
				if (rs.next()) {				
					loCucDai.boso = boso;
					loCucDai.ngay_quay =  rs.getString("ngay_quay");
					long solanquay = ( Calendar.getInstance().getTimeInMillis() - rs.getDate("ngay_quay").getTime())/ (24*60*60*1000);
					loCucDai.lanquay_chuave = (int)solanquay;
					
					if(province_id==14||province_id==24) loCucDai.lanquay_chuave = ((int)solanquay/7)*2;
					else if(province_id==0||province_id==1) loCucDai.lanquay_chuave = (int)solanquay;
					else loCucDai.lanquay_chuave = (int)(solanquay/7);
					//System.out.println(boso+"-->"+solanquay);
					loCucDai.lanquay_chuave = loCucDai.lanquay_chuave - 1;
					lotoGanCucDai.add(loCucDai);
				}
			}
			
		} catch (NoSuchElementException nse) {
			logger.error("getLastDateLotoGan: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLastDateLotoGan: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLastDateLotoGan: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		
		Collections.sort(lotoGanCucDai, new Comparator<TKLotoGanCucDai>() {
            @Override
            public int compare(TKLotoGanCucDai lhs, TKLotoGanCucDai rhs) {
                return lhs.lanquay_chuave > rhs.lanquay_chuave ? -1 : (lhs.lanquay_chuave < rhs.lanquay_chuave ) ? 1 : 0;
            }
        });
		
		return lotoGanCucDai;
	}
	
	
	public List<TKLotoGanCucDai> getTongDacBietGan(int province_id,String ngay_quay) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> listLotoGan = new ArrayList<String>();
		List<TKLotoGanCucDai> lotoGanCucDai = new ArrayList<TKLotoGanCucDai>();
		
		// Get List Loto Gan
		String lotos = getTongDacBietTheoNgay( province_id, ngay_quay);
		String arrLoto[] = lotos.split(",");
		int i = 0;
		while(i<10){
			int j = 0;
			// check loto
			boolean kt = false;
			while(j<arrLoto.length){
				if(i==Integer.parseInt(arrLoto[j]))
				{
					kt = true;
					break;
				}
				j++;
			}
			
			if(!kt) listLotoGan.add(i+"");
				
			i++;
		}
		
		
		try {
			conn = C3p0VesoPool.getConnection();	
			for (String boso : listLotoGan) {
				TKLotoGanCucDai loCucDai = new TKLotoGanCucDai();
				strSQL = new StringBuffer("SELECT  tong_bo, ngay_quay FROM "+table+" WHERE is_dacbiet = 1 AND  province_id ="+province_id+" AND "
						+ "tong_bo = '"+boso+"' ORDER BY ngay_quay DESC LIMIT 1");
				System.out.println(strSQL);
				preStmt = conn.prepareStatement(strSQL.toString());			
				rs = preStmt.executeQuery();
				if (rs.next()) {				
					loCucDai.boso = boso;
					loCucDai.ngay_quay =  rs.getString("ngay_quay");
					long solanquay = ( Calendar.getInstance().getTimeInMillis() - rs.getDate("ngay_quay").getTime())/ (24*60*60*1000);
					loCucDai.lanquay_chuave = (int)solanquay;
					
					if(province_id==14||province_id==24) loCucDai.lanquay_chuave = ((int)solanquay/7)*2;
					else if(province_id==0||province_id==1) loCucDai.lanquay_chuave = (int)solanquay;
					else loCucDai.lanquay_chuave = (int)(solanquay/7);
					loCucDai.lanquay_chuave = loCucDai.lanquay_chuave  -1;
					System.out.println(boso+"-->"+solanquay);
					lotoGanCucDai.add(loCucDai);
				}
			}
			
		} catch (NoSuchElementException nse) {
			logger.error("getLastDateLotoGan: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLastDateLotoGan: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLastDateLotoGan: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		
		Collections.sort(lotoGanCucDai, new Comparator<TKLotoGanCucDai>() {
            @Override
            public int compare(TKLotoGanCucDai lhs, TKLotoGanCucDai rhs) {
                return lhs.lanquay_chuave > rhs.lanquay_chuave ? -1 : (lhs.lanquay_chuave < rhs.lanquay_chuave ) ? 1 : 0;
            }
        });
		
		return lotoGanCucDai;
	}
	
	
	public List<TKLotoGanCucDai> getChamgDacBietGan(int province_id,String ngay_quay) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		List<String> listLotoGan = new ArrayList<String>();
		List<TKLotoGanCucDai> lotoGanCucDai = new ArrayList<TKLotoGanCucDai>();
		
		// Get List Loto Gan
		String lotos = getChamDacBietTheoNgay( province_id, ngay_quay);
		String arrLoto[] = lotos.split(",");
		int i = 0;
		while(i<10){
			int j = 0;
			// check loto
			boolean kt = false;
			while(j<arrLoto.length){
				if(i==Integer.parseInt(arrLoto[j]))
				{
					kt = true;
					break;
				}
				j++;
			}
			
			if(!kt) listLotoGan.add(i+"");
				
			i++;
		}
		
		
		try {
			conn = C3p0VesoPool.getConnection();	
			for (String boso : listLotoGan) {
				TKLotoGanCucDai loCucDai = new TKLotoGanCucDai();
				strSQL = new StringBuffer("SELECT  dau_so,dit_so, ngay_quay FROM "+table+" WHERE is_dacbiet = 1 AND  province_id ="+province_id+" AND "
						+ " (dau_so = '"+boso+"' OR dit_so = '"+boso+"') ORDER BY ngay_quay DESC LIMIT 1");
				System.out.println(strSQL);
				preStmt = conn.prepareStatement(strSQL.toString());			
				rs = preStmt.executeQuery();
				if (rs.next()) {				
					loCucDai.boso = boso;
					loCucDai.ngay_quay =  rs.getString("ngay_quay");
					long solanquay = ( Calendar.getInstance().getTimeInMillis() - rs.getDate("ngay_quay").getTime())/ (24*60*60*1000);
					loCucDai.lanquay_chuave = (int)solanquay;
					
					if(province_id==14||province_id==24) loCucDai.lanquay_chuave = ((int)solanquay/7)*2;
					else if(province_id==0||province_id==1) loCucDai.lanquay_chuave = (int)solanquay;
					else loCucDai.lanquay_chuave = (int)(solanquay/7);
					loCucDai.lanquay_chuave = loCucDai.lanquay_chuave  -1;
					System.out.println(boso+"-->"+solanquay);
					lotoGanCucDai.add(loCucDai);
				}
			}
			
		} catch (NoSuchElementException nse) {
			logger.error("getLastDateLotoGan: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getLastDateLotoGan: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getLastDateLotoGan: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		
		Collections.sort(lotoGanCucDai, new Comparator<TKLotoGanCucDai>() {
            @Override
            public int compare(TKLotoGanCucDai lhs, TKLotoGanCucDai rhs) {
                return lhs.lanquay_chuave > rhs.lanquay_chuave ? -1 : (lhs.lanquay_chuave < rhs.lanquay_chuave ) ? 1 : 0;
            }
        });
		
		return lotoGanCucDai;
	}
	
	public void tkLotoVeNhieu(int province_id,int so_ngay){
		List<BoSoThongKe> listBoso =  getBoSoVeNhieu(province_id,so_ngay);
		TKSMSContent sContent = new TKSMSContent();
		TKSMSContentDAO contentDAO = new TKSMSContentDAO();
		if(province_id==0) province_id =1;
		sContent.province_id = province_id;
		sContent.type_tk = 1;
		int i = 0;
		
		sContent.content ="Loto ve nhieu: ";
		for (BoSoThongKe boSoThongKe : listBoso) {
			if(i<10)
			sContent.content +=boSoThongKe.boso +":" + boSoThongKe.solan+" lan, ";
			
			i++;
			
		}
		contentDAO.save(sContent);
	}
	
	public void tkLotoGan(int province_id){
		String date_now = getLastOpenDate(province_id);
		
		List<TKLotoGanCucDai> listBoso =  getLotoGan(province_id,date_now);
		TKSMSContent sContent = new TKSMSContent();
		TKSMSContentDAO contentDAO = new TKSMSContentDAO();
		
		if(province_id==0) province_id =1;
		sContent.province_id = province_id;
		sContent.type_tk = 2;
		int i = 0;
		sContent.content ="Loto gan: ";
		for (TKLotoGanCucDai boSoThongKe : listBoso) {
			if(i<10)
			sContent.content +=boSoThongKe.boso +":" + boSoThongKe.lanquay_chuave+" lan, ";
			i++;
			
		}
		contentDAO.save(sContent);
	}
	
	public void tkLotoDacBietGan(int province_id){
		String date_now = getLastOpenDate(province_id);
		
		List<TKLotoGanCucDai> listBoso =  getLotoDacBietGan(province_id,date_now);
		TKSMSContent sContent = new TKSMSContent();
		TKSMSContentDAO contentDAO = new TKSMSContentDAO();
		if(province_id==0) province_id =1;
		sContent.province_id = province_id;
		sContent.type_tk = 3;
		int i = 0;
		sContent.content ="DB gan: ";
		for (TKLotoGanCucDai boSoThongKe : listBoso) {
			if(i<10)
			sContent.content +=boSoThongKe.boso +":" + boSoThongKe.lanquay_chuave+" lan, ";
			i++;
			
		}
		contentDAO.save(sContent);
	}
	
	public void tkTongDacBietGan(int province_id){
		String date_now = getLastOpenDate(province_id);
		
		List<TKLotoGanCucDai> listBoso =  getTongDacBietGan(province_id,date_now);
		TKSMSContent sContent = new TKSMSContent();
		TKSMSContentDAO contentDAO = new TKSMSContentDAO();
		if(province_id==0) province_id =1;
		sContent.province_id = province_id;
		sContent.type_tk = 4;
		int i = 0;
		sContent.content ="Tong DB gan: ";
		for (TKLotoGanCucDai boSoThongKe : listBoso) {
			if(i<10)
			sContent.content +=boSoThongKe.boso +":" + boSoThongKe.lanquay_chuave+" lan, ";
			i++;
			
		}
		contentDAO.save(sContent);
	}
	
	
	public void tkChamDacBietGan(int province_id){
		String date_now = getLastOpenDate(province_id);
		
		List<TKLotoGanCucDai> listBoso =  getChamgDacBietGan(province_id,date_now);
		TKSMSContent sContent = new TKSMSContent();
		TKSMSContentDAO contentDAO = new TKSMSContentDAO();
		if(province_id==0) province_id =1;
		sContent.province_id = province_id;
		sContent.type_tk = 5;
		int i = 0;
		sContent.content ="Cham DB gan: ";
		for (TKLotoGanCucDai boSoThongKe : listBoso) {
			if(i<10)
			sContent.content +=boSoThongKe.boso +":" + boSoThongKe.lanquay_chuave+" lan, ";
			i++;
			
		}
		contentDAO.save(sContent);
	}
	
	
	public static void main(String[] args) {
		/*LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
		lotoDAO.tkLotoVeNhieu(0, 30);
		lotoDAO.tkLotoGan(0);
		lotoDAO.tkLotoDacBietGan(0);
		lotoDAO.tkTongDacBietGan(0);
		lotoDAO.tkChamDacBietGan(0);*/
		//
	/*	List<TKLotoGanCucDai> lotoGanCucDai =lotoDAO.getLotoGan(0,"2016-07-11");
		JSONObject formDetailsJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		*/
	/*	String content = "Loto Gan ";
		int i = 0;
		for (TKLotoGanCucDai tkLotoGanCucDai : lotoGanCucDai) {
			System.out.println(tkLotoGanCucDai.boso+"-->"+tkLotoGanCucDai.lanquay_chuave);
			if(i<10){
			content +=  tkLotoGanCucDai.boso+":"+tkLotoGanCucDai.lanquay_chuave+",";
			}
			i++;
	        //formDetailsJson.put(tkLotoGanCucDai.boso,tkLotoGanCucDai.lanquay_chuave+" lần");
	        
		}
		jsonArray.add(formDetailsJson);
		System.out.println(content);
		System.out.println(formDetailsJson.toString());*/
		
	/*	lotoGanCucDai =lotoDAO.getLotoDacBietGan(0,"2016-07-11");
		for (TKLotoGanCucDai tkLotoGanCucDai : lotoGanCucDai) {
			System.out.println(tkLotoGanCucDai.boso+"-->"+tkLotoGanCucDai.lanquay_chuave);
		}*/
		
	/*	lotoGanCucDai =lotoDAO.getTongDacBietGan(0,"2016-07-11");
		for (TKLotoGanCucDai tkLotoGanCucDai : lotoGanCucDai) {
			System.out.println(tkLotoGanCucDai.boso+"-->"+tkLotoGanCucDai.lanquay_chuave);
		}
		
		lotoGanCucDai =lotoDAO.getChamgDacBietGan(0,"2016-07-11");
		for (TKLotoGanCucDai tkLotoGanCucDai : lotoGanCucDai) {
			System.out.println(tkLotoGanCucDai.boso+"-->"+tkLotoGanCucDai.lanquay_chuave);
		}
		*/
		
		/*LotoDAO lotoDAO2 = new LotoDAO("thongke_loto_miennam");
		lotoGanCucDai =lotoDAO2.getLotoGan(14,"2016-07-11");
		for (TKLotoGanCucDai tkLotoGanCucDai : lotoGanCucDai) {
			System.out.println(tkLotoGanCucDai.boso+"-->"+tkLotoGanCucDai.lanquay_chuave);
		}*/
		
		
		/*lotoDAO2.getBoSoVeNhieu(14,30);*/
		/*ProvinceDAO provinceDAO = new ProvinceDAO();
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
		}*/
		
		
	}
}
