package com.ice.model;

public class IceVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String ice_id;
	private String ice_des;
	
	public IceVo() {
		super();
	}
	public IceVo(String ice_id, String ice_des) {
		super();
		this.ice_id = ice_id;
		this.ice_des = ice_des;
	}
	public String getIce_id() {
		return ice_id;
	}
	public void setIce_id(String ice_id) {
		this.ice_id = ice_id;
	}
	public String getIce_des() {
		return ice_des;
	}
	public void setIce_des(String ice_des) {
		this.ice_des = ice_des;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
