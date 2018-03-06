package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.TKChuKiTongDBVeLienTiep;
import com.veso.dao.LotoDAO;
import com.veso.dao.TKChuKiTongVeLienTiepDAO;

public class TKChuKiTongDBVeLienTiepMBThread {
	private int province_id;
	private LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
	private TKChuKiTongVeLienTiepDAO boSoVeLienTiepDAO = new  TKChuKiTongVeLienTiepDAO();
	
	public void createChuKyTongDBVeLienTiep(Date open_date)
	{
		List<Integer> dausos = lotoDAO.getTongDacBiet(open_date);
		if(dausos.size()==0) return;
		TKChuKiTongDBVeLienTiep chuKyBoSo = null;
		
		for (Integer dau : dausos) {
			TKChuKiTongDBVeLienTiep chuKyBoSo2 = boSoVeLienTiepDAO.getChuKyCuoi(dau, this.province_id);
			if(chuKyBoSo2==null)
			{
				chuKyBoSo = new TKChuKiTongDBVeLienTiep();
				chuKyBoSo.tong = dau;
				chuKyBoSo.start_date = open_date;
				chuKyBoSo.end_date = open_date;
				chuKyBoSo.length = 1;
				chuKyBoSo.province_id = this.province_id;
				boSoVeLienTiepDAO.save(chuKyBoSo);
				
			}else
			{
				long time = open_date.getTime()-chuKyBoSo2.end_date.getTime(); 
				time = time/86400000;
				
				if(time==1)
				{
					chuKyBoSo2.end_date = open_date;					
					boSoVeLienTiepDAO.updateLengthChuKy(chuKyBoSo2);
				}else
				{
					chuKyBoSo = new TKChuKiTongDBVeLienTiep();
					chuKyBoSo.tong = dau;
					chuKyBoSo.start_date = open_date;
					chuKyBoSo.end_date = open_date;
					chuKyBoSo.length = 1;
					chuKyBoSo.province_id = this.province_id;
					boSoVeLienTiepDAO.save(chuKyBoSo);
				}
				
			}
		}
		
		List<TKChuKiTongDBVeLienTiep> chukis = boSoVeLienTiepDAO.getChuKyCanXoa(this.province_id, open_date);
		for (TKChuKiTongDBVeLienTiep chuKiDauSoVeLienTiep : chukis) {
			boSoVeLienTiepDAO.deleteChuKy(chuKiDauSoVeLienTiep.id);
			System.out.println(chuKiDauSoVeLienTiep.id);
		}
	}
	
	public static void main1(String[] args) {
		Calendar calendar =Calendar.getInstance();	
		long startDay = calendar.getTimeInMillis();		

		TKChuKiTongDBVeLienTiepMBThread boSoMBThread = new TKChuKiTongDBVeLienTiepMBThread();
		boSoMBThread.setProvince_id(1);		
		Date  openDate = null;		
		openDate = new Date(startDay);
		boSoMBThread.createChuKyTongDBVeLienTiep(openDate);
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Calendar calendar =Calendar.getInstance();	
		long endDay = calendar.getTimeInMillis();		
		calendar =Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);
		calendar.set(Calendar.MONTH, 00);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		long startDay = calendar.getTimeInMillis();
		
		long day = 86400000;
		TKChuKiTongDBVeLienTiepMBThread boSoMBThread = new TKChuKiTongDBVeLienTiepMBThread();
		boSoMBThread.setProvince_id(1);
		
		Date  openDate = null;
		while(startDay<=endDay)
		{
			openDate = new Date(startDay);
			boSoMBThread.createChuKyTongDBVeLienTiep(openDate);
			startDay= startDay+day;
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
