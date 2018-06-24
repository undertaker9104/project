package com.orderdetail.model;

public class OrderDetailVo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String ord_id;
	private String product_id;
	private String ice_id;
	private String sweet_id;
	private String mem_id;
	private Integer ord_price;
	private Integer tol_cup;
	
	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	
	public OrderDetailVo() {
		super();
	}
	
	public OrderDetailVo(String ord_id, String mem_id ,String product_id, String ice_id, String sweet_id, Integer ord_price,
			Integer tol_cup) {
		super();
		this.ord_id = ord_id;
		this.mem_id = mem_id;
		this.product_id = product_id;
		this.ice_id = ice_id;
		this.sweet_id = sweet_id;
		this.ord_price = ord_price;
		this.tol_cup = tol_cup;
	}
	/**
	 * @return the ord_id
	 */
	public String getOrd_id() {
		return ord_id;
	}
	/**
	 * @param ord_id the ord_id to set
	 */
	public void setOrd_id(String ord_id) {
		this.ord_id = ord_id;
	}
	/**
	 * @return the product_id
	 */
	public String getProduct_id() {
		return product_id;
	}
	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	/**
	 * @return the ice_id
	 */
	public String getIce_id() {
		return ice_id;
	}
	/**
	 * @param ice_id the ice_id to set
	 */
	public void setIce_id(String ice_id) {
		this.ice_id = ice_id;
	}
	/**
	 * @return the sweet_id
	 */
	public String getSweet_id() {
		return sweet_id;
	}
	/**
	 * @param sweet_id the sweet_id to set
	 */
	public void setSweet_id(String sweet_id) {
		this.sweet_id = sweet_id;
	}
	/**
	 * @return the ord_price
	 */
	public Integer getOrd_price() {
		return ord_price;
	}
	/**
	 * @param ord_price the ord_price to set
	 */
	public void setOrd_price(Integer ord_price) {
		this.ord_price = ord_price;
	}
	/**
	 * @return the tol_cup
	 */
	public Integer getTol_cup() {
		return tol_cup;
	}
	/**
	 * @param tol_cup the tol_cup to set
	 */
	public void setTol_cup(Integer tol_cup) {
		this.tol_cup = tol_cup;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
