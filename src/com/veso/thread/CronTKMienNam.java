package com.veso.thread;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.veso.dao.LotoDAO;
import com.veso.util.Logger;

public class CronTKMienNam {
	private static Logger logger = new Logger("CronTKMienNam");
	
	public static void main(String[] args) {
	//public static void main1(Calendar calendar) {
		// Time 18:35:00
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:CronTKMienNam");
		
		Calendar calendar =Calendar.getInstance();	
		
		/*calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 10);
		calendar.set(Calendar.DAY_OF_MONTH, 19);*/
		
		long startDay = calendar.getTimeInMillis();

		java.sql.Date sqlDate = new java.sql.Date(startDay);
		
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_miennam");
		if(lotoDAO.checkLotoMienNamByNgayQuay(sqlDate))return;
			
		LotoMienNamThread.tkLotoMienNam(sqlDate);
		
		// Tk chu ki bo so 
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoSoMNThread");
		TKChuKiBoSoMNThread.tk(startDay);
		
		// TK CHu ki bo so dac biet
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoSoDacBietMNThread");
		TKChuKiBoSoDacBietMNThread.tk(startDay);
		
		// TK CHu ki dau so
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDauSoMNThread");
		TKChuKiDauSoMNThread.tk(startDay);
		
		
		// TK Chu ki duoi so
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDuoiSoMNThread");
		TKChuKiDuoiSoMNThread.tk(startDay);
		
		// TK Chu ki tong dac biet 
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiTongDBMNThread");
		TKChuKiTongDBMNThread.tk(startDay);
		
		// TK Chu ki bo so lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoSoLienTiepMNThread");
		TKChuKiBoSoLienTiepMNThread.tk(startDay);
		
		// TK chu ki dau so ve lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDauSoVeLienTiepMNThread");
		TKChuKiDauSoVeLienTiepMNThread.tk(startDay);
		
		// TK chu ki duoi so ve lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDuoiSoVeLienTiepMNThread");
		TKChuKiDuoiSoVeLienTiepMNThread.tk(startDay);
		
		// Tk chu ki tong db ve lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiTongDBVeLienTiepMNThread");
		TKChuKiTongDBVeLienTiepMNThread.tk(startDay);
		
		ChotSoThread chotSoThread = new ChotSoThread();
		chotSoThread.chotMienNam();
		
		// Tk chu ki bo so xien 2
		//logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoXien2MNThread");
		//TKChuKiBoXien2MNThread.tk(startDay);
	}
	
	/*public static void main(String[] args) {
		Calendar calendar =Calendar.getInstance();	
		
		calendar.set(Calendar.YEAR, 2018);
		calendar.set(Calendar.MONTH, 00);
		calendar.set(Calendar.DAY_OF_MONTH, 16);
		
		Calendar calendar2 =Calendar.getInstance();	
		
		calendar2.set(Calendar.YEAR, 2018);
		calendar2.set(Calendar.MONTH, 00);
		calendar2.set(Calendar.DAY_OF_MONTH, 31);
		
		while(calendar.before(calendar2)){
			long time = calendar.getTimeInMillis()+24*60*60*1000;
			calendar.setTimeInMillis(time);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
			String date = sdf.format(calendar.getTime());
			System.out.println(date); //15/10/2013
			CronTKMienNam.main1(calendar);
			//System.out.println(calendar.toString());
		}
		
		
		
	}*/
}
