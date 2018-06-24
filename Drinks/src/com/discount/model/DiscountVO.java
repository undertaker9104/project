package com.discount.model;

public class DiscountVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String dis_id;
	private String dis_des;
	private Integer dis_cup;
	private Double dis_cup_rate;
	private Integer dis_price;
	private Double dis_price_rate;

	public String getDis_id() {
		return dis_id;
	}

	public void setDis_id(String dis_id) {
		this.dis_id = dis_id;
	}

	public String getDis_des() {
		return dis_des;
	}

	public void setDis_des(String dis_des) {
		this.dis_des = dis_des;
	}

	public Integer getDis_cup() {
		return dis_cup;
	}

	public void setDis_cup(Integer dis_cup) {
		this.dis_cup = dis_cup;
	}

	public Double getDis_cup_rate() {
		return dis_cup_rate;
	}

	public void setDis_cup_rate(Double dis_cup_rate) {
		this.dis_cup_rate = dis_cup_rate;
	}

	public Integer getDis_price() {
		return dis_price;
	}

	public void setDis_price(Integer dis_price) {
		this.dis_price = dis_price;
	}

	public Double getDis_price_rate() {
		return dis_price_rate;
	}

	public void setDis_price_rate(Double dis_price_rate) {
		this.dis_price_rate = dis_price_rate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
