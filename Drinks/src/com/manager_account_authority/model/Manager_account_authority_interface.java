	package com.manager_account_authority.model;

import java.util.List;




public interface Manager_account_authority_interface {
	 public void insert(Manager_account_authorityVO manager_account_authorityVO);
     public void update(Manager_account_authorityVO manager_account_authorityVO);
     public void delete(String man_acc_id);
     public Manager_account_authorityVO findByPrimaryKey(String authority_id);
     public List<String> findByPrimaryKey_empID(String man_acc_id);
     public List<String> findByPrimaryKey_authID(String authority_id);
     public List<Manager_account_authorityVO> getAll();
     public Manager_account_authorityVO findByPrimaryKey_OUT(String man_acc_id);

    
}
