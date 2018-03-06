package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.veso.bean.Ticket;
import com.veso.bean.TicketDetail;
import com.veso.bean.TransactionIn;
import com.veso.bean.User;
import com.veso.dao.LotoDAO;
import com.veso.dao.ProvinceDAO;
import com.veso.dao.TicketDAO;
import com.veso.dao.TicketDetailDAO;
import com.veso.dao.TransactionInDAO;
import com.veso.dao.UserDAO;
import com.veso.util.DateProc;
import com.veso.util.Logger;

public class CongSoThread {
	
	private static Logger logger = new Logger("CongSoThread");
	private static LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
	
	private static double tinhThuongLoto(int region,Date open_date,Ticket ticket,TicketDetail ticketDetail)
	{
		logger.log("------>Duyet Lo : Mien ="+region+"  TicketID = "+ticket.getId()+" Boso = "+ticketDetail.getBoso());
		int lanve = 0;
		double xu_thuong = 0;
		switch (region)
		{
			case 1:
				lanve = lotoDAO.checkLotoMienBac(open_date, ticketDetail.getBoso());
				if(lanve>0)
					xu_thuong = ticketDetail.getXu()*3.5*lanve;
				break;
			case 2:
				lanve =lotoDAO.checkLotoMienTrung(open_date, ticketDetail.getBoso(),ticket.getProvince_id()); 
				if(lanve>0)
					xu_thuong = ticketDetail.getXu()*3.5*lanve;
				break;
			case 3:
				lanve = lotoDAO.checkLotoMienNam(open_date, ticketDetail.getBoso(),ticket.getProvince_id());
				if(lanve>0)
					xu_thuong = ticketDetail.getXu()*3.5*lanve;
				break;
		}
		return xu_thuong;
	}
	
	private static double tinhThuongDe(int region,Date open_date,Ticket ticket,TicketDetail ticketDetail)
	{
		logger.log("------>Duyet De : Mien ="+region+"  TicketID = "+ticket.getId()+" Boso = "+ticketDetail.getBoso());
		double xu_thuong = 0;
		switch (region)
		{
			case 1:
				if(lotoDAO.checkDeMienBac(open_date, ticketDetail.getBoso()))
					xu_thuong = ticketDetail.getXu()*70;
				break;
			case 2:
				if(lotoDAO.checkDeMienTrung(open_date, ticketDetail.getBoso(),ticket.getProvince_id()))
					xu_thuong = ticketDetail.getXu()*70;
				break;
			case 3:
				if(lotoDAO.checkDeMienNam(open_date, ticketDetail.getBoso(),ticket.getProvince_id()))
					xu_thuong = ticketDetail.getXu()*70;
				break;
		}
		return xu_thuong;
	}
	
	private static double tinhThuongDeCuoi(int region,Date open_date,Ticket ticket,TicketDetail ticketDetail)
	{
		logger.log("------>Duyet De : Mien ="+region+"  TicketID = "+ticket.getId()+" Boso = "+ticketDetail.getBoso());
		double xu_thuong = 0;
		String arr_boso[] = ticketDetail.getBoso().split(",");
		switch (region)
		{
			case 2:		
				for (String socuoi : arr_boso) {
					if(lotoDAO.checkSoCuoiDBMienTrung(open_date, socuoi,ticket.getProvince_id()))
						xu_thuong += ticketDetail.getXu()*8;
				}
				
				break;
			case 3:
				for (String socuoi : arr_boso) {
					if(lotoDAO.checkSoCuoiDBMienNam(open_date, socuoi,ticket.getProvince_id()))
						xu_thuong += ticketDetail.getXu()*8;
				}
				break;
		}
		return xu_thuong;
	}
	
	
	private static double tinhThuongLoTruotMienBac(Date open_date,Ticket ticket,TicketDetail ticketDetail)
	{
		logger.log("------>Duyet De : Mien = Bac "+"  TicketID = "+ticket.getId()+" Boso = "+ticketDetail.getBoso());
		double xu_thuong = 0;
		String arr_boso [] = ticketDetail.getBoso().split(",");
		int length = arr_boso.length;
		if(lotoDAO.checkLotoTruotMienBac(open_date, arr_boso))
		{
			switch (length) {
			case 5:
				xu_thuong = 4*  ticketDetail.getXu();
				break;
			case 8:
				xu_thuong = 8*  ticketDetail.getXu();
				break;
			case 10:
				xu_thuong = 10*  ticketDetail.getXu();
				break;
			case 11:
				xu_thuong = 15*  ticketDetail.getXu();
				break;
			case 12:
				xu_thuong = 20*  ticketDetail.getXu();
				break;
			default:
				break;
			}
		}
		return xu_thuong;
	}
	
	
	private static double tinhThuongLoXien(int region,Date open_date,Ticket ticket,TicketDetail ticketDetail)
	{
		logger.log("------>Duyet Lo Xien : Mien ="+region+"  TicketID = "+ticket.getId()+" Boso = "+ticketDetail.getBoso());
		String bosos[] = null;boolean kq = true;int xien = 0;
		int nhan = 10;
		double xu_thuong=0;
		switch (region)
		{
			case 1:
				bosos = ticketDetail.getBoso().split(",");
				kq = true;
				xien = 0;
				for (String boso : bosos) {
					kq =kq&lotoDAO.checkLotoMienBac(open_date, boso)>0?true:false;
					xien =+1;	
				}
				if(xien==2) nhan = 10;
				if(xien==3) nhan = 40;
				if(xien==4) nhan = 100;
				if(kq) xu_thuong = ticketDetail.getXu()*nhan;
				break;
			case 2:
				bosos = ticketDetail.getBoso().split(",");
				kq = true;
				xien = 0;
				for (String boso : bosos) {
					kq =kq&lotoDAO.checkLotoMienTrung(open_date, boso,ticket.getProvince_id())>0?true:false;
					xien =+1;	
				}
				if(xien==2) nhan = 10;
				if(xien==3) nhan = 40;
				if(xien==4) nhan = 100;
				if(kq) xu_thuong = ticketDetail.getXu()*nhan;
				break;
			case 3:
				bosos = ticketDetail.getBoso().split(",");
				kq = true;
				xien = 0;
				for (String boso : bosos) {
					kq =kq&lotoDAO.checkLotoMienNam(open_date, boso,ticket.getProvince_id())>0?true:false;
					xien =+1;	
				}
				if(xien==2) nhan = 10;
				if(xien==3) nhan = 40;
				if(xien==4) nhan = 100;
				if(kq) xu_thuong = ticketDetail.getXu()*nhan;
				break;
		}
		
		return xu_thuong;
	}
	
	private static double tinhThuongDauDuoi(int region,Date open_date,Ticket ticket,TicketDetail ticketDetail)
	{
		logger.log("------>Duyet Dau duoi : Mien ="+region+"  TicketID = "+ticket.getId()+" Boso = "+ticketDetail.getBoso());
		String bosos[] = null;
		double xu_thuong=0;
		int lan_ve = 0;int ty_le = 0;
		switch (region)
		{
			case 1:
				ty_le = 8;
				bosos = ticketDetail.getBoso().split(",");
				if(ticketDetail.getType_dauduoi()==0)
				{
					// Check Theo Dau So
					for (String dauso : bosos) {
						lan_ve +=lotoDAO.checkDauDeMienBac(open_date, dauso)?1:0;
					}
				}else{
					// Check Theo duoi so
					for (String dauso : bosos) {
						lan_ve +=lotoDAO.checkDuoiDeMienBac(open_date, dauso)?1:0;
					}
				}
				xu_thuong = ticketDetail.getXu()*lan_ve*ty_le;
				break;
			case 2:
				ty_le = 85;
				bosos = ticketDetail.getBoso().split(",");
				switch (ticketDetail.getType_dauduoi()) {
					case 0://Dau
						for (String boso : bosos) {
							lan_ve +=lotoDAO.checkBosoG8MienTrung(open_date, boso, ticket.getProvince_id())?1:0;
						}	
						ty_le = 85;
						xu_thuong = ticketDetail.getXu()*lan_ve*ty_le;
						break;	
					case 1://Duoi
						for (String boso : bosos) {
							lan_ve +=lotoDAO.checkDeMienTrung(open_date, boso, ticket.getProvince_id())?1:0;
						}	
						ty_le = 85;
						 xu_thuong = ticketDetail.getXu()*lan_ve*ty_le;
						break;	
					case 2://Dau Duoi
						ty_le = 42;
						lan_ve=0;
						for (String boso : bosos) {
							lan_ve +=lotoDAO.checkBosoG8MienTrung(open_date, boso, ticket.getProvince_id())?1:0;
							lan_ve +=lotoDAO.checkDeMienTrung(open_date, boso, ticket.getProvince_id())?1:0;
							if(lan_ve>1) ty_le=85; 
							xu_thuong += ticketDetail.getXu()*ty_le;
							lan_ve = 0;
						}	
						break;	
					default:
						break;
				}
				break;
			case 3:
				ty_le = 85;
				bosos = ticketDetail.getBoso().split(",");
				switch (ticketDetail.getType_dauduoi()) {
					case 0://Dau
						for (String boso : bosos) {
							lan_ve +=lotoDAO.checkBosoG8MienNam(open_date, boso, ticket.getProvince_id())?1:0;
						}	
						ty_le = 85;
						xu_thuong = ticketDetail.getXu()*lan_ve*ty_le;
						break;	
					case 1://Duoi
						for (String boso : bosos) {
							lan_ve +=lotoDAO.checkDeMienNam(open_date, boso, ticket.getProvince_id())?1:0;
						}	
						ty_le = 85;
						 xu_thuong = ticketDetail.getXu()*lan_ve*ty_le;
						break;	
					case 2://Dau Duoi
						ty_le = 42;
						lan_ve=0;
						for (String boso : bosos) {
							lan_ve +=lotoDAO.checkBosoG8MienNam(open_date, boso, ticket.getProvince_id())?1:0;
							lan_ve +=lotoDAO.checkDeMienNam(open_date, boso, ticket.getProvince_id())?1:0;
							if(lan_ve>1) ty_le=85; 
							xu_thuong += ticketDetail.getXu()*ty_le;
							lan_ve = 0;
						}	
						break;	
					default:
						break;
				}
				break;
		}
		
		return xu_thuong;
	}
	
	
	private static double tinhThuongXiuChu(Date open_date,Ticket ticket,TicketDetail ticketDetail)
	{
		logger.log("------>Duyet Dau duoi : Mien = Nam"+"  TicketID = "+ticket.getId()+" Boso = "+ticketDetail.getBoso());
		String bosos[] = null;
		double xu_thuong=0;
		int lan_ve = 0;int ty_le = 0;
		ty_le = 650;
		bosos = ticketDetail.getBoso().split(",");
		switch (ticketDetail.getType_dauduoi()) {
			case 0://Dau
					for (String boso : bosos) {
						lan_ve +=lotoDAO.checkBo3soG7MienNam(open_date, boso, ticket.getProvince_id())?1:0;
					}	
					ty_le = 650;
					xu_thuong = ticketDetail.getXu()*lan_ve*ty_le;
					break;	
			case 1://Duoi
					for (String boso : bosos) {
						lan_ve +=lotoDAO.checkBo3soGDBMienNam(open_date, boso, ticket.getProvince_id())?1:0;
					}	
					ty_le = 650;
					 xu_thuong = ticketDetail.getXu()*lan_ve*ty_le;
					break;	
			case 2://Dau Duoi
					ty_le = 325;
					lan_ve=0;
					for (String boso : bosos) {
						lan_ve +=lotoDAO.checkBo3soG7MienNam(open_date, boso, ticket.getProvince_id())?1:0;
						lan_ve +=lotoDAO.checkBo3soGDBMienNam(open_date, boso, ticket.getProvince_id())?1:0;
						if(lan_ve>1) ty_le=650; 
						xu_thuong += ticketDetail.getXu()*ty_le;
						lan_ve = 0;
					}	
					break;	
				default:
					break;
			}
		return xu_thuong;
	}
	
	private static double tinhThuongBaCang(Date open_date,Ticket ticket,TicketDetail ticketDetail)
	{
		logger.log("------>Duyet Dau duoi : Mien = Trung"+"  TicketID = "+ticket.getId()+" Boso = "+ticketDetail.getBoso());
		String bosos[] = null;
		double xu_thuong=0;
		int lan_ve = 0;int ty_le = 0;
		ty_le = 650;
		bosos = ticketDetail.getBoso().split(",");
		switch (ticketDetail.getType_dauduoi()) {
			case 0://Dau
					for (String boso : bosos) {
						lan_ve +=lotoDAO.checkBo3soG7MienTrung(open_date, boso, ticket.getProvince_id())?1:0;
					}	
					ty_le = 650;
					xu_thuong = ticketDetail.getXu()*lan_ve*ty_le;
					break;	
			case 1://Duoi
					for (String boso : bosos) {
						lan_ve +=lotoDAO.checkBo3soGDBMienTrung(open_date, boso, ticket.getProvince_id())?1:0;
					}	
					ty_le = 650;
					 xu_thuong = ticketDetail.getXu()*lan_ve*ty_le;
					break;	
			case 2://Dau Duoi
					ty_le = 325;
					lan_ve=0;
					for (String boso : bosos) {
						lan_ve +=lotoDAO.checkBo3soG7MienTrung(open_date, boso, ticket.getProvince_id())?1:0;
						lan_ve +=lotoDAO.checkBo3soGDBMienTrung(open_date, boso, ticket.getProvince_id())?1:0;
						if(lan_ve>1) ty_le=650; 
						xu_thuong += ticketDetail.getXu()*ty_le;
						lan_ve = 0;
					}	
					break;	
				default:
					break;
			}
		return xu_thuong;
	}
	
	public static void main(String[] args) {
		TicketDAO ticketDAO = new TicketDAO();
		//LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
		TicketDetailDAO ticketDetailDAO = new TicketDetailDAO();		
		ProvinceDAO provinceDAO = new ProvinceDAO();
		TransactionInDAO transactionInDAO = new TransactionInDAO();
		UserDAO userDAO = new UserDAO();
		Calendar calendar = Calendar.getInstance();		
		/*calendar.set(Calendar.YEAR, 2012);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DAY_OF_MONTH, 9);*/
		logger.log(" Cong so ngay: "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR));
		
		Date open_date = new Date(calendar.getTimeInMillis());		
	    List<Ticket> tList = ticketDAO.findByOpenDate(open_date);
	    logger.log("-->So luong Ticket =  "+tList.size());
	    
	    List<TicketDetail> tdList = null;
	    Ticket ticket = null;	  
	    double tong_xu_thuong = 0;
	    double xu_thuong = 0;
	    int region = 0;
	    // Duyet danh sach ticket
	    logger.log("-->Duyet danh sach ticket");
	    
	    for (int i = 0; i < tList.size(); i++) {
	    	ticket = tList.get(i);
	    	tdList = ticketDetailDAO.findByTicket(ticket.getId());
	    	tong_xu_thuong = 0;
	    	region = provinceDAO.findRegion(ticket.getProvince_id());
	    	// Duyet danh sach boso
	    	
	    	logger.log("---->Duyet danh sach boso trong tung TicketID = "+ticket.getId());
	    	for (TicketDetail ticketDetail : tdList) {
	    		xu_thuong = 0;
	    		switch (ticketDetail.getKieu_danh()) {
					case 1: // Loto game
						xu_thuong = tinhThuongLoto(region, open_date, ticket, ticketDetail);
						break;
					case 2: // De game
						xu_thuong = tinhThuongDe(region, open_date, ticket, ticketDetail);
						break;
					case 3: // Xien game
						xu_thuong = tinhThuongLoXien(region, open_date, ticket, ticketDetail);
						break;
					case 4:// Dau duoi
						xu_thuong = tinhThuongDauDuoi(region, open_date, ticket, ticketDetail);
						break;
					case 5:// lo truot
						xu_thuong = tinhThuongLoTruotMienBac(open_date, ticket, ticketDetail);
						break;
					case 6:// xiu chu
						xu_thuong = tinhThuongXiuChu(open_date, ticket, ticketDetail);
						break;
					case 7:// Ba Cang
						xu_thuong = tinhThuongBaCang(open_date, ticket, ticketDetail);
						break;
					case 8:// De cuoi
						xu_thuong = tinhThuongDeCuoi(region, open_date, ticket, ticketDetail);
						break;
					default:
						break;
				}
				
				tong_xu_thuong +=xu_thuong;
				// cap nhat ticket detail				
				ticketDetailDAO.update(ticketDetail.getId(), xu_thuong);
				logger.log("---->Xu thuong cua TicketID = "+ticket.getId()+" Ticket detail ="+ticketDetail.getId()+" la = "+xu_thuong);
			}
	    	logger.log("---->Tong xu thuong cua TicketID = "+ticket.getId()+" la = "+tong_xu_thuong);
	    	ticket.setTien_thuong(tong_xu_thuong);	
	    	
	    	// cap nhat ticket 	    
	    	if(tong_xu_thuong>0)
	    		ticketDAO.update(ticket.getId(), tong_xu_thuong);
	    	else
	    		ticketDAO.update(ticket.getId(), tong_xu_thuong,1);	
		}
		
	    // Thanh toan
	   tList = ticketDAO.findTicketTransaction(open_date);
	   int j=0;
	   
	   TransactionIn transactionIn = null;
	   boolean kq1=false;
	   boolean kq2=false;
	   
	   logger.log("---->Cong tien thuong cho User");	   
	   while(j<tList.size())
	   {
		   ticket = tList.get(j);
		   //Cong xu
		   logger.log("------>Cong cho UserId = "+ticket.getUser_id()+" So tien thuong la = "+ticket.getTien_thuong()+" Voi ticketid = "+ticket.getId());
		   kq1 = userDAO.update(ticket.getUser_id(), ticket.getTien_thuong());
		   
		   // Tao transaction		
		   logger.log("---->Tao transaction TicketId="+ticket.getId());
		   transactionIn = new TransactionIn();  
		   transactionIn.setTicket_id(ticket.getId());
		   transactionIn.setUser_id(ticket.getUser_id());
		   transactionIn.setTk_chinh(ticket.getTien_thuong());
		   int type= ticket.getKieu_danh();
		   User user = userDAO.getUser(ticket.getUser_id());
		   if(user==null) continue;
		   transactionIn.setUsername(user.name);
		   transactionIn.setType(type);
		   transactionIn.setTransaction_date(DateProc.createTimestamp());
		   transactionIn.setContent("Trung thuong lodo game.");
		   kq2 = transactionInDAO.save(transactionIn);
		   // cap nhat ticket
		   if(kq1&kq2)
		   {
			   logger.log("---->Cap nhat trang thai da thanh toan cho  TicketId="+ticket.getId());
			   ticketDAO.update(ticket.getId(), 1);
		   }
		   
		   j++;
	   }
	    
	}
	
	
}
