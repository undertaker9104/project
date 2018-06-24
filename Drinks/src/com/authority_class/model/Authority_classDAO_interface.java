package com.authority_class.model;

import java.util.List;
import java.util.Set;

import com.manager_account.model.Manager_accountVO;


public interface Authority_classDAO_interface {
	  public void insert(Authority_classVO authority_classVO);
      public void delete(String authority_id);
      public Authority_classVO findByPrimaryKey(String authority_id);
      public List<Authority_classVO> getAll();
}
