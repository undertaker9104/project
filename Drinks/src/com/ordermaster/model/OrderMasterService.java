package com.ordermaster.model;

import java.util.List;

import com.orderdetail.model.OrderDetailVo;
import com.shoppingcart.model.*;

public class OrderMasterService {
	private OrderMasterDao_interface dao;
	public OrderMasterService() {
		dao = new OrderMasterDao();
	}
	

	public OrderMasterVo addItem(String mem_id, Integer grou
			                     , String oute_add, Integer ship_option,
			                     Integer ord_status, List<ShoppingCartVO> buylist) {
		OrderMasterVo orderMasterVO = new OrderMasterVo();
		orderMasterVO.setMem_id(mem_id);
	    orderMasterVO.setGrou(grou);
		orderMasterVO.setOute_add(oute_add);
		orderMasterVO.setShip_option(ship_option);
		orderMasterVO.setOrd_status(ord_status);
		dao.addWithOrderItem(orderMasterVO, buylist);
		return orderMasterVO;
	}

	public OrderMasterVo update(Integer ord_status, String ord_id) {
		OrderMasterVo masterVO = new OrderMasterVo();
		masterVO.setOrd_status(ord_status);
		masterVO.setOrd_id(ord_id);
		dao.update(masterVO);
		return masterVO;
	}
	
	
	public void disom(String SQL) {
	   dao.disom(SQL);
	}
	
	public void shipom(String SQL) {
		   dao.shipom(SQL);
	}
	
	public void doneom(String SQL) {
		   dao.doneom(SQL);
	}

	public OrderMasterVo getOne(String ord_id) {
		return dao.findById(ord_id);
	}

	public List<OrderMasterVo> getMyOrderMaster(String mem_id) {
		return dao.getAllByMen(mem_id);
	}

	public List<OrderMasterVo> getAll() {
		return dao.getAll();
	}
	
	public List<OrderMasterVo> getAllByTake() {
		return dao.getAllByTake();
	}
	
	public List<OrderMasterVo> getAllByOut() {
		return dao.getAllByOue();
				
	}
	
	public boolean update(String ord_id,Integer ord_status){
		return dao.update(ord_id,ord_status);
	}
	//Android使用
	public String add(OrderMasterVo order_masterVO,List<OrderDetailVo> orderDetailList){
		return dao.add(order_masterVO, orderDetailList);
	}
	//Android使用
		public List<OrderMasterVo> getAll_By_MemId(String mem_id){
			return dao.getAll_By_MemId(mem_id);
	}
	//Android使用
	public List<OrderMasterVo> getAll_By_ManId(String men_acc_id){
		return dao. getAll_By_ManId(men_acc_id);
	}


}
