package com.discount.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;

import javax.servlet.http.*;

import com.discount.model.*;


public class DiscountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) 	throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
        if ("insert".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String dis_des = req.getParameter("dis_des").trim();
				if (dis_des == null || dis_des.trim().length() == 0) {
					errorMsgs.add("敘述請勿空白");
				}
				Integer dis_cup = null;
				try {
					dis_cup = new Integer(req.getParameter("dis_cup").trim());
				} catch (NumberFormatException e) {
					dis_cup = 0;
					errorMsgs.add("敘述請勿空白");
				}
				
				Double dis_cup_rate = null;
				try {
					dis_cup_rate = new Double(req.getParameter("dis_cup_rate").trim());
				} catch (NumberFormatException e) {
					dis_cup_rate = 0.0;
					errorMsgs.add("敘述請勿空白");
				}
				
				Integer dis_price = null;
				try {
					dis_price = new Integer(req.getParameter("dis_price").trim());
				} catch (NumberFormatException e) {
					dis_price = 0;
					errorMsgs.add("敘述請勿空白");
				}
				
				
				
				Double dis_price_rate = null;
				try {
					dis_price_rate = new Double(req.getParameter("dis_price_rate").trim());
				} catch (NumberFormatException e) {
					dis_price_rate = 0.0;
					errorMsgs.add("敘述請勿空白");
				}
				
				DiscountVO discountVO = new DiscountVO();
				discountVO.setDis_des(dis_des);
				discountVO.setDis_cup(dis_cup);
				discountVO.setDis_cup_rate(dis_cup_rate);
				discountVO.setDis_price(dis_price);
				discountVO.setDis_price_rate(dis_price_rate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("discountVO", discountVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/discountAdd.jsp");
					failureView.forward(req, res);
					return;
				}
	
				DiscountService discountSvc = new DiscountService();
				discountVO = discountSvc.addDiscount(dis_des, dis_cup, dis_cup_rate, dis_price,dis_price_rate);
				String url = "/back-end/discount/discountList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
					
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/discountAdd.jsp");
				failureView.forward(req, res);
			}
		}
		
        
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String dis_id = new String(req.getParameter("dis_id"));
				DiscountService discountSvc = new DiscountService();
				DiscountVO discountVO = discountSvc.getOneDiscount(dis_id);
				req.setAttribute("discountVO", discountVO); 
				String url = "/back-end/discount/update_discount_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				
			} catch (Exception e) {
			   RequestDispatcher failureView = req.getRequestDispatcher("/back-end/discount/discountList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String dis_id = new String(req.getParameter("dis_id").trim());
				String dis_des = req.getParameter("dis_des").trim();
				if (dis_des == null || dis_des.trim().length() == 0) {
					errorMsgs.add("敘述請勿空白");
				}
				
				Integer dis_cup = null;
				try {
					dis_cup = new Integer(req.getParameter("dis_cup").trim());
				} catch (NumberFormatException e) {
					dis_cup = 0;
					errorMsgs.add("敘述請勿空白");
				}
				
				Double dis_cup_rate = null;
				try {
					dis_cup_rate = new Double(req.getParameter("dis_cup_rate").trim());
				} catch (NumberFormatException e) {
					dis_cup_rate = 0.0;
					errorMsgs.add("敘述請勿空白");
				}
				
				Integer dis_price = null;
				try {
					dis_price = new Integer(req.getParameter("dis_price").trim());
				} catch (NumberFormatException e) {
					dis_price = 0;
					errorMsgs.add("敘述請勿空白");
				}
				
				Double dis_price_rate = null;
				try {
					dis_price_rate = new Double(req.getParameter("dis_price_rate").trim());
				} catch (NumberFormatException e) {
					dis_price_rate = 0.9;
					errorMsgs.add("敘述請勿空白");
				}
			
				DiscountVO discountVO = new DiscountVO();
				discountVO.setDis_id(dis_id);
				discountVO.setDis_des(dis_des);
				discountVO.setDis_cup(dis_cup);
				discountVO.setDis_cup_rate(dis_cup_rate);
				discountVO.setDis_price(dis_price);
				discountVO.setDis_price_rate(dis_price_rate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("discountVO", discountVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/discount/discountList.jsp");
					failureView.forward(req, res);
					return; 
				}

				DiscountService discountSvc = new DiscountService();
				discountVO = discountSvc.updateDiscount(dis_id, dis_des, dis_cup, dis_cup_rate, dis_price, dis_price_rate);
				req.setAttribute("discountVO", discountVO);
				String url = "/back-end/discount/discountList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/discount/discountList.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				String dis_id = new String(req.getParameter("dis_id").trim());
				DiscountService discountSvc = new DiscountService();
				discountSvc.deleteDiscount(dis_id);
				
				String url = "/back-end/discount/discountList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/discount/discountList.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
