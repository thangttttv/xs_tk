package com.veso.bean;

import java.util.Calendar;

public class ProvinceBean {
	int id;
	String code;
	String name;
	int region;
	int thu2;
	int thu3;
	int thu4;
	int thu5;
	int thu6;
	int thu7;
	int thu8;
	public int getThu2() {
		return thu2;
	}
	public void setThu2(int thu2) {
		this.thu2 = thu2;
	}
	public int getThu3() {
		return thu3;
	}
	public void setThu3(int thu3) {
		this.thu3 = thu3;
	}
	public int getThu4() {
		return thu4;
	}
	public void setThu4(int thu4) {
		this.thu4 = thu4;
	}
	public int getThu5() {
		return thu5;
	}
	public void setThu5(int thu5) {
		this.thu5 = thu5;
	}
	public int getThu6() {
		return thu6;
	}
	public void setThu6(int thu6) {
		this.thu6 = thu6;
	}
	public int getThu7() {
		return thu7;
	}
	public void setThu7(int thu7) {
		this.thu7 = thu7;
	}
	public int getThu8() {
		return thu8;
	}
	public void setThu8(int thu8) {
		this.thu8 = thu8;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRegion() {
		return region;
	}
	public void setRegion(int region) {
		this.region = region;
	}
	
	public static boolean isOpenDate(long date,ProvinceBean provinceBean)
	{
		Calendar calendar =Calendar.getInstance();	
		calendar.setTimeInMillis(date);
		boolean is_open_date = false;		
		switch (calendar.get(Calendar.DAY_OF_WEEK))
		{
			case Calendar.MONDAY:
				is_open_date=provinceBean.getThu2()==1?true:false;
				break;
			case Calendar.TUESDAY:
				is_open_date=provinceBean.getThu3()==1?true:false;
				break;	
			case Calendar.WEDNESDAY:
				is_open_date=provinceBean.getThu4()==1?true:false;
				break;	
			case Calendar.THURSDAY:
				is_open_date=provinceBean.getThu5()==1?true:false;
				break;
			case Calendar.FRIDAY:
				is_open_date=provinceBean.getThu6()==1?true:false;
				break;
			case Calendar.SATURDAY:
				is_open_date=provinceBean.getThu7()==1?true:false;
				break;	
			case Calendar.SUNDAY:
				is_open_date=provinceBean.getThu8()==1?true:false;
				break;	
			default:
				break;
		} 
		return is_open_date;
	}
}
