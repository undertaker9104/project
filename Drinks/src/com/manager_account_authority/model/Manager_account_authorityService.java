package com.manager_account_authority.model;

import java.util.List;


public class Manager_account_authorityService {
	private Manager_account_authority_interface dao;


	public Manager_account_authorityService() {
		dao = new Manager_account_authorityDAO();
	}

	public Manager_account_authorityVO addManager_account_authority(String man_acc_id, String authority_id) {

		Manager_account_authorityVO manager_account_authorityVO = new Manager_account_authorityVO();

		manager_account_authorityVO.setAuthority_id(authority_id);
		manager_account_authorityVO.setMan_acc_id(man_acc_id);
	
		dao.insert(manager_account_authorityVO);

		return manager_account_authorityVO;
	}

	public Manager_account_authorityVO updateEmp(String man_acc_id, String authority_id) {

		Manager_account_authorityVO manager_account_authorityVO = new Manager_account_authorityVO();

		manager_account_authorityVO.setAuthority_id(authority_id);
		manager_account_authorityVO.setMan_acc_id(man_acc_id);
		dao.update(manager_account_authorityVO);

		return manager_account_authorityVO;
	}

	public void delete(String man_acc_id) {
		dao.delete(man_acc_id);
	}

	public Manager_account_authorityVO getOneEmp(String authority_id) {
		return dao.findByPrimaryKey(authority_id);
	}
	
	public List<String> getOneManager_account_authority(String man_acc_id) {
		return dao.findByPrimaryKey_empID(man_acc_id);
	}
	
	public List<String> getAllEmpAuthority(String authority_id) {
		return dao.findByPrimaryKey_authID(authority_id);
	}

	public List<Manager_account_authorityVO> getAll() {
		return dao.getAll();
	}
	
	public Manager_account_authorityVO getOutManager_account(String man_acc_id) {
		return dao.findByPrimaryKey_OUT(man_acc_id);
	
	}
	
		
}
