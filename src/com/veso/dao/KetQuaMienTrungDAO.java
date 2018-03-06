package com.veso.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Vector;

import com.veso.bean.DaySoBean;
import com.veso.bean.KetQuaMienTrungBean;
import com.veso.bean.LotoBean;
import com.veso.db.pool.C3p0VesoPool;
import com.veso.util.DateProc;
import com.veso.util.Logger;
import com.veso.util.StringTool;


public class KetQuaMienTrungDAO {
	private String table = "ketqua_mientrung";
	private Logger logger = new Logger(this.getClass().getName());

	public KetQuaMienTrungBean findById(long id) {
		KetQuaMienTrungBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id, province_id, ngay_quay, giai_dacbiet, giai_nhat, giai_nhi, giai_ba_1, giai_ba_2, giai_tu_1, giai_tu_2, " +
					"giai_tu_3, giai_tu_4, giai_tu_5, giai_tu_6, giai_tu_7, giai_nam, giai_sau_1, giai_sau_2, giai_sau_3, giai_bay,	giai_tam," +
					"create_user,create_date, modify_user, modify_date FROM "
					+ table + " WHERE id = '" + id + "'");
			preStmt = conn.prepareStatement(strSQL.toString());
			rs = preStmt.executeQuery();

			if (rs.next()) {
				kBean = new KetQuaMienTrungBean();
				kBean.setId(rs.getLong("id"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi(rs.getString("giai_nhi"));
				
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				
				
				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));
				kBean.setGiai_tu_5(rs.getString("giai_tu_5"));
				kBean.setGiai_tu_6(rs.getString("giai_tu_6"));
				kBean.setGiai_tu_7(rs.getString("giai_tu_7"));

				kBean.setGiai_nam(rs.getString("giai_nam"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay(rs.getString("giai_bay"));
				kBean.setGiai_tam(rs.getString("giai_tam"));
				

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));

			}

		} catch (NoSuchElementException nse) {
			logger.error("findById(id): Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findById(id): Error executing SQL >>>"
					+ strSQL.toString() + se.toString());
		} catch (Exception e) {
			logger.error("findById(id): Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);

		}
		return kBean;
	}

	public Vector<KetQuaMienTrungBean> findAll() {

		Vector<KetQuaMienTrungBean> kqList = null;
		KetQuaMienTrungBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id, province_id, ngay_quay, giai_dacbiet, giai_nhat, giai_nhi, giai_ba_1, giai_ba_2, giai_tu_1, giai_tu_2, " +
					"giai_tu_3, giai_tu_4, giai_tu_5, giai_tu_6, giai_tu_7, giai_nam, giai_sau_1, giai_sau_2, giai_sau_3, giai_bay,	giai_tam," +
					"create_user,create_date, modify_user, modify_date FROM "
					+ table );
			preStmt = conn.prepareStatement(strSQL.toString());
			rs = preStmt.executeQuery();
			kqList = new Vector<KetQuaMienTrungBean>();
			while (rs.next()) {
				kBean = new KetQuaMienTrungBean();
				kBean.setId(rs.getLong("id"));
				kBean.setProvince_id(rs.getInt("province_id"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi(rs.getString("giai_nhi"));
				
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				
				
				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));
				kBean.setGiai_tu_5(rs.getString("giai_tu_5"));
				kBean.setGiai_tu_6(rs.getString("giai_tu_6"));
				kBean.setGiai_tu_7(rs.getString("giai_tu_7"));

				kBean.setGiai_nam(rs.getString("giai_nam"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay(rs.getString("giai_bay"));
				kBean.setGiai_tam(rs.getString("giai_tam"));
				

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));
				kqList.addElement(kBean);
			}

		} catch (NoSuchElementException nse) {
			logger.error("findAll: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAll: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findAll: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kqList;
	}
	
	
	public Vector<KetQuaMienTrungBean> findByNgayQuay(Date ngay_quay) {

		Vector<KetQuaMienTrungBean> kqList = null;
		KetQuaMienTrungBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();
			;
			strSQL = new StringBuffer(
					"SELECT	id, province_id, ngay_quay, giai_dacbiet, giai_nhat, giai_nhi, giai_ba_1, giai_ba_2, giai_tu_1, giai_tu_2, " +
					"giai_tu_3, giai_tu_4, giai_tu_5, giai_tu_6, giai_tu_7, giai_nam, giai_sau_1, giai_sau_2, giai_sau_3, giai_bay,	giai_tam," +
					"create_user,create_date, modify_user, modify_date FROM "
					+ table +" WHERE ngay_quay = ? And province_id >0 ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDate(1, ngay_quay);
			rs = preStmt.executeQuery();
			kqList = new Vector<KetQuaMienTrungBean>();
			while (rs.next()) {
				kBean = new KetQuaMienTrungBean();
				kBean.setId(rs.getLong("id"));
				kBean.setProvince_id(rs.getInt("province_id"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi(rs.getString("giai_nhi"));
				
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				
				
				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));
				kBean.setGiai_tu_5(rs.getString("giai_tu_5"));
				kBean.setGiai_tu_6(rs.getString("giai_tu_6"));
				kBean.setGiai_tu_7(rs.getString("giai_tu_7"));

				kBean.setGiai_nam(rs.getString("giai_nam"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay(rs.getString("giai_bay"));
				kBean.setGiai_tam(rs.getString("giai_tam"));
				

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));
				kqList.addElement(kBean);
			}

		} catch (NoSuchElementException nse) {
			logger.error("findByNgayQuay: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findByNgayQuay: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findByNgayQuay: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kqList;
	}
	
	public Vector<KetQuaMienTrungBean> findKetQuaChuaTinhLoToMienTrung() {

		Vector<KetQuaMienTrungBean> kqList = null;
		KetQuaMienTrungBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();
			;
			strSQL = new StringBuffer(
					"SELECT	id, province_id, ngay_quay, giai_dacbiet, giai_nhat, giai_nhi, giai_ba_1, giai_ba_2, giai_tu_1, giai_tu_2, " +
					"giai_tu_3, giai_tu_4, giai_tu_5, giai_tu_6, giai_tu_7, giai_nam, giai_sau_1, giai_sau_2, giai_sau_3, giai_bay,	giai_tam," +
					"create_user,create_date, modify_user, modify_date FROM "
					+ table +" WHERE id NOT IN (SELECT ketqua_id FROM thongke_loto_mientrung) ");
			preStmt = conn.prepareStatement(strSQL.toString());
			rs = preStmt.executeQuery();
			kqList = new Vector<KetQuaMienTrungBean>();
			while (rs.next()) {
				kBean = new KetQuaMienTrungBean();
				kBean.setId(rs.getLong("id"));
				kBean.setProvince_id(rs.getInt("province_id"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi(rs.getString("giai_nhi"));
				
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				
				
				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));
				kBean.setGiai_tu_5(rs.getString("giai_tu_5"));
				kBean.setGiai_tu_6(rs.getString("giai_tu_6"));
				kBean.setGiai_tu_7(rs.getString("giai_tu_7"));

				kBean.setGiai_nam(rs.getString("giai_nam"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay(rs.getString("giai_bay"));
				kBean.setGiai_tam(rs.getString("giai_tam"));				

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));
				kqList.addElement(kBean);
			}

		} catch (NoSuchElementException nse) {
			logger.error("findKetQuaChuaTinhLoToMienTrung: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findKetQuaChuaTinhLoToMienTrung: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findKetQuaChuaTinhLoToMienTrung: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kqList;
	}


	public Vector<KetQuaMienTrungBean> findAll(long page, long rowsPerPage) {
		if (page <= 0 || rowsPerPage <= 0) {
			return null;
		}
		long startRow = (page - 1) * rowsPerPage;
		long stopRow = page * rowsPerPage;
		Vector<KetQuaMienTrungBean> kList = null;
		KetQuaMienTrungBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id, province_id, ngay_quay, giai_dacbiet, giai_nhat, giai_nhi, giai_ba_1, giai_ba_2, giai_tu_1, giai_tu_2, " +
					"giai_tu_3, giai_tu_4, giai_tu_5, giai_tu_6, giai_tu_7, giai_nam, giai_sau_1, giai_sau_2, giai_sau_3, giai_bay,	giai_tam," +
					"create_user,create_date, modify_user, modify_date FROM "
					+ table+ " ORDER BY id  LIMIT ?,?");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setLong(1, startRow);
			preStmt.setLong(2, stopRow);

			rs = preStmt.executeQuery();
			kList = new Vector<KetQuaMienTrungBean>();
			while (rs.next()) {
				kBean = new KetQuaMienTrungBean();
				kBean.setId(rs.getLong("id"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi(rs.getString("giai_nhi"));
				
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				
				
				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));
				kBean.setGiai_tu_5(rs.getString("giai_tu_5"));
				kBean.setGiai_tu_6(rs.getString("giai_tu_6"));
				kBean.setGiai_tu_7(rs.getString("giai_tu_7"));

				kBean.setGiai_nam(rs.getString("giai_nam"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay(rs.getString("giai_bay"));
				kBean.setGiai_tam(rs.getString("giai_tam"));
				

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));
				kList.addElement(kBean);
			}

		} catch (NoSuchElementException nse) {
			logger.error("findAll: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAll: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findAll: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kList;
	}
	
	public LotoBean saveLotoBean(long ketqua_id,int province_id,String ketqua,String giai,Date ngay_quay,int is_dacbiet)
	{
		LotoBean bean = new LotoBean();
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mientrung");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngay_quay);
		int dau=0;int dit=0;int tongbo=0;
		
		bean.setKetqua_id(ketqua_id);
		bean.setProvince_id(province_id);
		bean.setThu(calendar.get(Calendar.DAY_OF_WEEK));
		bean.setNgay_quay(ngay_quay);
		bean.setIs_dacbiet(is_dacbiet);
		bean.setGiai(giai);		
		
		String boso = ketqua.substring(ketqua.length()-2);
		dau = Integer.parseInt(boso.substring(0,1));
		dit = Integer.parseInt(boso.substring(1));
		bean.setBoso(boso);
		bean.setDau_so(dau);
		bean.setDit_so(dit);
		bean.setTan_so(1);
		tongbo = dau+dit;
		if(tongbo>9)
		tongbo = Integer.parseInt(String.valueOf(tongbo).substring(1));
		
		bean.setTong_bo(tongbo);
		
		int is_bochanchan = 0;
		if(dau%2==0&&dit%2==0) is_bochanchan = 1;
		bean.setIs_bochanchan(is_bochanchan);
		int is_bochanle = 0;
		if(dau%2==0&&dit%2!=0) is_bochanle = 1;
		bean.setIs_bochanle(is_bochanle);
		
		bean.setIs_bokep(StringTool.isKep(boso));
		
		int is_bolechan=0;
		if(dau%2!=0&&dit%2==0) is_bolechan = 1;
		bean.setIs_bolechan(is_bolechan);
		
		int is_bolele=0;
		if(dau%2!=0&&dit%2!=0) is_bolele = 1;
		bean.setIs_bolele(is_bolele);
		
		bean.setIs_bosatkep(StringTool.isSatKep(boso));
		
		int is_tongchan = 0;
		tongbo = dau+dit;
		if(tongbo%2==0) is_tongchan = 1;		
		bean.setIs_tongchan(is_tongchan);
		bean.setIs_tongle(1-is_tongchan);
		
		bean.setCreate_date(DateProc.createTimestamp());
		bean.setCreate_user("thangtt");
		bean.setModify_date(DateProc.createTimestamp());
		bean.setModify_user("thangtt");
		lotoDAO.save(bean);
		return bean;
	}
	
	public DaySoBean getDaySo(Date open_date) {

		Integer[] dayso = null;		
		DaySoBean daySoBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int i = 0,j=0;
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id, province_id, ngay_quay, giai_dacbiet, giai_nhat, giai_nhi, giai_ba_1, giai_ba_2, giai_tu_1, giai_tu_2, giai_tu_3," +
					"  giai_tu_4, giai_tu_5, giai_tu_6,	giai_tu_7, giai_nam, giai_sau_1, giai_sau_2, giai_sau_3, giai_bay, 	giai_tam, create_user," +
					"  create_date, modify_user, modify_date   FROM  "
							+  table +" WHERE ngay_quay = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDate(1, open_date);
			rs = preStmt.executeQuery();
			dayso = new Integer[82];
			
			if (rs.next()) {
				daySoBean = new DaySoBean();
				i =0;j=0;
				while(i<rs.getString("giai_dacbiet").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_dacbiet").charAt(i)+"");
					i++;j++;
				}
				i=0;
				while(i<rs.getString("giai_nhat").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nhat").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nhi").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nhi").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_ba_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_ba_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_3").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_3").charAt(i)+"");
					i++;j++;
				}
				
				
				i=0;
				while(i<rs.getString("giai_tu_4").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_4").charAt(i)+"");
					i++;j++;
				}
				
				
				i=0;
				while(i<rs.getString("giai_tu_5").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_5").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_6").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_6").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_7").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_7").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nam").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nam").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_sau_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_sau_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_sau_3").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_3").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_bay").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_bay").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tam").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tam").charAt(i)+"");
					i++;j++;
				}

				daySoBean.setDayso(dayso);
				daySoBean.setKequa_id(rs.getLong("id"));
			
			}

		} catch (NoSuchElementException nse) {
			logger.error("getDaySo: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("getDaySo: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getDaySo: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return daySoBean;
	}
	
	

	
	public DaySoBean getDaySoMT(Date open_date, int province_id) {

		Integer[] dayso = null;		
		DaySoBean daySoBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int i = 0,j=0;
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id, province_id, ngay_quay, giai_dacbiet, giai_nhat, giai_nhi, giai_ba_1, giai_ba_2, giai_tu_1, giai_tu_2, giai_tu_3," +
					"  giai_tu_4, giai_tu_5, giai_tu_6,	giai_tu_7, giai_nam, giai_sau_1, giai_sau_2, giai_sau_3, giai_bay, 	giai_tam, create_user," +
					"  create_date, modify_user, modify_date   FROM  "
							+  table +" WHERE ngay_quay = '"+open_date+"' AND province_id =  "+province_id);
			
			preStmt = conn.prepareStatement(strSQL.toString());
			/*preStmt.setDate(1, open_date);
			preStmt.setInt(2, province_id);*/
			rs = preStmt.executeQuery();
			
			
			
			if (rs.next()) {
				String lenthbs =  rs.getString("giai_dacbiet")+rs.getString("giai_nhat")+rs.getString("giai_nhi")+rs.getString("giai_ba_1")
						+rs.getString("giai_ba_2")+rs.getString("giai_tu_1")+rs.getString("giai_tu_2")+rs.getString("giai_tu_3")+rs.getString("giai_tu_4")
						+rs.getString("giai_tu_5")+rs.getString("giai_tu_6")+rs.getString("giai_tu_7")+rs.getString("giai_nam")
						+rs.getString("giai_sau_1")+rs.getString("giai_sau_2")+rs.getString("giai_sau_3")+rs.getString("giai_bay")
						+rs.getString("giai_tam");
				dayso = new Integer[lenthbs.length()];
				daySoBean = new DaySoBean();
				i =0;j=0;
				while(i<rs.getString("giai_dacbiet").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_dacbiet").charAt(i)+"");
					i++;j++;
				}
				i=0;
				while(i<rs.getString("giai_nhat").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nhat").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nhi").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nhi").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_ba_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_ba_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_3").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_3").charAt(i)+"");
					i++;j++;
				}
				
				
				i=0;
				while(i<rs.getString("giai_tu_4").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_4").charAt(i)+"");
					i++;j++;
				}
				
				
				i=0;
				while(i<rs.getString("giai_tu_5").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_5").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_6").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_6").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_7").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_7").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nam").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nam").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_sau_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_sau_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_sau_3").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_3").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_bay").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_bay").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tam").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tam").charAt(i)+"");
					i++;j++;
				}

				daySoBean.setDayso(dayso);
				daySoBean.setKequa_id(rs.getLong("id"));
			
			}

		} catch (NoSuchElementException nse) {
			System.out.println(strSQL.toString());
			logger.error("Cau lenh"+"getDaySoMT: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			System.out.println("Cau lenh "+strSQL.toString());
			logger.error("Cau lenh "+" getDaySoMT: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			System.out.println(strSQL.toString());
			logger.error("Cau lenh"+"getDaySoMT: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return daySoBean;
	}
	
	public DaySoBean getDaySo(Date open_date, int province_id) {

		Integer[] dayso = null;		
		DaySoBean daySoBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		int i = 0,j=0;
		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id, province_id, ngay_quay, giai_dacbiet, giai_nhat, giai_nhi, giai_ba_1, giai_ba_2, giai_tu_1, giai_tu_2, giai_tu_3," +
					"  giai_tu_4, giai_tu_5, giai_tu_6,	giai_tu_7, giai_nam, giai_sau_1, giai_sau_2, giai_sau_3, giai_bay, 	giai_tam, create_user," +
					"  create_date, modify_user, modify_date   FROM  "
							+  table +" WHERE ngay_quay = ? AND province_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDate(1, open_date);
			preStmt.setInt(2, province_id);
			rs = preStmt.executeQuery();
			dayso = new Integer[82];
			
			if (rs.next()) {
				daySoBean = new DaySoBean();
				i =0;j=0;
				while(i<rs.getString("giai_dacbiet").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_dacbiet").charAt(i)+"");
					i++;j++;
				}
				i=0;
				while(i<rs.getString("giai_nhat").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nhat").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nhi").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nhi").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_ba_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_ba_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_ba_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_3").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_3").charAt(i)+"");
					i++;j++;
				}
				
				
				i=0;
				while(i<rs.getString("giai_tu_4").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_4").charAt(i)+"");
					i++;j++;
				}
				
				
				i=0;
				while(i<rs.getString("giai_tu_5").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_5").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_6").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_6").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tu_7").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tu_7").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_nam").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_nam").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_sau_1").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_1").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_sau_2").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_2").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_sau_3").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_sau_3").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_bay").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_bay").charAt(i)+"");
					i++;j++;
				}
				
				i=0;
				while(i<rs.getString("giai_tam").length())
				{
					dayso[j] =Integer.parseInt(rs.getString("giai_tam").charAt(i)+"");
					i++;j++;
				}

				daySoBean.setDayso(dayso);
				daySoBean.setKequa_id(rs.getLong("id"));
			
			}

		} catch (NoSuchElementException nse) {
			logger.error("getDaySo: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAll: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("getDaySo: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return daySoBean;
	}
	
	
	public Vector<KetQuaMienTrungBean> findAll(int start) {

		Vector<KetQuaMienTrungBean> kqList = null;
		KetQuaMienTrungBean kBean = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;

		try {
			conn = C3p0VesoPool.getConnection();
			strSQL = new StringBuffer(
					"SELECT	id, province_id, ngay_quay, giai_dacbiet, giai_nhat, giai_nhi, giai_ba_1, giai_ba_2, giai_tu_1, giai_tu_2, " +
					"giai_tu_3, giai_tu_4, giai_tu_5, giai_tu_6, giai_tu_7, giai_nam, giai_sau_1, giai_sau_2, giai_sau_3, giai_bay,	giai_tam," +
					"create_user,create_date, modify_user, modify_date FROM "
					+ table +"  Where id > "+start+" Order by id  " );
			System.out.println(strSQL.toString());
			preStmt = conn.prepareStatement(strSQL.toString());
		//	preStmt.setInt(1, start);
			rs = preStmt.executeQuery();
			kqList = new Vector<KetQuaMienTrungBean>();
			while (rs.next()) {
				kBean = new KetQuaMienTrungBean();
				kBean.setId(rs.getLong("id"));
				kBean.setProvince_id(rs.getInt("province_id"));
				System.out.println(rs.getDate("ngay_quay"));
				kBean.setNgay_quay(rs.getDate("ngay_quay"));
				kBean.setGiai_dacbiet(rs.getString("giai_dacbiet"));
				kBean.setGiai_nhat(rs.getString("giai_nhat"));
				kBean.setGiai_nhi(rs.getString("giai_nhi"));
				
				kBean.setGiai_ba_1(rs.getString("giai_ba_1"));
				kBean.setGiai_ba_2(rs.getString("giai_ba_2"));
				
				
				kBean.setGiai_tu_1(rs.getString("giai_tu_1"));
				kBean.setGiai_tu_2(rs.getString("giai_tu_2"));
				kBean.setGiai_tu_3(rs.getString("giai_tu_3"));
				kBean.setGiai_tu_4(rs.getString("giai_tu_4"));
				kBean.setGiai_tu_5(rs.getString("giai_tu_5"));
				kBean.setGiai_tu_6(rs.getString("giai_tu_6"));
				kBean.setGiai_tu_7(rs.getString("giai_tu_7"));

				kBean.setGiai_nam(rs.getString("giai_nam"));

				kBean.setGiai_sau_1(rs.getString("giai_sau_1"));
				kBean.setGiai_sau_2(rs.getString("giai_sau_2"));
				kBean.setGiai_sau_3(rs.getString("giai_sau_3"));

				kBean.setGiai_bay(rs.getString("giai_bay"));
				kBean.setGiai_tam(rs.getString("giai_tam"));
				

				kBean.setCreate_date(rs.getTimestamp("create_date"));
				kBean.setCreate_user(rs.getString("create_user"));
				kBean.setModify_date(rs.getTimestamp("modify_date"));
				kBean.setModify_user(rs.getString("modify_user"));
				kqList.addElement(kBean);
			}

		} catch (NoSuchElementException nse) {
			logger.error("findAll: Error executing >>>" + nse.toString());
		} catch (SQLException se) {
			logger.error("findAll: Error executing SQL >>>" + strSQL.toString()
					+ se.toString());
		} catch (Exception e) {
			logger.error("findAll: Error executing >>>" + e.toString());
		} finally {
			C3p0VesoPool.attemptClose(rs);
			C3p0VesoPool.attemptClose(preStmt);
			C3p0VesoPool.attemptClose(conn);
		}
		return kqList;
	}
	
	public static void main(String[] args) {
		KetQuaMienTrungDAO dBacDAO = new KetQuaMienTrungDAO();
		Vector<KetQuaMienTrungBean> listKQ = dBacDAO.findAll();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH, 02);
		calendar.set(Calendar.DAY_OF_MONTH, 17);
		dBacDAO.getDaySoMT(new java.sql.Date(calendar.getTimeInMillis()), 23);
		for (KetQuaMienTrungBean ketQuaMienTrungBean : listKQ) {
			
			/*dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_dacbiet(), "GDB", ketQuaMienTrungBean.getNgay_quay(), 1);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_nhat(), "G1", ketQuaMienTrungBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_nhi(), "G2", ketQuaMienTrungBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_ba_1(), "G31", ketQuaMienTrungBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_ba_2(), "G32", ketQuaMienTrungBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_tu_1(), "G41", ketQuaMienTrungBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_tu_2(), "G42", ketQuaMienTrungBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_tu_3(), "G43", ketQuaMienTrungBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_tu_4(), "G44", ketQuaMienTrungBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_tu_5(), "G45", ketQuaMienTrungBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_tu_6(), "G46", ketQuaMienTrungBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_tu_7(), "G47", ketQuaMienTrungBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_nam(), "G5", ketQuaMienTrungBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_sau_1(), "G61", ketQuaMienTrungBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_sau_2(), "G62", ketQuaMienTrungBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_sau_3(), "G63", ketQuaMienTrungBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_bay(), "G7", ketQuaMienTrungBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_tam(), "G8", ketQuaMienTrungBean.getNgay_quay(), 0);
			
			*/
		}
		
	}
}
