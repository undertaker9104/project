package com.aboutus.model;

public class AboutUsVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String about_id;
	private String about_des;
	private String about_time;
	private String about_phone;
	private String about_add;

	public String getAbout_id() {
		return about_id;
	}

	public void setAbout_id(String about_id) {
		this.about_id = about_id;
	}

	public String getAbout_des() {
		return about_des;
	}

	public void setAbout_des(String about_des) {
		this.about_des = about_des;
	}

	public String getAbout_time() {
		return about_time;
	}

	public void setAbout_time(String about_time) {
		this.about_time = about_time;
	}

	public String getAbout_phone() {
		return about_phone;
	}

	public void setAbout_phone(String about_phone) {
		this.about_phone = about_phone;
	}

	public String getAbout_add() {
		return about_add;
	}

	public void setAbout_add(String about_add) {
		this.about_add = about_add;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
