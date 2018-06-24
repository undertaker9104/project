package com.manager_account_authority.model;

import java.io.Serializable;

public class Manager_account_authorityVO implements Serializable{
	

	private static final long serialVersionUID = 1L;
		private String man_acc_id ; 
		private String authority_id;
		
		public String getMan_acc_id() {
			return man_acc_id;
		}
		public void setMan_acc_id(String man_acc_id) {
			this.man_acc_id = man_acc_id;
		}
		public String getAuthority_id() {
			return authority_id;
		}
		public void setAuthority_id(String authority_id) {
			this.authority_id = authority_id;
		}
		
		
}
