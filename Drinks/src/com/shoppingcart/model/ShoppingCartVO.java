package com.shoppingcart.model;

import java.util.Base64;

public class ShoppingCartVO implements Comparable<ShoppingCartVO> ,java.io.Serializable {

	private static final long serialVersionUID = 1L;
	String addProduct_id;
	String product_name;
	Integer product_price;
	String product_des;
	private byte[]  product_img;
	Integer quantity;
	String ice_id;
	String sweet_id;
	String mem_id;
	
	
	public ShoppingCartVO() {
		super();
	}
	

	
	public ShoppingCartVO(String addProduct_id, String product_name, Integer product_price, String product_des, byte[] product_img,
			Integer quantity, String ice_id, String sweet_id, String mem_id) {
		super();
		this.addProduct_id = addProduct_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_des = product_des;
		this.product_img = product_img;
		this.quantity = quantity;
		this.ice_id = ice_id;
		this.sweet_id = sweet_id;
		this.mem_id = mem_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addProduct_id == null) ? 0 : addProduct_id.hashCode());
		result = prime * result + ((ice_id == null) ? 0 : ice_id.hashCode());
		result = prime * result + ((sweet_id == null) ? 0 : sweet_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartVO other = (ShoppingCartVO) obj;
		if (addProduct_id == null) {
			if (other.addProduct_id != null)
				return false;
		} else if (!addProduct_id.equals(other.addProduct_id))
			return false;
		if (ice_id == null) {
			if (other.ice_id != null)
				return false;
		} else if (!ice_id.equals(other.ice_id))
			return false;
		if (sweet_id == null) {
			if (other.sweet_id != null)
				return false;
		} else if (!sweet_id.equals(other.sweet_id))
			return false;
		return true;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(product_img);
	}

	public void setImage(byte[] product_img) {
		this.product_img = product_img;
	}

	public String getAddProduct_id() {
		return addProduct_id;
	}

	public void setAddProduct_id(String addProduct_id) {
		this.addProduct_id = addProduct_id;
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
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getIce_id() {
		return ice_id;
	}
	
	public void setIce_id(String ice_id) {
		this.ice_id = ice_id;
	}

	public String getSweet_id() {
		return sweet_id;
	}

	public void setSweet_id(String sweet_id) {
		this.sweet_id = sweet_id;
	}
	@Override
	public int compareTo(ShoppingCartVO o) {
		return 0;
	}

}
