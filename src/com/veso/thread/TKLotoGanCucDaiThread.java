package com.veso.thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.veso.bean.Feed;
import com.veso.bean.TKChuKyBoSo;
import com.veso.bean.TKLotoGanCucDai;
import com.veso.dao.FeedDAO;
import com.veso.dao.TKChuKiBoSoDAO;
import com.veso.dao.TKLotoGanCucDaiDAO;
import com.veso.util.Logger;
import com.veso.util.StringTool;

public class TKLotoGanCucDaiThread {
	public static final long miliSecondDay = 86400000;
	private static Logger logger = new Logger("TKLotoGanCucDai");
	
	public static void tk() {
		logger.log("TKLotoGanCucDaiThread "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		TKChuKiBoSoDAO chuKiBoSoDAO = new TKChuKiBoSoDAO();
		int so_ngay_tk = 60;
		int province_id = 1;
		
		List<TKChuKyBoSo> listChuKiBoSo = chuKiBoSoDAO.findAllBoSoChuaVe(so_ngay_tk, province_id);
		HashMap<String,TKChuKyBoSo> mapBoSo = chuKiBoSoDAO.findAllChuKyBoSoCucDai(so_ngay_tk, province_id);
		TKLotoGanCucDaiDAO tkLotoGanCucDaiDAO = new TKLotoGanCucDaiDAO();
		
		String pattern = "yyyy-MM-dd";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    String pattern2 = "yyyy-MM-dd hh:mm:ss";
	    SimpleDateFormat format2 = new SimpleDateFormat(pattern2);
	   
	    Feed feed = new Feed();
	    feed.title = "Loto M.Bắc gan tới ngưỡng cực đại: ";
		feed.image = "http://10h.vn/themes/images/xo-so-10h-icon-TIP.png";
		feed.url ="http://10h.vn/thong-ke-vip.html/#gancucdai";
		feed.type =8; //"TK_12BOSORAIT_MB";
		feed.create_user ="thongke";
		
		String bosos="";
		for (TKChuKyBoSo tkChuKyBoSo : listChuKiBoSo) {
			TKChuKyBoSo bosoGanCucDai = mapBoSo.get(tkChuKyBoSo.boso);
			if(bosoGanCucDai!=null&&tkChuKyBoSo.so_ngay_chua_ve >=bosoGanCucDai.length){
				System.out.println("Boso:"+tkChuKyBoSo.boso+" so_ngay_chua_ve:"+tkChuKyBoSo.so_ngay_chua_ve +" GanCD:"+mapBoSo.get(tkChuKyBoSo.boso).length);
				TKLotoGanCucDai lotoGanCucDai = new TKLotoGanCucDai();
				lotoGanCucDai.boso = tkChuKyBoSo.boso;
				lotoGanCucDai.lanquay_cucdai =(int) bosoGanCucDai.length;
				lotoGanCucDai.start_date = format.format(bosoGanCucDai.start_date);
				lotoGanCucDai.end_date = format.format(bosoGanCucDai.end_date);
				lotoGanCucDai.lanquay_chuave = tkChuKyBoSo.so_ngay_chua_ve;
				lotoGanCucDai.ngay_quay = format.format(tkChuKyBoSo.start_date);
				lotoGanCucDai.create_date = format2.format(Calendar.getInstance().getTime());
				lotoGanCucDai.modify_date = format2.format(Calendar.getInstance().getTime());
				lotoGanCucDai.create_user = "thongke";
				lotoGanCucDai.modify_user = "thongke";
				tkLotoGanCucDaiDAO.save(lotoGanCucDai);
				bosos+=tkChuKyBoSo.boso+",";
				
			}else{
				System.out.println("Boso:"+tkChuKyBoSo.boso+" so_ngay_chua_ve:"+tkChuKyBoSo.so_ngay_chua_ve );
			}
		}
		
		FeedDAO feedDao = new FeedDAO();
		if(!StringTool.isEmptyOrNul(bosos)){
			String title = "Loto M.Bắc gan tới ngưỡng cực đại: "+bosos.substring(0,bosos.length()-1);
			feed.title =  title;
			feed.isMobile = 0;
			feedDao.deleteByType(8);
			feedDao.save(feed);
			
			feed.url ="http://m.10h.vn/thong-ke-vip.html";
			feed.isMobile = 1;
			feedDao.save(feed);
		}
				
	}
	
	public static void main(String[] args) {
		TKLotoGanCucDaiThread.tk();
	}
}
