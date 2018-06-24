package com.addgroup.model;

import java.util.List;

public interface AddGroupDAO_interface {
	public List<AddGroupVO> findByMemid(String mem_id);
	public AddGroupVO addMem_id(String grou_id , String mem_id);
	public void insert(AddGroupVO AddGroupVO);
	public void leave(AddGroupVO AddGroupVO);
}
