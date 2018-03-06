package com.veso.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class TanSuatBoSoBean {
  /*
   * `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `boso` VARCHAR(2) DEFAULT NULL,
  `so_ngay_ve` INT(11) DEFAULT NULL,
  `p_so_ngay_ve` DOUBLE DEFAULT NULL,
  `so_lan_ve` INT(11) DEFAULT NULL,
  `p_so_lan_ve` DOUBLE DEFAULT NULL,
  `p_so_lan_ngay` DOUBLE DEFAULT NULL,
  `province_id` INT(11) DEFAULT NULL,
  `khoang_thoigian` INT(11) DEFAULT NULL,
  `is_dacbiet` TINYINT(4) DEFAULT NULL,
  `create_date` DATETIME DEFAULT NULL,
   */
	private long id;
	private String boso;
	private int so_ngay_ve;
	private double p_so_ngay_ve;
	private int so_lan_ve;
	private double p_so_lan_ve;
	private double p_so_lan_ngay;
	private int province_id;
	private int khoang_thoigian;
	private int is_dacbiet;
	private Timestamp create_date;
	private Date bien_ngay;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBoso() {
		return boso;
	}
	public void setBoso(String boso) {
		this.boso = boso;
	}
	public int getSo_ngay_ve() {
		return so_ngay_ve;
	}
	public void setSo_ngay_ve(int so_ngay_ve) {
		this.so_ngay_ve = so_ngay_ve;
	}
	public double getP_so_ngay_ve() {
		return p_so_ngay_ve;
	}
	public void setP_so_ngay_ve(double p_so_ngay_ve) {
		this.p_so_ngay_ve = p_so_ngay_ve;
	}
	public int getSo_lan_ve() {
		return so_lan_ve;
	}
	public void setSo_lan_ve(int so_lan_ve) {
		this.so_lan_ve = so_lan_ve;
	}
	public double getP_so_lan_ngay() {
		return p_so_lan_ngay;
	}
	public void setP_so_lan_ngay(double p_so_lan_ngay) {
		this.p_so_lan_ngay = p_so_lan_ngay;
	}
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	public int getKhoang_thoigian() {
		return khoang_thoigian;
	}
	public void setKhoang_thoigian(int khoang_thoigian) {
		this.khoang_thoigian = khoang_thoigian;
	}
	public int getIs_dacbiet() {
		return is_dacbiet;
	}
	public void setIs_dacbiet(int is_dacbiet) {
		this.is_dacbiet = is_dacbiet;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public double getP_so_lan_ve() {
		return p_so_lan_ve;
	}
	public void setP_so_lan_ve(double p_so_lan_ve) {
		this.p_so_lan_ve = p_so_lan_ve;
	}
	public Date getBien_ngay() {
		return bien_ngay;
	}
	public void setBien_ngay(Date bien_ngay) {
		this.bien_ngay = bien_ngay;
	}
	
}
