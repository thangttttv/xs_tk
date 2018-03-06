package com.veso.thead.thantai;

import java.util.Calendar;
import java.util.List;

import com.veso.bean.ProvinceBean;
import com.veso.dao.ProvinceDAO;
import com.veso.dao.SoiCauDAO;
import com.veso.util.Logger;

public class ThongKeCauDep {
	
	private static Logger logger = new Logger("ThongKeCauDep");
	public static void main(String[] args) {
		int soluong_boso = 5;
		if(args!=null&&args.length>0)
			soluong_boso = Integer.parseInt(args[0]);
		
		logger.log("Thong Ke Cau Dep Ngay"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		SoiCauDAO soiCauDAO = new SoiCauDAO();
		ProvinceDAO provinceDAO = new ProvinceDAO();
		Calendar calendar = Calendar.getInstance();
		String bien_ngay = Calendar.getInstance().get(Calendar.YEAR)+"-"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
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
		// Cau Dep Mien Bac
		logger.log("Mien Bac"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		soiCauDAO.statisCauDep(1, 1, bien_ngay,soluong_boso);
		soiCauDAO.statisCauDep(1, 2, bien_ngay,soluong_boso);
		soiCauDAO.statisCauDep(1, 3, bien_ngay,soluong_boso);
		logger.log("Mien Trung"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		// Mien Trung
		List<ProvinceBean> prList = provinceDAO.findProvinceOpen(2, thu);
		for (ProvinceBean provinceBean : prList) {
			soiCauDAO.statisCauDep(provinceBean.getId(), 1, bien_ngay,soluong_boso);
			soiCauDAO.statisCauDep(provinceBean.getId(), 2, bien_ngay,soluong_boso);
			soiCauDAO.statisCauDep(provinceBean.getId(), 3, bien_ngay,soluong_boso);
		}
		logger.log("Mien Nam"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		// Mien Nam
		prList = provinceDAO.findProvinceOpen(3, thu);
		for (ProvinceBean provinceBean : prList) {
			soiCauDAO.statisCauDep(provinceBean.getId(), 1, bien_ngay,soluong_boso);
			soiCauDAO.statisCauDep(provinceBean.getId(), 2, bien_ngay,soluong_boso);
			soiCauDAO.statisCauDep(provinceBean.getId(), 3, bien_ngay,soluong_boso);
		}
	}
	
}
