package com.orderdetail.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public OrderDetailServlet() {
        super();
      }
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
	}

}
