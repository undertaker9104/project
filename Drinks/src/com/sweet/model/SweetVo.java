package com.sweet.model;

public class SweetVo  implements java.io.Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private String sweet_id;
	private String sweet_des;
	
	
	public SweetVo(String sweet_id, String sweet_des) {
		super();
		this.sweet_id = sweet_id;
		this.sweet_des = sweet_des;
	}
	
	public SweetVo() {
		super();
		
	}
	
	public String getSweet_id() {
		return sweet_id;
	}
	public void setSweet_id(String sweet_id) {
		this.sweet_id = sweet_id;
	}
	public String getSweet_des() {
		return sweet_des;
	}
	public void setSweet_des(String sweet_des) {
		this.sweet_des = sweet_des;
	}
	
	

}
