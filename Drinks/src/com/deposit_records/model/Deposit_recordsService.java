package com.deposit_records.model;

import java.sql.Date;
import java.util.List;

public class Deposit_recordsService {
	private Deposit_records_interface dao;

	public Deposit_recordsService() {
		dao = new Deposit_recordsDAO();
	}

	public Deposit_recordsVO addRecords(String mem_id, Integer dep_cash, Date dep_suss_date) {

		Deposit_recordsVO deposit_recordsVO = new Deposit_recordsVO();

		deposit_recordsVO.setMem_id(mem_id);
		deposit_recordsVO.setDep_cash(dep_cash);
		deposit_recordsVO.setDep_suss_date(dep_suss_date);
		dao.insert(deposit_recordsVO);

		return deposit_recordsVO;
	}

	public Deposit_recordsVO updateRecords(String mem_id, String dep_rec_id, Integer dep_cash, Date dep_suss_date) {

		Deposit_recordsVO deposit_recordsVO = new Deposit_recordsVO();
		deposit_recordsVO.setDep_rec_id(dep_rec_id);
		deposit_recordsVO.setMem_id(mem_id);
		deposit_recordsVO.setDep_cash(dep_cash);
		deposit_recordsVO.setDep_suss_date(dep_suss_date);

		dao.update(deposit_recordsVO);

		return deposit_recordsVO;
	}

	public void deleteRecords(String dep_rec_id) {
		dao.delete(dep_rec_id);
	}

	public Deposit_recordsVO getOneRecords(String dep_rec_id) {
		return dao.findByPrimaryKey(dep_rec_id);
	}
	
	public Deposit_recordsVO getOneRecords_id(String mem_id) {
		return dao.findByPrimaryKey_MemId(mem_id);
	}
	
	public List<Deposit_recordsVO> getOneRecords_MemIdList(String mem_id) {
		return dao.findByPrimaryKey_MemIdList(mem_id);
	}

	public List<Deposit_recordsVO> getAll() {
		return dao.getAll();
	}
	public List<Deposit_recordsVO> getAll_MemId() {
		return dao.getAll_MemId();
	}
}
