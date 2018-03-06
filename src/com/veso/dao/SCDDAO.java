package com.veso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

import com.veso.bean.BuocCauBean;
import com.veso.bean.Dientoan123;
import com.veso.bean.Dientoan6x63;
import com.veso.bean.DientoanThantai;
import com.veso.bean.KetQuaMienBacBean;
import com.veso.bean.KetQuaMienNamBean;
import com.veso.bean.KetQuaMienTrungBean;
import com.veso.bean.LotoBean;
import com.veso.bean.SoiCauBean;
import com.veso.bean.TKChuKiBoSoVeLienTiep;
import com.veso.bean.TKChuKiBoXien2;
import com.veso.bean.TKChuKiDau;
import com.veso.bean.TKChuKiDauSoVeLienTiep;
import com.veso.bean.TKChuKiDuoi;
import com.veso.bean.TKChuKiDuoiSoVeLienTiep;
import com.veso.bean.TKChuKiTongDB;
import com.veso.bean.TKChuKiTongDBVeLienTiep;
import com.veso.bean.TKChuKyBoSo;
import com.veso.bean.TanSuatBoSoBean;
import com.veso.db.pool.C3p0SCDPool;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.Logger;

public class SCDDAO {
	
	private Logger logger = new Logger(this.getClass().getName());
	
	public int getMaxID(String table) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int max_id = 0;
		try {
			conn = C3p0SCDPool.getConnection();
			strSQL = new StringBuffer("SELECT max(id) FROM "	+ table);
			preStmt = conn.prepareStatement(strSQL.toString());
			rs = preStmt.executeQuery();
			if (rs.next()) {
				max_id = rs.getInt(1);
			}
		} catch (NoSuchElementException nse) {
			logger.error("getMaxID: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getMaxID: Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("getMaxID: Error executing >>>" + e.toString());
		} finally {
			C3p0SCDPool.attemptClose(rs);
			C3p0SCDPool.attemptClose(preStmt);			
			C3p0SCDPool.attemptClose(conn);
		}
		return max_id;
	}
	
	
	public void saveKQMB(KetQuaMienBacBean bean) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("INSERT INTO ketqua_mienbac (id,province_id,ngay_quay,giai_nhat,giai_nhi_1,giai_nhi_2,giai_ba_1," +
					"giai_ba_2,	giai_ba_3,giai_ba_4,giai_ba_5,giai_ba_6,giai_tu_1,giai_tu_2,giai_tu_3,giai_tu_4,giai_nam_1,giai_nam_2,	giai_nam_3," +
					"giai_nam_4,giai_nam_5,giai_nam_6,giai_sau_1,giai_sau_2,giai_sau_3,giai_bay_1,giai_bay_2,giai_bay_3,giai_bay_4,	create_user," +
					"create_date,modify_user,modify_date,giai_dacbiet) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1,bean.getId());
			preStmt.setInt(2,bean.getProvince_id());
			preStmt.setDate(3,bean.getNgay_quay());
			preStmt.setString(4,bean.getGiai_nhat());
			preStmt.setString(5,bean.getGiai_nhi_1());
			preStmt.setString(6,bean.getGiai_nhi_2());
			preStmt.setString(7,bean.getGiai_ba_1());
			preStmt.setString(8,bean.getGiai_ba_2());
			preStmt.setString(9,bean.getGiai_ba_3());
			preStmt.setString(10,bean.getGiai_ba_4());
			preStmt.setString(11,bean.getGiai_ba_5());
			preStmt.setString(12,bean.getGiai_ba_6());
			
			preStmt.setString(13,bean.getGiai_tu_1());
			preStmt.setString(14,bean.getGiai_tu_2());
			preStmt.setString(15,bean.getGiai_tu_3());
			preStmt.setString(16,bean.getGiai_tu_4());
			
			preStmt.setString(17,bean.getGiai_nam_1());
			preStmt.setString(18,bean.getGiai_nam_2());
			preStmt.setString(19,bean.getGiai_nam_3());
			preStmt.setString(20,bean.getGiai_nam_4());
			preStmt.setString(21,bean.getGiai_nam_5());
			preStmt.setString(22,bean.getGiai_nam_6());
			
			preStmt.setString(23,bean.getGiai_sau_1());
			preStmt.setString(24,bean.getGiai_sau_2());
			preStmt.setString(25,bean.getGiai_sau_3());
			
			preStmt.setString(26,bean.getGiai_bay_1());
			preStmt.setString(27,bean.getGiai_bay_2());
			preStmt.setString(28,bean.getGiai_bay_3());
			preStmt.setString(29,bean.getGiai_bay_4());
			preStmt.setString(30,bean.getCreate_user());
			preStmt.setTimestamp(31,bean.getCreate_date());
			
			preStmt.setString(32,bean.getModify_user());
			preStmt.setTimestamp(33,bean.getModify_date());
			preStmt.setString(34,bean.getGiai_dacbiet());
			
			
			preStmt.executeUpdate();			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("getMaxID: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getMaxID: Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("getMaxID: Error executing >>>" + e.toString());
		} finally {
			C3p0SCDPool.attemptClose(preStmt);			
			C3p0SCDPool.attemptClose(conn);
		}
	}
	
	
	public void saveKQMN(KetQuaMienNamBean bean) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("INSERT INTO ketqua_miennam (id,province_id,ngay_quay,giai_dacbiet,giai_nhat,giai_nhi,giai_ba_1," +
					" giai_ba_2,giai_tu_1,giai_tu_2,giai_tu_3,giai_tu_4,giai_tu_5,giai_tu_6,giai_tu_7,giai_nam,giai_sau_1,giai_sau_2,giai_sau_3," +
					" giai_bay,	giai_tam,create_user,create_date,modify_user,modify_date ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
					" ?,?,?,?)");
			
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1,bean.getId());
			preStmt.setInt(2,bean.getProvince_id());
			preStmt.setDate(3,bean.getNgay_quay());
			preStmt.setString(4,bean.getGiai_dacbiet());
			preStmt.setString(5,bean.getGiai_nhat());
			preStmt.setString(6,bean.getGiai_nhi());
			preStmt.setString(7,bean.getGiai_ba_1());
			preStmt.setString(8,bean.getGiai_ba_2());
			
			preStmt.setString(9,bean.getGiai_tu_1());
			preStmt.setString(10,bean.getGiai_tu_2());
			preStmt.setString(11,bean.getGiai_tu_3());
			preStmt.setString(12,bean.getGiai_tu_4());			
			preStmt.setString(13,bean.getGiai_tu_5());
			preStmt.setString(14,bean.getGiai_tu_6());
			preStmt.setString(15,bean.getGiai_tu_7());
			
			preStmt.setString(16,bean.getGiai_nam());
			
			preStmt.setString(17,bean.getGiai_sau_1());
			preStmt.setString(18,bean.getGiai_sau_2());
			preStmt.setString(19,bean.getGiai_sau_3());
			preStmt.setString(20,bean.getGiai_bay());
			preStmt.setString(21,bean.getGiai_tam());
			preStmt.setString(22,bean.getCreate_user());
			preStmt.setTimestamp(23,bean.getCreate_date());
			
			preStmt.setString(24,bean.getModify_user());
			preStmt.setTimestamp(25,bean.getModify_date());
			
			
			
			preStmt.executeUpdate();			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("getMaxID: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getMaxID: Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("getMaxID: Error executing >>>" + e.toString());
		} finally {
			C3p0SCDPool.attemptClose(preStmt);			
			C3p0SCDPool.attemptClose(conn);
		}
	}
	
	
	public void saveKQMT(KetQuaMienTrungBean bean) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("INSERT INTO ketqua_mientrung (id,province_id,ngay_quay,giai_dacbiet,giai_nhat,giai_nhi,giai_ba_1," +
					" giai_ba_2,giai_tu_1,giai_tu_2,giai_tu_3,giai_tu_4,giai_tu_5,giai_tu_6,giai_tu_7,giai_nam,giai_sau_1,giai_sau_2,giai_sau_3," +
					" giai_bay,	giai_tam,create_user,create_date,modify_user,modify_date ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
					" ?,?,?,?)");
			
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1,bean.getId());
			preStmt.setInt(2,bean.getProvince_id());
			preStmt.setDate(3,bean.getNgay_quay());
			preStmt.setString(4,bean.getGiai_dacbiet());
			preStmt.setString(5,bean.getGiai_nhat());
			preStmt.setString(6,bean.getGiai_nhi());
			preStmt.setString(7,bean.getGiai_ba_1());
			preStmt.setString(8,bean.getGiai_ba_2());
			
			preStmt.setString(9,bean.getGiai_tu_1());
			preStmt.setString(10,bean.getGiai_tu_2());
			preStmt.setString(11,bean.getGiai_tu_3());
			preStmt.setString(12,bean.getGiai_tu_4());			
			preStmt.setString(13,bean.getGiai_tu_5());
			preStmt.setString(14,bean.getGiai_tu_6());
			preStmt.setString(15,bean.getGiai_tu_7());
			
			preStmt.setString(16,bean.getGiai_nam());
			
			preStmt.setString(17,bean.getGiai_sau_1());
			preStmt.setString(18,bean.getGiai_sau_2());
			preStmt.setString(19,bean.getGiai_sau_3());
			preStmt.setString(20,bean.getGiai_bay());
			preStmt.setString(21,bean.getGiai_tam());
			preStmt.setString(22,bean.getCreate_user());
			preStmt.setTimestamp(23,bean.getCreate_date());
			
			preStmt.setString(24,bean.getModify_user());
			preStmt.setTimestamp(25,bean.getModify_date());
			
			
			
			preStmt.executeUpdate();			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("getMaxID: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getMaxID: Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("getMaxID: Error executing >>>" + e.toString());
		} finally {
			C3p0SCDPool.attemptClose(preStmt);			
			C3p0SCDPool.attemptClose(conn);
		}
	}
	
	
	
	
	public boolean saveLoto(LotoBean lotoBean,String table) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();	
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO "+table+" (ketqua_id,	province_id, boso, thu, is_dacbiet, is_tongchan, is_tongle," +
					"	is_bochanle, is_bolechan, is_bochanchan, is_bolele,	is_bokep, is_bosatkep, tan_so, dau_so, dit_so,	tong_bo," +
					"   giai, ngay_quay, create_date, create_user, modify_date, modify_user,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
					"    ?,?,?,?,?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1, lotoBean.getKetqua_id());
			preStmt.setInt(2,lotoBean.getProvince_id());
			preStmt.setString(3,lotoBean.getBoso());
			preStmt.setInt(4,lotoBean.getThu());
			
			preStmt.setInt(5,lotoBean.getIs_dacbiet());
			preStmt.setInt(6,lotoBean.getIs_tongchan());
			preStmt.setInt(7,lotoBean.getIs_tongle());
			preStmt.setInt(8,lotoBean.getIs_bochanle());
			preStmt.setInt(9,lotoBean.getIs_bolechan());
			preStmt.setInt(10,lotoBean.getIs_bochanchan());
			preStmt.setInt(11,lotoBean.getIs_bolele());
			preStmt.setInt(12,lotoBean.getIs_bokep());
			preStmt.setInt(13,lotoBean.getIs_bosatkep());
			
			preStmt.setInt(14,lotoBean.getTan_so());
			preStmt.setInt(15,lotoBean.getDau_so());
			preStmt.setInt(16,lotoBean.getDit_so());
			preStmt.setInt(17,lotoBean.getTong_bo());
			
			preStmt.setString(18,lotoBean.getGiai());
			preStmt.setDate(19, lotoBean.getNgay_quay());
			preStmt.setTimestamp(20, lotoBean.getCreate_date());
			preStmt.setString(21, lotoBean.getCreate_user());
			
			preStmt.setTimestamp(22, lotoBean.getModify_date());
			preStmt.setString(23, lotoBean.getModify_user());
			preStmt.setLong(24, lotoBean.getId());
			
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
	
	
	public boolean saveDT6x63(Dientoan6x63 dientoan6x63) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();	
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO ketqua_dientoan6x36 (id, ngay_quay,ketqua_1,ketqua_2, ketqua_3, ketqua_4, ketqua_5, ketqua_6, create_user, create_date," +
					" 	modify_user,modify_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, dientoan6x63.id);
			preStmt.setDate(2,dientoan6x63.ngay_quay);
			preStmt.setString(3,dientoan6x63.ketqua_1);
			preStmt.setString(4,dientoan6x63.ketqua_2);
			
			preStmt.setString(5,dientoan6x63.ketqua_3);
			preStmt.setString(6,dientoan6x63.ketqua_4);
			preStmt.setString(7,dientoan6x63.ketqua_5);
			preStmt.setString(8,dientoan6x63.ketqua_6);
			preStmt.setString(9,dientoan6x63.create_user);
			preStmt.setTimestamp(10,dientoan6x63.create_date);
			preStmt.setString(11,dientoan6x63.modify_user);
			preStmt.setTimestamp(12,dientoan6x63.modify_date);
			
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("saveDT6x63: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("saveDT6x63: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("saveDT6x63: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public boolean saveDT123(Dientoan123 dientoan123) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();	
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO vtc_soicaudep.ketqua_dientoan123 (id,ngay_quay,ketqua_1,ketqua_2,ketqua_3,create_user,create_date,modify_user," +
					"  modify_date ) VALUES (?,?,?,?,?,?,?,?,?" +
					" 	)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, dientoan123.id);
			preStmt.setDate(2,dientoan123.ngay_quay);
			preStmt.setString(3,dientoan123.ketqua_1);
			preStmt.setString(4,dientoan123.ketqua_2);
			
			preStmt.setString(5,dientoan123.ketqua_3);
			preStmt.setString(6,dientoan123.create_user);
			preStmt.setTimestamp(7,dientoan123.create_date);
			preStmt.setString(8,dientoan123.modify_user);
			preStmt.setTimestamp(9,dientoan123.modify_date);
			
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("saveDT123: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("saveDT123: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("saveDT123: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public boolean saveDTThanTai(DientoanThantai dientoanThantai) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();	
			conn.setAutoCommit(false);
			strSQL = new StringBuffer("INSERT INTO ketqua_thantai (id,ngay_quay,ketqua,create_user,create_date,modify_user,modify_date)" +
					" VALUES(?,?,?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, dientoanThantai.id);
			preStmt.setDate(2,dientoanThantai.ngay_quay);
			preStmt.setString(3,dientoanThantai.ketqua);
			preStmt.setString(4,dientoanThantai.create_user);
			preStmt.setTimestamp(5,dientoanThantai.create_date);
			preStmt.setString(6,dientoanThantai.modify_user);
			preStmt.setTimestamp(7,dientoanThantai.modify_date);
			
			if (preStmt.executeUpdate() == 1) {
				conn.commit();
				result = true;
			} else {
				conn.rollback();
			}
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			logger.error("saveDT123: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("saveDT123: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("saveDT123: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public long saveCau(SoiCauBean soiCauBean) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;		
		long id=0;
		try {
			conn = C3p0SCDPool.getConnection();		
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
			C3p0SCDPool.attemptClose(preStmt);
			C3p0SCDPool.attemptClose(conn);
		}
		return id;
	}
	
	
	public boolean save(BuocCauBean bean) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO soicau_buoccau (cau_id,kieu_cau,b1_id,b1_dau,b1_dit,b2_id,b2_dau, " +
							" b2_dit,b3_id,b3_dau,b3_dit,b4_id, b4_dau,  " +
							"b4_dit, b5_id,b5_dau, b5_dit,b6_id,b6_dau,b6_dit, " +
							"b7_id,b7_dau,b7_dit,x,y, create_date,boso)" +
							" VALUES(?,	?, ?, ?, ?,	?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,? )");

			preStmt = conn.prepareStatement(strSQL.toString());			
			
			preStmt.setLong(1,bean.getCau_id());
			preStmt.setInt(2,bean.getKieu_cau());			
			preStmt.setLong(3,bean.getB1_id());
			preStmt.setInt(4,bean.getB1_dau());			
			preStmt.setInt(5,bean.getB1_dit());		
			preStmt.setLong(6,bean.getB2_id());
			preStmt.setInt(7,bean.getB2_dau());			
			preStmt.setInt(8,bean.getB2_dit());
			preStmt.setLong(9,bean.getB3_id());
			preStmt.setInt(10,bean.getB3_dau());
			preStmt.setInt(11,bean.getB3_dit());
			preStmt.setLong(12,bean.getB4_id());
			preStmt.setInt(13,bean.getB4_dau());			
			preStmt.setInt(14,bean.getB4_dit());				
			preStmt.setLong(15,bean.getB5_id());
			preStmt.setInt(16,bean.getB5_dau());			
			preStmt.setInt(17,bean.getB5_dit());		
			preStmt.setLong(18,bean.getB6_id());
			preStmt.setInt(19,bean.getB6_dau());			
			preStmt.setInt(20,bean.getB6_dit());			
			preStmt.setLong(21,bean.getB7_id());
			preStmt.setInt(22,bean.getB7_dau());			
			preStmt.setInt(23,bean.getB7_dit());
			preStmt.setInt(24,bean.getX());
			preStmt.setInt(25,bean.getY());
			preStmt.setTimestamp(26,bean.getCreate_date());
			preStmt.setInt(27,bean.getBoso());
			
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
			C3p0SCDPool.attemptClose(preStmt);
			C3p0SCDPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public boolean saveBosoVeLienTiep(TKChuKiBoSoVeLienTiep chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_boso_ve_lientiep (id, province_id,	boso, start_date,end_date," +
					" length) VALUES (?, ?,	?,	?,	?,	? )");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKyBoSo.id);
			preStmt.setInt(2, chuKyBoSo.province_id);
			preStmt.setString(3, chuKyBoSo.boso);
			preStmt.setDate(4, chuKyBoSo.start_date);
			preStmt.setDate(5, chuKyBoSo.end_date);
			preStmt.setLong(6, chuKyBoSo.length);
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
	
	
	public boolean save(TKChuKyBoSo chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_chuky_boso(province_id,boso,start_date,end_date,length,is_special,id)"
							+ " VALUES (?, ?, ?,?, ?, ?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKyBoSo.province_id);
			preStmt.setString(2, chuKyBoSo.boso);
			preStmt.setDate(3, chuKyBoSo.start_date);
			preStmt.setDate(4, chuKyBoSo.end_date);
			preStmt.setLong(5, chuKyBoSo.length);
			preStmt.setInt(6, chuKyBoSo.is_special);
			preStmt.setInt(7, chuKyBoSo.id);

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
			C3p0SCDPool.attemptClose(preStmt);
			C3p0SCDPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public boolean save(TKChuKiBoXien2 chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_chuky_boso_xien2(province_id,boso1,boso2,start_date,end_date,length,is_special,id) "
							+ " VALUES (?, ?, ?, ?, ?, ?, ?,?) ");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKyBoSo.province_id);
			preStmt.setString(2, chuKyBoSo.boso1);
			preStmt.setString(3, chuKyBoSo.boso2);
			preStmt.setDate(4, chuKyBoSo.start_date);
			preStmt.setDate(5, chuKyBoSo.end_date);
			preStmt.setLong(6, chuKyBoSo.length);
			preStmt.setInt(7, chuKyBoSo.is_special);
			preStmt.setInt(8, chuKyBoSo.id);

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
			C3p0SCDPool.attemptClose(preStmt);
			C3p0SCDPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public boolean save(TKChuKiDau chuKiDau) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_chuky_dau (	province_id," +
					"	dau,start_date,	end_date,length,id) VALUES" +
					" 	(?,?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKiDau.province_id);
			preStmt.setString(2, chuKiDau.dau);
			preStmt.setDate(3, chuKiDau.start_date);
			preStmt.setDate(4, chuKiDau.end_date);
			preStmt.setLong(5, chuKiDau.length);
			preStmt.setLong(6, chuKiDau.id);

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
			C3p0SCDPool.attemptClose(preStmt);
			C3p0SCDPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public boolean save(TKChuKiDuoi chuKiDuoi) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_chuky_duoi (province_id," +
					"	duoi,start_date,end_date,length,id) VALUES" +
					" 	(?,?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKiDuoi.province_id);
			preStmt.setString(2, chuKiDuoi.duoi);
			preStmt.setDate(3, chuKiDuoi.start_date);
			preStmt.setDate(4, chuKiDuoi.end_date);
			preStmt.setLong(5, chuKiDuoi.length);
			preStmt.setLong(6, chuKiDuoi.id);

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
			C3p0SCDPool.attemptClose(preStmt);
			C3p0SCDPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public boolean save(TKChuKiTongDB chuKiDau) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_chuky_tong_db (	province_id," +
					"	tong,start_date,end_date,length,id) VALUES" +
					" 	(?,?,?,?,?,?)");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKiDau.province_id);
			preStmt.setInt(2, chuKiDau.tong);
			preStmt.setDate(3, chuKiDau.start_date);
			preStmt.setDate(4, chuKiDau.end_date);
			preStmt.setLong(5, chuKiDau.length);
			preStmt.setLong(6, chuKiDau.id);
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
			C3p0SCDPool.attemptClose(preStmt);
			C3p0SCDPool.attemptClose(conn);
		}
		return result;
	}
	
	public boolean save(TKChuKiDauSoVeLienTiep chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_dau_ve_lientiep (province_id, dau, start_date, end_date, length,id" +
					"	) VALUES (?,?,?,?,	?, ? )");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKyBoSo.province_id);
			preStmt.setString(2, chuKyBoSo.dau);
			preStmt.setDate(3, chuKyBoSo.start_date);
			preStmt.setDate(4, chuKyBoSo.end_date);
			preStmt.setLong(5, chuKyBoSo.length);
			preStmt.setLong(6, chuKyBoSo.id);
			
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
			C3p0SCDPool.attemptClose(preStmt);
			C3p0SCDPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public boolean save(TKChuKiDuoiSoVeLienTiep chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_duoi_ve_lientiep (province_id, duoi, start_date, end_date, length, id" +
					"	) VALUES (	?,	?,	?,	?,	?, ?  )");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKyBoSo.province_id);
			preStmt.setString(2, chuKyBoSo.duoi);
			preStmt.setDate(3, chuKyBoSo.start_date);
			preStmt.setDate(4, chuKyBoSo.end_date);
			preStmt.setLong(5, chuKyBoSo.length);
			preStmt.setLong(6, chuKyBoSo.id);
			
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
			C3p0SCDPool.attemptClose(preStmt);
			C3p0SCDPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public boolean save(TKChuKiTongDBVeLienTiep chuKyBoSo) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(" INSERT INTO thongke_tong_db_ve_lientiep (province_id, tong, start_date, end_date, length, id" +
					"	) VALUES (	?,	?,	?,	?,	?, ? )");

			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1, chuKyBoSo.province_id);
			preStmt.setInt(2, chuKyBoSo.tong);
			preStmt.setDate(3, chuKyBoSo.start_date);
			preStmt.setDate(4, chuKyBoSo.end_date);
			preStmt.setLong(5, chuKyBoSo.length);
			preStmt.setLong(6, chuKyBoSo.id);
			
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
			C3p0SCDPool.attemptClose(preStmt);
			C3p0SCDPool.attemptClose(conn);
		}
		return result;
	}
	
	
	public boolean save(TanSuatBoSoBean boSoBean,String table) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		boolean result = false;
		try {
			conn = C3p0SCDPool.getConnection();		
			conn.setAutoCommit(false);
			strSQL = new StringBuffer(
					" INSERT INTO "+table+" (boso,so_ngay_ve, p_so_ngay_ve,so_lan_ve,p_so_lan_ve,p_so_lan_ngay,province_id," +
							"khoang_thoigian,is_dacbiet,create_date,bien_ngay,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setString(1,boSoBean.getBoso());
			preStmt.setInt(2,boSoBean.getSo_ngay_ve());
			preStmt.setDouble(3, boSoBean.getP_so_ngay_ve());
			preStmt.setInt(4, boSoBean.getSo_lan_ve());
			preStmt.setDouble(5, boSoBean.getP_so_lan_ve());
			preStmt.setDouble(6, boSoBean.getP_so_lan_ngay());
			preStmt.setInt(7, boSoBean.getProvince_id());
			preStmt.setInt(8,boSoBean.getKhoang_thoigian());
			preStmt.setInt(9,boSoBean.getIs_dacbiet());
			preStmt.setTimestamp(10,boSoBean.getCreate_date());	
			preStmt.setDate(11,boSoBean.getBien_ngay());
			preStmt.setLong(12,boSoBean.getId());
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
			C3p0SCDPool.attemptClose(preStmt);
			C3p0SCDPool.attemptClose(conn);
		}
		return result;
	}
	

}
