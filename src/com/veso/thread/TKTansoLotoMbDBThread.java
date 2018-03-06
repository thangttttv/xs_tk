package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

import com.veso.bean.TanSuatBoSoBean;
import com.veso.dao.LotoDAO;
import com.veso.dao.TanSuatBoSoDAO;
import com.veso.util.DateProc;
import com.veso.util.Logger;

/*
 * Thread Dung Thong ke tuan suat loto Mien Bac
 * Chay Hang ngay sau khi co ket qua xo so MB 
 * @author: thangtt
 * 
 */

public class TKTansoLotoMbDBThread {
	public static final long miliSecondDay = 86400000;
	private static Logger logger = new Logger("TKTansoLotoMbThread");
	
	public static void main1(String[] args) {
		
		logger.log("TK Tan suat loto ngay "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
		TanSuatBoSoDAO tanSuatBoSoDAO = new TanSuatBoSoDAO("thongke_tansuat_loto_mienbac");
		
		int khoangTimes[] = {10,20,30,120,240,300,365,400,450,500};
		
		Date fromDate = null,toDate=null;
		HashMap<String, Integer> lisTS = null;
		HashMap<String, Integer> lisNV = null;
		TanSuatBoSoBean tanSuatBoSoBean = null;
		int i = 0;String boso="00";
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.set(Calendar.YEAR, 2012);
		calendarEnd.set(Calendar.MONTH, 01);
		calendarEnd.set(Calendar.DAY_OF_MONTH, 9);calendarEnd.set(Calendar.HOUR, 00);calendarEnd.set(Calendar.MINUTE, 00);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);
		calendar.set(Calendar.MONTH, 00);
		calendar.set(Calendar.DAY_OF_MONTH, 01);calendarEnd.set(Calendar.HOUR, 00);calendarEnd.set(Calendar.MINUTE, 00);
	
		long timeBegin = calendar.getTimeInMillis();
		long timeEnd = calendarEnd.getTimeInMillis();
		while(timeBegin<=timeEnd)
		{		
		long endDate = calendar.getTimeInMillis();		
		for (int songay : khoangTimes) {
			logger.log("---->So ngay = "+songay);
			long bienngay = songay*miliSecondDay;		
			toDate = new Date(endDate);
			fromDate = new Date(endDate-bienngay);
			lisTS = lotoDAO.tkTanSoLanVeDacBiet(fromDate, toDate);
			lisNV = lotoDAO.tkTanSoNgayVeDacBiet(fromDate, toDate);
			i = 00;
			while(i<=99)
			{
				boso=String.valueOf(i).length()==1?"0"+i:i+"";
				if(lisTS.containsKey(boso))
				{					
					tanSuatBoSoBean = new TanSuatBoSoBean();
					tanSuatBoSoBean.setBoso(boso);
					tanSuatBoSoBean.setCreate_date(DateProc.createTimestamp());
					tanSuatBoSoBean.setKhoang_thoigian(songay);
					
					tanSuatBoSoBean.setP_so_lan_ve(((double)lisTS.get(boso)/(songay*27))*100);					
					tanSuatBoSoBean.setSo_lan_ve(lisTS.get(boso));
					
					tanSuatBoSoBean.setSo_ngay_ve(lisNV.get(boso));
					tanSuatBoSoBean.setP_so_ngay_ve(((double)lisNV.get(boso)/songay)*100);
					
					tanSuatBoSoBean.setP_so_lan_ngay(((double)lisTS.get(boso)/lisNV.get(boso))*100);
					tanSuatBoSoBean.setProvince_id(0);
					tanSuatBoSoBean.setBien_ngay(toDate);
					tanSuatBoSoBean.setIs_dacbiet(1);
					tanSuatBoSoDAO.save(tanSuatBoSoBean);
				}
				i++;
			}
		}
		calendar.setTimeInMillis(calendar.getTimeInMillis()+86400000);
		timeBegin = calendar.getTimeInMillis();
		}		
	}	
		
	
	public static void main(String[] args) {
		
		logger.log("TK Tan suat de  ngay "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
		TanSuatBoSoDAO tanSuatBoSoDAO = new TanSuatBoSoDAO("thongke_tansuat_loto_mienbac");
		
		int khoangTimes[] = {10,20,30,120,240,300,365,400,450,500};
		
		Date fromDate = null,toDate=null;
		HashMap<String, Integer> lisTS = null;
		HashMap<String, Integer> lisNV = null;
		TanSuatBoSoBean tanSuatBoSoBean = null;
		int i = 0;String boso="00";
		Calendar calendar = Calendar.getInstance();
		long endDate = calendar.getTimeInMillis();
		
		for (int songay : khoangTimes) {
			logger.log("---->So ngay = "+songay);
			long bienngay = songay*miliSecondDay;		
			toDate = new Date(endDate);
			fromDate = new Date(endDate-bienngay);
			lisTS = lotoDAO.tkTanSoLanVeDacBiet(fromDate, toDate);
			lisNV = lotoDAO.tkTanSoNgayVeDacBiet(fromDate, toDate);
			i = 0;
			while(i<=99)
			{
				boso=String.valueOf(i).length()==1?"0"+i:i+"";
				if(lisTS.containsKey(boso))
				{					
					tanSuatBoSoBean = new TanSuatBoSoBean();
					tanSuatBoSoBean.setBoso(boso);
					tanSuatBoSoBean.setCreate_date(DateProc.createTimestamp());
					tanSuatBoSoBean.setKhoang_thoigian(songay);
					
					tanSuatBoSoBean.setP_so_lan_ve(((double)lisTS.get(boso)/(songay*27))*100);					
					tanSuatBoSoBean.setSo_lan_ve(lisTS.get(boso));
					
					tanSuatBoSoBean.setSo_ngay_ve(lisNV.get(boso));
					tanSuatBoSoBean.setP_so_ngay_ve(((double)lisNV.get(boso)/songay)*100);
					
					tanSuatBoSoBean.setP_so_lan_ngay(((double)lisTS.get(boso)/lisNV.get(boso))*100);
					tanSuatBoSoBean.setProvince_id(0);
					tanSuatBoSoBean.setBien_ngay(toDate);
					tanSuatBoSoBean.setIs_dacbiet(1);
					tanSuatBoSoDAO.save(tanSuatBoSoBean);
				}
				i++;
			}
			
			
		}
		
	}	
		
}