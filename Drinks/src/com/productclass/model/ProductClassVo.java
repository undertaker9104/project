package com.productclass.model;


public class ProductClassVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String product_cl_id;
	
	private String product_cl_name;
	
	private Integer product_cl_status;
	
	

	public ProductClassVo() {
		super();
	}

	public ProductClassVo(String product_cl_id, String product_cl_name, Integer product_cl_status) {
		super();
		this.product_cl_id = product_cl_id;
		this.product_cl_name = product_cl_name;
		this.product_cl_status = product_cl_status;
	}

	/**
	 * @return the product_cl_id
	 */
	public String getProduct_cl_id() {
		return product_cl_id;
	}

	/**
	 * @param product_cl_id the product_cl_id to set
	 */
	public void setProduct_cl_id(String product_cl_id) {
		this.product_cl_id = product_cl_id;
	}

	/**
	 * @return the product_cl_name
	 */
	public String getProduct_cl_name() {
		return product_cl_name;
	}

	/**
	 * @param product_cl_name the product_cl_name to set
	 */
	public void setProduct_cl_name(String product_cl_name) {
		this.product_cl_name = product_cl_name;
	}

	/**
	 * @return the product_cl_status
	 */
	public Integer getProduct_cl_status() {
		return product_cl_status;
	}

	/**
	 * @param product_cl_status the product_cl_status to set
	 */
	public void setProduct_cl_status(Integer product_cl_status) {
		this.product_cl_status = product_cl_status;
	}
	


}
