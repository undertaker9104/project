package com.group_art.model;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Base64;

public class Group_ArtVO implements Serializable{
	private String grou_id;
	private String mem_id;
	private String ord_id;
	private String ship_locat;
	private String send_locat;
	private Timestamp exp_date;
	private byte[] art_img;
	private String art_name;
	private Integer grou_price;
	private Integer grou_status;
	
	
	
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

	public String getOrd_id() {
		return ord_id;
	}

	public void setOrd_id(String ord_id) {
		this.ord_id = ord_id;
	}

	public String getShip_locat() {
		return ship_locat;
	}

	public void setShip_locat(String ship_locat) {
		this.ship_locat = ship_locat;
	}

	public String getSend_locat() {
		return send_locat;
	}

	public void setSend_locat(String send_locat) {
		this.send_locat = send_locat;
	}

	public Timestamp getExp_date() {
		return exp_date;
	}

	public void setExp_date(Timestamp exp_date) {
		this.exp_date = exp_date;
	}

	public byte[] getArt_img() {
		return art_img;
	}

	public void setArt_img(byte[] art_img) {
		this.art_img = art_img;
	}

	public String getArt_name() {
		return art_name;
	}

	public void setArt_name(String art_name) {
		this.art_name = art_name;
	}

	public Integer getGrou_price() {
		return grou_price;
	}

	public void setGrou_price(Integer grou_price) {
		this.grou_price = grou_price;
	}

	public Integer getGrou_status() {
		return grou_status;
	}

	public void setGrou_status(Integer grou_status) {
		this.grou_status = grou_status;
	}

	public String getBase64Image(){
		return Base64.getEncoder().encodeToString(art_img);
	}
	
	public void setImage(byte[] art_img){
		this.art_img = art_img;
	}
	
}
