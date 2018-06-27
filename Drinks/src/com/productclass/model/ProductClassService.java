package com.productclass.model;

import java.util.List;

public class ProductClassService {

	private ProductClassDao_interface dao;

	public ProductClassService() {
		dao = new ProductClassDao();
	}

	public ProductClassVo add(String product_class_name,Integer product_class_status) {
		ProductClassVo proClsVO = new ProductClassVo();
		proClsVO.setProduct_cl_name(product_class_name);
		proClsVO.setProduct_cl_status(product_class_status);
		dao.add(proClsVO);
		return proClsVO;
	}

	public ProductClassVo update(String product_class_id,String product_class_name) {
		ProductClassVo productClassVO = new ProductClassVo();
		productClassVO.setProduct_cl_id(product_class_id);
		productClassVO.setProduct_cl_name(product_class_name);
		dao.update(productClassVO);
		return productClassVO;
	}

	public ProductClassVo getOne(String product_class_id) {
		return dao.findByPK(product_class_id);
	}

	public List<ProductClassVo> getAll() {
		return dao.getAll();
	}
	
	public List<ProductClassVo> getAllFront() {
		return dao.getAllFront();
	}
	public List<ProductClassVo> getAllUnload() {
		return dao.getAllUnload();
	}
	
	public ProductClassVo upload(String product_class_id,Integer product_class_status) {
		ProductClassVo productClassVO = new ProductClassVo();
		productClassVO.setProduct_cl_id(product_class_id);
	    productClassVO.setProduct_cl_status(product_class_status);
		dao.upload(productClassVO);
		return productClassVO;
	}
	
	
	public ProductClassVo down(String product_class_id,Integer product_class_status) {
		ProductClassVo productClassVO = new ProductClassVo();
		productClassVO.setProduct_cl_id(product_class_id);
	    productClassVO.setProduct_cl_status(product_class_status);
		dao.down(productClassVO);
		return productClassVO;
	}
	
	
}
