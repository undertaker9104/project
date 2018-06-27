package com.ordermaster.model;

import java.util.List;
import java.util.Vector;

import com.orderdetail.model.OrderDetailVo;
import com.shoppingcart.model.*;

public interface OrderMasterDao_interface {
	OrderMasterVo findById(String order_id);
	List<OrderMasterVo> getAll();
	List<OrderMasterVo> getAllByMen(String mem_id);
	String addWithOrderItem(OrderMasterVo orderMaster, List<ShoppingCartVO> buylist);
	public void update(OrderMasterVo ordermaster);
	public void disom(String SQL);
	public void shipom(String SQL);
	public void doneom(String SQL);
	List<OrderMasterVo> getAllByTake();
	List<OrderMasterVo> getAllByOue();
	List<OrderMasterVo> getAll_By_MemId(String mem_id);
	List<OrderMasterVo> getAll_By_ManId(String men_acc_id);
	String add(OrderMasterVo order_masterVO,List<OrderDetailVo> orderDetailList);
	boolean update(String ord_id,Integer ord_status);


}
