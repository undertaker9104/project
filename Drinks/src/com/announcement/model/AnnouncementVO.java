package com.announcement.model;

import java.sql.Date;
import java.util.Base64;

public class AnnouncementVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String ann_id;
	private String ann_title;
	private String ann_des;
	private Date ann_date;
	private byte[] ann_img;
	private Integer ann_status;

	
	public String getAnn_id() {
		return ann_id;
	}

	public void setAnn_id(String ann_id) {
		this.ann_id = ann_id;
	}

	public String getAnn_title() {
		return ann_title;
	}

	public void setAnn_title(String ann_title) {
		this.ann_title = ann_title;
	}

	public String getAnn_des() {
		return ann_des;
	}

	public void setAnn_des(String ann_des) {
		this.ann_des = ann_des;
	}

	public Date getAnn_date() {
		return ann_date;
	}

	public void setAnn_date(Date ann_date) {
		this.ann_date = ann_date;
	}

	public byte[] getAnn_img() {
		return ann_img;
	}

	public void setAnn_img(byte[] ann_img) {
		this.ann_img = ann_img;
	}

	public String getBase64Image(){
		return Base64.getEncoder().encodeToString(ann_img);
	}
	
	public void setImage(byte[] ann_img){
		this.ann_img = ann_img;
	}
	
	public Integer getAnn_status() {
		return ann_status;
	}

	public void setAnn_status(Integer ann_status) {
		this.ann_status = ann_status;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
