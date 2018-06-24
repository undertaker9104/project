package com.producttrack.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.producttrack.model.ProductTrackService;


public class ProductTrackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");

		if ("0".equals(action)) {
			try {
				String mem_id = req.getParameter("mem_id");
				String addFaver_id = req.getParameter("addFaver_id");
				ProductTrackService trackScv = new ProductTrackService();
				trackScv.add(mem_id, addFaver_id);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		if ("1".equals(action)) {
			try {
				String mem_id = req.getParameter("mem_id");
				String addFaver_id = req.getParameter("addFaver_id");
				ProductTrackService trackScv = new ProductTrackService();
				trackScv.delete(mem_id, addFaver_id);
				RequestDispatcher View = req.getRequestDispatcher("/front-end/producttract/listMyFaver.jsp");
				View.forward(req, res);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		if ("deleteTrace".equals(action)) {
			String mem_id = req.getParameter("mem_id");
			String product_id = req.getParameter("product_id");
			ProductTrackService trackScv = new ProductTrackService();
			trackScv.delete(mem_id, product_id);
			RequestDispatcher View = req.getRequestDispatcher("/front-end/producttract/listMyFaver.jsp");
			View.forward(req, res);
		}

	}
}
