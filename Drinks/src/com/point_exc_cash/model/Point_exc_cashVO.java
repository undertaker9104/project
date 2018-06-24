package com.point_exc_cash.model;

import java.io.Serializable;
import java.sql.Date;

public class Point_exc_cashVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mem_id;
	private String exc_rec_id;
	private Date exc_date ;
	private Integer exc_cash;
	private Integer bank_acc;
	private Integer req_status;
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getExc_rec_id() {
		return exc_rec_id;
	}
	public void setExc_rec_id(String exc_rec_id) {
		this.exc_rec_id = exc_rec_id;
	}
	public Date getExc_date() {
		return exc_date;
	}
	public void setExc_date(Date exc_date) {
		this.exc_date = exc_date;
	}
	public Integer getExc_cash() {
		return exc_cash;
	}
	public void setExc_cash(Integer exc_cash) {
		this.exc_cash = exc_cash;
	}
	public Integer getBank_acc() {
		return bank_acc;
	}
	public void setBank_acc(Integer bank_acc) {
		this.bank_acc = bank_acc;
	}
	public Integer getReq_status() {
		return req_status;
	}
	public void setReq_status(Integer req_status) {
		this.req_status = req_status;
	}
	

}
