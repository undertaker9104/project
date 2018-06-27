package com.announcement.model;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

public class AnnouncementService {

	private AnnouncementDAO_interface dao;

	public AnnouncementService() {
		dao = new AnnouncementDAO();
	}

	public AnnouncementVO addAnnouncement(String ann_title, String ann_des, Date ann_date, byte[] ann_img, Integer ann_status) {

		AnnouncementVO announcementVO = new AnnouncementVO();

		announcementVO.setAnn_title(ann_title);
		announcementVO.setAnn_des(ann_des);
		announcementVO.setAnn_date(ann_date);
		announcementVO.setAnn_img(ann_img);
		announcementVO.setAnn_status(ann_status);
		dao.insert(announcementVO);
		return announcementVO;
	}

	public AnnouncementVO updateAnnouncement(String ann_id, String ann_title, String ann_des, Date ann_date, byte[] ann_img, Integer ann_status) {

		AnnouncementVO announcementVO = new AnnouncementVO();

		announcementVO.setAnn_id(ann_id);
		announcementVO.setAnn_title(ann_title);
		announcementVO.setAnn_des(ann_des);	
		announcementVO.setAnn_date(ann_date);
		announcementVO.setAnn_img(ann_img);
		announcementVO.setAnn_status(ann_status);
		dao.update(announcementVO);
		return announcementVO;
	}

	public void deleteAnnouncement(String ann_id) {
		dao.delete(ann_id);
	}

	public AnnouncementVO getOneAnnouncement(String ann_id) {
		return dao.findByPrimaryKey(ann_id);
	}

	public List<AnnouncementVO> getAll() {
		return dao.getAll();
	}
	
	public List<AnnouncementVO> getAllDesc() {
		return dao.getAllDesc();
	}
	
	public List<AnnouncementVO> getAllAd() {
		return dao.getAllAd();
	}
}
