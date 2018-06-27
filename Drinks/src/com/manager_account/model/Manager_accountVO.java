package com.manager_account.model;

import java.util.Base64;

public class Manager_accountVO {
	private String man_acc_id;
	private String accpw;
	private Integer man_acc_status;
	private String emp_name;
	private byte[] emp_img;
	private String emp_email;
	
	//
	private String imgbase64;
	public String getImgbase64() {
		return imgbase64;
	}
	
	public void setImgbase64(String imgbase64) {
		this.imgbase64 = imgbase64;
	}


	public String getMan_acc_id() {
		return man_acc_id;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public void setMan_acc_id(String man_acc_id) {
		this.man_acc_id = man_acc_id;
	}

	public String getAccpw() {
		return accpw;
	}

	public void setAccpw(String accpw) {
		this.accpw = accpw;
	}

	public Integer getMan_acc_status() {
		return man_acc_status;
	}

	public void setMan_acc_status(Integer man_acc_status) {
		this.man_acc_status = man_acc_status;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public byte[] getEmp_img() {
		return emp_img;
	}

	public void setEmp_img(byte[] emp_img) {
		this.emp_img = emp_img;
	}

	public String getImg(byte[] emp_img) {
		String src = null;
		try {
			src = new String(Base64.getEncoder().encode(emp_img));
		} catch (Exception e) {
			src = " ";
		}
		return src;
	}

}
