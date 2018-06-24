package com.point_exc_cash.model;

import java.util.List;


public class Point_exc_cashService {
	private Point_exc_cash_interface dao;

	public Point_exc_cashService() {
		dao = new Point_exc_cashDAO();
	}

	public Point_exc_cashVO addPoint_exc_cash( String mem_id, java.sql.Date exc_date, Integer exc_cash,
			Integer bank_acc) {

		Point_exc_cashVO point_exc_cashVO = new Point_exc_cashVO();		
		point_exc_cashVO.setMem_id(mem_id);
		point_exc_cashVO.setExc_date(exc_date);
		point_exc_cashVO.setExc_cash(exc_cash);
		point_exc_cashVO.setBank_acc(bank_acc);
		dao.insert(point_exc_cashVO);
		return point_exc_cashVO;
	}

	public Point_exc_cashVO updateEmp(String exc_rec_id, Integer req_status) {
			
		Point_exc_cashVO point_exc_cashVO = new Point_exc_cashVO();
		point_exc_cashVO.setExc_rec_id(exc_rec_id);
		point_exc_cashVO.setReq_status(req_status);
		dao.update(point_exc_cashVO);

		return point_exc_cashVO;
	}

	public void deleteEmp(String exc_rec_id) {
		dao.delete(exc_rec_id);
	}

	public List<Point_exc_cashVO> getOnePoint_exc_cash(String mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}
	
	public Point_exc_cashVO getOnePoint_exc_cashVO(String mem_id) {
		return dao.findByPrimaryKeyVO(mem_id);
	}
	public Point_exc_cashVO getOnePoint_exc_cash_(String exc_rec_id) {
		return dao.findByPrimaryKeyVO_exc_rec_id(exc_rec_id);
	}

	public List<Point_exc_cashVO> getAll() {
		return dao.getAll();
	}
	
	public List<Point_exc_cashVO> getAll_Moderated() {
		return dao.getAll_Moderated();
	}
	public List<Point_exc_cashVO> getAll_Complete() {
		return dao.getAll_Complete();
	}
}
