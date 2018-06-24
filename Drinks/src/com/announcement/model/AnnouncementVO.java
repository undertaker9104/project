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

	public String getImg(byte[] ann_img) {
		String src = null;
		try {
			src = new String(Base64.getEncoder().encode(ann_img));
		} catch (Exception e) {
			src = " ";
		}
		return src;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
