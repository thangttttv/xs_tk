package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.TKChuKiDuoi;
import com.veso.dao.LotoDAO;
import com.veso.dao.TKChuKiDuoiDAO;

public class TKChuKiDuoiSoMBThread {
	private int province_id;
	private LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
	private TKChuKiDuoiDAO chuKiDuoiDAO = new TKChuKiDuoiDAO();

	public void createChuKyDuoiSo(Date open_date) {
		List<String> duoisos = lotoDAO.getDuoiSoDacBiet(open_date);
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

	public static void main1(String[] args) {
		Calendar calendar = Calendar.getInstance();		
		long startDay = calendar.getTimeInMillis();
		TKChuKiDuoiSoMBThread chuKiDauMBThread = new TKChuKiDuoiSoMBThread();
		chuKiDauMBThread.setProvince_id(1);
		Date openDate =  new Date(startDay);
		chuKiDauMBThread.createChuKyDuoiSo(openDate);
			
	}
	
	public static void main(String[] args) throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		long endDay = calendar.getTimeInMillis();

		calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		long startDay = calendar.getTimeInMillis();

		long day = 86400000;
		TKChuKiDuoiSoMBThread chuKiDauMBThread = new TKChuKiDuoiSoMBThread();
		chuKiDauMBThread.setProvince_id(1);
		Date openDate = null;
		while (startDay < endDay) {
			openDate = new Date(startDay);
			chuKiDauMBThread.createChuKyDuoiSo(openDate);
			startDay = startDay + day;
			Thread.sleep(100);
		}

	}

	public int getProvince_id() {
		return province_id;
	}

	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
}
