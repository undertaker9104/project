package com.aboutus.model;

import java.util.*;

public interface AboutUsDAO_interface {
	public void insert(AboutUsVO aboutUsVO);
    public void update(AboutUsVO aboutUsVO);
    public void delete(String about_id);
    public AboutUsVO findByPrimaryKey(String about_id);
    public List<AboutUsVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
