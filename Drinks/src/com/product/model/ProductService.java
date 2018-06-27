package com.product.model;

import java.util.List;

public class ProductService {

	private ProductDao_interface dao;

	public ProductService() {
		dao = new ProductDao();
	}

	public ProductVo addProduct(String product_name, String product_class, Integer product_price, String product_des,
			byte[] product_img, Integer product_status) {

		ProductVo productVO = new ProductVo();
		productVO.setProduct_name(product_name);
		productVO.setProduct_cl_id(product_class);
		productVO.setProduct_price(product_price);
		productVO.setProduct_des(product_des);
		productVO.setProduct_img(product_img);
		productVO.setProduct_status(product_status);
		dao.add(productVO);
		return productVO;
	}

	public ProductVo update(String product_id, String product_cl_id, String product_name, Integer product_price,
			String product_des) {

		ProductVo productVO = new ProductVo();
		productVO.setProduct_id(product_id);
		productVO.setProduct_cl_id(product_cl_id);
		productVO.setProduct_name(product_name);
		productVO.setProduct_price(product_price);
		productVO.setProduct_des(product_des);
		dao.update(productVO);
		return productVO;
	}

	public ProductVo updatePhoto(String product_id,byte[] product_img) {
		ProductVo productVO = new ProductVo();
		productVO.setProduct_id(product_id);
		productVO.setProduct_img(product_img);
		dao.updatePhoto(productVO);
		return productVO;
	}

	public ProductVo getOneProduct(String productno) {
		return dao.findByPK(productno);
	}

	public List<ProductVo> getAll() {
		return dao.getAll();
	}
	
	public List<ProductVo> getAllUnload() {
		return dao.getAllUnload();
	}
	
	
	
	public List<ProductVo> getRank() {
		return dao.getrank();
	}

	public List<ProductVo> getFontAll() {
		return dao.getFontAll();
	}
	
	public List<ProductVo> getAllBYCLASS(String productclsno) {
		return dao.getAllByClass(productclsno);
	}
	

	public ProductVo upload(Integer prodect_satus,String product_id) {

		ProductVo productVO = new ProductVo();
		productVO.setProduct_status(prodect_satus);
		productVO.setProduct_id(product_id);
		dao.upload(productVO);
		return productVO;
	}
	
	
	public ProductVo down(Integer prodect_satus,String product_id) {
		ProductVo productVO = new ProductVo();
		productVO.setProduct_status(prodect_satus);
		productVO.setProduct_id(product_id);
		dao.upload(productVO);
		return productVO;
	}

}
