package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.TKChuKiBoSoVeLienTiep;
import com.veso.dao.LotoDAO;
import com.veso.dao.TKChuKiBoSoVeLienTiepDAO;

public class TKChuKiBoSoLienTiepMBThread {
	private int provice_id;
	private LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
	private TKChuKiBoSoVeLienTiepDAO boSoVeLienTiepDAO = new TKChuKiBoSoVeLienTiepDAO();

	public void createChuKyBoSoLienTiep(Date open_date) {
		List<String> bosos = lotoDAO.getLoto(open_date);
		TKChuKiBoSoVeLienTiep chuKyBoSo = null;

		for (String boso : bosos) {
			TKChuKiBoSoVeLienTiep chuKyBoSo2 = boSoVeLienTiepDAO.getChuKyCuoi(
					boso, this.provice_id);
			if (chuKyBoSo2 == null) {
				chuKyBoSo = new TKChuKiBoSoVeLienTiep();
				chuKyBoSo.boso = boso;
				chuKyBoSo.start_date = open_date;
				chuKyBoSo.end_date = open_date;
				chuKyBoSo.length = 1;
				chuKyBoSo.province_id = this.provice_id;
				boSoVeLienTiepDAO.save(chuKyBoSo);

			} else {
				long time = open_date.getTime() - chuKyBoSo2.end_date.getTime();
				time = time / 86400000;

				if (time == 1) {
					chuKyBoSo2.end_date = open_date;
					boSoVeLienTiepDAO.updateLengthChuKy(chuKyBoSo2);
				}else
				{
					chuKyBoSo = new TKChuKiBoSoVeLienTiep();
					chuKyBoSo.boso = boso;
					chuKyBoSo.start_date = open_date;
					chuKyBoSo.end_date = open_date;
					chuKyBoSo.length = 1;
					chuKyBoSo.province_id = this.provice_id;
					boSoVeLienTiepDAO.save(chuKyBoSo);
				}

			}
		}

		List<TKChuKiBoSoVeLienTiep> chukis = boSoVeLienTiepDAO.getChuKyCanXoa(
				this.provice_id, open_date);
		for (TKChuKiBoSoVeLienTiep chuKiBoSoVeLienTiep : chukis) {
			boSoVeLienTiepDAO.deleteChuKy(chuKiBoSoVeLienTiep.id);
			System.out.println(chuKiBoSoVeLienTiep.id);
		}
	}

	public static void main1(String[] args) {
		Calendar calendar = Calendar.getInstance();
		long startDay = calendar.getTimeInMillis();

		TKChuKiBoSoLienTiepMBThread boSoMBThread = new TKChuKiBoSoLienTiepMBThread();
		boSoMBThread.setProvice_id(1);
		Date openDate = new Date(startDay);
		System.out.println("Date--->" + openDate.toString());
		boSoMBThread.createChuKyBoSoLienTiep(openDate);

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
		TKChuKiBoSoLienTiepMBThread boSoMBThread = new TKChuKiBoSoLienTiepMBThread();
		boSoMBThread.setProvice_id(1);
		Date openDate = null;
		while (startDay < endDay) {
			openDate = new Date(startDay);
			System.out.println("Date--->" + openDate.toString());
			boSoMBThread.createChuKyBoSoLienTiep(openDate);
			startDay = startDay + day;
			Thread.sleep(100);
		}

	}

	public int getProvice_id() {
		return provice_id;
	}

	public void setProvice_id(int provice_id) {
		this.provice_id = provice_id;
	}
}
