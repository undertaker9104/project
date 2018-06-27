package com.product.model;
import java.util.Base64;

public class ProductVo implements java.io.Serializable {

	private static final long serialVersionUID = -5505664765786868998L;
	private String  product_id;
	private String  product_cl_id;
	private String  product_name;
	private Integer  product_price;
	private String  product_des;
	private byte[]  product_img;
	private Integer  product_status;
	
	private String base64;
	
	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}



	public ProductVo(String product_id, String product_cl_id, String product_name, Integer product_price,
			String product_des, byte[] product_img, Integer product_status) {
		super();
		this.product_id = product_id;
		this.product_cl_id = product_cl_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_des = product_des;
		this.product_img = product_img;
		this.product_status = product_status;
	}

	public ProductVo() {
		super();
	}
	
	public String getProduct_id() {
		return product_id;
	}


	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}


	public String getProduct_cl_id() {
		return product_cl_id;
	}

	public void setProduct_cl_id(String product_cl_id) {
		this.product_cl_id = product_cl_id;
	}

	public String getProduct_name() {
		return product_name;
	}
     public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Integer getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}


	public String getProduct_des() {
		return product_des;
	}

	public void setProduct_des(String product_des) {
		this.product_des = product_des;
	}
  
	public byte[] getProduct_img() {
		return product_img;
	}

	public void setProduct_img(byte[] product_img) {
		this.product_img = product_img;
	}

	public Integer getProduct_status() {
		return product_status;
	}


	public void setProduct_status(Integer product_status) {
		this.product_status = product_status;
	}

	//
	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(product_img);
	}

	public void setImage(byte[] product_img) {
		this.product_img = product_img;
	}

}
