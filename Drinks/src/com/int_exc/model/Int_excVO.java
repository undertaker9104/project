package com.int_exc.model;

import java.io.Serializable;
import java.sql.Date;

public class Int_excVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mem_id ; 
	private String int_exc_rec_id;
	private Date int_exc_date;
	private Integer integral ;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getInt_exc_rec_id() {
		return int_exc_rec_id;
	}
	public void setInt_exc_rec_id(String int_exc_rec_id) {
		this.int_exc_rec_id = int_exc_rec_id;
	}
	public Date getInt_exc_date() {
		return int_exc_date;
	}
	public void setInt_exc_date(Date int_exc_date) {
		this.int_exc_date = int_exc_date;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}


}
