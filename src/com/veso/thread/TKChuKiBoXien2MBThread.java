package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.TKChuKiBoXien2;
import com.veso.dao.LotoDAO;
import com.veso.dao.TKChuKiLoXien2DAO;

public class TKChuKiBoXien2MBThread {
	private int province_id;
	private LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
	private TKChuKiLoXien2DAO chuKiLoXien2DAO = new  TKChuKiLoXien2DAO();
	
	public void createChuKyBoSoXien2(Date open_date)
	{
		List<String> bosos = lotoDAO.getLoto(open_date);
		int i=0,j=0;
		while(i<bosos.size())
		{			
		
			TKChuKiBoXien2 chuKyBoSo = null;
			j=i+1;
			String boso1 = bosos.get(i);
			while(j<bosos.size())
			{	
				String boso2 = bosos.get(j);
				TKChuKiBoXien2 chuKyBoSo2 = chuKiLoXien2DAO.getChuKyCuoi(boso1,boso2, this.province_id);
				if(chuKyBoSo2==null)
				{
					// Them moi
					chuKyBoSo = new TKChuKiBoXien2();
					chuKyBoSo.boso1 = boso1;
					chuKyBoSo.boso2 = boso2;
					chuKyBoSo.start_date = open_date;
					chuKyBoSo.province_id = this.province_id;
					chuKyBoSo.length = 0;
					chuKiLoXien2DAO.save(chuKyBoSo);
				}else				
					if(chuKyBoSo2.end_date!=null)
					{
						// Them moi
						chuKyBoSo = new TKChuKiBoXien2();
						chuKyBoSo.boso1 = boso1;
						chuKyBoSo.boso2 = boso2;
						chuKyBoSo.start_date = open_date;
						chuKyBoSo.province_id = this.province_id;
						chuKyBoSo.length = 0;
						chuKiLoXien2DAO.save(chuKyBoSo);
						
					}else
					{
						// Dong chu ki
						chuKyBoSo2.end_date = open_date;
						System.out.println(open_date.getTime());
						System.out.println(chuKyBoSo2.start_date.getTime());
						long length =(open_date.getTime()-chuKyBoSo2.start_date.getTime());
						
						System.out.println("Length="+length);
						
						chuKyBoSo2.length = length/86400000;
						chuKiLoXien2DAO.updateChuKy(chuKyBoSo2);
						// Tao chu ki moi
						chuKyBoSo = new TKChuKiBoXien2();
						chuKyBoSo.boso1 = boso1;
						chuKyBoSo.boso2 = boso2;
						chuKyBoSo.start_date = open_date;
						chuKyBoSo.province_id = this.province_id;
						chuKyBoSo.length = 0;
						chuKiLoXien2DAO.save(chuKyBoSo);
					}
				j++;
			}
			i++;
		}
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
		TKChuKiBoXien2MBThread boSoMBThread = new TKChuKiBoXien2MBThread();
		boSoMBThread.setProvince_id(1);
		Date  openDate = null;
		while(startDay<endDay)
		{
			openDate = new Date(startDay);
			boSoMBThread.createChuKyBoSoXien2(openDate);
			startDay= startDay+day;
			Thread.sleep(100);
		}
		
	}
	
	public static void main1(String[] args) {
		Calendar calendar =Calendar.getInstance();	
		
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DAY_OF_MONTH, 12);
		
		long startDay = calendar.getTimeInMillis();
		TKChuKiBoXien2MBThread boSoMBThread = new TKChuKiBoXien2MBThread();
		boSoMBThread.setProvince_id(1);
		Date  openDate = null;		
		openDate = new Date(startDay);
		boSoMBThread.createChuKyBoSoXien2(openDate);
			
	}

	public int getProvince_id() {
		return province_id;
	}

	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	
}

