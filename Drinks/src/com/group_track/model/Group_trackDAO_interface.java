package com.group_track.model;

import java.util.*;

public interface Group_trackDAO_interface {

	public void insert(Group_trackVO group_trackVO);
	public void delete(String mem_id,String grou_id);
	public List<Group_trackVO> findByMem_id(String mem_id);
	public List<Group_trackVO> findByGrou_id(String grou_id);
	
	public List<Group_trackVO> getAll();
	
}
