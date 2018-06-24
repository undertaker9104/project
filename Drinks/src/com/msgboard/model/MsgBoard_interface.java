package com.msgboard.model;
import java.util.List;

public interface MsgBoard_interface {
	 public void add(MsgBoardVo msgVo);
     public void update(MsgBoardVo msgVo);
     public MsgBoardVo findByPK(String msgVo);
	 List<MsgBoardVo> getAll();
	 List<MsgBoardVo> getFrontAll();
}
