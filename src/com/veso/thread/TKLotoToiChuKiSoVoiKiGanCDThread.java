package com.veso.thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.veso.bean.TKChuKyBoSo;
import com.veso.bean.TKLotoDenKy;
import com.veso.dao.TKChuKiBoSoDAO;
import com.veso.dao.TKLotoDenKyDAO;
import com.veso.util.Logger;

public class TKLotoToiChuKiSoVoiKiGanCDThread {
	public static final long miliSecondDay = 86400000;
	private static Logger logger = new Logger("TKLotoToChuKiSoVoiKiMoiThread");
	
	public static void tk() {
		logger.log("TKLotoToChuKiSoVoiKiGanCDThread "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		TKChuKiBoSoDAO chuKiBoSoDAO = new TKChuKiBoSoDAO();
		int so_ngay_tk = 60;
		int province_id = 1;
		
		List<TKChuKyBoSo> listChuKiBoSo = chuKiBoSoDAO.findAllBoSoChuaVe(so_ngay_tk, province_id);
		HashMap<String,TKChuKyBoSo> mapBoSo = chuKiBoSoDAO.findAllChuKyBoSoCucDai(so_ngay_tk, province_id);
		TKLotoDenKyDAO tkLotoDenKyDAO = new TKLotoDenKyDAO();
		
		String pattern = "yyyy-MM-dd";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    String pattern2 = "yyyy-MM-dd hh:mm:ss";
	    SimpleDateFormat format2 = new SimpleDateFormat(pattern2);
	   
		
		for (TKChuKyBoSo tkChuKyBoSo : listChuKiBoSo) {
			TKChuKyBoSo bosoChukiMoi = mapBoSo.get(tkChuKyBoSo.boso);
			
			if(bosoChukiMoi!=null&&tkChuKyBoSo.so_ngay_chua_ve >=bosoChukiMoi.length){
				
				System.out.println("Boso:"+tkChuKyBoSo.boso+" so_ngay_chua_ve:"+tkChuKyBoSo.so_ngay_chua_ve +" Do Dai Chu ki gan cuc dai:"+mapBoSo.get(tkChuKyBoSo.boso).length);
				
				TKLotoDenKy lotoDenKy = new TKLotoDenKy();
				lotoDenKy.boso = tkChuKyBoSo.boso;
				
				lotoDenKy.dodai_chuky =(int) bosoChukiMoi.length;
				lotoDenKy.start_date = format.format(bosoChukiMoi.start_date);
				lotoDenKy.end_date = format.format(bosoChukiMoi.end_date);
				
				lotoDenKy.create_date = format2.format(Calendar.getInstance().getTime());
				lotoDenKy.modify_date = format2.format(Calendar.getInstance().getTime());
				lotoDenKy.create_user = "thongke";
				lotoDenKy.modify_user = "thongke";
				lotoDenKy.type = 1;
				tkLotoDenKyDAO.save(lotoDenKy);
				
			}else{
				System.out.println("Boso:"+tkChuKyBoSo.boso+" so_ngay_chua_ve:"+tkChuKyBoSo.so_ngay_chua_ve );
			}
		}
	}
	
	public static void main(String[] args) {
		TKLotoToiChuKiSoVoiKiGanCDThread.tk();
	}
}
