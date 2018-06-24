package com.aboutus.model;

import java.util.List;

public class AboutUsService {
	
	private AboutUsDAO_interface dao;
	
	public AboutUsService() {
		dao = new AboutUsDAO();
	}
	
	public AboutUsVO addAboutUs(String about_des, String about_time, String about_phone, String about_add) {

		AboutUsVO aboutUsVO = new AboutUsVO();

		aboutUsVO.setAbout_des(about_des);
		aboutUsVO.setAbout_time(about_time);
		aboutUsVO.setAbout_phone(about_phone);
		aboutUsVO.setAbout_add(about_add);
		dao.insert(aboutUsVO);

		return aboutUsVO;
	}
	
	public AboutUsVO updateAboutUs(String about_id, String about_des, String about_time, String about_phone, String about_add) {

		AboutUsVO aboutUsVO = new AboutUsVO();
		
		aboutUsVO.setAbout_id(about_id);
		aboutUsVO.setAbout_des(about_des);
		aboutUsVO.setAbout_time(about_time);
		aboutUsVO.setAbout_phone(about_phone);
		aboutUsVO.setAbout_add(about_add);
		dao.update(aboutUsVO);

		return aboutUsVO;
	}
	
	public void deleteAboutUs(String about_id) {
		dao.delete(about_id);
	}
	
	public AboutUsVO getOneAboutUs(String about_id) {
		return dao.findByPrimaryKey(about_id);
	}
	
	public List<AboutUsVO> getAll() {
		return dao.getAll();
	}
}	
