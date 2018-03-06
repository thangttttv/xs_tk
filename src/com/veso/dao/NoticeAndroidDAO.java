package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.veso.bean.Notice;
import com.veso.bean.NoticeQueue;
import com.veso.bean.NoticeUser;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class NoticeAndroidDAO {
	
	private Logger logger = new Logger(this.getClass().getName());
	
	public List<Notice> getNoticeToSendQueue() {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		Notice notice = null;
		List<Notice> list = new ArrayList<Notice>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT id,title,image,url,description,TYPE,STATUS,create_date,create_user,time_sent FROM vtc_10h_xs.xs_notice_android WHERE STATUS = 1");
			
			preStmt = conn.prepareStatement(strSQL.toString());	
			rs = preStmt.executeQuery();
			while (rs.next()) {		
				//id,title,image,url,description,TYPE,STATUS,create_date,create_user
				
				notice = new Notice();
				notice.id= rs.getInt("id");
				notice.title= rs.getString("title");
				notice.image= rs.getString("image");
				notice.url= rs.getString("url");
				notice.description= rs.getString("description");
				notice.type= rs.getInt("TYPE");
				notice.status = rs.getInt("STATUS");
				notice.create_date = rs.getString("create_date");
				notice.create_user = rs.getString("create_user");
				notice.time_sent = rs.getString("time_sent");
				list.add(notice);
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
	
	
	public boolean updateStatus(int notice_id,int status) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("UPDATE vtc_10h_xs.xs_notice_android SET STATUS = 1 WHERE id = ? ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, status);
			preStmt.setInt(2,notice_id);
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

	public boolean checkNoticeQueueExist(int notice_id,int app_client_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		boolean kq = false;
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT * FROM vtc_10h_xs.xs_notice_android_queue WHERE notice_id = ? AND app_client_id = ?");
			
			preStmt = conn.prepareStatement(strSQL.toString());			
			preStmt.setInt(1, notice_id);
			preStmt.setInt(2, app_client_id);
			
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				kq = true;
			}

		} catch (NoSuchElementException nse) {
			logger.error("checkNoticeQueueExist: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkNoticeQueueExist: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkNoticeQueueExist: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);			
			C3p0VesoPool.attemptClose(conn);
		}
		return kq;
	}
	
	
	public List<NoticeUser> getNoticeUser() {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		NoticeUser notice = null;
		List<NoticeUser> list = new ArrayList<NoticeUser>();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(
					"SELECT id,app_client_id,device_token, username, ip_address, create_date FROM vtc_10h_xs.xs_notice_android_user ");
			
			preStmt = conn.prepareStatement(strSQL.toString());	
			rs = preStmt.executeQuery();
			while (rs.next()) {		
				notice = new NoticeUser();
				notice.id= rs.getInt("id");
				notice.app_client_id= rs.getInt("app_client_id");
				notice.device_token= rs.getString("device_token");
				notice.username= rs.getString("username");
				notice.ip_address= rs.getString("ip_address");
				notice.create_date= rs.getString("create_date");
				list.add(notice);
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
	
	
	public boolean insertNoticeQueue(NoticeQueue notice) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO vtc_10h_xs.xs_notice_android_queue (notice_id, app_client_id, payload, time_queued, time_sent )" +
					" VALUES ( ?, ?, ?, NOW(),?) ;");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, notice.notice_id);
			preStmt.setInt(2,notice.app_client_id);
			preStmt.setString(3,notice.payload);
			preStmt.setString(4,notice.time_sent);
			
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("insertNoticeQueue: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("insertNoticeQueue: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("insertNoticeQueue: Error executing >>>" + e.toString());
		} finally {			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
	public boolean deleteQueue(int id) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		try {
			conn = C3p0VesoPool.getConnection();	
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("DELETE FROM vtc_10h_xs.xs_notice_android_queue WHERE id = ? ");
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
			logger.error("deleteQueue: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("deleteQueue: Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("deleteQueue: Error executing >>>" + e.toString());
		} finally {			
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}

}
