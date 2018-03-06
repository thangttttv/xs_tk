package com.veso.thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.veso.bean.Feed;
import com.veso.bean.TKChuKyBoSo;
import com.veso.bean.TKLotoDenKy;
import com.veso.dao.FeedDAO;
import com.veso.dao.TKChuKiBoSoDAO;
import com.veso.dao.TKLotoDenKyDAO;
import com.veso.util.Logger;
import com.veso.util.StringTool;

public class TKLotoToiChuKiSoVoiKiMoiThread {
	public static final long miliSecondDay = 86400000;
	private static Logger logger = new Logger("TKLotoToChuKiSoVoiKiMoiThread");
	
	public static void tk() {
		
		logger.log("TKLotoToChuKiSoVoiKiMoiThread "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		TKChuKiBoSoDAO chuKiBoSoDAO = new TKChuKiBoSoDAO();
		int so_ngay_tk = 60;
		int province_id = 1;
		
		List<TKChuKyBoSo> listChuKiBoSo = chuKiBoSoDAO.findAllBoSoChuaVe(so_ngay_tk, province_id);
		HashMap<String,TKChuKyBoSo> mapBoSo = chuKiBoSoDAO.findAllChuKyBoSoMoiNhat(so_ngay_tk, province_id);
		TKLotoDenKyDAO tkLotoDenKyDAO = new TKLotoDenKyDAO();
		
		String pattern = "yyyy-MM-dd";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    String pattern2 = "yyyy-MM-dd hh:mm:ss";
	    SimpleDateFormat format2 = new SimpleDateFormat(pattern2);
	   
	    Feed feed = new Feed();
	    feed.title = "Loto Miền Bắc tới kỳ so với kỳ mới nhất: ";
		feed.image = "http://10h.vn/themes/images/xo-so-10h-icon-TIP.png";
		feed.url ="http://10h.vn/thong-ke-vip.html#kygannhat";
		feed.type =9; //"TK_12BOSORAIT_MB";
		feed.create_user ="thongke";
		String bosos="";
		for (TKChuKyBoSo tkChuKyBoSo : listChuKiBoSo) {
			TKChuKyBoSo bosoChukiMoi = mapBoSo.get(tkChuKyBoSo.boso);
			
			if(bosoChukiMoi!=null&&tkChuKyBoSo.so_ngay_chua_ve ==bosoChukiMoi.length){
				
				System.out.println("Boso:"+tkChuKyBoSo.boso+" so_ngay_chua_ve:"+tkChuKyBoSo.so_ngay_chua_ve +" Do Dai Chu ki moi:"+mapBoSo.get(tkChuKyBoSo.boso).length);
				
				TKLotoDenKy lotoDenKy = new TKLotoDenKy();
				lotoDenKy.boso = tkChuKyBoSo.boso;
				
				lotoDenKy.dodai_chuky =(int) bosoChukiMoi.length;
				lotoDenKy.start_date = format.format(bosoChukiMoi.start_date);
				lotoDenKy.end_date = format.format(bosoChukiMoi.end_date);
				
				lotoDenKy.create_date = format2.format(Calendar.getInstance().getTime());
				lotoDenKy.modify_date = format2.format(Calendar.getInstance().getTime());
				lotoDenKy.create_user = "thongke";
				lotoDenKy.modify_user = "thongke";
				lotoDenKy.type = 0;
				tkLotoDenKyDAO.save(lotoDenKy);
				bosos+=tkChuKyBoSo.boso+",";
				
			}else{
				System.out.println("Boso:"+tkChuKyBoSo.boso+" so_ngay_chua_ve:"+tkChuKyBoSo.so_ngay_chua_ve );
			}
		}
		
		FeedDAO feedDao = new FeedDAO();
		if(!StringTool.isEmptyOrNul(bosos)){
			String title = "Loto M.Bắc tới kỳ so với kỳ mới nhất: "+bosos.substring(0,bosos.length()-1);
			feed.title =  title;
			feed.isMobile = 0;
			feedDao.deleteByType(9);
			feedDao.save(feed);
			
			feed.url ="http://m.10h.vn/thong-ke-vip.html";
			feed.isMobile = 1;
			feedDao.save(feed);
		}
	}
	
	public static void main(String[] args) {
		TKLotoToiChuKiSoVoiKiMoiThread.tk();
	}
}
