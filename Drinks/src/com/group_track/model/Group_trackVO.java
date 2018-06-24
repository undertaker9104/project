package com.group_track.model;

import java.io.Serializable;

public class Group_trackVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String mem_id;
	private String grou_id;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getGrou_id() {
		return grou_id;
	}
	public void setGrou_id(String grou_id) {
		this.grou_id = grou_id;
	}

	
	
	
}
