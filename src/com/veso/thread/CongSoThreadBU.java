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

public class CongSoThreadBU {
	
	private static Logger logger = new Logger("CongSoThread");
	private LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
	
	private double tinhThuongLoto(int region,Date open_date,Ticket ticket,TicketDetail ticketDetail)
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
	
	private double tinhThuongDe(int region,Date open_date,Ticket ticket,TicketDetail ticketDetail)
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
	
	private double tinhThuongLoXien(int region,Date open_date,Ticket ticket,TicketDetail ticketDetail)
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
	
	public static void main(String[] args) {
		TicketDAO ticketDAO = new TicketDAO();
		LotoDAO lotoDAO = new LotoDAO("thongke_loto_mienbac");
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
	    int lanve = 0;
	    for (int i = 0; i < tList.size(); i++) {
	    	ticket = tList.get(i);
	    	tdList = ticketDetailDAO.findByTicket(ticket.getId());
	    	tong_xu_thuong = 0;
	    	region = provinceDAO.findRegion(ticket.getProvince_id());
	    	// Duyet danh sach boso
	    	logger.log("---->Duyet danh sach boso trong tung TicketID = "+ticket.getId());
	    	for (TicketDetail ticketDetail : tdList) {
	    		xu_thuong = 0;
				if(ticket.getKieu_danh()==2)// De
				{
					logger.log("------>Duyet De : Mien ="+region+"  TicketID = "+ticket.getId()+" Boso = "+ticketDetail.getBoso());
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
					
				}
				
				if(ticket.getKieu_danh()==1)// loto
				{
					logger.log("------>Duyet Lo : Mien ="+region+"  TicketID = "+ticket.getId()+" Boso = "+ticketDetail.getBoso());
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
				}
				String bosos[] = null;boolean kq = true;int xien = 0;
				int nhan = 10;
				if(ticket.getKieu_danh()==3)// loto xien
				{
					logger.log("------>Duyet Lo Xien : Mien ="+region+"  TicketID = "+ticket.getId()+" Boso = "+ticketDetail.getBoso());
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
				}
				tong_xu_thuong +=xu_thuong;
				// cap nhat ticket detail
				logger.log("---->Xu thuong cua TicketID = "+ticket.getId()+" Ticket detail ="+ticketDetail.getId()+" la = "+xu_thuong);
				ticketDetailDAO.update(ticketDetail.getId(), xu_thuong);
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
		   int type= 1;
		   switch(ticket.getKieu_danh()) {
			case 1:
				type= 1;
				break;
			case 2:
				type= 2;
				break;
			case 3:
				type= 3;
				break;
			default:
				type= 1;
				break;
			}
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
