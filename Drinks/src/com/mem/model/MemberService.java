package com.mem.model;

import java.sql.Connection;
import java.util.List;

public class MemberService {
	private Member_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMem(String mem_name, String mem_email, String mem_pwd, String mem_sex, java.sql.Date mem_birth,
			String mem_phone, String mem_ads, byte[] mem_pic, byte[] mem_qrcode) {

		MemberVO MemberVO = new MemberVO();

		MemberVO.setMem_name(mem_name);
		MemberVO.setMem_email(mem_email);
		MemberVO.setMem_pwd(mem_pwd);
		MemberVO.setMem_sex(mem_sex);
		MemberVO.setMem_birth(mem_birth);
		MemberVO.setMem_phone(mem_phone);
		MemberVO.setMem_ads(mem_ads);
		MemberVO.setMem_pic(mem_pic);
		MemberVO.setMem_qrcode(mem_qrcode);

		dao.insert(MemberVO);

		return MemberVO;
	}

	public MemberVO updateMem(String mem_pwd, String mem_phone, String mem_ads, byte[] mem_pic, String getMem_email) {
		MemberVO MemberVO = new MemberVO();

		MemberVO.setMem_pwd(mem_pwd);
		MemberVO.setMem_phone(mem_phone);
		MemberVO.setMem_ads(mem_ads);
		MemberVO.setMem_pic(mem_pic);
		MemberVO.setMem_email(getMem_email);
		dao.update(MemberVO);

		return MemberVO;
	}

	public MemberVO updateMem_Manager(String mem_id, Integer mem_point, Integer mem_int, Integer mem_acc_status,
			byte[] mem_pic) {
		MemberVO MemberVO = new MemberVO();

		MemberVO.setMem_id(mem_id);
		MemberVO.setMem_point(mem_point);
		MemberVO.setMem_int(mem_int);
		MemberVO.setMem_acc_status(mem_acc_status);
		MemberVO.setMem_pic(mem_pic);
		dao.update_Manager(MemberVO);

		return MemberVO;
	}

	public MemberVO updateMem_Point(String mem_id, Integer mem_point) {
		MemberVO MemberVO = new MemberVO();

		MemberVO.setMem_id(mem_id);
		MemberVO.setMem_point(mem_point);
		dao.update_Point(MemberVO);

		return MemberVO;
	}

	public void deleteMem(String mem_id) {
		dao.delete(mem_id);
	}

	public MemberVO getOneMem(String mem_email) {
		return dao.findByPrimaryKey(mem_email);
	}

	public MemberVO getOneMem_id(String mem_id) {
		return dao.findByPrimaryKey_id(mem_id);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}

	public MemberVO getMem_id(String mem_id) {
		return dao.findMem_id(mem_id);
	}

	public MemberVO getImg(String mem_id) {
		return dao.findimg(mem_id);
	}

	// Android使用
	public boolean isMember(String mem_email, String mem_pwd) {
		return dao.isMember(mem_email, mem_pwd);
	}

	// Android使用
	public void update(String mem_id, Integer mem_point, Connection con) {
		dao.update(mem_id, mem_point, con);
	}
}
