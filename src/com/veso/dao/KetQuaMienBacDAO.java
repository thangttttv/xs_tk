package com.veso.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import com.veso.bean.DaySoBean;
import com.veso.bean.Dientoan123;
import com.veso.bean.Dientoan6x63;
import com.veso.bean.DientoanThantai;
import com.veso.bean.KetQuaMienBacBean;
import com.veso.bean.LotoBean;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.DateProc;
import com.veso.util.Logger;
import com.veso.util.StringTool;

public class KetQuaMienBacDAO {

	private String table = "ketqua_mienbac";
	private Logger logger = new Logger(this.getClass().getName());

	public KetQuaMienBacBean findById(long id) {
		KetQuaMienBacBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id,ngay_quay,giai_dacbiet, giai_nhat, giai_nhi_1, giai_nhi_2, giai_ba_1, giai_ba_2, giai_ba_3,"
							+ "	giai_ba_4,	giai_ba_5,giai_ba_6,giai_tu_1,giai_tu_2,giai_tu_3,giai_tu_4,giai_nam_1,giai_nam_2,"
							+ "	giai_nam_3,	giai_nam_4,	giai_nam_5,	giai_nam_6,	giai_sau_1,	giai_sau_2,	giai_sau_3,	giai_bay_1,"
							+ "	giai_bay_2,	giai_bay_3,	giai_bay_4,	create_user,create_date, modify_user,modify_date FROM"
							+ "	" + table + " WHERE id = '" + id + "'");
			preStmt = conn.prepareStatement(strSQL.toString());
			rs = preStmt.executeQuery();

			if (rs.next()) {
				kBean = new KetQuaMienBacBean();
				kBean.setId(rs.getLong("id"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi_1(rs.getString("giai_nhi_1"));
				kBean.setGiai_nhi_2(rs.getString("giai_nhi_2"));
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				kBean.setGiai_ba_3(rs.getString("giai_ba_3"));

				kBean.setGiai_ba_4(rs.getString("giai_ba_4"));
				kBean.setGiai_ba_5(rs.getString("giai_ba_5"));
				kBean.setGiai_ba_6(rs.getString("giai_ba_6"));

				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));

				kBean.setGiai_nam_1(rs.getString("giai_nam_1"));
				kBean.setGiai_nam_2(rs.getString("giai_nam_2"));
				kBean.setGiai_nam_3(rs.getString("giai_nam_3"));
				kBean.setGiai_nam_4(rs.getString("giai_nam_4"));
				kBean.setGiai_nam_5(rs.getString("giai_nam_5"));
				kBean.setGiai_nam_6(rs.getString("giai_nam_6"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay_1(rs.getString("giai_bay_1"));
				kBean.setGiai_bay_2(rs.getString("giai_bay_2"));
				kBean.setGiai_bay_3(rs.getString("giai_bay_3"));
				kBean.setGiai_bay_4(rs.getString("giai_bay_4"));

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));

			}

		} catch (NoSuchElementException nse) {
			logger.error("findById(id): Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findById(id): Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("findById(id): Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);

		}
		return kBean;
	}

	public Vector<KetQuaMienBacBean> findAll() {

		Vector<KetQuaMienBacBean> kqList = null;
		KetQuaMienBacBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection()	;
			strSQL = new StringBuffer(
					"SELECT	id,province_id,ngay_quay,giai_dacbiet, giai_nhat, giai_nhi_1, giai_nhi_2, giai_ba_1, giai_ba_2, giai_ba_3,"
							+ "	giai_ba_4,	giai_ba_5,giai_ba_6,giai_tu_1,giai_tu_2,giai_tu_3,giai_tu_4,giai_nam_1,giai_nam_2,"
							+ "	giai_nam_3,	giai_nam_4,	giai_nam_5,	giai_nam_6,	giai_sau_1,	giai_sau_2,	giai_sau_3,	giai_bay_1,"
							+ "	giai_bay_2,	giai_bay_3,	giai_bay_4,	create_user,create_date, modify_user,modify_date FROM"
							+ "	" + table);
			preStmt = conn.prepareStatement(strSQL.toString());
			rs = preStmt.executeQuery();
			kqList = new Vector<KetQuaMienBacBean>();
			while (rs.next()) {
				kBean = new KetQuaMienBacBean();			
				kBean.setId(rs.getLong("id"));
				kBean.setProvince_id(rs.getInt("province_id"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi_1(rs.getString("giai_nhi_1"));
				kBean.setGiai_nhi_2(rs.getString("giai_nhi_2"));
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				kBean.setGiai_ba_3(rs.getString("giai_ba_3"));

				kBean.setGiai_ba_4(rs.getString("giai_ba_4"));
				kBean.setGiai_ba_5(rs.getString("giai_ba_5"));
				kBean.setGiai_ba_6(rs.getString("giai_ba_6"));

				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));

				kBean.setGiai_nam_1(rs.getString("giai_nam_1"));
				kBean.setGiai_nam_2(rs.getString("giai_nam_2"));
				kBean.setGiai_nam_3(rs.getString("giai_nam_3"));
				kBean.setGiai_nam_4(rs.getString("giai_nam_4"));
				kBean.setGiai_nam_5(rs.getString("giai_nam_5"));
				kBean.setGiai_nam_6(rs.getString("giai_nam_6"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay_1(rs.getString("giai_bay_1"));
				kBean.setGiai_bay_2(rs.getString("giai_bay_2"));
				kBean.setGiai_bay_3(rs.getString("giai_bay_3"));
				kBean.setGiai_bay_4(rs.getString("giai_bay_4"));

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));
				kqList.addElement(kBean);
			}

		} catch (NoSuchElementException nse) {
			logger.error("findAll: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAll: Error executing SQL >>>"
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

	public Vector<KetQuaMienBacBean> findAll(long page, long rowsPerPage) {
		if (page <= 0 || rowsPerPage <= 0) {
			return null;
		}
		long startRow = (page - 1) * rowsPerPage;
		long stopRow = page * rowsPerPage;
		Vector<KetQuaMienBacBean> kList = null;
		KetQuaMienBacBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id,ngay_quay,giai_dacbiet, giai_nhat, giai_nhi_1, giai_nhi_2, giai_ba_1, giai_ba_2, giai_ba_3,"
							+ "	giai_ba_4,	giai_ba_5,giai_ba_6,giai_tu_1,giai_tu_2,giai_tu_3,giai_tu_4,giai_nam_1,giai_nam_2,"
							+ "	giai_nam_3,	giai_nam_4,	giai_nam_5,	giai_nam_6,	giai_sau_1,	giai_sau_2,	giai_sau_3,	giai_bay_1,"
							+ "	giai_bay_2,	giai_bay_3,	giai_bay_4,	create_user,create_date, modify_user,modify_date FROM"
							+ "	" + table + " ORDER BY id  LIMIT ?,?");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1, startRow);
			preStmt.setLong(2, stopRow);

			rs = preStmt.executeQuery();
			kList = new Vector<KetQuaMienBacBean>();
			while (rs.next()) {
				kBean = new KetQuaMienBacBean();			
				kBean.setId(rs.getLong("id"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi_1(rs.getString("giai_nhi_1"));
				kBean.setGiai_nhi_2(rs.getString("giai_nhi_2"));
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				kBean.setGiai_ba_3(rs.getString("giai_ba_3"));

				kBean.setGiai_ba_4(rs.getString("giai_ba_4"));
				kBean.setGiai_ba_5(rs.getString("giai_ba_5"));
				kBean.setGiai_ba_6(rs.getString("giai_ba_6"));

				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));

				kBean.setGiai_nam_1(rs.getString("giai_nam_1"));
				kBean.setGiai_nam_2(rs.getString("giai_nam_2"));
				kBean.setGiai_nam_3(rs.getString("giai_nam_3"));
				kBean.setGiai_nam_4(rs.getString("giai_nam_4"));
				kBean.setGiai_nam_5(rs.getString("giai_nam_5"));
				kBean.setGiai_nam_6(rs.getString("giai_nam_6"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay_1(rs.getString("giai_bay_1"));
				kBean.setGiai_bay_2(rs.getString("giai_bay_2"));
				kBean.setGiai_bay_3(rs.getString("giai_bay_3"));
				kBean.setGiai_bay_4(rs.getString("giai_bay_4"));

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));
				kList.addElement(kBean);
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
		return kList;
	}
	
	
	
	public List<KetQuaMienBacBean> findAll(int start) {
		List<KetQuaMienBacBean> kList = new Vector<KetQuaMienBacBean>();
		KetQuaMienBacBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id,ngay_quay,giai_dacbiet, giai_nhat, giai_nhi_1, giai_nhi_2, giai_ba_1, giai_ba_2, giai_ba_3,"
							+ "	giai_ba_4,	giai_ba_5,giai_ba_6,giai_tu_1,giai_tu_2,giai_tu_3,giai_tu_4,giai_nam_1,giai_nam_2,"
							+ "	giai_nam_3,	giai_nam_4,	giai_nam_5,	giai_nam_6,	giai_sau_1,	giai_sau_2,	giai_sau_3,	giai_bay_1,"
							+ "	giai_bay_2,	giai_bay_3,	giai_bay_4,	create_user,create_date, modify_user,modify_date FROM"
							+ "	" + table + " Where id > ? ORDER BY id  ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,start );			
			rs = preStmt.executeQuery();
			while (rs.next()) {
				kBean = new KetQuaMienBacBean();			
				kBean.setId(rs.getLong("id"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi_1(rs.getString("giai_nhi_1"));
				kBean.setGiai_nhi_2(rs.getString("giai_nhi_2"));
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				kBean.setGiai_ba_3(rs.getString("giai_ba_3"));

				kBean.setGiai_ba_4(rs.getString("giai_ba_4"));
				kBean.setGiai_ba_5(rs.getString("giai_ba_5"));
				kBean.setGiai_ba_6(rs.getString("giai_ba_6"));

				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));

				kBean.setGiai_nam_1(rs.getString("giai_nam_1"));
				kBean.setGiai_nam_2(rs.getString("giai_nam_2"));
				kBean.setGiai_nam_3(rs.getString("giai_nam_3"));
				kBean.setGiai_nam_4(rs.getString("giai_nam_4"));
				kBean.setGiai_nam_5(rs.getString("giai_nam_5"));
				kBean.setGiai_nam_6(rs.getString("giai_nam_6"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay_1(rs.getString("giai_bay_1"));
				kBean.setGiai_bay_2(rs.getString("giai_bay_2"));
				kBean.setGiai_bay_3(rs.getString("giai_bay_3"));
				kBean.setGiai_bay_4(rs.getString("giai_bay_4"));

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));
				kList.add(kBean);
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
		return kList;
	}
	
	
	public List<Dientoan123> findAllDT123(int start) {
		List<Dientoan123> kList = new Vector<Dientoan123>();
		Dientoan123 kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"   SELECT	id,ngay_quay,ketqua_1,ketqua_2,ketqua_3,create_user,create_date,modify_user,modify_date	FROM" +
					" 	ketqua_dientoan123 Where id > ? Order by id ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,start );			
			rs = preStmt.executeQuery();
			while (rs.next()) {
				kBean = new Dientoan123();			
				kBean.id=rs.getInt("id");
				kBean.ngay_quay=rs.getDate("ngay_quay");
				kBean.ketqua_1=rs.getString("ketqua_1");
				kBean.ketqua_2=rs.getString("ketqua_2");
				kBean.ketqua_3=rs.getString("ketqua_3");
				kBean.create_date=rs.getTimestamp("create_date");
				kBean.create_user=rs.getString("create_user");
				kBean.modify_date=rs.getTimestamp("modify_date");
				kBean.modify_user=rs.getString("modify_user");
				kList.add(kBean);
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
		return kList;
	}
	
	
	public List<Dientoan6x63> findAllDT6x63(int start) {
		List<Dientoan6x63> kList = new Vector<Dientoan6x63>();
		Dientoan6x63 kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"   SELECT 	id, ngay_quay, ketqua_1, ketqua_2,ketqua_3, ketqua_4, ketqua_5, ketqua_6, create_user," +
					" 	create_date,modify_user,modify_date FROM ketqua_dientoan6x36	Where id > ? Order by id ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,start );			
			rs = preStmt.executeQuery();
			while (rs.next()) {
				kBean = new Dientoan6x63();			
				kBean.id=rs.getInt("id");
				kBean.ngay_quay=rs.getDate("ngay_quay");
				kBean.ketqua_1=rs.getString("ketqua_1");
				kBean.ketqua_2=rs.getString("ketqua_2");
				kBean.ketqua_3=rs.getString("ketqua_3");
				kBean.ketqua_4=rs.getString("ketqua_4");
				kBean.ketqua_5=rs.getString("ketqua_5");
				kBean.ketqua_6=rs.getString("ketqua_6");
				kBean.create_date=rs.getTimestamp("create_date");
				kBean.create_user=rs.getString("create_user");
				kBean.modify_date=rs.getTimestamp("modify_date");
				kBean.modify_user=rs.getString("modify_user");
				kList.add(kBean);
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
		return kList;
	}
	
	
	public List<DientoanThantai> findAllDTThanTai(int start) {
		List<DientoanThantai> kList = new Vector<DientoanThantai>();
		DientoanThantai kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"   SELECT 	id,	ngay_quay,ketqua,create_user,create_date,modify_user,modify_date FROM" +
					"    ketqua_thantai	Where id > ? Order by id ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,start );			
			rs = preStmt.executeQuery();
			while (rs.next()) {
				kBean = new DientoanThantai();			
				kBean.id=rs.getInt("id");
				kBean.ngay_quay=rs.getDate("ngay_quay");
				kBean.ketqua=rs.getString("ketqua");
				kBean.create_date=rs.getTimestamp("create_date");
				kBean.create_user=rs.getString("create_user");
				kBean.modify_date=rs.getTimestamp("modify_date");
				kBean.modify_user=rs.getString("modify_user");
				kList.add(kBean);
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
		return kList;
	}
	
	
	public Vector<KetQuaMienBacBean> findByNgayQuay(Date ngay_quay) {
		Vector<KetQuaMienBacBean> kList = null;
		KetQuaMienBacBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id,ngay_quay,giai_dacbiet, giai_nhat, giai_nhi_1, giai_nhi_2, giai_ba_1, giai_ba_2, giai_ba_3,"
							+ "	giai_ba_4,	giai_ba_5,giai_ba_6,giai_tu_1,giai_tu_2,giai_tu_3,giai_tu_4,giai_nam_1,giai_nam_2,"
							+ "	giai_nam_3,	giai_nam_4,	giai_nam_5,	giai_nam_6,	giai_sau_1,	giai_sau_2,	giai_sau_3,	giai_bay_1,"
							+ "	giai_bay_2,	giai_bay_3,	giai_bay_4,	create_user,create_date, modify_user,modify_date FROM"
							+ "	" + table + " WHERE ngay_quay = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDate(1, ngay_quay);

			rs = preStmt.executeQuery();
			kList = new Vector<KetQuaMienBacBean>();
			while (rs.next()) {
				kBean = new KetQuaMienBacBean();			
				kBean.setId(rs.getLong("id"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi_1(rs.getString("giai_nhi_1"));
				kBean.setGiai_nhi_2(rs.getString("giai_nhi_2"));
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				kBean.setGiai_ba_3(rs.getString("giai_ba_3"));

				kBean.setGiai_ba_4(rs.getString("giai_ba_4"));
				kBean.setGiai_ba_5(rs.getString("giai_ba_5"));
				kBean.setGiai_ba_6(rs.getString("giai_ba_6"));

				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));

				kBean.setGiai_nam_1(rs.getString("giai_nam_1"));
				kBean.setGiai_nam_2(rs.getString("giai_nam_2"));
				kBean.setGiai_nam_3(rs.getString("giai_nam_3"));
				kBean.setGiai_nam_4(rs.getString("giai_nam_4"));
				kBean.setGiai_nam_5(rs.getString("giai_nam_5"));
				kBean.setGiai_nam_6(rs.getString("giai_nam_6"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay_1(rs.getString("giai_bay_1"));
				kBean.setGiai_bay_2(rs.getString("giai_bay_2"));
				kBean.setGiai_bay_3(rs.getString("giai_bay_3"));
				kBean.setGiai_bay_4(rs.getString("giai_bay_4"));

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));
				kList.addElement(kBean);
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
		return kList;
	}
	
	
	public Vector<KetQuaMienBacBean> findByMonthYear(int month,int year) {
		Vector<KetQuaMienBacBean> kList = null;
		KetQuaMienBacBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id,ngay_quay,giai_dacbiet, giai_nhat, giai_nhi_1, giai_nhi_2, giai_ba_1, giai_ba_2, giai_ba_3,"
							+ "	giai_ba_4,	giai_ba_5,giai_ba_6,giai_tu_1,giai_tu_2,giai_tu_3,giai_tu_4,giai_nam_1,giai_nam_2,"
							+ "	giai_nam_3,	giai_nam_4,	giai_nam_5,	giai_nam_6,	giai_sau_1,	giai_sau_2,	giai_sau_3,	giai_bay_1,"
							+ "	giai_bay_2,	giai_bay_3,	giai_bay_4,	create_user,create_date, modify_user,modify_date FROM"
							+ "	" + table + " WHERE month(ngay_quay) = ? AND year(ngay_quay)= ? ORDER BY ngay_quay  ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, month);
			preStmt.setInt(2, year);			
			rs = preStmt.executeQuery();
			kList = new Vector<KetQuaMienBacBean>();
			while (rs.next()) {
				kBean = new KetQuaMienBacBean();			
				kBean.setId(rs.getLong("id"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi_1(rs.getString("giai_nhi_1"));
				kBean.setGiai_nhi_2(rs.getString("giai_nhi_2"));
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				kBean.setGiai_ba_3(rs.getString("giai_ba_3"));

				kBean.setGiai_ba_4(rs.getString("giai_ba_4"));
				kBean.setGiai_ba_5(rs.getString("giai_ba_5"));
				kBean.setGiai_ba_6(rs.getString("giai_ba_6"));

				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));

				kBean.setGiai_nam_1(rs.getString("giai_nam_1"));
				kBean.setGiai_nam_2(rs.getString("giai_nam_2"));
				kBean.setGiai_nam_3(rs.getString("giai_nam_3"));
				kBean.setGiai_nam_4(rs.getString("giai_nam_4"));
				kBean.setGiai_nam_5(rs.getString("giai_nam_5"));
				kBean.setGiai_nam_6(rs.getString("giai_nam_6"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay_1(rs.getString("giai_bay_1"));
				kBean.setGiai_bay_2(rs.getString("giai_bay_2"));
				kBean.setGiai_bay_3(rs.getString("giai_bay_3"));
				kBean.setGiai_bay_4(rs.getString("giai_bay_4"));

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));
				kList.addElement(kBean);
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
		return kList;
	}
	
	
	public DaySoBean getDaySo(Date open_date) {

		Integer[] dayso = null;		
		DaySoBean daySoBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int i = 0,j=0;
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id,province_id,ngay_quay,giai_dacbiet, giai_nhat, giai_nhi_1, giai_nhi_2, giai_ba_1, giai_ba_2, giai_ba_3,"
							+ "	giai_ba_4,	giai_ba_5,giai_ba_6,giai_tu_1,giai_tu_2,giai_tu_3,giai_tu_4,giai_nam_1,giai_nam_2,"
							+ "	giai_nam_3,	giai_nam_4,	giai_nam_5,	giai_nam_6,	giai_sau_1,	giai_sau_2,	giai_sau_3,	giai_bay_1,"
							+ "	giai_bay_2,	giai_bay_3,	giai_bay_4,	create_user,create_date, modify_user,modify_date FROM  "
							+  table +" WHERE ngay_quay = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDate(1, open_date);
			rs = preStmt.executeQuery();
			dayso = new Integer[107];
			
			if (rs.next()) {
				daySoBean = new DaySoBean();
				i =0;j=0;
				while(i<rs.getString("giai_dacbiet").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_dacbiet").charAt(i)+"");
					i++;j++;
				}
				i=0;
				while(i<rs.getString("giai_nhat").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nhat").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nhi_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nhi_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nhi_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nhi_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_ba_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_ba_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_ba_3").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_3").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_ba_4").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_4").charAt(i)+"");
					i++;j++;
				}
				
				
				i=0;
				while(i<rs.getString("giai_ba_5").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_5").charAt(i)+"");
					i++;j++;
				}
				
				
				i=0;
				while(i<rs.getString("giai_ba_6").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_6").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_3").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_3").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_4").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_4").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nam_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nam_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nam_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nam_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nam_3").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nam_3").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nam_4").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nam_4").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nam_5").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nam_5").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nam_6").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nam_6").charAt(i)+"");
					i++;j++;
				}
				
				
				i=0;
				while(i<rs.getString("giai_sau_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_sau_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_sau_3").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_3").charAt(i)+"");
					i++;j++;
				}

				
				i=0;
				while(i<rs.getString("giai_bay_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_bay_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_bay_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_bay_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_bay_3").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_bay_3").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_bay_4").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_bay_4").charAt(i)+"");
					i++;j++;
				}

				daySoBean.setDayso(dayso);
				daySoBean.setKequa_id(rs.getLong("id"));
			
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
		return daySoBean;
	}

	
	public LotoBean saveLotoBean(long ketqua_id,int province_id,String ketqua,String giai,Date ngay_quay,int is_dacbiet)
	{
		LotoBean bean = new LotoBean();
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngay_quay);
		int dau=0;int dit=0;int tongbo=0;
		
		bean.setKetqua_id(ketqua_id);
		bean.setProvince_id(province_id);
		bean.setThu(calendar.get(Calendar.DAY_OF_WEEK));
		bean.setNgay_quay(ngay_quay);
		bean.setIs_dacbiet(is_dacbiet);
		bean.setGiai(giai);		
		
		String boso = ketqua.substring(ketqua.length()-2);
		dau = Integer.parseInt(boso.substring(0,1));
		dit = Integer.parseInt(boso.substring(1));
		bean.setBoso(boso);
		bean.setDau_so(dau);
		bean.setDit_so(dit);
		bean.setTan_so(1);
		tongbo = dau+dit;
		if(tongbo>9)
		tongbo = Integer.parseInt(String.valueOf(tongbo).substring(1));
		
		bean.setTong_bo(tongbo);
		
		int is_bochanchan = 0;
		if(dau%2==0&&dit%2==0) is_bochanchan = 1;
		bean.setIs_bochanchan(is_bochanchan);
		int is_bochanle = 0;
		if(dau%2==0&&dit%2!=0) is_bochanle = 1;
		bean.setIs_bochanle(is_bochanle);
		
		bean.setIs_bokep(StringTool.isKep(boso));
		
		int is_bolechan=0;
		if(dau%2!=0&&dit%2==0) is_bolechan = 1;
		bean.setIs_bolechan(is_bolechan);
		
		int is_bolele=0;
		if(dau%2!=0&&dit%2!=0) is_bolele = 1;
		bean.setIs_bolele(is_bolele);
		
		bean.setIs_bosatkep(StringTool.isSatKep(boso));
		
		int is_tongchan = 0;
		tongbo = dau+dit;
		if(tongbo%2==0) is_tongchan = 1;		
		bean.setIs_tongchan(is_tongchan);
		bean.setIs_tongle(1-is_tongchan);
		
		bean.setCreate_date(DateProc.createTimestamp());
		bean.setCreate_user("thangtt");
		bean.setModify_date(DateProc.createTimestamp());
		bean.setModify_user("thangtt");
		lotoDAO.save(bean);
		return bean;
	}
	public static void main(String[] args) {
		KetQuaMienBacDAO dBacDAO = new KetQuaMienBacDAO();
		Vector<KetQuaMienBacBean> listKQ = dBacDAO.findAll();
		for (KetQuaMienBacBean ketQuaMienBacBean : listKQ) {
			
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_dacbiet(), "GDB", ketQuaMienBacBean.getNgay_quay(), 1);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nhat(), "G1", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nhi_1(), "G21", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nhi_2(), "G22", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_1(), "G31", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_2(), "G32", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_3(), "G33", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_4(), "G34", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_5(), "G35", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_6(), "G36", ketQuaMienBacBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_1(), "G41", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_2(), "G42", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_3(), "G43", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_4(), "G44", ketQuaMienBacBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_1(), "G51", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_2(), "G52", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_3(), "G53", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_4(), "G54", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_5(), "G55", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_6(), "G56", ketQuaMienBacBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_sau_1(), "G61", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_sau_2(), "G62", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_sau_3(), "G63", ketQuaMienBacBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_1(), "G71", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_2(), "G72", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_3(), "G73", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_4(), "G74", ketQuaMienBacBean.getNgay_quay(), 0);
			
			
		}
		
	}
}
