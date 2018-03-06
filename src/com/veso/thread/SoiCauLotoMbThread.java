package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;

import com.veso.bean.BuocCauBean;
import com.veso.bean.DaySoBean;
import com.veso.bean.SoiCauBean;
import com.veso.dao.BuocCauDAO;
import com.veso.dao.KetQuaMienBacDAO;
import com.veso.dao.LotoDAO;
import com.veso.dao.SoiCauDAO;
import com.veso.util.DateProc;
import com.veso.util.Logger;

public class SoiCauLotoMbThread {
	private static Logger logger = new Logger("SoiCauLotoMbThread");
	public final static long miliSecondDay = 86400000;
		
	public boolean createBuocCauMienBac(String boso,int x, int y, long bien_ngay, int buoc,int loaicau, int songay,BuocCauBean buocCauBean)
	{
		boolean kq = false;
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
		KetQuaMienBacDAO ketQuaMienBacDAO = new KetQuaMienBacDAO();		
		Date open_date = new Date(bien_ngay+(buoc-1)*miliSecondDay*loaicau);
		Integer[] dayso = null;
		DaySoBean daySoBean = null;
		
		if(lotoDAO.checkLotoMienBac(open_date, boso)>0)
		{
			if(songay==buoc)
			{
				daySoBean = ketQuaMienBacDAO.getDaySo(open_date);
				dayso = daySoBean.getDayso();
				boso = String.valueOf(dayso[x]+""+dayso[y]);
				setBuocCau(buocCauBean,buoc,boso,daySoBean.getKequa_id());
				kq = true;
			}	
			if(buoc<songay)
			{
				daySoBean = ketQuaMienBacDAO.getDaySo(open_date);
				dayso = daySoBean.getDayso();
				boso = String.valueOf(dayso[x]+""+dayso[y]);
				setBuocCau(buocCauBean,buoc,boso,daySoBean.getKequa_id());
				buoc +=1; 
				kq = createBuocCauMienBac(boso,x,y,bien_ngay,buoc,loaicau,songay,buocCauBean) ;
			}
		}
		
		return kq;
		
		// Lay list loto
		// check boso exits in list loto
		// if kq = false return false
		// else if(songay=buocthu) return true 
		// else if(buocthu<songay)
		// boso = getBoso(province_id,open_date);
		//call checkBuocCau(boso,buocthu+1,loaicau,songay) 
		
	}
	
	private void setBuocCau(BuocCauBean buocCauBean,int buoc, String boso, long kequa_id)
	{
		try
		{
			switch (buoc)
			{
				case 1:
					buocCauBean.setB1_dau(Integer.parseInt(boso.substring(0,1)));
					buocCauBean.setB1_dit(Integer.parseInt(boso.substring(1)));
					buocCauBean.setB1_id(kequa_id);
				break;
				case 2:
					buocCauBean.setB2_dau(Integer.parseInt(boso.substring(0,1)));
					buocCauBean.setB2_dit(Integer.parseInt(boso.substring(1)));
					buocCauBean.setB2_id(kequa_id);
					break;
				case 3:
					buocCauBean.setB3_dau(Integer.parseInt(boso.substring(0,1)));
					buocCauBean.setB3_dit(Integer.parseInt(boso.substring(1)));
					buocCauBean.setB3_id(kequa_id);
					break;
				case 4:
					buocCauBean.setB4_dau(Integer.parseInt(boso.substring(0,1)));
					buocCauBean.setB4_dit(Integer.parseInt(boso.substring(1)));
					buocCauBean.setB4_id(kequa_id);
					break;
				case 5:
					buocCauBean.setB5_dau(Integer.parseInt(boso.substring(0,1)));
					buocCauBean.setB5_dit(Integer.parseInt(boso.substring(1)));
					buocCauBean.setB5_id(kequa_id);
					break;
				case 6:
					buocCauBean.setB6_dau(Integer.parseInt(boso.substring(0,1)));
					buocCauBean.setB6_dit(Integer.parseInt(boso.substring(1)));
					buocCauBean.setB6_id(kequa_id);
					break;
				case 7:
					buocCauBean.setB7_dau(Integer.parseInt(boso.substring(0,1)));
					buocCauBean.setB7_dit(Integer.parseInt(boso.substring(1)));
					buocCauBean.setB7_id(kequa_id);
					break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String getBoSo(BuocCauBean buocCauBean,int so_ngay)
	{
		String boso="";
		switch (so_ngay)
		{
			case 1:
				boso = buocCauBean.getB1_dau()+""+buocCauBean.getB1_dit();
			break;
			case 2:
				boso = buocCauBean.getB2_dau()+""+buocCauBean.getB2_dit();
				break;
			case 3:
				boso = buocCauBean.getB3_dau()+""+buocCauBean.getB3_dit();
				break;
			case 4:
				boso = buocCauBean.getB4_dau()+""+buocCauBean.getB4_dit();
				break;
			case 5:
				boso = buocCauBean.getB5_dau()+""+buocCauBean.getB5_dit();
				break;
			case 6:
				boso = buocCauBean.getB6_dau()+""+buocCauBean.getB6_dit();
				break;
			case 7:
				boso = buocCauBean.getB7_dau()+""+buocCauBean.getB7_dit();
				break;
		}
		return boso;
	}
	
	
	public static void main(String[] args) {
		logger.log("Soi cau Loto Mien Bac ngay "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		KetQuaMienBacDAO ketQuaMienBacDAO = new KetQuaMienBacDAO();
		SoiCauDAO soiCauDAO = new SoiCauDAO();
		BuocCauDAO buocCauDAO = new BuocCauDAO();		
		// Tao cau
		SoiCauLotoMbThread soiCauHaiNgayThread = new SoiCauLotoMbThread();
		BuocCauBean buocCauBean = null;
		SoiCauBean soiCauBean = null;
		DaySoBean daySoBean = null;
		int i = 0,j=0;
		String boso="";
		
		boolean kq = false;
		int[] so_ngay = {2,3,4,5,6,7,8}; // so ngay chay cau
		//int[] locai_cau = {1,2,3,4};
		int[] locai_cau = {1}; // cach buoc cau
		int k=0;int q=0;
		Calendar calendar = Calendar.getInstance();
		long bien_ngay_tren = calendar.getTimeInMillis();
		// Tao cau qua so_ngay 
		while (k<so_ngay.length)
		{
			logger.log("---->So ngay = "+so_ngay[k]);
			soiCauBean =  new SoiCauBean();
			soiCauBean.setBien_do_ngay(so_ngay[k]);
			soiCauBean.setBien_ngay(new Date(calendar.getTimeInMillis()));
			soiCauBean.setCreate_date(DateProc.createTimestamp());
			soiCauBean.setProvince_id(1);
			soiCauBean.setType(1);// soi cau loto
			long cau_id =soiCauDAO.saveCau(soiCauBean);
			// Tinh Bien ngay duoi
			q=0;
			while(q<locai_cau.length)
			{
					logger.log("-------->Loai cau = "+locai_cau[q]);							
					//long bien_ngay_duoi = bien_ngay_tren - ( (locai_cau[q]-1)+((so_ngay[k]-1)*locai_cau[q] ))*miliSecondDay ;
					long bien_ngay_duoi = bien_ngay_tren - (so_ngay[k])*miliSecondDay ;
					Date bienduoi = new Date(bien_ngay_duoi);
					logger.log("-------->Bien Duoi = "+bienduoi.toString());	
					daySoBean = ketQuaMienBacDAO.getDaySo(new Date(bien_ngay_duoi));
					i=0;
					if(daySoBean!=null&&daySoBean.getDayso()!=null)
					while(i<daySoBean.getDayso().length)
					{
						boso = "";
						for(j=i+1;j<daySoBean.getDayso().length;j++)
						{
							if(daySoBean.getDayso()[j]!=null)
							{
								try
								{
									boso = String.valueOf(daySoBean.getDayso()[i])+String.valueOf(daySoBean.getDayso()[j]);
									buocCauBean = new BuocCauBean();
									buocCauBean.setB1_id(daySoBean.getKequa_id());
									buocCauBean.setB1_dau(Integer.parseInt(boso.substring(0,1)));
									buocCauBean.setB1_dit(Integer.parseInt(boso.substring(1)));
									buocCauBean.setX(i);
									buocCauBean.setY(j);
									buocCauBean.setCreate_date(DateProc.createTimestamp());
									buocCauBean.setCau_id(cau_id);
									kq=soiCauHaiNgayThread.createBuocCauMienBac(boso, i, j, bien_ngay_duoi, 2, locai_cau[q], so_ngay[k], buocCauBean);
									if(kq)
									{
									//	logger.log("------------>Tao cau boso " + boso+" Vi chi xy = "+i+""+"j");		
										buocCauBean.setKieu_cau(locai_cau[q]);
										buocCauBean.setBoso(Integer.parseInt(soiCauHaiNgayThread.getBoSo(buocCauBean, so_ngay[k])));
										buocCauDAO.save(buocCauBean);
										soiCauDAO.updateBoso(cau_id, "bs_"+soiCauHaiNgayThread.getBoSo(buocCauBean, so_ngay[k]));
									}
									}catch (Exception e) {
										e.printStackTrace();
									}
							}
						}
						
						i++;
					}
					q++;
			}
			k++;
		}
		
	}
	
	
}
