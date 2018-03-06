package com.veso.thread.vtc;

import java.util.List;

import com.veso.bean.BuocCauBean;
import com.veso.bean.Dientoan123;
import com.veso.bean.Dientoan6x63;
import com.veso.bean.DientoanThantai;
import com.veso.bean.KetQuaMienBacBean;
import com.veso.bean.KetQuaMienNamBean;
import com.veso.bean.KetQuaMienTrungBean;
import com.veso.bean.LotoBean;
import com.veso.bean.SoiCauBean;
import com.veso.bean.TKChuKiBoSoVeLienTiep;
import com.veso.bean.TKChuKiBoXien2;
import com.veso.bean.TKChuKiDau;
import com.veso.bean.TKChuKiDauSoVeLienTiep;
import com.veso.bean.TKChuKiDuoi;
import com.veso.bean.TKChuKiDuoiSoVeLienTiep;
import com.veso.bean.TKChuKiTongDB;
import com.veso.bean.TKChuKiTongDBVeLienTiep;
import com.veso.bean.TKChuKyBoSo;
import com.veso.bean.TanSuatBoSoBean;
import com.veso.dao.BuocCauDAO;
import com.veso.dao.KetQuaMienBacDAO;
import com.veso.dao.KetQuaMienNamDAO;
import com.veso.dao.KetQuaMienTrungDAO;
import com.veso.dao.LotoDAO;
import com.veso.dao.SCDDAO;
import com.veso.dao.SoiCauDAO;
import com.veso.dao.TKChuKiBoSoDAO;
import com.veso.dao.TKChuKiBoSoVeLienTiepDAO;
import com.veso.dao.TKChuKiDauDAO;
import com.veso.dao.TKChuKiDauSoVeLienTiepDAO;
import com.veso.dao.TKChuKiDuoiDAO;
import com.veso.dao.TKChuKiDuoiSoVeLienTiepDAO;
import com.veso.dao.TKChuKiLoXien2DAO;
import com.veso.dao.TKChuKiTongDBDAO;
import com.veso.dao.TKChuKiTongVeLienTiepDAO;
import com.veso.dao.TanSuatBoSoDAO;

public class KetquaThread {
	
	public void getKQMB()
	{
		SCDDAO scddao = new SCDDAO();
		KetQuaMienBacDAO ketQuaMienBacDAO = new KetQuaMienBacDAO();
		int kq = scddao.getMaxID("ketqua_mienbac");
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<KetQuaMienBacBean> list = ketQuaMienBacDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (KetQuaMienBacBean ketQuaMienBacBean : list) {
					scddao.saveKQMB(ketQuaMienBacBean);
				}
				
				start =  (int)list.get(list.size()-1).getId();
			}
			
		}
	}
	
	
	public void getKQMN()
	{
		SCDDAO scddao = new SCDDAO();
		KetQuaMienNamDAO ketQuaMienBacDAO = new KetQuaMienNamDAO();
		int kq = scddao.getMaxID("ketqua_miennam");
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<KetQuaMienNamBean> list = ketQuaMienBacDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (KetQuaMienNamBean ketQuaMienBacBean : list) {
					scddao.saveKQMN(ketQuaMienBacBean);
				}
				
				start =  (int)list.get(list.size()-1).getId();
			}
			
		}
	}
	
	
	public void getKQMT()
	{
		System.out.println("Get KQ MT");
		SCDDAO scddao = new SCDDAO();
		KetQuaMienTrungDAO ketQuaMienBacDAO = new KetQuaMienTrungDAO();
		int kq = scddao.getMaxID("ketqua_mientrung");
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<KetQuaMienTrungBean> list = ketQuaMienBacDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (KetQuaMienTrungBean ketQuaMienBacBean : list) {
					scddao.saveKQMT(ketQuaMienBacBean);
				}
				
				start =  (int)list.get(list.size()-1).getId();
			}
			
		}
	}
	
	
	public void getLotoMB()
	{
		SCDDAO scddao = new SCDDAO();
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
		int kq = scddao.getMaxID("thongke_loto_mienbac");
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<LotoBean> list = lotoDAO.getLoto(start,"thongke_loto_mienbac");
			if(list.size()==0) break;
			else{
				for (LotoBean ketQuaMienBacBean : list) {
					scddao.saveLoto(ketQuaMienBacBean,"thongke_loto_mienbac");
				}
				
				start =  (int)list.get(list.size()-1).getId();
			}
			
		}
	}
	
	public void getLotoMN()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="thongke_loto_miennam";
		LotoDAO lotoDAO = new LotoDAO(table);
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<LotoBean> list = lotoDAO.getLoto(start,table);
			if(list.size()==0) break;
			else{
				for (LotoBean ketQuaMienBacBean : list) {
					scddao.saveLoto(ketQuaMienBacBean,table);
				}
				
				start =  (int)list.get(list.size()-1).getId();
			}
			
		}
	}
	
	public void getLotoMT()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="thongke_loto_mientrung";
		LotoDAO lotoDAO = new LotoDAO(table);
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<LotoBean> list = lotoDAO.getLoto(start,table);
			if(list.size()==0) break;
			else{
				for (LotoBean ketQuaMienBacBean : list) {
					scddao.saveLoto(ketQuaMienBacBean,table);
				}
				
				start =  (int)list.get(list.size()-1).getId();
			}
			
		}
	}
	
	
	public void getDientoan123()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="ketqua_dientoan123";
		KetQuaMienBacDAO ketQuaMienBacBean = new KetQuaMienBacDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<Dientoan123> list = ketQuaMienBacBean.findAllDT123(start);
			if(list.size()==0) break;
			else{
				for (Dientoan123 dientoan123 : list) {
					scddao.saveDT123(dientoan123);
				}
				
				start =  (int)list.get(list.size()-1).id;
			}
			
		}
	}
	
	public void getDientoan6x63()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="ketqua_dientoan6x36";
		KetQuaMienBacDAO ketQuaMienBacBean = new KetQuaMienBacDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<Dientoan6x63> list = ketQuaMienBacBean.findAllDT6x63(start);
			if(list.size()==0) break;
			else{
				for (Dientoan6x63 dientoan6x63 : list) {
					System.out.println(dientoan6x63.id);
					scddao.saveDT6x63(dientoan6x63);
				}
				start =  (int)list.get(list.size()-1).id;
			}
		}
	}
	
	public void getDientoanThanTai()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="ketqua_thantai";
		KetQuaMienBacDAO ketQuaMienBacBean = new KetQuaMienBacDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<DientoanThantai> list = ketQuaMienBacBean.findAllDTThanTai(start);
			if(list.size()==0) break;
			else{
				for (DientoanThantai dientoanThantai : list) {
					System.out.println(dientoanThantai.id);
					scddao.saveDTThanTai(dientoanThantai);
				}
				start =  (int)list.get(list.size()-1).id;
			}
		}
	}
	
	
	public void getSoiCau()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="soicau";
		SoiCauDAO soiCauDAO = new SoiCauDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		
		while(start<Integer.MAX_VALUE)
		{
			List<SoiCauBean> list = soiCauDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (SoiCauBean soiCauBean : list) {
					System.out.println(soiCauBean.getId());
					scddao.saveCau(soiCauBean);
				}
				start =  (int)list.get(list.size()-1).getId();
			}
		}
		
		
		BuocCauDAO buocCauDAO = new BuocCauDAO();
		start = scddao.getMaxID("soicau_buoccau");
		while(start<Integer.MAX_VALUE)
		{
			List<BuocCauBean> list = buocCauDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (BuocCauBean bean : list) {
					System.out.println(bean.getId());
					scddao.save(bean);
				}
				start =  (int)list.get(list.size()-1).getId();
			}
		}
	}
	
	
	public void getChuKiBosoVelientiep()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="thongke_boso_ve_lientiep";
		TKChuKiBoSoVeLienTiepDAO boSoVeLienTiepDAO = new TKChuKiBoSoVeLienTiepDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<TKChuKiBoSoVeLienTiep> list = boSoVeLienTiepDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (TKChuKiBoSoVeLienTiep boSoVeLienTiep : list) {
					System.out.println(boSoVeLienTiep.id);
					scddao.saveBosoVeLienTiep(boSoVeLienTiep);
				}
				start =  (int)list.get(list.size()-1).id;
			}
		}
	}
	
	public void getChuKiBoso()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="thongke_chuky_boso";
		TKChuKiBoSoDAO boSoDAO = new TKChuKiBoSoDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<TKChuKyBoSo> list = boSoDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (TKChuKyBoSo boSo : list) {
					System.out.println(boSo.id);
					scddao.save(boSo);
				}
				start =  (int)list.get(list.size()-1).id;
			}
		}
	}
	
	public void getChuKiBosoXien2()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="thongke_chuky_boso_xien2";
		TKChuKiLoXien2DAO boSoDAO = new TKChuKiLoXien2DAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<TKChuKiBoXien2> list = boSoDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (TKChuKiBoXien2 boSo : list) {
					System.out.println(boSo.id);
					scddao.save(boSo);
				}
				start =  (int)list.get(list.size()-1).id;
			}
		}
	}
	
	
	public void getChuKiDau()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="thongke_chuky_dau";
		TKChuKiDauDAO boSoDAO = new TKChuKiDauDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<TKChuKiDau> list = boSoDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (TKChuKiDau boSo : list) {
					System.out.println(boSo.id);
					scddao.save(boSo);
				}
				start =  (int)list.get(list.size()-1).id;
			}
		}
	}
	
	
	public void getChuKiDuoi()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="thongke_chuky_duoi";
		TKChuKiDuoiDAO boSoDAO = new TKChuKiDuoiDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<TKChuKiDuoi> list = boSoDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (TKChuKiDuoi boSo : list) {
					System.out.println(boSo.id);
					scddao.save(boSo);
				}
				start =  (int)list.get(list.size()-1).id;
			}
		}
	}
	
	public void getChukiTong()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="thongke_chuky_tong_db";
		TKChuKiTongDBDAO boSoDAO = new TKChuKiTongDBDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<TKChuKiTongDB> list = boSoDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (TKChuKiTongDB boSo : list) {
					System.out.println(boSo.id);
					scddao.save(boSo);
				}
				start =  (int)list.get(list.size()-1).id;
			}
		}
	}
	
	public void getDausovelientiep()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="thongke_dau_ve_lientiep";
		TKChuKiDauSoVeLienTiepDAO boSoDAO = new TKChuKiDauSoVeLienTiepDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<TKChuKiDauSoVeLienTiep> list = boSoDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (TKChuKiDauSoVeLienTiep boSo : list) {
					System.out.println(boSo.id);
					scddao.save(boSo);
				}
				start =  (int)list.get(list.size()-1).id;
			}
		}
	}
	
	
	public void getDuoisovelientiep()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="thongke_duoi_ve_lientiep";
		TKChuKiDuoiSoVeLienTiepDAO boSoDAO = new TKChuKiDuoiSoVeLienTiepDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<TKChuKiDuoiSoVeLienTiep> list = boSoDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (TKChuKiDuoiSoVeLienTiep boSo : list) {
					System.out.println(boSo.id);
					scddao.save(boSo);
				}
				start =  (int)list.get(list.size()-1).id;
			}
		}
	}
	
	
	public void getTongvelientiep()
	{
		SCDDAO scddao = new SCDDAO();
		String table ="thongke_tong_db_ve_lientiep";
		TKChuKiTongVeLienTiepDAO boSoDAO = new TKChuKiTongVeLienTiepDAO();
		int kq = scddao.getMaxID(table);
		int start = kq;
		while(start<Integer.MAX_VALUE)
		{
			List<TKChuKiTongDBVeLienTiep> list = boSoDAO.findAll(start);
			if(list.size()==0) break;
			else{
				for (TKChuKiTongDBVeLienTiep boSo : list) {
					System.out.println(boSo.id);
					scddao.save(boSo);
				}
				start =  (int)list.get(list.size()-1).id;
			}
		}
	}
	
	
	public void getTanSuatLoto(String table)
	{
		SCDDAO scddao = new SCDDAO();		
		TanSuatBoSoDAO boSoDAO = new TanSuatBoSoDAO(table);
		int start = scddao.getMaxID(table);
		while(start<Integer.MAX_VALUE)
		{
			List<TanSuatBoSoBean> list = boSoDAO.findAll(start,table);
			if(list.size()==0) break;
			else{
				for (TanSuatBoSoBean boSo : list) {
					System.out.println(boSo.getId());
					scddao.save(boSo,table);
				}
				start =  (int)list.get(list.size()-1).getId();
			}
		}
	}
	

	
	
	
	
	public static void main(String[] args) {
		KetquaThread ketquaThread = new KetquaThread();
		
		ketquaThread.getKQMB();
		ketquaThread.getLotoMB();
		
		ketquaThread.getKQMT();
		ketquaThread.getLotoMT();
	
		ketquaThread.getKQMN();
		ketquaThread.getLotoMN();
		
		ketquaThread.getDientoan6x63();
		ketquaThread.getDientoanThanTai();
		ketquaThread.getDientoan123();
		
		ketquaThread.getSoiCau();
		ketquaThread.getChuKiBosoVelientiep();
		ketquaThread.getChuKiBoso();
		ketquaThread.getChuKiBosoXien2();
		ketquaThread.getChuKiDau();
		ketquaThread.getChuKiDuoi();
		ketquaThread.getChukiTong();
		ketquaThread.getDausovelientiep();
		ketquaThread.getDuoisovelientiep();
		ketquaThread.getTongvelientiep();
		ketquaThread.getTanSuatLoto("thongke_tansuat_loto_mienbac");
		ketquaThread.getTanSuatLoto("thongke_tansuat_loto_miennam");
		ketquaThread.getTanSuatLoto("thongke_tansuat_loto_mientrung");
		
	}
}
