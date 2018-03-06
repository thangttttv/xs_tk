package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.TransactionIn;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.DateProc;
import com.veso.util.Logger;

public class TransactionInDAO {
	
	private Logger logger = new Logger(this.getClass().getName());
	
	public boolean save(TransactionIn transactionIn) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("INSERT INTO transaction_in (user_id,ticket_id, tk_chinh, content, transaction_date,user_name,type)" +
					" VALUES  (?,?,?,?,?,?,?) ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, transactionIn.getUser_id());
			preStmt.setInt(2, transactionIn.getTicket_id());
			preStmt.setDouble(3, transactionIn.getTk_chinh());
			preStmt.setString(4, transactionIn.getContent());
			preStmt.setTimestamp(5, DateProc.createTimestamp());
			preStmt.setString(6, transactionIn.getUsername());
			preStmt.setInt(7, transactionIn.getType());
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
	
	public List<TransactionIn> getAllTransaction() {
		List<TransactionIn> tList = null;		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		TransactionIn ticket = null;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT 	id, user_id, user_name, ticket_id, bill_id, tk_chinh," +
					" 	tk_phu, TYPE, content, transaction_date	FROM az24_veso.transaction_in order by id ");
			preStmt = conn.prepareStatement(strSQL.toString());		
			
			rs = preStmt.executeQuery();
			tList = new ArrayList<TransactionIn>();
			while (rs.next()) {	
				ticket = new TransactionIn();
				ticket.setId(rs.getInt("id"));
				ticket.setUser_id(rs.getInt("user_id"));				
				tList.add(ticket);
			}

		} catch (NoSuchElementException nse) {
			logger.error("findByOpenDate: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findByOpenDate: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findByOpenDate: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return tList;
	}
	
	public boolean UpdateUserName(TransactionIn transactionIn) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("Update  transaction_in Set user_name = ? Where id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setString(1, transactionIn.getUsername());
			preStmt.setInt(2, transactionIn.getId());
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
	
	public static void main(String[] args) {
		TransactionInDAO transactionInDAO = new TransactionInDAO();
		UserDAO userDAO = new UserDAO();
		List<TransactionIn> list = transactionInDAO.getAllTransaction();
		for (TransactionIn transactionIn : list) {
			transactionIn.setUsername(userDAO.getUser(transactionIn.getUser_id()).name);
			transactionInDAO.UpdateUserName(transactionIn);
		}
	}
	
}
