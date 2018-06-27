package com.productclass.controller;

import java.io.*;


import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVo;
import com.productclass.model.ProductClassService;
import com.productclass.model.ProductClassVo;

public class ProductClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductClassServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		String productClass_name = req.getParameter("productClass_name");
		
		
		if ("down".equals(action)) {
			List<String> errorMsgs;
			errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String proclsno = req.getParameter("proclsno");
			Integer productClass_Status = Integer.parseInt(req.getParameter("productClass_Status"));
		
		try {
			    ProductClassService proClasSvc = new ProductClassService();
			    ProductClassVo proClasVO = proClasSvc.down(proclsno, productClass_Status);
			    RequestDispatcher successView = req.getRequestDispatcher("/back-end/productclass/listAllProductClass.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/productclass/listAllProductClass.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("upload".equals(action)) {
			List<String> errorMsgs;
			errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String product_cl_id = req.getParameter("product_cl_id");
			Integer productClass_Status = Integer.parseInt(req.getParameter("productClass_Status"));
		
		try {
			    ProductClassService proClasSvc = new ProductClassService();
			    ProductClassVo proClasVO = proClasSvc.upload(product_cl_id, productClass_Status);
			    RequestDispatcher successView = req.getRequestDispatcher("/back-end/productclass/listAllProductClass.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/productclass/listAllProductClass.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer productClass_Status = Integer.parseInt(req.getParameter("productClass_Status"));
			try {
 			if (productClass_name == null || productClass_name.trim().length() == 0) {
					errorMsgs.add("產品名稱請勿空白");
				}
				if (!errorMsgs.isEmpty()) {
					ProductClassService  clsSvc =new ProductClassService();
					ProductClassVo proclsVo = clsSvc.add(productClass_name,productClass_Status);
					req.setAttribute("proclsVo", proclsVo);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/productclass/addProductClass.jsp");
					failureView.forward(req, res);
					return;
				}
				ProductClassService  clsSvc =new ProductClassService();
				ProductClassVo proclsVo = clsSvc.add(productClass_name,productClass_Status);
				RequestDispatcher View = req.getRequestDispatcher("back-end/productclass/listAllProductClass.jsp");
			    View.forward(req, res);
		
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if ("getOne".equals(action)) {
			List<String> errorMsgs;
			errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String find_one = req.getParameter("proclsno");
			try {
				ProductClassService  clsSvc =new ProductClassService();
 				ProductClassVo proclsVO = clsSvc.getOne(find_one);
				req.setAttribute("proclsVO", proclsVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/productclass/update_procls_input.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("找不到對應資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/productclass/listAllProductClass.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String product_cls_name = req.getParameter("product_cls_name");
			String product_cls_id = req.getParameter("product_cls_id");
			if (product_cls_name == null || product_cls_name.trim().length() == 0) {
				errorMsgs.add("產品類別請勿空白");
			}
			try {
				if (!errorMsgs.isEmpty()) {
					ProductClassVo proclsVO  =new ProductClassVo();
				    proclsVO.setProduct_cl_name(product_cls_name);
				    proclsVO.setProduct_cl_id(product_cls_id);
					req.setAttribute("proclsVO", proclsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/productclass/update_procls_input.jsp");
					failureView.forward(req, res);
					return;
				}
				ProductClassService  proclsSvc = new ProductClassService();
				ProductClassVo proclsVO  =new ProductClassVo();
				proclsVO = proclsSvc.update(product_cls_id,product_cls_name);
				RequestDispatcher View = req.getRequestDispatcher("back-end/productclass/listAllProductClass.jsp");
			    View.forward(req, res);
		    } catch (Exception e) {
				errorMsgs.add("找不到對應資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/productclass/update_procls_input.jsp");
				failureView.forward(req, res);
			}

		}

	}

}
