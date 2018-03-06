package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import com.veso.bean.KetQuaMienBacBean;
import com.veso.dao.KetQuaMienBacDAO;
import com.veso.dao.LotoDAO;
import com.veso.util.Logger;

/*
 * Thread chay cap nhat du lieu vao bang thong ke loto mien bac
 * Chay sau 19.45
 * * 
 */
		
public class LotoMienBacThread {
	private static Logger logger = new Logger("LotoMienBacThread");
	
	public static void tkLotoMienBac(Date ngay_quay) {
		logger.log("LotoMienBacThread "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		KetQuaMienBacDAO dBacDAO = new KetQuaMienBacDAO();
		
		/*Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DAY_OF_MONTH, 13);
		Date ngay_quay = new Date(calendar.getTimeInMillis());*/
		
		Vector<KetQuaMienBacBean> listKQ = dBacDAO.findByNgayQuay(ngay_quay);
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");

		if(!lotoDAO.checkLotoMienBacByNgayQuay(ngay_quay))
		for (KetQuaMienBacBean ketQuaMienBacBean : listKQ) {
			logger.log(ngay_quay.toString()+": LotoMienBacThread---->KqId="+ketQuaMienBacBean.getId());
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_dacbiet(), "GDB", ketQuaMienBacBean.getNgay_quay(), 1);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nhat(), "G1", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nhi_1(), "G21", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nhi_2(), "G22", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_1(), "G31", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_2(), "G32", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_3(), "G33", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_4(), "G34", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_5(), "G35", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_6(), "G36", ketQuaMienBacBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_1(), "G41", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_2(), "G42", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_3(), "G43", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_4(), "G44", ketQuaMienBacBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_1(), "G51", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_2(), "G52", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_3(), "G53", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_4(), "G54", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_5(), "G55", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_6(), "G56", ketQuaMienBacBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_sau_1(), "G61", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_sau_2(), "G62", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_sau_3(), "G63", ketQuaMienBacBean.getNgay_quay(), 0);
			
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_1(), "G71", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_2(), "G72", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_3(), "G73", ketQuaMienBacBean.getNgay_quay(), 0);
			dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_4(), "G74", ketQuaMienBacBean.getNgay_quay(), 0);
		}
		
	}
	
	public static void main1(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DAY_OF_MONTH, 13);
		Date ngay_quay = new Date(calendar.getTimeInMillis());	
		
		LotoMienBacThread.tkLotoMienBac(ngay_quay); 
	}
	
	
	public static void main(String[] args) {
		logger.log("Loto Mien Bac ngay "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		KetQuaMienBacDAO dBacDAO = new KetQuaMienBacDAO();
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
			
			Vector<KetQuaMienBacBean> listKQ = dBacDAO.findByNgayQuay(openDate);
			LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");

			if(!lotoDAO.checkLotoMienBacByNgayQuay(openDate))
			for (KetQuaMienBacBean ketQuaMienBacBean : listKQ) {
				logger.log("---->KqId="+ketQuaMienBacBean.getId());
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_dacbiet(), "GDB", ketQuaMienBacBean.getNgay_quay(), 1);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nhat(), "G1", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nhi_1(), "G21", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nhi_2(), "G22", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_1(), "G31", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_2(), "G32", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_3(), "G33", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_4(), "G34", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_5(), "G35", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_ba_6(), "G36", ketQuaMienBacBean.getNgay_quay(), 0);
				
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_1(), "G41", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_2(), "G42", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_3(), "G43", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_tu_4(), "G44", ketQuaMienBacBean.getNgay_quay(), 0);
				
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_1(), "G51", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_2(), "G52", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_3(), "G53", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_4(), "G54", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_5(), "G55", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_nam_6(), "G56", ketQuaMienBacBean.getNgay_quay(), 0);
				
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_sau_1(), "G61", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_sau_2(), "G62", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_sau_3(), "G63", ketQuaMienBacBean.getNgay_quay(), 0);
				
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_1(), "G71", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_2(), "G72", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_3(), "G73", ketQuaMienBacBean.getNgay_quay(), 0);
				dBacDAO.saveLotoBean(ketQuaMienBacBean.getId(), ketQuaMienBacBean.getProvince_id(),  ketQuaMienBacBean.getGiai_bay_4(), "G74", ketQuaMienBacBean.getNgay_quay(), 0);
			}
			
			startDay = startDay + day;
		}
		
		
	}
	
	
	
	
}
