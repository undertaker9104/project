package com.int_exc.model;


import java.util.List;


public class Int_excService {

	private Int_exc_interface dao;

	public Int_excService() {
		dao = new Int_excDAO();
	}

	public Int_excVO addInt_exc(String mem_id, java.sql.Date int_exc_date,
			Integer integral) {

		Int_excVO int_excVO = new Int_excVO();
		
		int_excVO.setMem_id(mem_id);
		int_excVO.setInt_exc_date(int_exc_date);
		int_excVO.setIntegral(integral);
		dao.insert(int_excVO);

		return int_excVO;
	}

	public Int_excVO updateInt_exc(String mem_id, String int_exc_rec_id, java.sql.Date int_exc_date,
			Integer integral) {

		Int_excVO int_excVO = new Int_excVO();

		int_excVO.setMem_id(mem_id);
		int_excVO.setInt_exc_rec_id(int_exc_rec_id);
		int_excVO.setInt_exc_date(int_exc_date);
		int_excVO.setIntegral(integral);
		dao.update(int_excVO);

		return int_excVO;
	}

	public void deleteInt_exc(String int_exc_rec_id) {
		dao.delete(int_exc_rec_id);
	}

	public Int_excVO getOneInt_exc_rec_id(String int_exc_rec_id) {
		return dao.findByPrimaryKeyInt_exc_rec_id(int_exc_rec_id);
	}
	
	public Int_excVO getOneBymem_id(String mem_id) {
		return dao.findByPrimaryKeyMem_id(mem_id);
	}

	public List<Int_excVO> getAll() {
		return dao.getAll();
	}
}
