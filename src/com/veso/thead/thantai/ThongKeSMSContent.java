package com.veso.thead.thantai;

import java.util.Calendar;
import java.util.List;

import com.veso.bean.ProvinceBean;
import com.veso.dao.LotoDAO;
import com.veso.dao.ProvinceDAO;
import com.veso.util.Logger;

public class ThongKeSMSContent {
	private static Logger logger = new Logger("ThongKeSMSContent");
	
	public static void main(String args[]){
		logger.log("Thong ke Mien Bac-->");
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
			lotoDAO.tkLotoVeNhieu(0, 30);
			lotoDAO.tkLotoGan(0);
			lotoDAO.tkLotoDacBietGan(0);
			lotoDAO.tkTongDacBietGan(0);
			lotoDAO.tkChamDacBietGan(0);
		
		ProvinceDAO provinceDAO = new ProvinceDAO();
		logger.log("Thong ke Mien Trung-->");
			String thu="";
			switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
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
	//		thu="thu3";
	 List<ProvinceBean> province =	provinceDAO.findProvinceOpen(2, thu);
	 LotoDAO lotoDAO2= new LotoDAO("thongke_loto_mientrung");
	 for (ProvinceBean provinceBean : province) {
		 lotoDAO2.tkLotoVeNhieu(provinceBean.getId(), 30);
		 lotoDAO2.tkLotoGan(provinceBean.getId());
		 lotoDAO2.tkLotoDacBietGan(provinceBean.getId());
		 lotoDAO2.tkTongDacBietGan(provinceBean.getId());
		 lotoDAO2.tkChamDacBietGan(provinceBean.getId());
	 }
	 
	 logger.log("Thong ke Mien Nam-->");
	 province =	provinceDAO.findProvinceOpen(3, thu);
	 LotoDAO lotoDAO3= new LotoDAO("thongke_loto_miennam");
	 for (ProvinceBean provinceBean : province) {
		 lotoDAO3.tkLotoVeNhieu(provinceBean.getId(), 30);
		 lotoDAO3.tkLotoGan(provinceBean.getId());
		 lotoDAO3.tkLotoDacBietGan(provinceBean.getId());
		 lotoDAO3.tkTongDacBietGan(provinceBean.getId());
		 lotoDAO3.tkChamDacBietGan(provinceBean.getId());
	 }
	 
	}
}
