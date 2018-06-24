package com.producttrack.model;
import java.util.List;

public interface ProductTrackDao_interface {
	
     public void add(ProductTrackVo proVo);
     public void delete(String mem_id,String pro_id);
     public ProductTrackVo findByPK(String mem_id);
 	 List<ProductTrackVo> getAll();
 	 List<ProductTrackVo> getMyAll(String mem_id);

}
