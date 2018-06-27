package com.productclass.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.productclass.model.ProductClassVo;
import com.productclass.model.ProductClassService;


public class Product_Class_Servlet_Android extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		String outStr = "";
		ProductClassService productclassSvc = new ProductClassService();
		List<ProductClassVo> productclassList = productclassSvc.getAll();
		outStr = new Gson().toJson(productclassList);
		
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
