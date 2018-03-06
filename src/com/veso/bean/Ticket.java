package com.veso.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Ticket {
	private int id; 
	private int user_id; 
	private Date ngay_quay; 
	private int province_id; 
	private double total_xu; 
	private double tien_thuong; 
	private int kieu_danh; 
	private Timestamp create_date;
	private String username;
	private String mobile;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getNgay_quay() {
		return ngay_quay;
	}
	public void setNgay_quay(Date ngay_quay) {
		this.ngay_quay = ngay_quay;
	}
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	public double getTotal_xu() {
		return total_xu;
	}
	public void setTotal_xu(double total_xu) {
		this.total_xu = total_xu;
	}
	public double getTien_thuong() {
		return tien_thuong;
	}
	public void setTien_thuong(double tien_thuong) {
		this.tien_thuong = tien_thuong;
	}
	public int getKieu_danh() {
		return kieu_danh;
	}
	public void setKieu_danh(int kieu_danh) {
		this.kieu_danh = kieu_danh;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
}
