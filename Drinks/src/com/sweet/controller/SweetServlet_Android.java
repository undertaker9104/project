package com.sweet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sweet.model.SweetService;
import com.sweet.model.SweetVo;

public class SweetServlet_Android extends HttpServlet{

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		String action = req.getParameter("action");
		String outStr = "";
		SweetService sweetSvc = new SweetService();
		if("getAll".equals(action)){
			List<SweetVo> sweetList = sweetSvc.getAll();
			outStr = new Gson().toJson(sweetList);
		}else if("findById".equals(action)){
			String sweet_id = req.getParameter("sweet_id");
			SweetVo sweetVO = sweetSvc.getOneSweet(sweet_id);
			outStr = new Gson().toJson(sweetVO);
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
