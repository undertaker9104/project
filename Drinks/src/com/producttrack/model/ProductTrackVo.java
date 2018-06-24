package com.producttrack.model;

public class ProductTrackVo implements java.io.Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
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
		ProductTrackVo other = (ProductTrackVo) obj;
		if (mem_id == null) {
			if (other.mem_id != null)
				return false;
		} else if (!mem_id.equals(other.mem_id))
			return false;
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		return true;
	}


	private static final long serialVersionUID = 1L;
	private String mem_id;
	private String product_id;
	
	public ProductTrackVo(String mem_id, String product_id) {
		super();
		this.mem_id = mem_id;
		this.product_id = product_id;
	}

	public ProductTrackVo() {
		super();
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	

}
