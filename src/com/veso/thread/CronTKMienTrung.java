package com.veso.thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.veso.dao.LotoDAO;
import com.veso.util.Logger;

public class CronTKMienTrung {
	private static Logger logger = new Logger("CronTKMienTrung");
	
	public static void main(String[] args) {
	//public static void main1(Calendar calendar) {
		// Time 18:35:00
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:CronTKMienTrung");
		
		Calendar calendar =Calendar.getInstance();	
		
		/*calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 10);
		calendar.set(Calendar.DAY_OF_MONTH, 18);*/
		
		long startDay = calendar.getTimeInMillis();

		java.sql.Date sqlDate = new java.sql.Date(startDay);
		
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mientrung");
		if(lotoDAO.checkLotoMienTrungByNgayQuay(sqlDate))return;
			
		LotoMienTrungThread.tkLotoMienTrung(sqlDate);
		
		// Tk chu ki bo so 
		
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoSoMTThread");
		TKChuKiBoSoMTThread.tk(startDay);
		
		// TK CHu ki bo so dac biet
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoSoDacBietMTThread");
		TKChuKiBoSoDacBietMTThread.tk(startDay);
		
		// TK CHu ki dau so
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDauSoMTThread");
		TKChuKiDauSoMTThread.tk(startDay);
		
		
		// TK Chu ki duoi so
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDuoiSoMTThread");
		TKChuKiDuoiSoMTThread.tk(startDay);
		
		// TK Chu ki tong dac biet 
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiTongDBMTThread");
		TKChuKiTongDBMTThread.tk(startDay);
		
		// TK Chu ki bo so lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoSoLienTiepMTThread");
		TKChuKiBoSoLienTiepMTThread.tk(startDay);
		
		// TK chu ki dau so ve lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDauSoVeLienTiepMTThread");
		TKChuKiDauSoVeLienTiepMTThread.tk(startDay);
		
		// TK chu ki duoi so ve lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDuoiSoVeLienTiepMTThread");
		TKChuKiDuoiSoVeLienTiepMTThread.tk(startDay);
		
		// Tk chu ki tong db ve lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiTongDBVeLienTiepMTThread");
		TKChuKiTongDBVeLienTiepMTThread.tk(startDay);
		
		ChotSoThread chotSoThread = new ChotSoThread();
		chotSoThread.chotMienTrung();
		// Tk chu ki bo so xien 2
		//logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoXien2MTThread");
		//TKChuKiBoXien2MTThread.tk(startDay);
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
			CronTKMienTrung.main1(calendar);
			//System.out.println(calendar.toString());
		}
	}*/
}
