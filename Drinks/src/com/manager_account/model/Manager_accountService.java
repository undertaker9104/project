package com.manager_account.model;

import java.util.List;

public class Manager_accountService {

	private Manager_accountDAO_interface dao;

	public Manager_accountService() {
		dao = new Manager_accountDAO();
	}

	public Manager_accountVO addEmp(String accpw, String emp_name, byte[] emp_img , String emp_email) {

		Manager_accountVO manager_accountVO = new Manager_accountVO();

		manager_accountVO.setAccpw(accpw);
		manager_accountVO.setEmp_name(emp_name);
		manager_accountVO.setEmp_img(emp_img);
		manager_accountVO.setEmp_email(emp_email);

		dao.insert(manager_accountVO);

		return manager_accountVO;
	}

	public Manager_accountVO updateEmp(String accpw, String emp_name, byte[] emp_img, Integer man_acc_status,
			String man_acc_id,String emp_email) {

		Manager_accountVO manager_accountVO = new Manager_accountVO();

		manager_accountVO.setAccpw(accpw);
		manager_accountVO.setEmp_name(emp_name);
		manager_accountVO.setMan_acc_status(man_acc_status);
		manager_accountVO.setEmp_img(emp_img);
		manager_accountVO.setMan_acc_id(man_acc_id);
		manager_accountVO.setEmp_email(emp_email);
		dao.update(manager_accountVO);

		return manager_accountVO;
	}

	public void deleteEmp(String man_acc_id) {
		dao.delete(man_acc_id);
	}

	public Manager_accountVO getOneEmp(String man_acc_id) {
		return dao.findByPrimaryKey(man_acc_id);
	}
	
	public Manager_accountVO getOneEmp_name(String emp_name) {
		return dao.findByPrimaryKey_name(emp_name);
	}

	public List<Manager_accountVO> getAll() {
		return dao.getAll();
	}
	
	public List<Manager_accountVO> getOutAll() {
		return dao.getOutAll();
	}
	
	public boolean isManager(String man_acc_id,String accpw){
		return dao.isManager(man_acc_id, accpw);
	}
	public boolean update(String man_acc_id,Integer man_acc_status){
		return dao.update(man_acc_id, man_acc_status);
	}

}
