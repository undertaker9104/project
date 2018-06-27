package com.ice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ice.model.IceService;
import com.ice.model.IceVo;

public class IceServlet_Android extends HttpServlet{

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		String action = req.getParameter("action");
		String outStr = "";
		IceService iceSvc = new IceService();
		if("getAll".equals(action)){
			List<IceVo> iceList = iceSvc.getAll();
			outStr = new Gson().toJson(iceList);
		}else if("findById".equals(action)){
			String ice_id = req.getParameter("ice_id");
			IceVo iceVO = iceSvc.getOneIce(ice_id);
			outStr = new Gson().toJson(iceVO);
		}
		
		
		res.setContentType(contentType);
		PrintWriter out = res.getWriter();
		out.print(outStr);
		out.close();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
}
