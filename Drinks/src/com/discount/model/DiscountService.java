package com.discount.model;

import java.io.InputStream;
import java.util.List;

public class DiscountService {

	private DiscountDAO_interface dao;

	public DiscountService() {
		dao = new DiscountDAO();
	}

	public DiscountVO addDiscount(String dis_des, Integer dis_cup, Double dis_cup_rate, Integer dis_price, Double dis_price_rate) {

		DiscountVO discountVO = new DiscountVO();

		discountVO.setDis_des(dis_des);
		discountVO.setDis_cup(dis_cup);
		discountVO.setDis_cup_rate(dis_cup_rate);
		discountVO.setDis_price(dis_price);
		discountVO.setDis_price_rate(dis_price_rate);
		dao.insert(discountVO);

		return discountVO;
	}

	public DiscountVO updateDiscount(String dis_id, String dis_des, Integer dis_cup, Double dis_cup_rate, Integer dis_price, Double dis_price_rate) {

		DiscountVO discountVO = new DiscountVO();

		discountVO.setDis_id(dis_id);
		discountVO.setDis_des(dis_des);
		discountVO.setDis_cup(dis_cup);
		discountVO.setDis_cup_rate(dis_cup_rate);
		discountVO.setDis_price(dis_price);
		discountVO.setDis_price_rate(dis_price_rate);
		dao.update(discountVO);
		return discountVO;
	}

	public void deleteDiscount(String dis_id) {
		dao.delete(dis_id);
	}

	public DiscountVO getOneDiscount(String dis_id) {
		return dao.findByPrimaryKey(dis_id);
	}

	public List<DiscountVO> getAll() {
		return dao.getAll();
	}
}
