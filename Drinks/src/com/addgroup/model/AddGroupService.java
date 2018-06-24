package com.addgroup.model;

import java.util.List;

import com.mem.model.MemberVO;

public class AddGroupService {
	private AddGroupDAO_interface dao;

	public AddGroupService() {
		dao = new AddGroupDAO();
	}
	
	public List<AddGroupVO> getAllByMemid(String mem_id){
		return dao.findByMemid(mem_id);
	}
	
	public AddGroupVO getMem_id(String grou_id , String mem_id){
		return dao.addMem_id(grou_id,mem_id);
	}
	public void LeaveGroup(AddGroupVO AddGroupVO){
		dao.leave(AddGroupVO);
	}
	
	public AddGroupVO add(String mem_id,String grou_id){
		AddGroupVO AddGroupVO = new AddGroupVO();

		AddGroupVO.setMem_id(mem_id);
		AddGroupVO.setGrou_id(grou_id);
		

		dao.insert(AddGroupVO);

		return AddGroupVO;
	}
	
}
