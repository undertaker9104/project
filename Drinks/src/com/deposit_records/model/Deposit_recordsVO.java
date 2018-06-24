package com.deposit_records.model;

import java.io.Serializable;
import java.sql.Date;

public class Deposit_recordsVO implements Serializable{	

	private static final long serialVersionUID = 1L;
	private String dep_rec_id ; 
	private String mem_id ;
	private Integer  dep_cash ;
	private Date dep_suss_date;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getDep_rec_id() {
		return dep_rec_id;
	}
	public void setDep_rec_id(String dep_rec_id) {
		this.dep_rec_id = dep_rec_id;
	}
	public Integer getDep_cash() {
		return dep_cash;
	}
	public void setDep_cash(Integer dep_cash) {
		this.dep_cash = dep_cash;
	}
	public Date getDep_suss_date() {
		return dep_suss_date;
	}
	public void setDep_suss_date(Date dep_suss_date) {
		this.dep_suss_date = dep_suss_date;
	}
	
	
	
}
