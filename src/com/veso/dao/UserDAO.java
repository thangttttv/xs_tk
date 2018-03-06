package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import com.veso.bean.User;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class UserDAO {
	
	private Logger logger = new Logger(this.getClass().getName());
	
	public boolean update(int id, double xu) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("UPDATE user_veso SET tk_chinh  = tk_chinh + ?  WHERE	id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDouble(1, xu);
			preStmt.setInt(2, id);
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("update: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("update: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("update: Error executing >>>" + e.toString());
		} finally {			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
	public User getUser(int id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;		
		User user = null;
		try {
			conn = C3p0VesoPool.getConnection();		
			strSQL = new StringBuffer("SELECT 	id, username, fullname, avatar_url, email, mobile, address, tk_phu,tk_chinh, status," +
					"create_user, create_date,modify_user,modify_date FROM az24_veso.user_veso Where id = ?");
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, id);
			ResultSet rs = preStmt.executeQuery();
			if(rs.next())
			{
				user = new User();
				user.id = rs.getInt("id");
				user.name = rs.getString("username");
			}
			
		} catch (NoSuchElementException nse) {
			logger.error("update: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("update: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("update: Error executing >>>" + e.toString());
		} finally {			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return user;
	}
}
