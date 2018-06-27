package com.orderdetail.model;

import java.util.List;
import java.sql.Connection;
import com.group_order_detail.model.Group_order_detailVO;

public class OrderDetailService {
	
	private OrderDetailDao_interface dao;
	public OrderDetailService() {
		dao = new OrderDetailDao();
	}

    public List<Group_order_detailVO> insertgroup(List<Group_order_detailVO> orderdetailList) {
    	
    	System.out.println(orderdetailList.size()+"------------------------------");
    	dao.add(orderdetailList);  	
		return orderdetailList;
	}
   	
	
	public List<OrderDetailVo> getOne(String ord_id) {
		return dao.findByOrderId(ord_id);
	}
	
	public void addWithOrderMaster(OrderDetailVo orderItem, Connection con){
		dao.addWithOrderMaster(orderItem, con);
	}
			
	public List<OrderDetailVo> findByOrderId(String ord_id){
		return dao.findByOrderId(ord_id);
	}


	

}
