package com.veso.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class BangTanSuatLotoBean {
	/*
	 *`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `province_id` INT(11) DEFAULT NULL,
  `content` TEXT,
  `ngay_bien` DATE DEFAULT NULL,
  `so_ngay` INT(11) DEFAULT NULL,  
  `create_date` DATETIME DEFAULT NULL,

	 */	
	private int id;
	private int province_id;
	private String content;
	private Date ngay_bien;
	private int so_ngay;
	private Timestamp create_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getNgay_bien() {
		return ngay_bien;
	}
	public void setNgay_bien(Date ngay_bien) {
		this.ngay_bien = ngay_bien;
	}
	public int getSo_ngay() {
		return so_ngay;
	}
	public void setSo_ngay(int so_ngay) {
		this.so_ngay = so_ngay;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	
}
