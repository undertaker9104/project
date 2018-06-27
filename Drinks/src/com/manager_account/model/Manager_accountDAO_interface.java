package com.manager_account.model;

import java.util.List;

public interface Manager_accountDAO_interface {
	public void insert(Manager_accountVO manager_accountVO);

	public void update(Manager_accountVO manager_accountVO);

	public void delete(String man_acc_id);

	public Manager_accountVO findByPrimaryKey(String man_acc_id);
	
	public Manager_accountVO findByPrimaryKey_name(String emp_name);

	public List<Manager_accountVO> getAll();
	public List<Manager_accountVO> getOutAll();
	
	boolean isManager(String man_acc_id,String accpw);
	
	boolean update(String man_acc_id,Integer man_acc_status);

		
}
