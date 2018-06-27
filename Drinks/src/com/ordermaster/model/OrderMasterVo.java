package com.ordermaster.model;

import java.util.List;

public class OrderMasterVo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String ord_id;
	private String mem_id;
	private String man_acc_id;
	private Integer grou;
	private String oute_add;
	private Integer ship_option;
	private Integer ord_status;
	
	//
   private List<CartVO> orderProductList;
	
	public List<CartVO> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<CartVO> orderProductList) {
		this.orderProductList = orderProductList;
	}

	
	
	public OrderMasterVo() {
		super();
		
	}

//
	public OrderMasterVo(String ord_id, String mem_id, String man_acc_id, Integer grou, String oute_add,
			Integer ship_option, Integer ord_status) {
		super();
		this.ord_id = ord_id;
		this.mem_id = mem_id;
		this.man_acc_id = man_acc_id;
		this.grou = grou;
		this.oute_add = oute_add;
		this.ship_option = ship_option;
		this.ord_status = ord_status;
	}


	public String getOrd_id() {
		return ord_id;
	}


	public void setOrd_id(String ord_id) {
		this.ord_id = ord_id;
	}


	public String getMem_id() {
		return mem_id;
	}


	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}


	public String getMan_acc_id() {
		return man_acc_id;
	}


	public void setMan_acc_id(String man_acc_id) {
		this.man_acc_id = man_acc_id;
	}


	public Integer getGrou() {
		return grou;
	}


	public void setGrou(Integer grou) {
		this.grou = grou;
	}


	public String getOute_add() {
		return oute_add;
	}


	public void setOute_add(String oute_add) {
		this.oute_add = oute_add;
	}


	public Integer getShip_option() {
		return ship_option;
	}


	public void setShip_option(Integer ship_option) {
		this.ship_option = ship_option;
	}


	public Integer getOrd_status() {
		return ord_status;
	}


	public void setOrd_status(Integer ord_status) {
		this.ord_status = ord_status;
	}
	
	
	

	

}
