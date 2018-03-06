package com.veso.bean;

import java.sql.Timestamp;

public class TicketDetail {
	private int id; 
	private int ticket_id; 
	private String boso; 
	private int kieu_danh; 
	private int type_dauduoi;	
	private double xu; 
	private double tien_thuong; 
	private Timestamp create_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	public String getBoso() {
		return boso;
	}
	public void setBoso(String boso) {
		this.boso = boso;
	}
	public int getKieu_danh() {
		return kieu_danh;
	}
	public void setKieu_danh(int kieu_danh) {
		this.kieu_danh = kieu_danh;
	}
	public double getXu() {
		return xu;
	}
	public void setXu(double xu) {
		this.xu = xu;
	}
	public double getTien_thuong() {
		return tien_thuong;
	}
	public void setTien_thuong(double tien_thuong) {
		this.tien_thuong = tien_thuong;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public int getType_dauduoi() {
		return type_dauduoi;
	}
	public void setType_dauduoi(int typeDauduoi) {
		type_dauduoi = typeDauduoi;
	}

}
