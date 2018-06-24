package com.sweet.model;
import java.util.List;

public interface SweetDao_interface {
	
     public void add(SweetVo sweetVo);
     public void update(SweetVo sweetVo);
     public SweetVo findByPK(String sweet_id);
	 List<SweetVo> getAll();

}
