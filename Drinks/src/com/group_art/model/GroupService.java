package com.group_art.model;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class GroupService {
	private Group_ArtDAO_interface dao;
	
	public GroupService(){
		dao = new Group_ArtDAO();
	}
	
	public Group_ArtVO addGroup_Art(String mem_id,String ship_locat,String send_locat,Timestamp exp_date,byte[] art_img,
			String art_name,Integer grou_price) {

		Group_ArtVO group_ArtVO = new Group_ArtVO();
		group_ArtVO.setMem_id(mem_id);
		group_ArtVO.setShip_locat(ship_locat);
		group_ArtVO.setSend_locat(send_locat);
		group_ArtVO.setExp_date(exp_date);
		group_ArtVO.setArt_img(art_img);
		group_ArtVO.setArt_name(art_name);
		group_ArtVO.setGrou_price(grou_price);
		dao.insert(group_ArtVO);

		return group_ArtVO;
	}
	
	
	public List<Group_ArtVO> getAll(){
		return dao.getAll();
	}
	
	public List<Group_ArtVO> getAllForTrack(){
		return dao.getAllForTrack();
	}
	
	public List<Group_ArtVO> getAllByMemId(String mem_id){
		return dao.findByMemId(mem_id);
	}
	
	
	public List<Group_ArtVO> getAllBySr(String art_name){
		return dao.getAllBySr(art_name);
	}
	
	public void deleteGroup(String grou_id){
		dao.delete(grou_id);
	}
	
	public Group_ArtVO getOneArt(String grou_id){
		return dao.getOneArt(grou_id);
	}
	
	public Group_ArtVO updateGrou(String ship_locat,String send_locat,Timestamp exp_date,
			byte[] art_img,String art_name,Integer grou_price,String grou_id,String ord_id){
		Group_ArtVO group_ArtVO = new Group_ArtVO();
		group_ArtVO.setShip_locat(ship_locat);
		group_ArtVO.setSend_locat(send_locat);
		group_ArtVO.setExp_date(exp_date);
		group_ArtVO.setArt_img(art_img);
		group_ArtVO.setArt_name(art_name);
		group_ArtVO.setGrou_price(grou_price);
		group_ArtVO.setGrou_id(grou_id);
		group_ArtVO.setOrd_id(ord_id);
		dao.update(group_ArtVO);
		
		return group_ArtVO;
	}
}
