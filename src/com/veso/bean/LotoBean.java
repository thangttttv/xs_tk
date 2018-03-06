package com.veso.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class LotoBean {
	/*
	 * `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `ketqua_id` BIGINT(20) DEFAULT NULL,
  `province_id` INT(11) DEFAULT NULL,
  `boso` VARCHAR(2) DEFAULT NULL,
  `thu` INT(11) DEFAULT NULL,
  `is_dacbiet` TINYINT(1) DEFAULT NULL,
  `is_tongchan` TINYINT(1) DEFAULT NULL,
  `is_tongle` TINYINT(1) DEFAULT NULL,
  `is_bochanle` TINYINT(1) DEFAULT NULL,
  `is_bolechan` TINYINT(1) DEFAULT NULL,
  `is_bochanchan` TINYINT(1) DEFAULT NULL,
  `is_bolele` TINYINT(1) DEFAULT NULL,
  `is_bokep` TINYINT(1) DEFAULT NULL,
  `is_bosatkep` TINYINT(1) DEFAULT NULL,
  `tan_so` TINYINT(11) DEFAULT NULL,
  `dau_so` INT(11) DEFAULT NULL,
  `dit_so` INT(11) DEFAULT NULL,
  `tong_bo` INT(11) DEFAULT NULL,
  `giai` VARCHAR(3) DEFAULT NULL,
  `ngay_quay` DATE DEFAULT NULL,
  `create_date` DATETIME DEFAULT NULL,
  `create_user` VARCHAR(256) DEFAULT NULL,
  `modify_date` DATETIME DEFAULT NULL,
  `modify_user` VARCHAR(256) DEFAULT NULL,
	 * 
	 * 
	 * 
	 */
	private long id;
	private long ketqua_id;
	private int province_id;
	private String boso;
	private int thu;
	private int is_dacbiet;
	private int is_tongchan;
	private int is_tongle;
	private int is_bochanle;
	private int is_bolechan;
	private int is_bochanchan;
	private int is_bolele;
	private int is_bokep;
	private int is_bosatkep;
	private int tan_so;
	private int dau_so;
	private int dit_so;
	private int tong_bo;
	private String giai;
	private Date ngay_quay;
	private Timestamp create_date;
	private String create_user;
	private Timestamp modify_date;
	private String modify_user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getKetqua_id() {
		return ketqua_id;
	}
	public void setKetqua_id(long ketqua_id) {
		this.ketqua_id = ketqua_id;
	}
	public String getBoso() {
		return boso;
	}
	public void setBoso(String boso) {
		this.boso = boso;
	}
	public int getThu() {
		return thu;
	}
	public void setThu(int thu) {
		this.thu = thu;
	}
	public int getIs_dacbiet() {
		return is_dacbiet;
	}
	public void setIs_dacbiet(int is_dacbiet) {
		this.is_dacbiet = is_dacbiet;
	}
	public int getIs_tongchan() {
		return is_tongchan;
	}
	public void setIs_tongchan(int is_tongchan) {
		this.is_tongchan = is_tongchan;
	}
	public int getIs_tongle() {
		return is_tongle;
	}
	public void setIs_tongle(int is_tongle) {
		this.is_tongle = is_tongle;
	}
	public int getIs_bochanle() {
		return is_bochanle;
	}
	public void setIs_bochanle(int is_bochanle) {
		this.is_bochanle = is_bochanle;
	}
	public int getIs_bolechan() {
		return is_bolechan;
	}
	public void setIs_bolechan(int is_bolechan) {
		this.is_bolechan = is_bolechan;
	}
	public int getIs_bochanchan() {
		return is_bochanchan;
	}
	public void setIs_bochanchan(int is_bochanchan) {
		this.is_bochanchan = is_bochanchan;
	}
	public int getIs_bolele() {
		return is_bolele;
	}
	public void setIs_bolele(int is_bolele) {
		this.is_bolele = is_bolele;
	}
	public int getIs_bokep() {
		return is_bokep;
	}
	public void setIs_bokep(int is_bokep) {
		this.is_bokep = is_bokep;
	}
	public int getIs_bosatkep() {
		return is_bosatkep;
	}
	public void setIs_bosatkep(int is_bosatkep) {
		this.is_bosatkep = is_bosatkep;
	}
	public int getTan_so() {
		return tan_so;
	}
	public void setTan_so(int tan_so) {
		this.tan_so = tan_so;
	}
	public int getDau_so() {
		return dau_so;
	}
	public void setDau_so(int dau_so) {
		this.dau_so = dau_so;
	}
	public int getDit_so() {
		return dit_so;
	}
	public void setDit_so(int dit_so) {
		this.dit_so = dit_so;
	}
	public int getTong_bo() {
		return tong_bo;
	}
	public void setTong_bo(int tong_bo) {
		this.tong_bo = tong_bo;
	}
	public String getGiai() {
		return giai;
	}
	public void setGiai(String giai) {
		this.giai = giai;
	}
	public Date getNgay_quay() {
		return ngay_quay;
	}
	public void setNgay_quay(Date ngay_quay) {
		this.ngay_quay = ngay_quay;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public Timestamp getModify_date() {
		return modify_date;
	}
	public void setModify_date(Timestamp modify_date) {
		this.modify_date = modify_date;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	
	
}
