package com.group_track.model;

import java.util.List;


public class Group_trackService {

	private Group_trackDAO_interface dao;

	public Group_trackService() {
		dao = new Group_trackDAO();
	}
	
	public Group_trackVO insertGroup_track(String mem_id ,String grou_id){
		Group_trackVO group_trackVO = new Group_trackVO();
		group_trackVO.setMem_id(mem_id);
		group_trackVO.setGrou_id(grou_id);
		dao.insert(group_trackVO);
		return group_trackVO;
	}
	
	public void deleteGroup_track(String mem_id,String grou_id){
		dao.delete(mem_id,grou_id);
	}
	
	public List<Group_trackVO> getMem_id(String mem_id){
		return dao.findByMem_id(mem_id);
	}
	
	public List<Group_trackVO> getGrou_id(String grou_id){
		return dao.findByGrou_id(grou_id);
	}
	
	public List<Group_trackVO> getAll() {
		return dao.getAll();
	}
}
