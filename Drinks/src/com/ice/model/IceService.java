package com.ice.model;
import java.util.List;

public class IceService {

	private IceDao_interface dao;
	public IceService() {
		dao = new IceDao();
	}

	public IceVo add(String ice_des) {
		IceVo iceVo = new IceVo();
		iceVo.setIce_des(ice_des);
		dao.add(iceVo);
	    return iceVo;
	}

	public IceVo update(String ice_id,String ice_des) {
		IceVo iceVo = new IceVo();
		iceVo.setIce_id(ice_id);
		iceVo.setIce_des(ice_des);
	   	dao.update(iceVo);
		return iceVo;
	}

	public IceVo getOneIce(String ice_id) {
		return dao.findByPK(ice_id);
	}

	public List<IceVo> getAll() {
		return dao.getAll();
	}



}
