package com.ice.model;
import java.util.List;

public interface IceDao_interface {
	
     public void add(IceVo iceVo);
     public void update(IceVo iceVo);
     public IceVo findByPK(String iceVo);
	 List<IceVo> getAll();


}
