package com.msgboard.model;
import java.util.List;

public class MsgBoardService {

	private MsgBoard_interface dao;
	public MsgBoardService() {
		dao = new MsgBoardDao();
	}

	public MsgBoardVo addMsg(String mem_id,String product_id,String msg_des ,Integer msg_status) {
		MsgBoardVo msgVo = new MsgBoardVo();
		msgVo.setMem_id(mem_id);
		msgVo.setProduct_id(product_id);
		msgVo.setMsg_des(msg_des);
		msgVo.setMsg_status(msg_status);
		dao.add(msgVo);
	    return msgVo;
	}

	public MsgBoardVo updateMsg(String msg_board_id,Integer msg_status) {
		MsgBoardVo msgVo = new MsgBoardVo();
		msgVo.setMsg_board_id(msg_board_id);
		msgVo.setMsg_status(msg_status);
	   	dao.update(msgVo);
		return msgVo;
	}

	public MsgBoardVo getOneMsg(String msg_board_id) {
		return dao.findByPK(msg_board_id);
	}
	

	public List<MsgBoardVo> getAll() {
		return dao.getAll();
	}
	
	public List<MsgBoardVo> getFrontAll() {
		return dao.getFrontAll();
	}

}
