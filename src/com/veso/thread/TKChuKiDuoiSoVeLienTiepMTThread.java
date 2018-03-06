package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.ProvinceBean;
import com.veso.bean.TKChuKiDuoiSoVeLienTiep;
import com.veso.dao.LotoDAO;
import com.veso.dao.ProvinceDAO;
import com.veso.dao.TKChuKiDuoiSoVeLienTiepDAO;

public class TKChuKiDuoiSoVeLienTiepMTThread {
	private int province_id;
	private String province_code;
	private LotoDAO lotoDAO = new LotoDAO("thongke_loto_mientrung");
	private TKChuKiDuoiSoVeLienTiepDAO boSoVeLienTiepDAO = new TKChuKiDuoiSoVeLienTiepDAO();

	public void createChuKyDuoiSoVeLienTiep(Date open_date) {
		List<String> dausos = lotoDAO.getDuoiSoDacBiet(open_date,province_id);
		if(dausos.size()==0) return;
		TKChuKiDuoiSoVeLienTiep chuKyBoSo = null;
		int kc_ngay_quay = 7;
		for (String dau : dausos) {
			TKChuKiDuoiSoVeLienTiep chuKyBoSo2 = boSoVeLienTiepDAO
					.getChuKyCuoi(dau, this.province_id);
			if (chuKyBoSo2 == null) {
				chuKyBoSo = new TKChuKiDuoiSoVeLienTiep();
				chuKyBoSo.duoi = dau;
				chuKyBoSo.start_date = open_date;
				chuKyBoSo.end_date = open_date;
				chuKyBoSo.length = 1;
				chuKyBoSo.province_id = this.province_id;
				boSoVeLienTiepDAO.save(chuKyBoSo);

			} else {
				long time = open_date.getTime() - chuKyBoSo2.end_date.getTime();
				time = time / 86400000;
				
				if("dng".equalsIgnoreCase(province_code))
				{
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(open_date);
					if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY)
					{
						kc_ngay_quay = 4;
					}else
					{
						kc_ngay_quay = 3;
					}
				}
				
				if (time == kc_ngay_quay) {
					chuKyBoSo2.end_date = open_date;
					boSoVeLienTiepDAO.updateLengthChuKy(chuKyBoSo2);
				}else
				{
					chuKyBoSo = new TKChuKiDuoiSoVeLienTiep();
					chuKyBoSo.duoi = dau;
					chuKyBoSo.start_date = open_date;
					chuKyBoSo.end_date = open_date;
					chuKyBoSo.length = 1;
					chuKyBoSo.province_id = this.province_id;
					boSoVeLienTiepDAO.save(chuKyBoSo);
				}

			}
		}

		List<TKChuKiDuoiSoVeLienTiep> chukis = boSoVeLienTiepDAO
				.getChuKyCanXoa(this.province_id, open_date);
		for (TKChuKiDuoiSoVeLienTiep chuKiDuoiSoVeLienTiep : chukis) {
			boSoVeLienTiepDAO.deleteChuKy(chuKiDuoiSoVeLienTiep.id);
			System.out.println(chuKiDuoiSoVeLienTiep.id);
		}
	}

	public static void tk(long startDay) {
		ProvinceDAO provinceDAO = new ProvinceDAO();
		List<ProvinceBean> provinces = provinceDAO.findAll(2);
		
		for (ProvinceBean provinceBean : provinces) {			
				TKChuKiDuoiSoVeLienTiepMTThread boSoMBThread = new TKChuKiDuoiSoVeLienTiepMTThread();
				boSoMBThread.setProvince_id(provinceBean.getId());
				boSoMBThread.setProvince_code(provinceBean.getCode());
				Date openDate = null;			
				boolean is_open_date = ProvinceBean.isOpenDate(startDay, provinceBean) ;
				if(is_open_date)
				{
				 openDate = new Date(startDay);
				 boSoMBThread.createChuKyDuoiSoVeLienTiep(openDate);
				}
				
		}
	}
	
	public static void main1(String[] args) {
		Calendar calendar = Calendar.getInstance();
		long startDay = calendar.getTimeInMillis();
		
		TKChuKiDuoiSoVeLienTiepMTThread.tk(startDay);
	}
	
	public static void main(String[] args) throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		long endDay = calendar.getTimeInMillis();
		
		ProvinceDAO provinceDAO = new ProvinceDAO();
		List<ProvinceBean> provinces = provinceDAO.findAll(2);
		
		for (ProvinceBean provinceBean : provinces) {
			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2009);
			calendar.set(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			long startDay = calendar.getTimeInMillis();	
			long day = 86400000;
			TKChuKiDuoiSoVeLienTiepMTThread boSoMBThread = new TKChuKiDuoiSoVeLienTiepMTThread();
			boSoMBThread.setProvince_id(provinceBean.getId());
			boSoMBThread.setProvince_code(provinceBean.getCode());			
			Date openDate = null;
			while (startDay <= endDay) {
				boolean is_open_date = ProvinceBean.isOpenDate(startDay, provinceBean) ;
				if(is_open_date)
				{
				 openDate = new Date(startDay);
				 boSoMBThread.createChuKyDuoiSoVeLienTiep(openDate);
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

	public String getProvince_code() {
		return province_code;
	}

	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
}
