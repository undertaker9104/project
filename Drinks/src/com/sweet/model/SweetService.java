package com.sweet.model;
import java.util.List;

public class SweetService {

	private SweetDao_interface dao;
	public SweetService() {
		dao = new SweetDao();
	}

	public SweetVo add(String sweet_des) {
		SweetVo sweetVo = new SweetVo();
		sweetVo.setSweet_des(sweet_des);
		dao.add(sweetVo);
	    return sweetVo;
	}

	public SweetVo update(String sweet_id,String sweet_des) {
		SweetVo sweetVo = new SweetVo();
		sweetVo.setSweet_id(sweet_id);
		sweetVo.setSweet_des(sweet_des);
     	dao.update(sweetVo);
		return sweetVo;
	}

	public SweetVo getOneSweet(String sweet_id) {
		return dao.findByPK(sweet_id);
	}

	public List<SweetVo> getAll() {
		return dao.getAll();
	}

}
