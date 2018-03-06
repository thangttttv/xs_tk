package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.ProvinceBean;
import com.veso.bean.TKChuKiDuoi;
import com.veso.dao.LotoDAO;
import com.veso.dao.ProvinceDAO;
import com.veso.dao.TKChuKiDuoiDAO;

public class TKChuKiDuoiSoMNThread {
	private int province_id;
	private LotoDAO lotoDAO = new LotoDAO("thongke_loto_miennam");
	private TKChuKiDuoiDAO chuKiDuoiDAO = new TKChuKiDuoiDAO();

	public void createChuKyDuoiSo(Date open_date) {
		List<String> duoisos = lotoDAO.getDuoiSoDacBiet(open_date,province_id);
		if(duoisos.size()==0)return;
		TKChuKiDuoi chuKiDuoi = null;

		for (String duoiso : duoisos) {
			TKChuKiDuoi chuKiDuoi2 = chuKiDuoiDAO.getChuKyCuoi(duoiso,
					this.province_id);
			if (chuKiDuoi2 == null) {
				// Them moi
				chuKiDuoi = new TKChuKiDuoi();
				chuKiDuoi.duoi = duoiso;
				chuKiDuoi.start_date = open_date;
				chuKiDuoi.province_id = this.province_id;
				chuKiDuoi.length = 0;
				chuKiDuoiDAO.save(chuKiDuoi);
			} else if (chuKiDuoi2.end_date != null) {
				// Them moi
				chuKiDuoi = new TKChuKiDuoi();
				chuKiDuoi.duoi = duoiso;
				chuKiDuoi.start_date = open_date;
				chuKiDuoi.province_id = this.province_id;
				chuKiDuoi.length = 0;
				chuKiDuoiDAO.save(chuKiDuoi);

			} else {
				// Dong chu ki
				chuKiDuoi2.end_date = open_date;
				System.out.println(open_date.getTime());
				System.out.println(chuKiDuoi2.start_date.getTime());
				long length = (open_date.getTime() - chuKiDuoi2.start_date
						.getTime());

				System.out.println("Length=" + length);

				chuKiDuoi2.length = length / 86400000;
				chuKiDuoiDAO.updateChuKy(chuKiDuoi2);
				// Tao chu ki moi
				chuKiDuoi = new TKChuKiDuoi();
				chuKiDuoi.duoi = duoiso;
				chuKiDuoi.start_date = open_date;
				chuKiDuoi.province_id = this.province_id;
				chuKiDuoi.length = 0;
				chuKiDuoiDAO.save(chuKiDuoi);
			}
		}
	}

	public static void tk(long startDay) {
		ProvinceDAO provinceDAO = new ProvinceDAO();
		List<ProvinceBean> provinces = provinceDAO.findAll(3);
		
		for (ProvinceBean provinceBean : provinces) {	
			TKChuKiDuoiSoMNThread chuKiDauMBThread = new TKChuKiDuoiSoMNThread();
			chuKiDauMBThread.setProvince_id(provinceBean.getId());
			Date openDate = null;
			boolean is_open_date = ProvinceBean.isOpenDate(startDay, provinceBean) ;
				if(is_open_date)
				{
					openDate = new Date(startDay);
					chuKiDauMBThread.createChuKyDuoiSo(openDate);
				}
				
		}
	}
	
	public static void main1(String[] args){
		Calendar calendar = Calendar.getInstance();
		long startDay = calendar.getTimeInMillis();
		TKChuKiDuoiSoMNThread.tk(startDay);
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		long endDay = calendar.getTimeInMillis();
		
		ProvinceDAO provinceDAO = new ProvinceDAO();
		List<ProvinceBean> provinces = provinceDAO.findAll(3);
		
		for (ProvinceBean provinceBean : provinces) {	
			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2009);
			calendar.set(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			long startDay = calendar.getTimeInMillis();
	
			long day = 86400000;
			TKChuKiDuoiSoMNThread chuKiDauMBThread = new TKChuKiDuoiSoMNThread();
			chuKiDauMBThread.setProvince_id(provinceBean.getId());
			Date openDate = null;
			while (startDay < endDay) {
				boolean is_open_date = ProvinceBean.isOpenDate(startDay, provinceBean) ;
				if(is_open_date)
				{
					openDate = new Date(startDay);
					chuKiDauMBThread.createChuKyDuoiSo(openDate);
				}
				startDay = startDay + day;
				Thread.sleep(100);
			}
		}
	}

	public int getProvince_id() {
		return province_id;
	}

	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
}
