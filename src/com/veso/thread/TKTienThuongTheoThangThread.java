package com.veso.thread;

import java.sql.Date;
import java.util.Calendar;

import com.veso.bean.Ticket;
import com.veso.bean.TicketStatisticBonus;
import com.veso.dao.TicketDAO;
import com.veso.dao.TicketStatisticBonusDAO;

public class TKTienThuongTheoThangThread {
	
	public static void main(String[] args) {
	
		TicketDAO ticketDAO = new TicketDAO();
		TicketStatisticBonusDAO ticketStatisticBonusDAO = new TicketStatisticBonusDAO();
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(calendar.getTimeInMillis());
		java.util.List<Ticket> tickets = ticketDAO.findTicketStatisticBonus(date);		
		TicketStatisticBonus ticketStatisticBonus = null;
		
		for (Ticket ticket : tickets) {
			ticketStatisticBonus = ticketStatisticBonusDAO.getTicketByMonth(ticket.getUser_id(),calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
			if(ticketStatisticBonus==null)
			{
				ticketStatisticBonus = new TicketStatisticBonus();
				ticketStatisticBonus.bonus = ticket.getTien_thuong();
				ticketStatisticBonus.mobile = ticket.getMobile();
				ticketStatisticBonus.user_id = ticket.getUser_id();
				ticketStatisticBonus.user_name = ticket.getUsername();
				ticketStatisticBonus.ticket_month = calendar.get(Calendar.MONTH)+1;
				ticketStatisticBonus.ticket_year =  calendar.get(Calendar.YEAR);
				ticketStatisticBonusDAO.save(ticketStatisticBonus);
			}else
			{
				ticketStatisticBonusDAO.updateBonus(ticketStatisticBonus.id,ticket.getTien_thuong());
			}
			
		}
	}
	
	public static void main_bak(String[] args) {
		
		TicketDAO ticketDAO = new TicketDAO();
		TicketStatisticBonusDAO ticketStatisticBonusDAO = new TicketStatisticBonusDAO();
		

		Calendar calendarEnd = Calendar.getInstance();
		long endDay = calendarEnd.getTimeInMillis();
		
		
		Calendar calendarBegin = Calendar.getInstance();
		calendarBegin.set(Calendar.YEAR, 2011);
		calendarBegin.set(Calendar.MONTH, 01);
		calendarBegin.set(Calendar.DAY_OF_MONTH, 01);
		
		long startDay = calendarBegin.getTimeInMillis();
		long day = 86400000;
		
		while(startDay<endDay)
		{
			Date date = new Date(startDay);
			System.out.println(date.toString());
			java.util.List<Ticket> tickets = ticketDAO.findTicketStatisticBonus(date);		
			TicketStatisticBonus ticketStatisticBonus = null;
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			for (Ticket ticket : tickets) {
				ticketStatisticBonus = ticketStatisticBonusDAO.getTicketByMonth(ticket.getUser_id(),calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
				if(ticketStatisticBonus==null)
				{
					ticketStatisticBonus = new TicketStatisticBonus();
					ticketStatisticBonus.bonus = ticket.getTien_thuong();
					ticketStatisticBonus.mobile = ticket.getMobile();
					ticketStatisticBonus.user_id = ticket.getUser_id();
					ticketStatisticBonus.user_name = ticket.getUsername();
					ticketStatisticBonus.ticket_month = calendar.get(Calendar.MONTH)+1;
					ticketStatisticBonus.ticket_year =  calendar.get(Calendar.YEAR);
					ticketStatisticBonusDAO.save(ticketStatisticBonus);
				}else
				{
					ticketStatisticBonusDAO.updateBonus(ticketStatisticBonus.id,ticket.getTien_thuong());
				}
				
			}
			startDay = startDay + day;
		}
		
	}
}
