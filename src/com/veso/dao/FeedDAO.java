package com.veso.dao;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

import com.veso.bean.Feed;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.db.pool.DBConfig;
import com.veso.util.Logger;
import com.veso.util.StringTool;

public class FeedDAO {
	private Logger logger = new Logger(this.getClass().getName());
	
	public boolean save(Feed feed) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO xs_feed (title,image,url,description,type,create_date, create_user,isMobile) VALUES " +
					"  (?, ?, ?, ?,? ,NOW(),?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setString(1,feed.title);
			preStmt.setString(2,feed.image);
			preStmt.setString(3,feed.url);
			preStmt.setString(4,feed.description);
			preStmt.setInt(5,feed.type);
			preStmt.setString(6,feed.create_user);
			preStmt.setInt(7,feed.isMobile);

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
	
	public boolean delete(int id) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		try {
			conn = C3p0VesoPool.getConnection();	
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("DELETE FROM xs_feed WHERE id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, id);
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
	
	public boolean deleteByType(int type) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		try {
			conn = C3p0VesoPool.getConnection();	
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("DELETE FROM xs_feed WHERE type = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, type);
			if (preStmt.executeUpdate()>0) {
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
	
	public void createNewFeed12BoSoMBRaNhieuMB(){
		String  sql = "SELECT boso,COUNT(boso) AS sl FROM thongke_loto_mienbac WHERE DATEDIFF(CURRENT_DATE,ngay_quay)<10 GROUP BY boso Order by sl desc limit 12";
		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		String bosos = "Thống kê XSMB,12 Bộ số ra nhiều trong 10 ngày: ";
		Feed feed = new Feed();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(sql);
			preStmt = conn.prepareStatement(strSQL.toString());			
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos +=rs.getString("boso")+":"+rs.getString("sl")+",";
			}
			feed.title = bosos;
			feed.image = "http://10h.vn/themes/images/xo-so-10h-icon-NOTE.png";
			feed.url ="http://m.10h.vn/thong-ke-nhanh-xo-so-mien-bac.html";
			feed.type =4; //"TK_12BOSORANHIEU_MB";
			feed.create_user ="thongke";
			if(!StringTool.isEmptyOrNul(bosos)){
				feed.title = bosos.substring(0,bosos.length()-1);
				deleteByType(4);
				save(feed);
				
				feed.image = "http://10h.vn/themes/images/xo-so-10h-icon-NOTE.png";
				feed.url ="http://m.10h.vn/thong-ke-nhanh-xo-so-mien-bac.html";
				feed.isMobile = 1;
				save(feed);
			}
		} catch (NoSuchElementException nse) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing SQL >>>" + strSQL.toString()+ se.toString());
		} catch (Exception e) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
	}
	
	public void createNewFeed12BoSoMBRaItMB(){
		String  sql = "SELECT boso,COUNT(boso) AS sl FROM thongke_loto_mienbac WHERE DATEDIFF(CURRENT_DATE,ngay_quay)<10 GROUP BY boso Order by sl asc limit 12";
		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		String bosos = "Thống kê XSMB,12 Bộ số ra ít trong 10 ngày: ";
		Feed feed = new Feed();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(sql);
			preStmt = conn.prepareStatement(strSQL.toString());			
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos +=rs.getString("boso")+":"+rs.getString("sl")+",";
			}
			feed.title = bosos;
			feed.image = "http://10h.vn/themes/images/xo-so-10h-icon-NOTE.png";
			feed.url ="http://10h.vn/thong-ke-nhanh-xo-so-mien-bac.html/#itnhat";
			feed.type =5; //"TK_12BOSORAIT_MB";
			feed.isMobile = 0;
			feed.create_user ="thongke";
			if(!StringTool.isEmptyOrNul(bosos)){
				feed.title = bosos.substring(0,bosos.length()-1);
				deleteByType(5);
				save(feed);
				
				feed.image = "http://10h.vn/themes/images/xo-so-10h-icon-NOTE.png";
				feed.url ="http://m.10h.vn/thong-ke-nhanh-xo-so-mien-bac.html";
				feed.isMobile = 1;
				save(feed);
				
			}
		} catch (NoSuchElementException nse) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing SQL >>>" + strSQL.toString()+ se.toString());
		} catch (Exception e) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
	}
	
	
	public void createNewFeedLotoRoiMienBac(){
	 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	 	Date date = new Date(Calendar.getInstance().getTime().getTime()-24*60*60*1000);
	 	String datestr = formatter.format(date);
		String  sql =  "SELECT boso,DATE_FORMAT(start_date,'%d/%m/%Y') as start_date,DATE_FORMAT(end_date,'%d/%m/%Y') as end_date ,length as dodai_chuky FROM thongke_boso_ve_lientiep WHERE province_id = 1   AND LENGTH > 1 AND end_date = '"+datestr+"'";
		
		System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		String bosos = "Thống kê XSMB, loto rơi: ";
		Feed feed = new Feed();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(sql);
			preStmt = conn.prepareStatement(strSQL.toString());			
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				bosos +=rs.getString("boso")+":"+rs.getString("start_date")+",";
			}
			feed.title = bosos;
			feed.image = "http://10h.vn/themes/images/xo-so-10h-icon-NOTE.png";
			feed.url ="http://m.10h.vn/thong-ke-nhanh-xo-so-mien-bac.html";
			feed.type =6; //"TK_12BOSORAIT_MB";
			feed.create_user ="thongke";
			if(!StringTool.isEmptyOrNul(bosos)){
				feed.title = bosos.substring(0,bosos.length()-1);
				deleteByType(6);
				save(feed);
				
				feed.image = "http://10h.vn/themes/images/xo-so-10h-icon-NOTE.png";
				feed.url ="http://m.10h.vn/thong-ke-nhanh-xo-so-mien-bac.html";
				feed.isMobile = 1;
				save(feed);
			}
			
		} catch (NoSuchElementException nse) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing SQL >>>" + strSQL.toString()+ se.toString());
		} catch (Exception e) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
	}
	
	public void createNewFeedLotoGan10NgayMB(){
	 	String  sql =   "SELECT DISTINCT boso FROM thongke_loto_mienbac WHERE DATEDIFF(CURRENT_DATE,ngay_quay)<10"; 
		
		System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;		
		String bosos = "";
		Feed feed = new Feed();
		try {
			conn = C3p0VesoPool.getConnection();			
			strSQL = new StringBuffer(sql);
			preStmt = conn.prepareStatement(strSQL.toString());			
			rs = preStmt.executeQuery();
			int i = 0;
			while (rs.next()) {				
				bosos +=rs.getString("boso")+",";
				i++;
			}
			//int sl =100 - i;
			bosos = bosos.substring(0,bosos.length()-1);
			
			sql = "SELECT DISTINCT boso,DATE_FORMAT(ngay_quay,'%d/%m/%Y') as ngay_quay ,DATEDIFF(CURRENT_DATE,ngay_quay) as so_ngay FROM thongke_loto_mienbac WHERE boso NOT IN ("+bosos+") And DATEDIFF(CURRENT_DATE,ngay_quay)<60 Order by id DESC ";
			
			System.out.println(sql);
			strSQL = new StringBuffer(sql);
			preStmt = conn.prepareStatement(strSQL.toString());			
			rs = preStmt.executeQuery();
			
			String title = "Thông kê XSMB, loto gan trên 10 ngay: ";
			while (rs.next()) {				
				title +=rs.getString("boso")+":"+rs.getString("so_ngay")+",";
			}
			
			feed.title = title;
			feed.image = "http://10h.vn/themes/images/xo-so-10h-icon-NOTE.png";
			feed.url ="http://m.10h.vn/thong-ke-nhanh-xo-so-mien-bac.html";
			feed.type =7; //"TK_12BOSORAIT_MB";
			feed.create_user ="thongke";
			if(!StringTool.isEmptyOrNul(title)){
				feed.title = title.substring(0,title.length()-1);
				deleteByType(7);
				save(feed);
				
				feed.image = "http://10h.vn/themes/images/xo-so-10h-icon-NOTE.png";
				feed.url ="http://m.10h.vn/thong-ke-nhanh-xo-so-mien-bac.html";
				feed.isMobile = 1;
				save(feed);
			}
			
		} catch (NoSuchElementException nse) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing SQL >>>" + strSQL.toString()+ se.toString());
		} catch (Exception e) {
			logger.error("createNewFeed12BoSoMBRaNhieu: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
	}
	
	public ArrayList<Feed> getNewFeed(int isMobile) {
		PreparedStatement ps;
		ArrayList<Feed> feeds = new ArrayList<Feed>();
		
		try {
			Connection connection = C3p0VesoPool.getConnection();
			ps = connection.prepareStatement("SELECT id,title,image, url, description, TYPE FROM vtc_10h_xs.xs_feed Where isMobile = ? ORDER BY id DESC LIMIT 0, 10");
			ps.setInt(1, isMobile);
			
			ResultSet rs =	ps.executeQuery();
			while(rs.next())
			{
				Feed feed = new Feed();
				feed.id = rs.getInt("id");
				feed.title = rs.getString("title");
				feed.image = rs.getString("image");
				feed.url = rs.getString("url");
				feed.description = rs.getString("description");
				feed.type = rs.getInt("type");
				feeds.add(feed);
			}
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(ps);
			C3p0VesoPool.attemptClose(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feeds;
	}
	
	public void createFileFeed(ArrayList<Feed> feeds){
		try {
			com.veso.db.pool.DBConfig.loadProperties();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		String kq_str="<script type=\"text/javascript\" src=\"http://10h.vn/themes/js/jquery.cycle.all.js\"></script>";
		
		kq_str+="<script type=\"text/javascript\">";
		kq_str+="$(document).ready(function(){ ";
		kq_str+=" $('#slideshow').cycle({";
		kq_str+=" timeout: 3000, ";
		kq_str+=" cleartype: 1, ";
		kq_str+=" speed: 400,}); ";
		kq_str+=" $('#slideshow').cycle({ cleartype:  false }); ";
		kq_str+=" }); ";
		kq_str+="</script>\r\n";
		
		kq_str+="<div id=\"slideshow\" style=\"width: 500px;\">\r\n";
		if(feeds.size()>0){
			int i = 0;
			while(i<feeds.size()){
				Feed feed = feeds.get(i);
				kq_str+="<div class=\"feed\">\r\n";
				kq_str+="<a href='"+feed.url+"'>\r\n";
				kq_str+="<img src='"+feed.image+"' class='img_Note' alt='"+feed.title+"'>&nbsp;\r\n";
				kq_str+=feed.title;
				kq_str+="</a>\r\n";
				kq_str+="</div>\r\n";
				
				i++;
			}
		}
		kq_str+="</div>";
		try {
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(DBConfig.file_feed), "UTF8"));
				out.write(kq_str, 0, kq_str.length());
				out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void createFileFeedMobile(ArrayList<Feed> feeds){
		try {
			com.veso.db.pool.DBConfig.loadProperties();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		String kq_str="<script type=\"text/javascript\" src=\"http://10h.vn/themes/js/jquery.cycle.all.js\"></script>";
		
		kq_str+="<script type=\"text/javascript\">";
		kq_str+="$(document).ready(function(){ ";
		kq_str+=" $('#slideshow').cycle({";
		kq_str+=" timeout: 3000, ";
		kq_str+=" cleartype: 1, ";
		kq_str+=" speed: 400,}); ";
		kq_str+=" $('#slideshow').cycle({ cleartype:  false }); ";
		kq_str+=" }); ";
		kq_str+="</script>\r\n";
		
		kq_str+="<div id=\"slideshow\" style=\"width: 500px;\">\r\n";
		if(feeds.size()>0){
			int i = 0;
			while(i<feeds.size()){
				Feed feed = feeds.get(i);
				kq_str+="<div class=\"feed\">\r\n";
				kq_str+="<a href='"+feed.url+"'>\r\n";
				kq_str+="<img src='"+feed.image+"' class='img_Note' alt='"+feed.title+"'>&nbsp;\r\n";
				kq_str+=feed.title;
				kq_str+="</a>\r\n";
				kq_str+="</div>\r\n";
				
				i++;
			}
		}
		kq_str+="</div>";
		try {
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(DBConfig.file_feed_mobile), "UTF8"));
				out.write(kq_str, 0, kq_str.length());
				out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		FeedDAO feedDAO = new FeedDAO();
		//feedDAO.createNewFeedLotoGan10NgayMB();
		
		ArrayList<Feed> feeds = feedDAO.getNewFeed(0);
		feedDAO.createFileFeed(feeds);
	}

}
