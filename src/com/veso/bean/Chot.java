package com.veso.bean;

public class Chot {
	public int id;
	public String loto;
	public String loto_dac_biet;
	public int user_id;
	public int province_id;
	public String ngay_quay;
	
	public Chot(int id,int user_id, int province_id, String loto,String loto_dac_biet,String ngay_quay){
		this.id = id;
		this.loto = loto;
		this.loto_dac_biet = loto_dac_biet;
		this.user_id = user_id;
		this.province_id = province_id;
		this.ngay_quay = ngay_quay;
	}
}
