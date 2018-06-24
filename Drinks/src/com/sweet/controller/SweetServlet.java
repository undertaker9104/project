package com.sweet.controller;

import java.io.*;


import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sweet.model.SweetService;
import com.sweet.model.SweetVo;

public class SweetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SweetServlet() {
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
		String sweet_des = req.getParameter("sweet_des");
		String sweet_id = req.getParameter("sweet_id");
		
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
 			if (sweet_des == null || sweet_des.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				if (!errorMsgs.isEmpty()) {
					SweetService sweetSvc =new SweetService();
					SweetVo  sweetVo =sweetSvc.add(sweet_des);
					req.setAttribute("sweetVo", sweetVo);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sweet/addSweet.jsp");
					failureView.forward(req, res);
					return;
				}
				SweetService sweetSvc =new SweetService();
				SweetVo  sweetVo =sweetSvc.add(sweet_des);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sweet/listAllSweet.jsp");
				failureView.forward(req, res);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if ("getOne".equals(action)) {
			List<String> errorMsgs;
			errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		 try {
				
				SweetService  sweetSvc =new SweetService();
				SweetVo sweetVo = sweetSvc.getOneSweet(sweet_id);
				req.setAttribute("sweetVo", sweetVo);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/sweet/update_sweet_input.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("查無此資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sweet/listAllSweet.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			if (sweet_des == null || sweet_des.trim().length() == 0) {
				errorMsgs.add("");
			}
			try {
				if (!errorMsgs.isEmpty()) {
					SweetVo sweetVo  =new SweetVo();
					sweetVo.setSweet_id(sweet_id);
					sweetVo.setSweet_des(sweet_des);
			    	req.setAttribute("sweetVo", sweetVo);
					RequestDispatcher View = req.getRequestDispatcher("/back-end/sweet/update_sweet_input.jsp");
					View.forward(req, res);
					return;
				}
				SweetService  sweetSvc =new SweetService();
				SweetVo sweetVo  =new SweetVo();
				sweetVo = sweetSvc.update(sweet_id,sweet_des);
				RequestDispatcher View = req.getRequestDispatcher("/back-end/sweet/listAllSweet.jsp");
				View.forward(req, res);
				

			} catch (Exception e) {
				errorMsgs.add("查無此資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sweet/update_sweet_input.jsp");
				failureView.forward(req, res);
			}

		}

	}

}
