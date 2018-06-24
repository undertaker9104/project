package com.authority_class.model;

import java.util.List;
import java.util.Set;

import com.manager_account.model.Manager_accountVO;




public class Authority_classService {
	
	private Authority_classDAO_interface dao;

	public Authority_classService() {
		dao = new Authority_classDAO();
	}


	public Authority_classVO addAuthority(String authority_id, String authority_des) {

		Authority_classVO authority_classVO = new Authority_classVO();
		
		authority_classVO.setAuthority_id(authority_id);
		authority_classVO.setAuthority_des(authority_des);
		dao.insert(authority_classVO);
		return authority_classVO;
	}
	
	public List<Authority_classVO> getAll() {
		return dao.getAll();
	}

	public Authority_classVO getOneAuthority(String authority_id) {
		return dao.findByPrimaryKey(authority_id);
	}

	public void deleteAuthority(String authority_id) {
		dao.delete(authority_id);
	}
}
