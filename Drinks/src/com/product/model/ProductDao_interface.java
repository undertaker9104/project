package com.product.model;
import java.util.List;
import java.util.Vector;

public interface ProductDao_interface {
	
     public void add(ProductVo productVo);
     public void update(ProductVo productVo);
     public void down(ProductVo productVo);
     public void updatePhoto(ProductVo productVo);
     public ProductVo findByPK(String member);
	 List<ProductVo> getAll();
	 List<ProductVo> getFontAll();
	 List<ProductVo> getBuyAll();
	 List<ProductVo> getAllByClass(String procls);
	 List<ProductVo> getrank();
	 List<ProductVo> getAllUnload();
	 public void upload(ProductVo productVo);

}
