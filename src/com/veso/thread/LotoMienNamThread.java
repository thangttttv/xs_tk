package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import com.veso.bean.KetQuaMienNamBean;
import com.veso.dao.KetQuaMienNamDAO;
import com.veso.dao.LotoDAO;
import com.veso.util.Logger;

/*
 * Thread chay cap nhat du lieu vao bang thong ke loto mien nam
 * Chay sau 16.45
 * * 
 */

public class LotoMienNamThread {
	private static Logger logger = new Logger("LotoMienNamThread");
	public static void tkLotoMienNam(Date ngay_quay) {
		logger.log("LotoMienNamThread "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		KetQuaMienNamDAO dBacDAO = new KetQuaMienNamDAO();
		
		Vector<KetQuaMienNamBean> listKQ = dBacDAO.findByNgayQuay(ngay_quay);
		
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_miennam");
		if(!lotoDAO.checkLotoMienNamByNgayQuay(ngay_quay))
		for (KetQuaMienNamBean ketQuaMienNamBean : listKQ) {
			logger.log(ngay_quay.toString()+":LotoMienNamThread---->KetquaId="+ketQuaMienNamBean.getId());
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_dacbiet(), "GDB", ketQuaMienNamBean.getNgay_quay(), 1);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_nhat(), "G1", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_nhi(), "G2", ketQuaMienNamBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_ba_1(), "G31", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_ba_2(), "G32", ketQuaMienNamBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_1(), "G41", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_2(), "G42", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_3(), "G43", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_4(), "G44", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_5(), "G45", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_6(), "G46", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_7(), "G47", ketQuaMienNamBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_nam(), "G5", ketQuaMienNamBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_sau_1(), "G61", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_sau_2(), "G62", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_sau_3(), "G63", ketQuaMienNamBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_bay(), "G7", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tam(), "G8", ketQuaMienNamBean.getNgay_quay(), 0);
			
			
		}
		
	}
	
	public static void main1(String[] args) {
		Calendar calendar = Calendar.getInstance();
		//calendar.set(Calendar.YEAR, 2011);
		//calendar.set(Calendar.MONTH, 5);
		//calendar.set(Calendar.DAY_OF_MONTH, 6);
		Date ngay_quay = new Date(calendar.getTimeInMillis());	
		
		LotoMienNamThread.tkLotoMienNam(ngay_quay);
	}
	
	public static void main_lotothieu(String[] args) {
		logger.log("Loto Mien Nam ngay "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		KetQuaMienNamDAO dBacDAO = new KetQuaMienNamDAO();
	
		Vector<KetQuaMienNamBean> listKQ = dBacDAO.findKetQuaChuaTinhLoToMienNam();
		
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_miennam");
		
		for (KetQuaMienNamBean ketQuaMienNamBean : listKQ) {
			logger.log("---->KetquaId="+ketQuaMienNamBean.getId());
			
			if(lotoDAO.checkLotoMienNamByNgayQuay(ketQuaMienNamBean.getNgay_quay(),ketQuaMienNamBean.getProvince_id())) continue;
				
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_dacbiet(), "GDB", ketQuaMienNamBean.getNgay_quay(), 1);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_nhat(), "G1", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_nhi(), "G2", ketQuaMienNamBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_ba_1(), "G31", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_ba_2(), "G32", ketQuaMienNamBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_1(), "G41", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_2(), "G42", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_3(), "G43", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_4(), "G44", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_5(), "G45", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_6(), "G46", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_7(), "G47", ketQuaMienNamBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_nam(), "G5", ketQuaMienNamBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_sau_1(), "G61", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_sau_2(), "G62", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_sau_3(), "G63", ketQuaMienNamBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_bay(), "G7", ketQuaMienNamBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tam(), "G8", ketQuaMienNamBean.getNgay_quay(), 0);
		}
		
	}
	
	public static void main(String[] args) {
		logger.log("Loto Mien Nam ngay "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		KetQuaMienNamDAO dBacDAO = new KetQuaMienNamDAO();
		
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
			
			Vector<KetQuaMienNamBean> listKQ = dBacDAO.findByNgayQuay(openDate);
			LotoDAO lotoDAO = new LotoDAO("thongke_loto_miennam");
			if(!lotoDAO.checkLotoMienNamByNgayQuay(openDate))
			for (KetQuaMienNamBean ketQuaMienNamBean : listKQ) {
				logger.log("---->KetquaId="+ketQuaMienNamBean.getId());
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_dacbiet(), "GDB", ketQuaMienNamBean.getNgay_quay(), 1);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_nhat(), "G1", ketQuaMienNamBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_nhi(), "G2", ketQuaMienNamBean.getNgay_quay(), 0);
				
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_ba_1(), "G31", ketQuaMienNamBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_ba_2(), "G32", ketQuaMienNamBean.getNgay_quay(), 0);
				
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_1(), "G41", ketQuaMienNamBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_2(), "G42", ketQuaMienNamBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_3(), "G43", ketQuaMienNamBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_4(), "G44", ketQuaMienNamBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_5(), "G45", ketQuaMienNamBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_6(), "G46", ketQuaMienNamBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tu_7(), "G47", ketQuaMienNamBean.getNgay_quay(), 0);
				
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_nam(), "G5", ketQuaMienNamBean.getNgay_quay(), 0);
				
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_sau_1(), "G61", ketQuaMienNamBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_sau_2(), "G62", ketQuaMienNamBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_sau_3(), "G63", ketQuaMienNamBean.getNgay_quay(), 0);
				
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_bay(), "G7", ketQuaMienNamBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienNamBean.getId(), ketQuaMienNamBean.getProvince_id(),  ketQuaMienNamBean.getGiai_tam(), "G8", ketQuaMienNamBean.getNgay_quay(), 0);
				
				
			}
			
			startDay = startDay + day;
		}
		
		
	}
}
