package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import com.veso.bean.TKSMSContent;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TKSMSContentDAO {
	
private Logger logger = new Logger(this.getClass().getName());
	
	public boolean save(TKSMSContent content) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO thongke_sms_content  (province_id, type_tk, content, create_date)"
					+ "VALUES (?,?,?,NOW())");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,content.province_id);
			preStmt.setInt(2,content.type_tk);
			preStmt.setString(3,content.content);
		
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
	
	
}
