package com.ice.controller;

import java.io.*;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ice.model.IceService;
import com.ice.model.IceVo;

public class IceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IceServlet() {
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
		String ice_des = req.getParameter("ice_des");
		String ice_id = req.getParameter("ice_id");
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
 			if (ice_des == null || ice_des.trim().length() == 0) {
					errorMsgs.add("冰塊名稱請勿空白");
				}
				if (!errorMsgs.isEmpty()) {
					IceService iceSvc =new IceService();
					IceVo  iceVo =iceSvc.add(ice_des);
					req.setAttribute("iceVo", iceVo);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ice/addIce.jsp");
					failureView.forward(req, res);
					return;
				}
				IceService iceSvc =new IceService();
				IceVo  iceVo =iceSvc.add(ice_des);
				RequestDispatcher View = req.getRequestDispatcher("/back-end/ice/listAllIce.jsp");
				View.forward(req, res);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if ("getOne".equals(action)) {
			List<String> errorMsgs;
			errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
    		try {
				
				IceService iceSvc =new IceService();
				IceVo iceVo = iceSvc.getOneIce(ice_id);
			    req.setAttribute("iceVo", iceVo);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/ice/update_ice_input.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("找不到對應資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ice/listAllIce.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			if (ice_des == null || ice_des.trim().length() == 0) {
				errorMsgs.add("冰塊敘述請勿空白");
			}
			try {
				if (!errorMsgs.isEmpty()) {
					IceVo iceVo  =new IceVo();
					iceVo.setIce_id(ice_id);
					iceVo.setIce_des(ice_des);
			    	req.setAttribute("iceVo", iceVo);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/update_ice_input.jsp");
					failureView.forward(req, res);
					return;
				}
				IceService  iceSvc =new IceService();
				IceVo iceVo  =new IceVo();
				iceVo = iceSvc.update(ice_id,ice_des);
				RequestDispatcher View = req.getRequestDispatcher("/back-end/ice/listAllIce.jsp");
				View.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("找不到對應資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/update_ice_input.jsp");
				failureView.forward(req, res);
			}

		}

	}

}
