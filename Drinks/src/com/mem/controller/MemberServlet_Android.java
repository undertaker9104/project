package com.mem.controller;

//import java.awt.List;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.g2.main.ImageUtil;
import com.google.gson.Gson;
import com.mem.model.MemberVO;
import com.mem.model.MemberService;

public class MemberServlet_Android extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		req.setCharacterEncoding("UTF-8");
		
		MemberService memSvc = new MemberService();
		Gson gson = new Gson();
		String outStr = "";

		String action = req.getParameter("action");
		System.out.println(action);
		
		if ("isMember".equals(action)) {
			String email = req.getParameter("mem_email");
			String password = req.getParameter("mem_pwd");		;
			outStr = String.valueOf(memSvc.isMember(email, password));
		} else if ("findById".equals(action)) {
			String email = req.getParameter("mem_email");
			System.out.println("email : "+email);
			MemberVO member = memSvc.getOneMem(email);
			System.out.println(member.getMem_point());
			byte[] img = ImageUtil.shrink(member.getMem_pic(),1);
			String imgStr = Base64.getMimeEncoder().encodeToString(img);
			member.setImgbase64(imgStr);
			byte[] qrcodeimg = ImageUtil.shrink(member.getMem_qrcode(),1);
			String imgStr2 = Base64.getMimeEncoder().encodeToString(qrcodeimg);
			member.setQrcodebase64(imgStr2);
			outStr = gson.toJson(member);
		}else if ("findByMemId".equals(action)) {
			String mem_id = req.getParameter("mem_id");
			System.out.println("mem_id : "+mem_id);
			MemberVO member = memSvc.getOneMem_id(mem_id);
			System.out.println(member.getMem_point());
			byte[] img = ImageUtil.shrink(member.getMem_pic(),1);
			String imgStr = Base64.getMimeEncoder().encodeToString(img);
			member.setImgbase64(imgStr);
			byte[] qrcodeimg = ImageUtil.shrink(member.getMem_qrcode(),1);
			String imgStr2 = Base64.getMimeEncoder().encodeToString(qrcodeimg);
			member.setQrcodebase64(imgStr2);
			System.out.println("mem_name:"+member.getMem_name());			
			outStr = gson.toJson(member);
		}
//		else if ("update".equals(action)){
//			System.out.println("member_update");
//			String orderJson = req.getParameter("orderJson");
//			System.out.println(orderJson);
//			MemberVO member = gson.fromJson(orderJson, MemberVO.class);		
//			memSvc.update(member);
//		}

		res.setContentType(contentType);
		PrintWriter out = res.getWriter();
		System.out.println(outStr);
		out.print(outStr);
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
}
