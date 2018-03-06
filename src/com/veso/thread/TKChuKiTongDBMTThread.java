package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.ProvinceBean;
import com.veso.bean.TKChuKiTongDB;
import com.veso.dao.LotoDAO;
import com.veso.dao.ProvinceDAO;
import com.veso.dao.TKChuKiTongDBDAO;

public class TKChuKiTongDBMTThread {
	private int province_id;
	private LotoDAO lotoDAO = new LotoDAO("thongke_loto_mientrung");
	private TKChuKiTongDBDAO chuKiDauDAO = new  TKChuKiTongDBDAO();
	
	public void createChuKyDauSo(Date open_date)
	{
		List<Integer> dausos = lotoDAO.getTongDacBiet(open_date,province_id);
		TKChuKiTongDB chukidau = null;
		
		for (Integer dauso : dausos) {
			TKChuKiTongDB chuKiDau2 = chuKiDauDAO.getChuKyCuoi(dauso, this.province_id);
			if(chuKiDau2==null)
			{
				// Them moi
				chukidau = new TKChuKiTongDB();
				chukidau.tong = dauso;
				chukidau.start_date = open_date;
				chukidau.province_id = this.province_id;
				chukidau.length = 0;
				chuKiDauDAO.save(chukidau);
			}else				
				if(chuKiDau2.end_date!=null)
				{
					// Them moi
					chukidau = new TKChuKiTongDB();
					chukidau.tong = dauso;
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
					chukidau = new TKChuKiTongDB();
					chukidau.tong = dauso;
					chukidau.start_date = open_date;
					chukidau.province_id = this.province_id;
					chukidau.length = 0;
					chuKiDauDAO.save(chukidau);
				}
		}
	}
	
	public static void tk(long startDay) {
		ProvinceDAO provinceDAO = new ProvinceDAO();
		List<ProvinceBean> provinces = provinceDAO.findAll(2);

		for (ProvinceBean provinceBean : provinces) {
			TKChuKiTongDBMTThread chuKiDauMBThread = new TKChuKiTongDBMTThread();
			chuKiDauMBThread.setProvince_id(provinceBean.getId());
			Date  openDate = null;		
			boolean is_open_date = ProvinceBean.isOpenDate(startDay, provinceBean) ;
			if(is_open_date)
			{
				openDate = new Date(startDay);
				chuKiDauMBThread.createChuKyDauSo(openDate);
			}
			
	  }	
	}

	public static void main1(String[] args){
		Calendar calendar =Calendar.getInstance();	
		long startDay = calendar.getTimeInMillis();
		
		TKChuKiTongDBMTThread.tk(startDay);
	}
	
	public static void main(String[] args) throws InterruptedException {
		Calendar calendar =Calendar.getInstance();	
		long endDay = calendar.getTimeInMillis();
		
		ProvinceDAO provinceDAO = new ProvinceDAO();
		List<ProvinceBean> provinces = provinceDAO.findAll(2);
		
		for (ProvinceBean provinceBean : provinces) {
		
		calendar =Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		long startDay = calendar.getTimeInMillis();
		
		long day = 86400000;
		TKChuKiTongDBMTThread chuKiDauMBThread = new TKChuKiTongDBMTThread();
		chuKiDauMBThread.setProvince_id(provinceBean.getId());
		Date  openDate = null;
		while(startDay<endDay)
		{
			boolean is_open_date = ProvinceBean.isOpenDate(startDay, provinceBean) ;
			if(is_open_date)
			{
				openDate = new Date(startDay);
				chuKiDauMBThread.createChuKyDauSo(openDate);
			}
			startDay= startDay+day;
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
