package com.mem.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Base64;

public class MemberVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String mem_id;
	private String mem_name;
	private String mem_email;
	private String mem_pwd;
	private String mem_sex;
	private Date mem_birth;
	private String mem_phone;
	private String mem_ads;
	private Integer mem_point;
	private Integer mem_int;
	private Integer mem_acc_status;
	private byte[] mem_pic;
	private byte[] mem_qrcode;

	//
	private String imgbase64;
	
	private String qrcodebase64;

	public String getImgbase64() {
		return imgbase64;
	}

	public void setImgbase64(String imgbase64) {
		this.imgbase64 = imgbase64;
	}

	public String getQrcodebase64() {
		return qrcodebase64;
	}

	public void setQrcodebase64(String qrcodebase64) {
		this.qrcodebase64 = qrcodebase64;
	}

	public byte[] getMem_qrcode() {
		return mem_qrcode;
	}

	public void setMem_qrcode(byte[] mem_qrcode) {
		this.mem_qrcode = mem_qrcode;
	}

	public MemberVO() {
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_pwd() {
		return mem_pwd;
	}

	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}

	public String getMem_sex() {
		return mem_sex;
	}

	public void setMem_sex(String mem_sex) {
		this.mem_sex = mem_sex;
	}

	public Date getMem_birth() {
		return mem_birth;
	}

	public void setMem_birth(Date mem_birth) {
		this.mem_birth = mem_birth;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMem_ads() {
		return mem_ads;
	}

	public void setMem_ads(String mem_ads) {
		this.mem_ads = mem_ads;
	}

	public Integer getMem_point() {
		return mem_point;
	}

	public void setMem_point(Integer mem_point) {
		this.mem_point = mem_point;
	}

	public Integer getMem_int() {
		return mem_int;
	}

	public void setMem_int(Integer mem_int) {
		this.mem_int = mem_int;
	}

	public Integer getMem_acc_status() {
		return mem_acc_status;
	}

	public void setMem_acc_status(Integer mem_acc_status) {
		this.mem_acc_status = mem_acc_status;
	}

	public byte[] getMem_pic() {
		return mem_pic;
	}

	public void setMem_pic(byte[] mem_pic) {
		this.mem_pic = mem_pic;
	}

	public String getImg(byte[] mem_pic) {
		String src = null;
		try {
			src = new String(Base64.getEncoder().encode(mem_pic));
		} catch (Exception e) {
			src = " ";
		}
		return src;
	}

	public String getImg2(byte[] mem_qrcode) {
		String src = null;
		try {
			src = new String(Base64.getEncoder().encode(mem_qrcode));
		} catch (Exception e) {
			src = " ";
			e.getMessage();
		}
		return src;
	}

	//
	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(mem_pic);
	}

	public void setImage(byte[] mem_pic) {
		this.mem_pic = mem_pic;
	}
}
