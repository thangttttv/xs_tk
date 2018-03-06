package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import com.veso.bean.TKLotoGanCucDai;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TKLotoGanCucDaiDAO {
	private Logger logger = new Logger(this.getClass().getName());

	public boolean save(TKLotoGanCucDai tkLotoGanCucDai) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_loto_gan_cucdai  (boso,lanquay_cucdai,start_date,end_date," +
					" lanquay_chuave,ngay_quay,create_date,create_user,modify_date,modify_user)" +
					" VALUES (?,?,?,?,?,?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setString(1, tkLotoGanCucDai.boso);
			preStmt.setInt(2, tkLotoGanCucDai.lanquay_cucdai);
			preStmt.setString(3, tkLotoGanCucDai.start_date);
			preStmt.setString(4, tkLotoGanCucDai.end_date);
			preStmt.setInt(5, tkLotoGanCucDai.lanquay_chuave);
			
			preStmt.setString(6, tkLotoGanCucDai.ngay_quay);
			preStmt.setString(7, tkLotoGanCucDai.create_date);
			preStmt.setString(8, tkLotoGanCucDai.create_user);
			preStmt.setString(9, tkLotoGanCucDai.modify_date);
			preStmt.setString(10, tkLotoGanCucDai.modify_user);

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
