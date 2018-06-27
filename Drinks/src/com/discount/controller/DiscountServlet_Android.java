package com.discount.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.discount.model.DiscountService;
import com.discount.model.DiscountVO;
import com.google.gson.Gson;

public class DiscountServlet_Android extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		String action = req.getParameter("action");
		String outStr = "";
		DiscountService Svc = new DiscountService();
		if("getAll".equals(action)){
			List<DiscountVO> discountList = Svc.getAll();
			outStr = new Gson().toJson(discountList);
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
