package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;

import com.veso.bean.BangTanSuatLotoBean;
import com.veso.dao.BangTanSuatLotoDAO;
import com.veso.dao.LotoDAO;
import com.veso.util.DateProc;
import com.veso.util.Logger;
//thongke_bang_tansuatloto
public class ChuKiLotoMBThread {
	private static Logger logger = new Logger("ChuKiLotoMBThread");
	public static void main(String[] args) {
		logger.log("Chu ki Loto Mien Bac ngay "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
		BangTanSuatLotoDAO bangTanSuatLotoDAO = new BangTanSuatLotoDAO();
		Calendar calendar = Calendar.getInstance();
		int[] khoang_ngays = {10,20,30};
		BangTanSuatLotoBean bean = null;
		for (int i : khoang_ngays) {
			logger.log("Khoang = "+i);
			bean = new BangTanSuatLotoBean();
			bean.setProvince_id(1);
			bean.setContent(lotoDAO.createTableChuKy(new Date(calendar.getTimeInMillis()), i));
			bean.setNgay_bien(new Date(calendar.getTimeInMillis()));
			bean.setCreate_date(DateProc.createTimestamp());
			bean.setSo_ngay(i);		
			bangTanSuatLotoDAO.save(bean);
		}
		
		
	}
	
}
