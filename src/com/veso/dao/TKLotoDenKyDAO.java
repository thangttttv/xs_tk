package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import com.veso.bean.TKLotoDenKy;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TKLotoDenKyDAO {
	private Logger logger = new Logger(this.getClass().getName());

	public boolean save(TKLotoDenKy lotoDenKy) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("INSERT INTO thongke_loto_denky (boso,dodai_chuky,start_date,end_date, " +
					" TYPE,create_date,create_user,modify_date,modify_user" +
					" )VALUES(?,?,?,?,?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setString(1, lotoDenKy.boso);
			preStmt.setInt(2, lotoDenKy.dodai_chuky);
			preStmt.setString(3, lotoDenKy.start_date);
			preStmt.setString(4, lotoDenKy.end_date);
			preStmt.setInt(5, lotoDenKy.type);
			preStmt.setString(6, lotoDenKy.create_date);
			preStmt.setString(7, lotoDenKy.create_user);
			preStmt.setString(8, lotoDenKy.modify_date);
			preStmt.setString(9, lotoDenKy.modify_user);

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
