package com.msgboard.model;

import java.util.Base64;

public class MsgBoardVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String msg_board_id;
	private String mem_id;
	private String product_id;
	private String msg_des;
	private Integer msg_status;

	public MsgBoardVo() {
		super();
	}

	public MsgBoardVo(String msg_board_id, String mem_id, String product_id, String msg_des, Integer msg_status) {
		super();
		this.msg_board_id = msg_board_id;
		this.mem_id = mem_id;
		this.product_id = product_id;
		this.msg_des = msg_des;
		this.msg_status = msg_status;
	}

	public Integer getMsg_status() {
		return msg_status;
	}

	public void setMsg_status(Integer msg_status) {
		this.msg_status = msg_status;
	}

	public String getMsg_board_id() {
		return msg_board_id;
	}

	public void setMsg_board_id(String msg_board_id) {
		this.msg_board_id = msg_board_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getMsg_des() {
		return msg_des;
	}

	public void setMsg_des(String msg_des) {
		this.msg_des = msg_des;
	}
	//
	


}
