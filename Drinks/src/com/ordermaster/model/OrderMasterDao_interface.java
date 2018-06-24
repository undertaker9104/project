package com.ordermaster.model;

import java.util.List;
import java.util.Vector;
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

}
