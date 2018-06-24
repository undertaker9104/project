package com.point_exc_cash.model;

import java.util.List;


public interface Point_exc_cash_interface {
	 public void insert(Point_exc_cashVO point_exc_cashVO);
     public void update(Point_exc_cashVO point_exc_cashVO);
     public void delete(String exc_rec_id);
     public List<Point_exc_cashVO> findByPrimaryKey(String mem_id);
     public Point_exc_cashVO findByPrimaryKeyVO(String mem_id);
     public Point_exc_cashVO findByPrimaryKeyVO_exc_rec_id(String exc_rec_id);
     public List<Point_exc_cashVO> getAll();
     public List<Point_exc_cashVO> getAll_Moderated();
     public List<Point_exc_cashVO> getAll_Complete();
}
