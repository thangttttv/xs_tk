package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Vector;

import com.veso.bean.ProvinceBean;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class ProvinceDAO {
	
	private String table = "province";
	private Logger logger = new Logger(this.getClass().getName());
	
	public Vector<ProvinceBean> findAll(int region) {

		Vector<ProvinceBean> kqList = null;
		ProvinceBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT id, code,name,region, thu2, thu3, thu4, thu5, thu6, thu7, thu8, create_user, create_date, modify_user," +
					" modify_date from	"+table+" WHERE region = ?  ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, region);
			rs = preStmt.executeQuery();			
			kqList = new Vector<ProvinceBean>();
			while (rs.next()) {
				kBean = new ProvinceBean();			
				kBean.setId(rs.getInt("id"));
				kBean.setCode(rs.getString("code"));
				kBean.setName(rs.getString("name"));
				kBean.setRegion(region);
				kBean.setThu2(rs.getInt("thu2"));
				kBean.setThu3(rs.getInt("thu3"));
				kBean.setThu4(rs.getInt("thu4"));
				kBean.setThu5(rs.getInt("thu5"));
				kBean.setThu6(rs.getInt("thu6"));
				kBean.setThu7(rs.getInt("thu7"));
				kBean.setThu8(rs.getInt("thu8"));
				
				kqList.addElement(kBean);
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
	
	public Vector<ProvinceBean> findProvinceOpen(int region,String thu) {

		Vector<ProvinceBean> kqList = null;
		ProvinceBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT id, code,name,region, thu2, thu3, thu4, thu5, thu6, thu7, thu8, create_user, create_date, modify_user," +
					" modify_date from	"+table+" WHERE region = ? AND " + thu +" = 1 ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, region);
			
			rs = preStmt.executeQuery();			
			kqList = new Vector<ProvinceBean>();
			while (rs.next()) {
				kBean = new ProvinceBean();			
				kBean.setId(rs.getInt("id"));
				kBean.setCode(rs.getString("code"));
				kBean.setName(rs.getString("name"));
				kBean.setRegion(region);
				kqList.addElement(kBean);
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
	
	public int findRegion(int province_id) {		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int region = 0;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer("SELECT region from	"+table+" WHERE  id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, province_id);
			
			rs = preStmt.executeQuery();		
		
			while (rs.next()) {
				region = rs.getInt("region");
			}

		} catch (NoSuchElementException nse) {
			logger.error("findRegion: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findRegion: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findRegion: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return region;
	}

}
