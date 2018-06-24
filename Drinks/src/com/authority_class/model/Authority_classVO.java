package com.authority_class.model;

import java.io.Serializable;

public class Authority_classVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String authority_id;
	private String authority_des;
	
	public String getAuthority_id() {
		return authority_id;
	}
	public void setAuthority_id(String authority_id) {
		this.authority_id = authority_id;
	}
	public String getAuthority_des() {
		return authority_des;
	}
	public void setAuthority_des(String authority_des) {
		this.authority_des = authority_des;
	}
	
	
}
