package com.orderdetail.model;

import java.sql.Connection;
import java.util.List;

import com.group_order_detail.model.Group_order_detailVO;



public interface OrderDetailDao_interface {
	
	void addWithOrderMaster(OrderDetailVo orderItem, Connection con);
	List<OrderDetailVo> findByOrderId(String ord_id);
	
	public void insert(OrderDetailVo orderdetailVo);
	public void add(List<Group_order_detailVO> orderdetailList);


}
