package com.productclass.model;
import java.util.List;

public interface ProductClassDao_interface {
	
	 public void add(ProductClassVo producclassVo);
     public void update(ProductClassVo producclassVo);
     public void down(ProductClassVo producclassVo);
     public ProductClassVo findByPK(String procl_id);
	 List<ProductClassVo> getAll();
	 List<ProductClassVo> getAllFront();
	 List<ProductClassVo> getAllUnload();
	 public void upload(ProductClassVo producclassVo);

}
