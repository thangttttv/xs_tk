package com.veso.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.TanSuatBoSoBean;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TanSuatBoSoDAO {
	private String table = "thongke_tansuat_loto_mienbac";
	private Logger logger = new Logger(this.getClass().getName());
	
	public TanSuatBoSoDAO(String table)
	{
		this.table = table;
	}
	
	public boolean save(TanSuatBoSoBean boSoBean) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO "+this.table+" (boso,so_ngay_ve, p_so_ngay_ve,so_lan_ve,p_so_lan_ve,p_so_lan_ngay,province_id," +
							"khoang_thoigian,is_dacbiet,create_date,bien_ngay) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setString(1,boSoBean.getBoso());
			preStmt.setInt(2,boSoBean.getSo_ngay_ve());
			preStmt.setDouble(3, boSoBean.getP_so_ngay_ve());
			preStmt.setInt(4, boSoBean.getSo_lan_ve());
			preStmt.setDouble(5, boSoBean.getP_so_lan_ve());
			preStmt.setDouble(6, boSoBean.getP_so_lan_ngay());
			preStmt.setInt(7, boSoBean.getProvince_id());
			preStmt.setInt(8,boSoBean.getKhoang_thoigian());
			preStmt.setInt(9,boSoBean.getIs_dacbiet());
			preStmt.setTimestamp(10,boSoBean.getCreate_date());	
			preStmt.setDate(11,boSoBean.getBien_ngay());		
			
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
	
	
	public List<TanSuatBoSoBean> findAll(int start,String table) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		StringBuffer strSQL = null;
		TanSuatBoSoBean tanSuatBoSoBean = null;
		List<TanSuatBoSoBean> list = new ArrayList<TanSuatBoSoBean>();
		try {
			conn = C3p0VesoPool.getConnection();	
			
			strSQL = new StringBuffer("SELECT 	id,boso,so_ngay_ve,p_so_ngay_ve,so_lan_ve,p_so_lan_ve,p_so_lan_ngay,province_id,khoang_thoigian," +
					" is_dacbiet,bien_ngay,create_date	FROM	"+table+"	Where id >? Order by id ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1, start);
			rs = preStmt.executeQuery();
			while(rs.next()) {
				tanSuatBoSoBean = new TanSuatBoSoBean();
				tanSuatBoSoBean.setId(rs.getLong("id"));
				tanSuatBoSoBean.setSo_ngay_ve(rs.getInt("so_ngay_ve"));
				tanSuatBoSoBean.setP_so_ngay_ve(rs.getDouble("p_so_ngay_ve"));
				tanSuatBoSoBean.setSo_lan_ve(rs.getInt("so_lan_ve"));
				tanSuatBoSoBean.setP_so_lan_ngay(rs.getDouble("p_so_lan_ngay"));
				tanSuatBoSoBean.setProvince_id(rs.getInt("province_id"));
				tanSuatBoSoBean.setKhoang_thoigian(rs.getInt("khoang_thoigian"));
				tanSuatBoSoBean.setIs_dacbiet(rs.getInt("is_dacbiet"));
				tanSuatBoSoBean.setBien_ngay(rs.getDate("bien_ngay"));
				tanSuatBoSoBean.setCreate_date(rs.getTimestamp("create_date"));
				list.add(tanSuatBoSoBean);
			}
			
		} catch (NoSuchElementException nse) {
			logger.error("findAll: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAll: Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
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
