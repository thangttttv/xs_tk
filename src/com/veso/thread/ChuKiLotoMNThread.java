package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.BangTanSuatLotoBean;
import com.veso.bean.ProvinceBean;
import com.veso.dao.BangTanSuatLotoDAO;
import com.veso.dao.LotoDAO;
import com.veso.dao.ProvinceDAO;
import com.veso.util.DateProc;
import com.veso.util.Logger;

public class ChuKiLotoMNThread {
	private static Logger logger = new Logger("ChuKiLotoMNThread");	
	public static void main(String[] args) {	
		
		logger.log("Chu i loto Mien Nam ngay "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_miennam");
		BangTanSuatLotoDAO bangTanSuatLotoDAO = new BangTanSuatLotoDAO();
		Calendar calendar = Calendar.getInstance();
		int[] khoang_ngays = {10,20,30};
		String thu="";
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SUNDAY:
				thu="thu8";
				break;
			case Calendar.MONDAY:
				thu="thu2";
				break;
			case Calendar.TUESDAY:
				thu="thu3";
				break;
			case Calendar.WEDNESDAY:
				thu="thu4";
				break;
			case Calendar.THURSDAY:
				thu="thu5";
				break;
			case Calendar.FRIDAY:
				thu="thu6";
				break;
			case Calendar.SATURDAY:
				thu="thu7";
				break;
			default:
			break;		
		} 
		ProvinceDAO provinceDAO = new ProvinceDAO();
		List<ProvinceBean> prList = provinceDAO.findProvinceOpen(3, thu);
		BangTanSuatLotoBean bean = null;
		for (ProvinceBean provinceBean : prList) {
			logger.log("--->Tinh="+provinceBean.getName());
			for (int i : khoang_ngays) {
				logger.log("------->Khoang="+i);
				bean = new BangTanSuatLotoBean();
				bean.setProvince_id(provinceBean.getId());
				bean.setContent(lotoDAO.createTableChuKy(new Date(calendar.getTimeInMillis()), i));
				bean.setNgay_bien(new Date(calendar.getTimeInMillis()));
				bean.setCreate_date(DateProc.createTimestamp());
				bean.setSo_ngay(i);		
				bangTanSuatLotoDAO.save(bean);
			}
			
		}
		
		
	}
}
