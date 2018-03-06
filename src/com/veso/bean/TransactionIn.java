package com.veso.bean;

import java.sql.Timestamp;

public class TransactionIn {
	private int id; 
	private int user_id; 
	private String username;
	private int ticket_id; 
	private double tk_chinh; 
	private double tk_phu; 
	private String content; 
	private int type;
	private Timestamp transaction_date;
	
	public final int  Type_Lo_Game =    1;
	public final int  Type_De_Game =    2;
	public final int  Type_Xien_Game =  3;
	public final int  Type_SMS =    4;
	public final int  Type_Card =    5;
	
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
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	
	public double getTk_chinh() {
		return tk_chinh;
	}
	public void setTk_chinh(double tk_chinh) {
		this.tk_chinh = tk_chinh;
	}
	public double getTk_phu() {
		return tk_phu;
	}
	public void setTk_phu(double tk_phu) {
		this.tk_phu = tk_phu;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(Timestamp transaction_date) {
		this.transaction_date = transaction_date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
