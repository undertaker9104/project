package com.announcement.model;

import java.io.InputStream;
import java.util.*;

public interface AnnouncementDAO_interface {
	public void insert(AnnouncementVO announcementVO);
    public void update(AnnouncementVO announcementVO);
    public void delete(String ann_id);
    public AnnouncementVO findByPrimaryKey(String ann_id);
    public List<AnnouncementVO> getAll();
    public List<AnnouncementVO> getAllDesc();
    public List<AnnouncementVO> getAllAd();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
