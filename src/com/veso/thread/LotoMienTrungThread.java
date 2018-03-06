package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import com.veso.bean.KetQuaMienTrungBean;
import com.veso.dao.KetQuaMienTrungDAO;
import com.veso.dao.LotoDAO;
import com.veso.util.Logger;

/*
 * Thread chay cap nhat du lieu vao bang thong ke loto mien trung
 * Chay sau 17.45
 * * 
 */

public class LotoMienTrungThread {
	private static Logger logger = new Logger("LotoMienNamThread");
	
	public static void tkLotoMienTrung(Date ngay_quay) {
		logger.log("LotoMienTrungThread: "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		KetQuaMienTrungDAO dBacDAO = new KetQuaMienTrungDAO();
		
		Vector<KetQuaMienTrungBean> listKQ = dBacDAO.findByNgayQuay(ngay_quay);
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mientrung");
		
		if(!lotoDAO.checkLotoMienTrungByNgayQuay(ngay_quay))
		for (KetQuaMienTrungBean ketQuaMienTrungBean : listKQ) {
			logger.log(ngay_quay.toString()+":LotoMienTrungThread---->KetquaID="+ketQuaMienTrungBean.getId());
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_dacbiet(), "GDB", ketQuaMienTrungBean.getNgay_quay(), 1);
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
		}
		
	}
	
	public static void main1(String[] args){
		Calendar calendar = Calendar.getInstance();
		//calendar.set(Calendar.YEAR, 2011);
		//calendar.set(Calendar.MONTH, 5);
		//calendar.set(Calendar.DAY_OF_MONTH, 6);
		Date ngay_quay = new Date(calendar.getTimeInMillis());
		
		LotoMienTrungThread.tkLotoMienTrung(ngay_quay);
	}
	
	public static void main_lotothieu(String[] args) {
		logger.log("Loto Mien Trung ngay "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		KetQuaMienTrungDAO dBacDAO = new KetQuaMienTrungDAO();
		Calendar calendar = Calendar.getInstance();
		
		Date ngay_quay = new Date(calendar.getTimeInMillis());	
		Vector<KetQuaMienTrungBean> listKQ = dBacDAO.findKetQuaChuaTinhLoToMienTrung();
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mientrung");
		
		for (KetQuaMienTrungBean ketQuaMienTrungBean : listKQ) {
			logger.log("---->KetquaID="+ketQuaMienTrungBean.getId());

			if(lotoDAO.checkLotoMienTrungByNgayQuay(ngay_quay,ketQuaMienTrungBean.getProvince_id())) continue;
				
			dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_dacbiet(), "GDB", ketQuaMienTrungBean.getNgay_quay(), 1);
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
		}
		
	}
	
	
	public static void main(String[] args) {
		logger.log("Loto Mien TRung ngay "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		KetQuaMienTrungDAO dBacDAO = new KetQuaMienTrungDAO();
		
		Calendar calendar = Calendar.getInstance();
		long endDay = calendar.getTimeInMillis();

		calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		long startDay = calendar.getTimeInMillis();
		long day = 86400000;
		
		while (startDay < endDay) {
			Date openDate = new Date(startDay);
			Vector<KetQuaMienTrungBean> listKQ = dBacDAO.findByNgayQuay(openDate);
			LotoDAO lotoDAO = new LotoDAO("thongke_loto_mientrung");
			
			if(!lotoDAO.checkLotoMienTrungByNgayQuay(openDate))
			for (KetQuaMienTrungBean ketQuaMienTrungBean : listKQ) {
				logger.log("---->KetquaID="+ketQuaMienTrungBean.getId());
				dBacDAO.saveLotoBean(ketQuaMienTrungBean.getId(), ketQuaMienTrungBean.getProvince_id(),  ketQuaMienTrungBean.getGiai_dacbiet(), "GDB", ketQuaMienTrungBean.getNgay_quay(), 1);
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
			}
			
			startDay = startDay + day;
		}
		
		
	}
}
