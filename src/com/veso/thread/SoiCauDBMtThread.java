package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.BuocCauBean;
import com.veso.bean.DaySoBean;
import com.veso.bean.ProvinceBean;
import com.veso.bean.SoiCauBean;
import com.veso.dao.BuocCauDAO;
import com.veso.dao.KetQuaMienTrungDAO;
import com.veso.dao.LotoDAO;
import com.veso.dao.ProvinceDAO;
import com.veso.dao.SoiCauDAO;
import com.veso.util.DateProc;
import com.veso.util.Logger;

public class SoiCauDBMtThread {
	
	public final static long miliSecondDay = 86400000;
	public final static String danang ="dng";
	private static Logger logger = new Logger("SoiCauDBMtThread");
	
	public boolean createBuocCauMienTrung(String boso,int x, int y, long bien_ngay, int buoc,int loaicau, int songay,BuocCauBean buocCauBean,int province_id,String province_code)
	{
		boolean kq = false;
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mientrung");
		KetQuaMienTrungDAO ketQuaMienTrungDAO = new KetQuaMienTrungDAO();	
		int kc_ngay_quay = 7;	
		Date open_date = null;
		int kc_5=0;
		int kc_2=0;
		if(danang.equalsIgnoreCase(province_code))
		{
			Date bien_date = new Date(bien_ngay);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(bien_date);
			if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY)
			{
				
				kc_5 = (loaicau +1)/2*3 + loaicau/2*4; 
				kc_2 = (loaicau +1)/2*4 + loaicau/2*3; 
				kc_ngay_quay = (buoc/2)*kc_5 + (buoc-1)/2*kc_2;
			}
			if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
			{
			
				kc_5 = (loaicau +1)/2*3 + loaicau/2*3; 
				kc_2 = (loaicau +1)/2*4 + loaicau/2*4; 
				kc_ngay_quay = (buoc/2)*kc_2 + (buoc-1)/2*kc_5;
			
			}
			open_date = new Date(bien_ngay+miliSecondDay*kc_ngay_quay);
		}else
		{	
			open_date = new Date(bien_ngay+(buoc-1)*miliSecondDay*loaicau*kc_ngay_quay);
		}
		Integer[] dayso = null;
		DaySoBean daySoBean = null;
		
		if(lotoDAO.checkDeMienTrung(open_date, boso,province_id))
		{
			if(songay==buoc)
			{
				daySoBean = ketQuaMienTrungDAO.getDaySoMT(open_date,province_id);
				if(daySoBean!=null)
				{
				dayso = daySoBean.getDayso();
				boso = String.valueOf(dayso[x]+""+dayso[y]);
				setBuocCau(buocCauBean,buoc,boso,daySoBean.getKequa_id());
				kq = true;
				}
			}	
			if(buoc<songay)
			{
				daySoBean = ketQuaMienTrungDAO.getDaySoMT(open_date,province_id);
				if(daySoBean!=null)
				{
					dayso = daySoBean.getDayso();
					boso = String.valueOf(dayso[x]+""+dayso[y]);
					setBuocCau(buocCauBean,buoc,boso,daySoBean.getKequa_id());
					buoc +=1; 
					kq = createBuocCauMienTrung(boso,x,y,bien_ngay,buoc,loaicau,songay,buocCauBean,province_id,province_code) ;
				}
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
		logger.log("Soi cau Dac biet Mien Trung ngay "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		
		KetQuaMienTrungDAO ketQuaMienTrungDAO = new KetQuaMienTrungDAO();
		SoiCauDAO soiCauDAO = new SoiCauDAO();
		BuocCauDAO buocCauDAO = new BuocCauDAO();		
		// Tao cau
		SoiCauDBMtThread soiCauDBMnThread = new SoiCauDBMtThread();
		BuocCauBean buocCauBean = null;
		SoiCauBean soiCauBean = null;
		DaySoBean daySoBean = null;
		int i = 0,j=0;
		String boso="";
		
		boolean kq = false;
		int[] so_ngay = {2,3};
		//int[] locai_cau = {1,2,3,4};
		int[] locai_cau = {1};
		int k=0;int q=0;
		int kc_1 = 0;
		int kc_2 = 0;
		// get current date
		Calendar calendar = Calendar.getInstance();
		long bien_ngay_tren = calendar.getTimeInMillis();		
		// get danh sach tinh
		String thu="";
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SUNDAY:
				thu="thu8";
				break;
			case Calendar.MONDAY:
				thu="thu2";
				break;
			case Calendar.TUESDAY:
				thu="thu3";
				break;
			case Calendar.WEDNESDAY:
				thu="thu4";
				break;
			case Calendar.THURSDAY:
				thu="thu5";
				break;
			case Calendar.FRIDAY:
				thu="thu6";
				break;
			case Calendar.SATURDAY:
				thu="thu7";
				break;
			default:
			break;		
		} 
		//thu="thu4";
		ProvinceDAO provinceDAO = new ProvinceDAO();
		List<ProvinceBean> prList = provinceDAO.findProvinceOpen(2, thu);
		
		for (ProvinceBean provinceBean : prList) {
		logger.log("---->Tinh = "+provinceBean.getName());
		Date bientrendate = new Date(bien_ngay_tren);
		// xac dinh bien ngay tren neu la dna
		/*if(danang.equalsIgnoreCase(provinceBean.getCode()))
		{
			Date bien_date1 = new Date(bien_ngay_tren);
			calendar = Calendar.getInstance();
			calendar.setTime(bien_date1);
			int khoang_ngay = 7;
			if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY)
			{
				khoang_ngay = 2;
			}
			if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
			{
				khoang_ngay = 5;
			}
			bien_ngay_tren = bien_ngay_tren - khoang_ngay*SoiCauDBMnThread.miliSecondDay;
			bientrendate = new Date(bien_ngay_tren);
		}else
		{
			bien_ngay_tren = bien_ngay_tren - 7*SoiCauDBMnThread.miliSecondDay;
			bientrendate = new Date(bien_ngay_tren);
		}*/
		
		logger.log("---->Bien Tren = "+bientrendate.toString());
		k = 0;
		// Tao cau qua so_ngay 
		while (k<so_ngay.length)
				{	logger.log("-------->So ngay = "+so_ngay[k]);
					soiCauBean =  new SoiCauBean();
					soiCauBean.setBien_do_ngay(so_ngay[k]);
					soiCauBean.setBien_ngay(new Date(bien_ngay_tren));
					soiCauBean.setCreate_date(DateProc.createTimestamp());
					soiCauBean.setProvince_id(provinceBean.getId());
					soiCauBean.setType(2);// soi cau dac biet
					long cau_id =soiCauDAO.saveCau(soiCauBean);
					// Tinh Bien ngay duoi
					q=0;
					while(q<locai_cau.length)
					{		logger.log("------------>Loai cau = "+locai_cau[q]);											
							//long bien_ngay_duoi = bien_ngay_tren - ( (locai_cau[q]-1)+((so_ngay[k]-1)*locai_cau[q] ))*miliSecondDay*7 ;
							long bien_ngay_duoi = bien_ngay_tren - (so_ngay[k])*miliSecondDay*7 ;
							if(danang.equalsIgnoreCase(provinceBean.getCode()))
							{
								Date bien_date = new Date(bien_ngay_tren);
								calendar = Calendar.getInstance();
								calendar.setTime(bien_date);
								if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY)
								{
									kc_1 = ((locai_cau[q]+1)/2)*4+((locai_cau[q]+1)/2+(locai_cau[q]+1)%2-1)*3;
									kc_2 = ((locai_cau[q]+1)/2)*3+((locai_cau[q]+1)/2+(locai_cau[q]+1)%2-1)*4;
									//bien_ngay_duoi = bien_ngay_tren - miliSecondDay*(so_ngay[k]/2*kc_1+((so_ngay[k]/2+(so_ngay[k]%2-1))*kc_2));
									long so_ngay_cach = miliSecondDay*((so_ngay[k]/2+so_ngay[k]%2)*kc_1+(so_ngay[k]/2*kc_2));
									long iSoNgayCach = so_ngay_cach/miliSecondDay;
									System.out.println(iSoNgayCach);
									bien_ngay_duoi = bien_ngay_tren - so_ngay_cach;
								}
								if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
								{
									kc_1 = ((locai_cau[q]+1)/2)*3+((locai_cau[q]+1)/2+(locai_cau[q]+1)%2-1)*4;
									kc_2 = ((locai_cau[q]+1)/2)*4+((locai_cau[q]+1)/2+(locai_cau[q]+1)%2-1)*3;
									//bien_ngay_duoi = bien_ngay_tren - miliSecondDay*(so_ngay[k]/2*kc_1+((so_ngay[k]/2+(so_ngay[k]%2-1))*kc_2));
									long so_ngay_cach = miliSecondDay*((so_ngay[k]/2+so_ngay[k]%2)*kc_1+(so_ngay[k]/2*kc_2));
									long iSoNgayCach = so_ngay_cach/miliSecondDay;
									System.out.println(iSoNgayCach);
									bien_ngay_duoi = bien_ngay_tren - so_ngay_cach;
									
								}
							}
													
							Date bienduoi = new Date(bien_ngay_duoi);
							logger.log("------------>Bien duoi = "+bienduoi.toString());
							daySoBean = ketQuaMienTrungDAO.getDaySoMT(new Date(bien_ngay_duoi),provinceBean.getId());
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
										kq=soiCauDBMnThread.createBuocCauMienTrung(boso, i, j, bien_ngay_duoi, 2, locai_cau[q], so_ngay[k], buocCauBean,provinceBean.getId(),provinceBean.getCode());
										if(kq)
										{
											logger.log("---------------->Tao cau boso "+boso+" vi tri xy ="+i+""+j);
											buocCauBean.setKieu_cau(locai_cau[q]);
											buocCauBean.setBoso(Integer.parseInt(soiCauDBMnThread.getBoSo(buocCauBean, so_ngay[k])));
											buocCauDAO.save(buocCauBean);
											soiCauDAO.updateBoso(cau_id, "bs_"+soiCauDBMnThread.getBoSo(buocCauBean, so_ngay[k]));
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
	 } // End Tinh
}
