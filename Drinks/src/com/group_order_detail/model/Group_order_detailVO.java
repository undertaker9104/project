package com.group_order_detail.model;

import java.io.Serializable;

public class Group_order_detailVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private String grou_id;
	private String mem_id;
	private String product_id;
	private String sweet_id;
	private String ice_id;
	private String ord_id;
	private Integer group_ord_price;
	private Integer group_tol_cup;
	
	public String getGrou_id() {
		return grou_id;
	}
	public void setGrou_id(String grou_id) {
		this.grou_id = grou_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getSweet_id() {
		return sweet_id;
	}
	public void setSweet_id(String sweet_id) {
		this.sweet_id = sweet_id;
	}
	public String getIce_id() {
		return ice_id;
	}
	public void setIce_id(String ice_id) {
		this.ice_id = ice_id;
	}
	public String getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(String ord_id) {
		this.ord_id = ord_id;
	}
	public Integer getGroup_ord_price() {
		return group_ord_price;
	}
	public void setGroup_ord_price(Integer group_ord_price) {
		this.group_ord_price = group_ord_price;
	}
	public Integer getGroup_tol_cup() {
		return group_tol_cup;
	}
	public void setGroup_tol_cup(Integer group_tol_cup) {
		this.group_tol_cup = group_tol_cup;
	}
	
	public Group_order_detailVO(String grou_id, String mem_id, String product_id, String sweet_id, String ice_id,
			String ord_id, Integer group_ord_price, Integer group_tol_cup) {
		super();
		this.grou_id = grou_id;
		this.mem_id = mem_id;
		this.product_id = product_id;
		this.sweet_id = sweet_id;
		this.ice_id = ice_id;
		this.ord_id = ord_id;
		this.group_ord_price = group_ord_price;
		this.group_tol_cup = group_tol_cup;
	}
	public Group_order_detailVO(String grou_id) {
		super();
		this.grou_id = grou_id;
	}
	public Group_order_detailVO() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grou_id == null) ? 0 : grou_id.hashCode());
		result = prime * result + ((ice_id == null) ? 0 : ice_id.hashCode());
		result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
		result = prime * result + ((ord_id == null) ? 0 : ord_id.hashCode());
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
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
		Group_order_detailVO other = (Group_order_detailVO) obj;
		if (grou_id == null) {
			if (other.grou_id != null)
				return false;
		} else if (!grou_id.equals(other.grou_id))
			return false;
		if (ice_id == null) {
			if (other.ice_id != null)
				return false;
		} else if (!ice_id.equals(other.ice_id))
			return false;
		if (mem_id == null) {
			if (other.mem_id != null)
				return false;
		} else if (!mem_id.equals(other.mem_id))
			return false;
		if (ord_id == null) {
			if (other.ord_id != null)
				return false;
		} else if (!ord_id.equals(other.ord_id))
			return false;
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		if (sweet_id == null) {
			if (other.sweet_id != null)
				return false;
		} else if (!sweet_id.equals(other.sweet_id))
			return false;
		return true;
	}
	



	
	
	
}
