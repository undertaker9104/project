package com.int_exc.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.int_exc.model.Int_excService;
import com.int_exc.model.Int_excVO;
import com.mem.model.MemberService;
import com.mem.model.MemberVO;
import com.point_exc_cash.model.Point_exc_cashService;
import com.point_exc_cash.model.Point_exc_cashVO;


@WebServlet("/Int_excServlet")
public class Int_excServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Int_excServlet() {
        super();
    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		response.setContentType("text/html;charset=utf-8");
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String mem_id = request.getParameter("mem_id");
				if (mem_id == null || (mem_id.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_point.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				Int_excService int_excSvc = new Int_excService();
				Int_excVO int_excVO = int_excSvc.getOneBymem_id(mem_id);
				if (int_excVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_point.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				request.setAttribute("int_excVO", int_excVO);
				String url = "/back-end/mem/listOneInt_exc.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_point.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String mem_id = request.getParameter("mem_id");
			
			String integeral = request.getParameter("integeral");
					
			java.util.Date now = new java.util.Date();
			java.sql.Date int_exc_date = new java.sql.Date(now.getTime());
				
			Int_excService int_excSvc = new Int_excService();
				
			int_excSvc.addInt_exc(mem_id, int_exc_date, new Integer(integeral));
		}
		
		
	}
}
