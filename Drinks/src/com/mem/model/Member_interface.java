package com.mem.model;

import java.util.List;

public interface Member_interface {
	public void insert(MemberVO MemberVO);

	public void update(MemberVO MemberVO);

	public void delete(String mem_id);

	public MemberVO findByPrimaryKey(String mem_email);

	public MemberVO findByPrimaryKey_id(String mem_id);

	public List<MemberVO> getAll();

	public void update_Manager(MemberVO memberVO);
	
	public void update_Point(MemberVO memberVO);
	
    public MemberVO findMem_id(String mem_id);
	
	public MemberVO findimg(String mem_id);

}
