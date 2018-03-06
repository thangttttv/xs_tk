package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.veso.bean.BosoTongCau;
import com.veso.bean.CauDep;
import com.veso.bean.SoiCauBean;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class SoiCauDAO {
	private String table = "soicau";
	private Logger logger = new Logger(this.getClass().getName());

	public long saveCau(SoiCauBean soiCauBean) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;		
		long id=0;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					"INSERT INTO soicau(province_id, bien_ngay, bien_do_ngay, bs_00, bs_01, bs_02, bs_03, bs_04, bs_05, bs_06, bs_07, " +
					" bs_08, bs_09,	bs_10, bs_11, bs_12, bs_13, bs_14,	bs_15, bs_16, bs_17, bs_18,	bs_19,	bs_20, bs_21, bs_22, bs_23," +
					" bs_24,bs_25,bs_26,bs_27,bs_28,bs_29,bs_30,bs_31,bs_32,bs_33,bs_34,bs_35,bs_36,bs_37,bs_38,bs_39,bs_40,bs_41,bs_42," +
					" bs_43,bs_44,bs_45,bs_46,bs_47,bs_48,bs_49,bs_50,bs_51,bs_52,bs_53,bs_54,bs_55,bs_56,bs_57,bs_58,bs_59,bs_60,bs_61," +
					" bs_62,bs_63,bs_64,bs_65,bs_66,bs_67,bs_68,bs_69,bs_70,bs_71,bs_72,bs_73,bs_74,bs_75,bs_76,bs_77,bs_78,bs_79," +
					" bs_80,bs_81,bs_82,bs_83,bs_84,bs_85,bs_86,bs_87,bs_88,bs_89,bs_90,bs_91,bs_92,bs_93," +
					" bs_94,bs_95,bs_96,bs_97,bs_98,bs_99,create_date,type)VALUES( ?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?," +
					" ?,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
					" ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1, soiCauBean.getProvince_id());
			preStmt.setDate(2, soiCauBean.getBien_ngay());
			preStmt.setInt(3, soiCauBean.getBien_do_ngay());
			
			preStmt.setInt(4, soiCauBean.getBs_00());
			preStmt.setInt(5, soiCauBean.getBs_01());
			preStmt.setInt(6, soiCauBean.getBs_02());
			preStmt.setInt(7, soiCauBean.getBs_03());
			preStmt.setInt(8, soiCauBean.getBs_04());
			preStmt.setInt(9, soiCauBean.getBs_05());
			preStmt.setInt(10, soiCauBean.getBs_06());
			preStmt.setInt(11, soiCauBean.getBs_07());
			preStmt.setInt(12, soiCauBean.getBs_08());
			preStmt.setInt(13, soiCauBean.getBs_09());
			
			preStmt.setInt(14, soiCauBean.getBs_10());
			preStmt.setInt(15, soiCauBean.getBs_11());
			preStmt.setInt(16, soiCauBean.getBs_12());
			preStmt.setInt(17, soiCauBean.getBs_13());
			preStmt.setInt(18, soiCauBean.getBs_14());
			preStmt.setInt(19, soiCauBean.getBs_15());
			preStmt.setInt(20, soiCauBean.getBs_16());
			preStmt.setInt(21, soiCauBean.getBs_17());
			preStmt.setInt(22, soiCauBean.getBs_18());
			preStmt.setInt(23, soiCauBean.getBs_19());
			
			preStmt.setInt(24, soiCauBean.getBs_20());
			preStmt.setInt(25, soiCauBean.getBs_21());
			preStmt.setInt(26, soiCauBean.getBs_22());
			preStmt.setInt(27, soiCauBean.getBs_23());
			preStmt.setInt(28, soiCauBean.getBs_24());
			preStmt.setInt(29, soiCauBean.getBs_25());
			preStmt.setInt(30, soiCauBean.getBs_26());
			preStmt.setInt(31, soiCauBean.getBs_27());
			preStmt.setInt(32, soiCauBean.getBs_28());
			preStmt.setInt(33, soiCauBean.getBs_29());
			
			preStmt.setInt(34, soiCauBean.getBs_30());
			preStmt.setInt(35, soiCauBean.getBs_31());
			preStmt.setInt(36, soiCauBean.getBs_32());
			preStmt.setInt(37, soiCauBean.getBs_33());
			preStmt.setInt(38, soiCauBean.getBs_34());
			preStmt.setInt(39, soiCauBean.getBs_35());
			preStmt.setInt(40, soiCauBean.getBs_36());
			preStmt.setInt(41, soiCauBean.getBs_37());
			preStmt.setInt(42, soiCauBean.getBs_38());
			preStmt.setInt(43, soiCauBean.getBs_39());
			
			preStmt.setInt(44, soiCauBean.getBs_40());
			preStmt.setInt(45, soiCauBean.getBs_41());
			preStmt.setInt(46, soiCauBean.getBs_42());
			preStmt.setInt(47, soiCauBean.getBs_43());
			preStmt.setInt(48, soiCauBean.getBs_44());
			preStmt.setInt(49, soiCauBean.getBs_45());
			preStmt.setInt(50, soiCauBean.getBs_46());
			preStmt.setInt(51, soiCauBean.getBs_47());
			preStmt.setInt(52, soiCauBean.getBs_48());
			preStmt.setInt(53, soiCauBean.getBs_49());
			
			preStmt.setInt(54, soiCauBean.getBs_50());
			preStmt.setInt(55, soiCauBean.getBs_51());
			preStmt.setInt(56, soiCauBean.getBs_52());
			preStmt.setInt(57, soiCauBean.getBs_53());
			preStmt.setInt(58, soiCauBean.getBs_54());
			preStmt.setInt(59, soiCauBean.getBs_55());
			preStmt.setInt(60, soiCauBean.getBs_56());
			preStmt.setInt(61, soiCauBean.getBs_57());
			preStmt.setInt(62, soiCauBean.getBs_58());
			preStmt.setInt(63, soiCauBean.getBs_59());
			
			preStmt.setInt(64, soiCauBean.getBs_60());
			preStmt.setInt(65, soiCauBean.getBs_61());
			preStmt.setInt(66, soiCauBean.getBs_62());
			preStmt.setInt(67, soiCauBean.getBs_63());
			preStmt.setInt(68, soiCauBean.getBs_64());
			preStmt.setInt(69, soiCauBean.getBs_65());
			preStmt.setInt(70, soiCauBean.getBs_66());
			preStmt.setInt(71, soiCauBean.getBs_67());
			preStmt.setInt(72, soiCauBean.getBs_68());
			preStmt.setInt(73, soiCauBean.getBs_69());
			
			preStmt.setInt(74, soiCauBean.getBs_70());
			preStmt.setInt(75, soiCauBean.getBs_71());
			preStmt.setInt(76, soiCauBean.getBs_72());
			preStmt.setInt(77, soiCauBean.getBs_73());
			preStmt.setInt(78, soiCauBean.getBs_74());
			preStmt.setInt(79, soiCauBean.getBs_75());
			preStmt.setInt(80, soiCauBean.getBs_76());
			preStmt.setInt(81, soiCauBean.getBs_77());
			preStmt.setInt(82, soiCauBean.getBs_78());
			preStmt.setInt(83, soiCauBean.getBs_79());
			
			preStmt.setInt(84, soiCauBean.getBs_80());
			preStmt.setInt(85, soiCauBean.getBs_81());
			preStmt.setInt(86, soiCauBean.getBs_82());
			preStmt.setInt(87, soiCauBean.getBs_83());
			preStmt.setInt(88, soiCauBean.getBs_84());
			preStmt.setInt(89, soiCauBean.getBs_85());
			preStmt.setInt(90, soiCauBean.getBs_86());
			preStmt.setInt(91, soiCauBean.getBs_87());
			preStmt.setInt(92, soiCauBean.getBs_88());
			preStmt.setInt(93, soiCauBean.getBs_89());
			
			preStmt.setInt(94, soiCauBean.getBs_90());
			preStmt.setInt(95, soiCauBean.getBs_91());
			preStmt.setInt(96, soiCauBean.getBs_92());
			preStmt.setInt(97, soiCauBean.getBs_93());
			preStmt.setInt(98, soiCauBean.getBs_94());
			preStmt.setInt(99, soiCauBean.getBs_95());
			
			preStmt.setInt(100, soiCauBean.getBs_96());
			preStmt.setInt(101, soiCauBean.getBs_97());
			preStmt.setInt(102, soiCauBean.getBs_98());
			preStmt.setInt(103, soiCauBean.getBs_99());
			
			preStmt.setTimestamp(104, soiCauBean.getCreate_date());
			preStmt.setInt(105, soiCauBean.getType());
			
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				String queryStr = "select LAST_INSERT_ID()";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery( queryStr );
				if ( rs.next() )
					{
						id = rs.getInt(1);
					}				
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
		return id;
	}
	
	
	public boolean save(SoiCauBean soiCauBean) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					"INSERT INTO soicau(province_id, bien_ngay, bien_do_ngay, bs_00, bs_01, bs_02, bs_03, bs_04, bs_05, bs_06, bs_07, " +
					" bs_08, bs_09,	bs_10, bs_11, bs_12, bs_13, bs_14,	bs_15, bs_16, bs_17, bs_18,	bs_19,	bs_20, bs_21, bs_22, bs_23," +
					" bs_24,bs_25,bs_26,bs_27,bs_28,bs_29,bs_30,bs_31,bs_32,bs_33,bs_34,bs_35,bs_36,bs_37,bs_38,bs_39,bs_40,bs_41,bs_42," +
					" bs_43,bs_44,bs_45,bs_46,bs_47,bs_48,bs_49,bs_50,bs_51,bs_52,bs_53,bs_54,bs_55,bs_56,bs_57,bs_58,bs_59,bs_60,bs_61," +
					" bs_62,bs_63,bs_64,bs_65,bs_66,bs_67,bs_68,bs_69,bs_70,bs_71,bs_72,bs_73,bs_74,bs_75,bs_76,bs_77,bs_78,bs_79," +
					" bs_80,bs_81,bs_82,bs_83,bs_84,bs_85,bs_86,bs_87,bs_88,bs_89,bs_90,bs_91,bs_92,bs_93," +
					" bs_94,bs_95,bs_96,bs_97,bs_98,bs_99,create_date)VALUES( ?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?," +
					" ?,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
					" ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1, soiCauBean.getProvince_id());
			preStmt.setDate(2, soiCauBean.getBien_ngay());
			preStmt.setInt(3, soiCauBean.getBien_do_ngay());
			
			preStmt.setInt(4, soiCauBean.getBs_00());
			preStmt.setInt(5, soiCauBean.getBs_01());
			preStmt.setInt(6, soiCauBean.getBs_02());
			preStmt.setInt(7, soiCauBean.getBs_03());
			preStmt.setInt(8, soiCauBean.getBs_04());
			preStmt.setInt(9, soiCauBean.getBs_05());
			preStmt.setInt(10, soiCauBean.getBs_06());
			preStmt.setInt(11, soiCauBean.getBs_07());
			preStmt.setInt(12, soiCauBean.getBs_08());
			preStmt.setInt(13, soiCauBean.getBs_09());
			
			preStmt.setInt(14, soiCauBean.getBs_10());
			preStmt.setInt(15, soiCauBean.getBs_11());
			preStmt.setInt(16, soiCauBean.getBs_12());
			preStmt.setInt(17, soiCauBean.getBs_13());
			preStmt.setInt(18, soiCauBean.getBs_14());
			preStmt.setInt(19, soiCauBean.getBs_15());
			preStmt.setInt(20, soiCauBean.getBs_16());
			preStmt.setInt(21, soiCauBean.getBs_17());
			preStmt.setInt(22, soiCauBean.getBs_18());
			preStmt.setInt(23, soiCauBean.getBs_19());
			
			preStmt.setInt(24, soiCauBean.getBs_20());
			preStmt.setInt(25, soiCauBean.getBs_21());
			preStmt.setInt(26, soiCauBean.getBs_22());
			preStmt.setInt(27, soiCauBean.getBs_23());
			preStmt.setInt(28, soiCauBean.getBs_24());
			preStmt.setInt(29, soiCauBean.getBs_25());
			preStmt.setInt(30, soiCauBean.getBs_26());
			preStmt.setInt(31, soiCauBean.getBs_27());
			preStmt.setInt(32, soiCauBean.getBs_28());
			preStmt.setInt(33, soiCauBean.getBs_29());
			
			preStmt.setInt(34, soiCauBean.getBs_30());
			preStmt.setInt(35, soiCauBean.getBs_31());
			preStmt.setInt(36, soiCauBean.getBs_32());
			preStmt.setInt(37, soiCauBean.getBs_33());
			preStmt.setInt(38, soiCauBean.getBs_34());
			preStmt.setInt(39, soiCauBean.getBs_35());
			preStmt.setInt(40, soiCauBean.getBs_36());
			preStmt.setInt(41, soiCauBean.getBs_37());
			preStmt.setInt(42, soiCauBean.getBs_38());
			preStmt.setInt(43, soiCauBean.getBs_39());
			
			preStmt.setInt(44, soiCauBean.getBs_40());
			preStmt.setInt(45, soiCauBean.getBs_41());
			preStmt.setInt(46, soiCauBean.getBs_42());
			preStmt.setInt(47, soiCauBean.getBs_43());
			preStmt.setInt(48, soiCauBean.getBs_44());
			preStmt.setInt(49, soiCauBean.getBs_45());
			preStmt.setInt(50, soiCauBean.getBs_46());
			preStmt.setInt(51, soiCauBean.getBs_47());
			preStmt.setInt(52, soiCauBean.getBs_48());
			preStmt.setInt(53, soiCauBean.getBs_49());
			
			preStmt.setInt(54, soiCauBean.getBs_50());
			preStmt.setInt(55, soiCauBean.getBs_51());
			preStmt.setInt(56, soiCauBean.getBs_52());
			preStmt.setInt(57, soiCauBean.getBs_53());
			preStmt.setInt(58, soiCauBean.getBs_54());
			preStmt.setInt(59, soiCauBean.getBs_55());
			preStmt.setInt(60, soiCauBean.getBs_56());
			preStmt.setInt(61, soiCauBean.getBs_57());
			preStmt.setInt(62, soiCauBean.getBs_58());
			preStmt.setInt(63, soiCauBean.getBs_59());
			
			preStmt.setInt(64, soiCauBean.getBs_60());
			preStmt.setInt(65, soiCauBean.getBs_61());
			preStmt.setInt(66, soiCauBean.getBs_62());
			preStmt.setInt(67, soiCauBean.getBs_63());
			preStmt.setInt(68, soiCauBean.getBs_64());
			preStmt.setInt(69, soiCauBean.getBs_65());
			preStmt.setInt(70, soiCauBean.getBs_66());
			preStmt.setInt(71, soiCauBean.getBs_67());
			preStmt.setInt(72, soiCauBean.getBs_68());
			preStmt.setInt(73, soiCauBean.getBs_69());
			
			preStmt.setInt(74, soiCauBean.getBs_70());
			preStmt.setInt(75, soiCauBean.getBs_71());
			preStmt.setInt(76, soiCauBean.getBs_72());
			preStmt.setInt(77, soiCauBean.getBs_73());
			preStmt.setInt(78, soiCauBean.getBs_74());
			preStmt.setInt(79, soiCauBean.getBs_75());
			preStmt.setInt(80, soiCauBean.getBs_76());
			preStmt.setInt(81, soiCauBean.getBs_77());
			preStmt.setInt(82, soiCauBean.getBs_78());
			preStmt.setInt(83, soiCauBean.getBs_79());
			
			preStmt.setInt(84, soiCauBean.getBs_80());
			preStmt.setInt(85, soiCauBean.getBs_81());
			preStmt.setInt(86, soiCauBean.getBs_82());
			preStmt.setInt(87, soiCauBean.getBs_83());
			preStmt.setInt(88, soiCauBean.getBs_84());
			preStmt.setInt(89, soiCauBean.getBs_85());
			preStmt.setInt(90, soiCauBean.getBs_86());
			preStmt.setInt(91, soiCauBean.getBs_87());
			preStmt.setInt(92, soiCauBean.getBs_88());
			preStmt.setInt(93, soiCauBean.getBs_89());
			
			preStmt.setInt(94, soiCauBean.getBs_90());
			preStmt.setInt(95, soiCauBean.getBs_91());
			preStmt.setInt(96, soiCauBean.getBs_92());
			preStmt.setInt(97, soiCauBean.getBs_93());
			preStmt.setInt(98, soiCauBean.getBs_94());
			preStmt.setInt(99, soiCauBean.getBs_95());
			
			preStmt.setInt(100, soiCauBean.getBs_96());
			preStmt.setInt(101, soiCauBean.getBs_97());
			preStmt.setInt(102, soiCauBean.getBs_98());
			preStmt.setInt(103, soiCauBean.getBs_99());
			
			preStmt.setTimestamp(104, soiCauBean.getCreate_date());
			
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

	public boolean updateBoso(long id, String boso) {

		boolean result = false;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		try {			
			conn = C3p0VesoPool.getConnection();			
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" UPDATE "+this.table+" SET "+boso+"  = "+boso+" + 1 WHERE id = ? ");
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
	
	
	public List<SoiCauBean> findAll(int start) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		StringBuffer strSQL = null;
		List<SoiCauBean>  list = new ArrayList<SoiCauBean>();
		SoiCauBean soiCauBean = null;
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer("SELECT 	id,	province_id,bien_ngay,bien_do_ngay,TYPE,bs_00,bs_01,bs_02,bs_03,bs_04,bs_05,bs_06," +
					" 	bs_07,bs_08,bs_09,bs_10,bs_11,bs_12,bs_13,bs_14,bs_15,bs_16,bs_17,bs_18,bs_19,bs_20,bs_21,bs_22,bs_23,bs_24," +
					"  	bs_25,bs_26,bs_27,bs_28,bs_29,bs_30,bs_31,bs_32,bs_33,bs_34,bs_35,bs_36,bs_37,bs_38,bs_39,bs_40,bs_41,bs_42," +
					"   bs_43,bs_44,bs_45,bs_46,bs_47,bs_48,bs_49,bs_50,bs_51,bs_52,bs_53,bs_54,bs_55,bs_56,bs_57,bs_58,bs_59,bs_60," +
					"   bs_61,bs_62,bs_63,bs_64,bs_65,bs_66,bs_67,bs_68,bs_69,bs_70,bs_71,bs_72,bs_73,bs_74,bs_75,bs_76,bs_77,bs_78," +
					"  	bs_79,bs_80,bs_81,bs_82,bs_83,bs_84,bs_85,bs_86,bs_87,bs_88,bs_89,bs_90,bs_91,bs_92,bs_93,bs_94,bs_95,bs_96," +
					"   bs_97,bs_98,bs_99, create_date 	FROM soicau Where id > ?  Order by id");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1, start);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				 soiCauBean = new SoiCauBean();
				 soiCauBean.setId(rs.getLong("id"));
				 soiCauBean.setProvince_id(rs.getInt("province_id"));
				 soiCauBean.setBien_ngay(rs.getDate("bien_ngay"));
				 soiCauBean.setBien_do_ngay(rs.getInt("bien_do_ngay"));
				 soiCauBean.setType(rs.getInt("type"));
				 soiCauBean.setBs_00(rs.getInt("bs_00"));
				 soiCauBean.setBs_01(rs.getInt("bs_01"));
				 soiCauBean.setBs_02(rs.getInt("bs_02"));
				 soiCauBean.setBs_03(rs.getInt("bs_03"));
				 soiCauBean.setBs_04(rs.getInt("bs_04"));
				 soiCauBean.setBs_05(rs.getInt("bs_05"));
				 soiCauBean.setBs_06(rs.getInt("bs_06"));
				 soiCauBean.setBs_07(rs.getInt("bs_07"));
				 soiCauBean.setBs_08(rs.getInt("bs_08"));
				 soiCauBean.setBs_09(rs.getInt("bs_09"));
				 
				 soiCauBean.setBs_10(rs.getInt("bs_10"));
				 soiCauBean.setBs_11(rs.getInt("bs_11"));
				 soiCauBean.setBs_12(rs.getInt("bs_12"));
				 soiCauBean.setBs_13(rs.getInt("bs_13"));
				 soiCauBean.setBs_14(rs.getInt("bs_14"));
				 soiCauBean.setBs_15(rs.getInt("bs_15"));
				 soiCauBean.setBs_16(rs.getInt("bs_16"));
				 soiCauBean.setBs_17(rs.getInt("bs_17"));
				 soiCauBean.setBs_18(rs.getInt("bs_18"));
				 soiCauBean.setBs_19(rs.getInt("bs_19"));
				 
				 soiCauBean.setBs_20(rs.getInt("bs_20"));
				 soiCauBean.setBs_21(rs.getInt("bs_21"));
				 soiCauBean.setBs_22(rs.getInt("bs_22"));
				 soiCauBean.setBs_23(rs.getInt("bs_23"));
				 soiCauBean.setBs_24(rs.getInt("bs_24"));
				 soiCauBean.setBs_25(rs.getInt("bs_25"));
				 soiCauBean.setBs_26(rs.getInt("bs_26"));
				 soiCauBean.setBs_27(rs.getInt("bs_27"));
				 soiCauBean.setBs_28(rs.getInt("bs_28"));
				 soiCauBean.setBs_29(rs.getInt("bs_29"));
				 
				 soiCauBean.setBs_30(rs.getInt("bs_30"));
				 soiCauBean.setBs_31(rs.getInt("bs_31"));
				 soiCauBean.setBs_32(rs.getInt("bs_32"));
				 soiCauBean.setBs_33(rs.getInt("bs_33"));
				 soiCauBean.setBs_34(rs.getInt("bs_34"));
				 soiCauBean.setBs_35(rs.getInt("bs_35"));
				 soiCauBean.setBs_36(rs.getInt("bs_36"));
				 soiCauBean.setBs_37(rs.getInt("bs_37"));
				 soiCauBean.setBs_38(rs.getInt("bs_38"));
				 soiCauBean.setBs_39(rs.getInt("bs_39"));
				 
				 soiCauBean.setBs_40(rs.getInt("bs_40"));
				 soiCauBean.setBs_41(rs.getInt("bs_41"));
				 soiCauBean.setBs_42(rs.getInt("bs_42"));
				 soiCauBean.setBs_43(rs.getInt("bs_43"));
				 soiCauBean.setBs_44(rs.getInt("bs_44"));
				 soiCauBean.setBs_45(rs.getInt("bs_45"));
				 soiCauBean.setBs_46(rs.getInt("bs_46"));
				 soiCauBean.setBs_47(rs.getInt("bs_47"));
				 soiCauBean.setBs_48(rs.getInt("bs_48"));
				 soiCauBean.setBs_49(rs.getInt("bs_49"));
				 
				 soiCauBean.setBs_50(rs.getInt("bs_50"));
				 soiCauBean.setBs_51(rs.getInt("bs_51"));
				 soiCauBean.setBs_52(rs.getInt("bs_52"));
				 soiCauBean.setBs_53(rs.getInt("bs_53"));
				 soiCauBean.setBs_54(rs.getInt("bs_54"));
				 soiCauBean.setBs_55(rs.getInt("bs_55"));
				 soiCauBean.setBs_56(rs.getInt("bs_56"));
				 soiCauBean.setBs_57(rs.getInt("bs_57"));
				 soiCauBean.setBs_58(rs.getInt("bs_58"));
				 soiCauBean.setBs_59(rs.getInt("bs_59"));
				 
				 soiCauBean.setBs_60(rs.getInt("bs_60"));
				 soiCauBean.setBs_61(rs.getInt("bs_61"));
				 soiCauBean.setBs_62(rs.getInt("bs_62"));
				 soiCauBean.setBs_63(rs.getInt("bs_63"));
				 soiCauBean.setBs_64(rs.getInt("bs_64"));
				 soiCauBean.setBs_65(rs.getInt("bs_65"));
				 soiCauBean.setBs_66(rs.getInt("bs_66"));
				 soiCauBean.setBs_67(rs.getInt("bs_67"));
				 soiCauBean.setBs_68(rs.getInt("bs_68"));
				 soiCauBean.setBs_69(rs.getInt("bs_69"));
				 
				 soiCauBean.setBs_70(rs.getInt("bs_70"));
				 soiCauBean.setBs_71(rs.getInt("bs_71"));
				 soiCauBean.setBs_72(rs.getInt("bs_72"));
				 soiCauBean.setBs_73(rs.getInt("bs_73"));
				 soiCauBean.setBs_74(rs.getInt("bs_74"));
				 soiCauBean.setBs_75(rs.getInt("bs_75"));
				 soiCauBean.setBs_76(rs.getInt("bs_76"));
				 soiCauBean.setBs_77(rs.getInt("bs_77"));
				 soiCauBean.setBs_78(rs.getInt("bs_78"));
				 soiCauBean.setBs_79(rs.getInt("bs_79"));
				 
				 soiCauBean.setBs_80(rs.getInt("bs_80"));
				 soiCauBean.setBs_81(rs.getInt("bs_81"));
				 soiCauBean.setBs_82(rs.getInt("bs_82"));
				 soiCauBean.setBs_83(rs.getInt("bs_83"));
				 soiCauBean.setBs_84(rs.getInt("bs_84"));
				 soiCauBean.setBs_85(rs.getInt("bs_85"));
				 soiCauBean.setBs_86(rs.getInt("bs_86"));
				 soiCauBean.setBs_87(rs.getInt("bs_87"));
				 soiCauBean.setBs_88(rs.getInt("bs_88"));
				 soiCauBean.setBs_89(rs.getInt("bs_89"));
				 
				 soiCauBean.setBs_90(rs.getInt("bs_90"));
				 soiCauBean.setBs_91(rs.getInt("bs_91"));
				 soiCauBean.setBs_92(rs.getInt("bs_92"));
				 soiCauBean.setBs_93(rs.getInt("bs_93"));
				 soiCauBean.setBs_94(rs.getInt("bs_94"));
				 soiCauBean.setBs_95(rs.getInt("bs_95"));
				 soiCauBean.setBs_96(rs.getInt("bs_96"));
				 soiCauBean.setBs_97(rs.getInt("bs_97"));
				 soiCauBean.setBs_98(rs.getInt("bs_98"));
				 soiCauBean.setBs_99(rs.getInt("bs_99"));
				 
				 soiCauBean.setCreate_date(rs.getTimestamp("create_date"));
				 
				 list.add(soiCauBean);
			}
		} catch (NoSuchElementException nse) {
			logger.error("findAll: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAll: Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("findAll: Error executing >>>" + e.toString());
		} finally {
		
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return list;
	}
	
	public int[] statisTongDuongCau(String bien_ngay,int province_id,int type) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		StringBuffer strSQL = null;
		int[] arrTongCau  = new int[100];
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer("SELECT 	id,	province_id,bien_ngay,bien_do_ngay,TYPE,bs_00,bs_01,bs_02,bs_03,bs_04,bs_05,bs_06," +
					" 	bs_07,bs_08,bs_09,bs_10,bs_11,bs_12,bs_13,bs_14,bs_15,bs_16,bs_17,bs_18,bs_19,bs_20,bs_21,bs_22,bs_23,bs_24," +
					"  	bs_25,bs_26,bs_27,bs_28,bs_29,bs_30,bs_31,bs_32,bs_33,bs_34,bs_35,bs_36,bs_37,bs_38,bs_39,bs_40,bs_41,bs_42," +
					"   bs_43,bs_44,bs_45,bs_46,bs_47,bs_48,bs_49,bs_50,bs_51,bs_52,bs_53,bs_54,bs_55,bs_56,bs_57,bs_58,bs_59,bs_60," +
					"   bs_61,bs_62,bs_63,bs_64,bs_65,bs_66,bs_67,bs_68,bs_69,bs_70,bs_71,bs_72,bs_73,bs_74,bs_75,bs_76,bs_77,bs_78," +
					"  	bs_79,bs_80,bs_81,bs_82,bs_83,bs_84,bs_85,bs_86,bs_87,bs_88,bs_89,bs_90,bs_91,bs_92,bs_93,bs_94,bs_95,bs_96," +
					"   bs_97,bs_98,bs_99, create_date 	FROM soicau Where bien_ngay = ? And  province_id = ? And type = ? Order by id  ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setString(1, bien_ngay);
			preStmt.setLong(2, province_id);
			preStmt.setLong(3, type);
			rs = preStmt.executeQuery();
			
			while (rs.next()) {
				 int i = 0;
				 while(i<100){
					 String strI = ""+i;
					 if(i<10) strI = "0"+i;
					 arrTongCau[i] += rs.getInt("bs_"+strI);
					 i++;
				 }
			}
		} catch (NoSuchElementException nse) {
			logger.error("statisTongDuongCau: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("statisTongDuongCau: Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("statisTongDuongCau: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return arrTongCau;
	}
	
	
	public int getCauByBosoBienNgayTinh(String bien_ngay,int province_id,int type,String boso) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		StringBuffer strSQL = null;
		int id = 0;
		try {
			conn = C3p0VesoPool.getConnection();
			String str_boso = "bs_"+boso;
			strSQL = new StringBuffer("SELECT 	id,	province_id,bien_ngay,bien_do_ngay,TYPE,bs_00,bs_01,bs_02,bs_03,bs_04,bs_05,bs_06," +
					" 	bs_07,bs_08,bs_09,bs_10,bs_11,bs_12,bs_13,bs_14,bs_15,bs_16,bs_17,bs_18,bs_19,bs_20,bs_21,bs_22,bs_23,bs_24," +
					"  	bs_25,bs_26,bs_27,bs_28,bs_29,bs_30,bs_31,bs_32,bs_33,bs_34,bs_35,bs_36,bs_37,bs_38,bs_39,bs_40,bs_41,bs_42," +
					"   bs_43,bs_44,bs_45,bs_46,bs_47,bs_48,bs_49,bs_50,bs_51,bs_52,bs_53,bs_54,bs_55,bs_56,bs_57,bs_58,bs_59,bs_60," +
					"   bs_61,bs_62,bs_63,bs_64,bs_65,bs_66,bs_67,bs_68,bs_69,bs_70,bs_71,bs_72,bs_73,bs_74,bs_75,bs_76,bs_77,bs_78," +
					"  	bs_79,bs_80,bs_81,bs_82,bs_83,bs_84,bs_85,bs_86,bs_87,bs_88,bs_89,bs_90,bs_91,bs_92,bs_93,bs_94,bs_95,bs_96," +
					"   bs_97,bs_98,bs_99, create_date 	FROM soicau Where bien_ngay = ? And  province_id = ? And TYPE = ? And "+str_boso+" > 0  Order by bien_do_ngay DESC Limit 1 ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setString(1, bien_ngay);
			preStmt.setLong(2, province_id);
			preStmt.setLong(3, type);
			rs = preStmt.executeQuery();
			
			while (rs.next()) {
				 id = rs.getInt("id");
				
			}
		} catch (NoSuchElementException nse) {
			logger.error("getCauByBosoBienNgayTinh: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getCauByBosoBienNgayTinh: Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("getCauByBosoBienNgayTinh: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return id;
	}
	
	
	public long saveCauDep(CauDep cauDep) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;		
		long id=0;
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("INSERT INTO xs_cau_dep (province_id, TYPE, bien_ngay, boso, create_date)"
					+ " VALUES (?,?,?,?,NOW())");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,cauDep.province_id);
			preStmt.setInt(2,cauDep.type);
			preStmt.setString(3,cauDep.bien_ngay);
			preStmt.setString(4,cauDep.boso);
			
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				String queryStr = "select LAST_INSERT_ID()";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery( queryStr );
				if ( rs.next() )
					{
						id = rs.getInt(1);
					}				
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("saveCauDep: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("saveCauDep: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("saveCauDep: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return id;
	}
	
	public void updateCauDep(CauDep cauDep) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;		
		try {
			conn = C3p0VesoPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("Update  xs_cau_dep Set boso = ? Where id = ? ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setString(1,cauDep.boso);
			preStmt.setInt(2,cauDep.id);
			
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("saveCauDep: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("saveCauDep: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("saveCauDep: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
	}
	
	public int checkTKCauDep(int province_id,int type_cau,String bien_ngay) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;		
		int id = 0;
		try {
			conn = C3p0VesoPool.getConnection();		
			strSQL = new StringBuffer("Select id From xs_cau_dep Where province_id = ? And TYPE = ? And bien_ngay = ? ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,province_id);
			preStmt.setInt(2,type_cau);
			preStmt.setString(3,bien_ngay);
			ResultSet rs = preStmt.executeQuery();
			
			if (rs.next()) {
				id = rs.getInt(1);
			}

			
		} catch (NoSuchElementException nse) {
			logger.error("checkTKCauDep: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("checkTKCauDep: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("checkTKCauDep: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return id;
	}
	
	
	public void statisCauDep(int province_id,int type_cau,String bien_ngay,int soluong_boso){
		SoiCauDAO soiCauDAO = new SoiCauDAO();
		int[] arrTongCau  = soiCauDAO.statisTongDuongCau(bien_ngay, province_id,type_cau);

		int i = 0;
		// Tong Cau
		List<BosoTongCau> listBs = new ArrayList<BosoTongCau>();
		while (i<100) {
			System.out.println(i+":"+arrTongCau[i]);
			BosoTongCau bosoTongCau = new BosoTongCau();
			 String strI = ""+i;
			 if(i<10) strI = "0"+i;
			bosoTongCau.boso = strI;
			bosoTongCau.tongcau = arrTongCau[i];
			listBs.add(bosoTongCau);
			i++;
		}
		
		// Sort
		Collections.sort(listBs , new Comparator<BosoTongCau>() {
            @Override
            public int compare(BosoTongCau lhs, BosoTongCau rhs) {
                return lhs.tongcau > rhs.tongcau ? -1 : (lhs.tongcau < rhs.tongcau ) ? 1 : 0;
            }
        });
		
		System.out.println("---------------------------------");
		i = 0;
		// Save cau dep
		CauDep cauDep = new CauDep();
		cauDep.bien_ngay = bien_ngay;
		cauDep.province_id = province_id;
		cauDep.type = type_cau;
		
		JSONArray jsonArray = new JSONArray();
		String boso = "[";
		for (BosoTongCau bosoTongCau : listBs) {
			if(i<soluong_boso){
				int cau_id = soiCauDAO.getCauByBosoBienNgayTinh(bien_ngay, province_id,type_cau,bosoTongCau.boso);
				/*if(i==0)
					boso +="{\"cau_id\":"+cau_id+",\"boso\":"+bosoTongCau.boso+",\"tongcau\":"+bosoTongCau.tongcau+"}";
				else 
					boso +=", {\"cau_id\":"+cau_id+",\"boso\":"+bosoTongCau.boso+",\"tongcau\":"+bosoTongCau.tongcau+"}";
				    System.out.println(bosoTongCau.boso+":"+bosoTongCau.tongcau+" ---->"+cau_id);
				    */
				    JSONObject formDetailsJson = new JSONObject();
			        formDetailsJson.put("cau_id",cau_id);
			        formDetailsJson.put("boso", bosoTongCau.boso);
			        formDetailsJson.put("tongcau", bosoTongCau.tongcau);
			        jsonArray.add(formDetailsJson);
			}
			i++;
		}
		boso = jsonArray.toString();
		
		cauDep.boso = boso;
		int id = soiCauDAO.checkTKCauDep(province_id, type_cau, bien_ngay);
		if(id==0)
			soiCauDAO.saveCauDep(cauDep);
		else{
			cauDep.id = id;
			soiCauDAO.updateCauDep(cauDep);
		}
		
	}

}
