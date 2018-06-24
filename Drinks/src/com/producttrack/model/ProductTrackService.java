package com.producttrack.model;

import java.util.List;

public class ProductTrackService {
	private ProductTrackDao_interface dao;

	public ProductTrackService() {
		dao = new ProductTrackDao();
	}

	public ProductTrackVo add(String mem_id, String product_id) {
		ProductTrackVo trackVo = new ProductTrackVo();
		trackVo.setMem_id(mem_id);
		trackVo.setProduct_id(product_id);
		dao.add(trackVo);
		return trackVo;
	}

	public void delete(String mem_id, String product_id) {
		ProductTrackVo trackVo = new ProductTrackVo();
		trackVo.setMem_id(mem_id);
		trackVo.setProduct_id(product_id);
		dao.delete(mem_id, product_id);
	}

	public ProductTrackVo getOneByMen(String mem_id) {
		return dao.findByPK(mem_id);
	}

	public List<ProductTrackVo> getAll() {
		return dao.getAll();
	}
	
	public List<ProductTrackVo> getMyAll(String mem_id) {
		return dao.getMyAll(mem_id);
	}

}
