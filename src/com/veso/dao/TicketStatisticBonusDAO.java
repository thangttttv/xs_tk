package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import com.veso.bean.TicketStatisticBonus;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class TicketStatisticBonusDAO {
	private Logger logger = new Logger(this.getClass().getName());

	public boolean save(TicketStatisticBonus chuKiDau) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("INSERT INTO ticket_statistic_bonus (user_id,	user_name,mobile," +
					"bonus,	ticket_month,ticket_year) VALUES (?,?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKiDau.user_id);
			preStmt.setString(2, chuKiDau.user_name);
			preStmt.setString(3, chuKiDau.mobile);
			preStmt.setDouble(4, chuKiDau.bonus);
			preStmt.setInt(5, chuKiDau.ticket_month);
			preStmt.setInt(6, chuKiDau.ticket_year);

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
	
	public boolean updateBonus(int id,double bonus) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" UPDATE  ticket_statistic_bonus SET bonus = bonus + ?  WHERE id = ? ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDouble(1,bonus);
			preStmt.setInt(2, id);

			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("updateChuKy: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("updateChuKy: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("updateChuKy: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
	public TicketStatisticBonus getTicketByMonth(int user_id,int month,int year) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		TicketStatisticBonus tChuKyBoSo = null;
		try {

			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"   SELECT 	id,user_id,	user_name, mobile,	bonus,	ticket_month,ticket_year " +
					" 	FROM ticket_statistic_bonus WHERE user_id = ? AND ticket_month = ?  AND ticket_year = ? " +
					"  ");

			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, user_id);
			preStmt.setInt(2, month);
			preStmt.setInt(3, year);
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				tChuKyBoSo = new TicketStatisticBonus();
				tChuKyBoSo.id = rs.getInt("id");
				tChuKyBoSo.user_id = rs.getInt("user_id");
				tChuKyBoSo.user_name = rs.getString("user_name");
				tChuKyBoSo.mobile = rs.getString("mobile");
				tChuKyBoSo.bonus = rs.getDouble("bonus");
				tChuKyBoSo.ticket_month = rs.getInt("ticket_month");
				tChuKyBoSo.ticket_year = rs.getInt("ticket_year");				
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

}
