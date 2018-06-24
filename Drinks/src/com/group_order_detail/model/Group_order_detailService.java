package com.group_order_detail.model;

import java.util.List;


public class Group_order_detailService {

	private Group_order_detailDAO_interface dao;

	public Group_order_detailService() {
		dao = new Group_order_detailDAO();
	}

	public Group_order_detailVO insertGroup_order_detail(String grou_id, String mem_id, String product_id,
			String sweet_id, String ice_id, String ord_id, Integer group_ord_price, Integer group_tol_cup) {
		Group_order_detailVO group_order_detailVO = new Group_order_detailVO(grou_id, mem_id, product_id, sweet_id, ice_id,ord_id,group_ord_price,group_tol_cup);
		group_order_detailVO.setGrou_id(grou_id);
		group_order_detailVO.setMem_id(mem_id);
		group_order_detailVO.setProduct_id(product_id);
		group_order_detailVO.setSweet_id(sweet_id);
		group_order_detailVO.setIce_id(ice_id);
		group_order_detailVO.setOrd_id(ord_id);
		group_order_detailVO.setGroup_ord_price(group_ord_price);
		group_order_detailVO.setGroup_tol_cup(group_tol_cup);
		dao.insert(group_order_detailVO);
		return group_order_detailVO;
	}

	public Group_order_detailVO updateGroup_order_detail(String grou_id, String mem_id, String product_id,
			String sweet_id, String ice_id, String ord_id, Integer group_ord_price, Integer group_tol_cup) {
		Group_order_detailVO group_order_detailVO1 = new Group_order_detailVO(grou_id, mem_id, product_id, sweet_id, ice_id,ord_id,group_ord_price,group_tol_cup);

		group_order_detailVO1.setGrou_id(grou_id);
		group_order_detailVO1.setMem_id(mem_id);
		group_order_detailVO1.setProduct_id(product_id);
		group_order_detailVO1.setSweet_id(sweet_id);
		group_order_detailVO1.setIce_id(ice_id);
		group_order_detailVO1.setOrd_id(ord_id);
		group_order_detailVO1.setGroup_ord_price(group_ord_price);
		group_order_detailVO1.setGroup_tol_cup(group_tol_cup);

		dao.update(group_order_detailVO1);
		return group_order_detailVO1;
	}
	
	public void deleteGroup_order_detail(String grou_id,String mem_id,String product_id,String sweet_id,String ice_id,String ord_id){
		dao.delete(grou_id,mem_id,product_id,sweet_id,ice_id,ord_id);
	}
	
	public void deleteByMem_id(String mem_id){
		dao.deleteByMem_id(mem_id);
	}
	
	public List<Group_order_detailVO> getGrou_id(String grou_id){
		return dao.findByGrou_id(grou_id);
	}
	
	public Group_order_detailVO getByGrou_id(String grou_id){
		return dao.findGrou_id(grou_id);
	}
    public List<Group_order_detailVO> getMemEmail(String grou_id){
		
		return dao.findMem(grou_id);
	}
	public Group_order_detailVO getByMem_id(String mem_id){
		return dao.findMem_id(mem_id);
	}
	
	public List<Group_order_detailVO> getAll() {
		return dao.getAll();
	}
	public Group_order_detailVO getThreeInfo(String grou_id){
		return dao.getThreeInfo(grou_id);
	}
	public List<Group_order_detailVO> getPrice(String mem_id,String grou_id){
		return dao.findMemPrice(mem_id,grou_id);
	}
	public List<Group_order_detailVO> getMem_id(String grou_id){
		return dao.everymem_id(grou_id);
	}
}
