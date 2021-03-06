package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.TKChuKyBoSo;
import com.veso.dao.LotoDAO;
import com.veso.dao.TKChuKiBoSoDAO;

public class TKChuKiBoSoDacBietMBThread  {
	
	private int province_id;
	private LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
	private TKChuKiBoSoDAO chuKiBoSoDAO = new  TKChuKiBoSoDAO();
	
	public void createChuKyBoSo(Date open_date)
	{
		List<String> bosos = lotoDAO.getLotoDacBiet(open_date);
		TKChuKyBoSo chuKyBoSo = null;
		
		for (String boso : bosos) {
			TKChuKyBoSo chuKyBoSo2 = chuKiBoSoDAO.getChuKyCuoiDacBiet(boso, this.province_id);
			if(chuKyBoSo2==null)
			{
				// Them moi
				chuKyBoSo = new TKChuKyBoSo();
				chuKyBoSo.boso = boso;
				chuKyBoSo.start_date = open_date;
				chuKyBoSo.province_id = this.province_id;
				chuKyBoSo.length = 0;
				chuKyBoSo.is_special = 1;
				chuKiBoSoDAO.save(chuKyBoSo);
			}else				
				if(chuKyBoSo2.end_date!=null)
				{
					// Them moi
					chuKyBoSo = new TKChuKyBoSo();
					chuKyBoSo.boso = boso;
					chuKyBoSo.start_date = open_date;
					chuKyBoSo.province_id = this.province_id;
					chuKyBoSo.length = 0;
					chuKyBoSo.is_special = 1;
					chuKiBoSoDAO.save(chuKyBoSo);
					
				}else
				{
					// Dong chu ki
					chuKyBoSo2.end_date = open_date;
					System.out.println(open_date.getTime());
					System.out.println(chuKyBoSo2.start_date.getTime());
					long length =(open_date.getTime()-chuKyBoSo2.start_date.getTime());
					
					System.out.println("Length="+length);
					
					chuKyBoSo2.length = length/86400000;
					chuKiBoSoDAO.updateChuKy(chuKyBoSo2);
					// Tao chu ki moi
					chuKyBoSo = new TKChuKyBoSo();
					chuKyBoSo.boso = boso;
					chuKyBoSo.start_date = open_date;
					chuKyBoSo.province_id = this.province_id;
					chuKyBoSo.length = 0;
					chuKyBoSo.is_special = 1;
					chuKiBoSoDAO.save(chuKyBoSo);
				}
		}
	}
	
	public static void main1(String[] args) {
		Calendar calendar =Calendar.getInstance();	
		long startDay = calendar.getTimeInMillis();
		TKChuKiBoSoDacBietMBThread boSoMBThread = new TKChuKiBoSoDacBietMBThread();
		boSoMBThread.setProvince_id(1);
		Date  openDate = new Date(startDay);
		boSoMBThread.createChuKyBoSo(openDate);
			
		
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
		TKChuKiBoSoDacBietMBThread boSoMBThread = new TKChuKiBoSoDacBietMBThread();
		boSoMBThread.setProvince_id(1);
		Date  openDate = null;
		while(startDay<endDay)
		{
			openDate = new Date(startDay);
			boSoMBThread.createChuKyBoSo(openDate);
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
