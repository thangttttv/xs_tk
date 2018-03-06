package com.veso.thread;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.veso.bean.Feed;
import com.veso.dao.FeedDAO;
import com.veso.dao.LotoDAO;
import com.veso.util.Logger;

public class CronTKMienBac {
	private static Logger logger = new Logger("CronTKMienBac");
	
	public static void main(String[] args) {
	//	public static void main1(Calendar calendar) {	
		// Time 18:35:00
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:CronTKMienBac");
		
		Calendar calendar =Calendar.getInstance();	
		
		/*calendar.set(Calendar.YEAR, 2018);
		calendar.set(Calendar.MONTH, 00);
		calendar.set(Calendar.DAY_OF_MONTH, 19);*/
		
		long startDay = calendar.getTimeInMillis();	
		Date  openDate = new Date(startDay);
		
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
		if(lotoDAO.checkLotoMienBacByNgayQuay(openDate)) return;
		
		LotoMienBacThread.tkLotoMienBac(openDate);
		
		// Tk chu ki bo so 
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoSoMBThread");
		TKChuKiBoSoMBThread boSoMBThread = new TKChuKiBoSoMBThread();
		boSoMBThread.setProvince_id(1);
		boSoMBThread.createChuKyBoSo(openDate);	
		
		// TK CHu ki bo so dac biet
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoSoDacBietMBThread");
		TKChuKiBoSoDacBietMBThread boSoDacBietMBThread = new TKChuKiBoSoDacBietMBThread();
		boSoDacBietMBThread.setProvince_id(1);
		boSoDacBietMBThread.createChuKyBoSo(openDate);
		
		// TK CHu ki dau so
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDauSoMBThread");
		TKChuKiDauSoMBThread chuKiDauMBThread = new TKChuKiDauSoMBThread();
		chuKiDauMBThread.setProvince_id(1);
		chuKiDauMBThread.createChuKyDauSo(openDate);
		
		// TK Chu ki duoi so
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDuoiSoMBThread");
		TKChuKiDuoiSoMBThread chuKiDuoiSoMBThread = new TKChuKiDuoiSoMBThread();
		chuKiDuoiSoMBThread.setProvince_id(1);
		chuKiDuoiSoMBThread.createChuKyDuoiSo(openDate);
		
		// TK Chu ki tong dac biet 
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiTongDBMBThread");
		TKChuKiTongDBMBThread chuKiTongDBMBThread = new TKChuKiTongDBMBThread();
		chuKiTongDBMBThread.setProvince_id(1);
		chuKiTongDBMBThread.createChuKyTongDB(openDate);
		
		// TK Chu ki bo so lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoSoLienTiepMBThread");
		TKChuKiBoSoLienTiepMBThread chuKiBoSoLienTiepMBThread = new TKChuKiBoSoLienTiepMBThread();
		chuKiBoSoLienTiepMBThread.setProvice_id(1);
		chuKiBoSoLienTiepMBThread.createChuKyBoSoLienTiep(openDate);
		
		// TK chu ki dau so ve lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDauSoVeLienTiepMBThread");
		TKChuKiDauSoVeLienTiepMBThread chuKiDauSoVeLienTiepMBThread = new TKChuKiDauSoVeLienTiepMBThread();
		chuKiDauSoVeLienTiepMBThread.setProvince_id(1);
		chuKiDauSoVeLienTiepMBThread.createChuKyDauSoVeLienTiep(openDate);
		
		// TK chu ki duoi so ve lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiDuoiSoVeLienTiepMBThread");
		TKChuKiDuoiSoVeLienTiepMBThread chuKiDuoiSoVeLienTiepMBThread = new TKChuKiDuoiSoVeLienTiepMBThread();
		chuKiDuoiSoVeLienTiepMBThread.setProvince_id(1);
		chuKiDuoiSoVeLienTiepMBThread.createChuKyDuoiSoVeLienTiep(openDate);
		
		// Tk chu ki tong db ve lien tiep
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiTongDBVeLienTiepMBThread");
		TKChuKiTongDBVeLienTiepMBThread chuKiTongDBVeLienTiepMBThread = new TKChuKiTongDBVeLienTiepMBThread();
		chuKiTongDBVeLienTiepMBThread.setProvince_id(1);		
		chuKiTongDBVeLienTiepMBThread.createChuKyTongDBVeLienTiep(openDate);
		
		// TK VIP
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKLotoGanCucDaiThread");
		TKLotoGanCucDaiThread.tk();
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKLotoToiChuKiSoVoiKiGanCDThread");
		TKLotoToiChuKiSoVoiKiGanCDThread.tk();
		logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKLotoToiChuKiSoVoiKiMoiThread");
		TKLotoToiChuKiSoVoiKiMoiThread.tk();
		
		ChotSoThread chotSoThread = new ChotSoThread();
		chotSoThread.chotMienBac();
		
		// Feed
		//FeedDAO feedDAO = new FeedDAO();
		/*feedDAO.createNewFeed12BoSoMBRaItMB();
		feedDAO.createNewFeed12BoSoMBRaNhieuMB();
		feedDAO.createNewFeedLotoGan10NgayMB();
		feedDAO.createNewFeedLotoRoiMienBac();*/
		
		//ArrayList<Feed> feeds = feedDAO.getNewFeed(0);
		//feedDAO.createFileFeed(feeds);
		
		//feeds = feedDAO.getNewFeed(1);
		//feedDAO.createFileFeedMobile(feeds);
		
		// Tk chu ki bo so xien 2
		/*logger.log(Calendar.getInstance().getTime().toString()+"->Start:TKChuKiBoXien2MBThread");
		TKChuKiBoXien2MBThread chuKiBoXien2MBThread = new TKChuKiBoXien2MBThread();
		chuKiBoXien2MBThread.setProvince_id(1);
		chuKiBoXien2MBThread.createChuKyBoSoXien2(openDate);*/
	}
	
	/*public static void main(String[] args) {
		Calendar calendar =Calendar.getInstance();	
		
		calendar.set(Calendar.YEAR, 2018);
		calendar.set(Calendar.MONTH, 00);
		calendar.set(Calendar.DAY_OF_MONTH, 19);
		
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
			CronTKMienBac.main1(calendar);
			//System.out.println(calendar.toString());
		}
		
		
		
	}*/
}
