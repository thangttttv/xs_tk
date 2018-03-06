package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.TKChuKiDau;
import com.veso.dao.LotoDAO;
import com.veso.dao.TKChuKiDauDAO;

public class TKChuKiDauSoMBThread {
	private int province_id;
	private LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
	private TKChuKiDauDAO chuKiDauDAO = new  TKChuKiDauDAO();
	
	public void createChuKyDauSo(Date open_date)
	{
		List<String> dausos = lotoDAO.getDauSoDacBiet(open_date);
		TKChuKiDau chukidau = null;
		
		for (String dauso : dausos) {
			TKChuKiDau chuKiDau2 = chuKiDauDAO.getChuKyCuoi(dauso, this.province_id);
			if(chuKiDau2==null)
			{
				// Them moi
				chukidau = new TKChuKiDau();
				chukidau.dau = dauso;
				chukidau.start_date = open_date;
				chukidau.province_id = this.province_id;
				chukidau.length = 0;
				chuKiDauDAO.save(chukidau);
			}else				
				if(chuKiDau2.end_date!=null)
				{
					// Them moi
					chukidau = new TKChuKiDau();
					chukidau.dau = dauso;
					chukidau.start_date = open_date;
					chukidau.province_id = this.province_id;
					chukidau.length = 0;
					chuKiDauDAO.save(chukidau);
					
				}else
				{
					// Dong chu ki
					chuKiDau2.end_date = open_date;
					System.out.println(open_date.getTime());
					System.out.println(chuKiDau2.start_date.getTime());
					long length =(open_date.getTime()-chuKiDau2.start_date.getTime());
					
					System.out.println("Length="+length);
					
					chuKiDau2.length = length/86400000;
					chuKiDauDAO.updateChuKy(chuKiDau2);
					// Tao chu ki moi
					chukidau = new TKChuKiDau();
					chukidau.dau = dauso;
					chukidau.start_date = open_date;
					chukidau.province_id = this.province_id;
					chukidau.length = 0;
					chuKiDauDAO.save(chukidau);
				}
		}
	}
	
	
	public static void main1(String[] args) {
		Calendar calendar =Calendar.getInstance();		
		long startDay = calendar.getTimeInMillis();	
		TKChuKiDauSoMBThread chuKiDauMBThread = new TKChuKiDauSoMBThread();
		chuKiDauMBThread.setProvince_id(1);
		Date  openDate = null;		
		openDate = new Date(startDay);
		chuKiDauMBThread.createChuKyDauSo(openDate);
	}
	
	public static void main(String[] args) throws InterruptedException {
		Calendar calendar =Calendar.getInstance();	
		long endDay = calendar.getTimeInMillis();
		
		calendar =Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		long startDay = calendar.getTimeInMillis();
		
		long day = 86400000;
		TKChuKiDauSoMBThread chuKiDauMBThread = new TKChuKiDauSoMBThread();
		chuKiDauMBThread.setProvince_id(1);
		Date  openDate = null;
		while(startDay<endDay)
		{
			openDate = new Date(startDay);
			chuKiDauMBThread.createChuKyDauSo(openDate);
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
