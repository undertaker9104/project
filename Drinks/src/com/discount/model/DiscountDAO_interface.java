package com.discount.model;

import java.io.InputStream;
import java.util.*;

public interface DiscountDAO_interface {
	public void insert(DiscountVO discountVO);
    public void update(DiscountVO discountVO);
    public void delete(String dis_id);
    public DiscountVO findByPrimaryKey(String dis_id);
    public List<DiscountVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
