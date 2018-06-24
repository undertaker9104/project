package com.ordermaster.controller;

import java.io.IOException;


import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ordermaster.model.OrderMasterService;
import com.ordermaster.model.OrderMasterVo;

public class OrderMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderMasterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		if ("getOneOrderDetail".equals(action)) {
			try {
				String ord_id = req.getParameter("ord_id");
				boolean openModal = true;
				req.setAttribute("ord_id", ord_id);
				req.setAttribute("openModal", openModal);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/ordermaster/listAllOrderMasterByShip.jsp");
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}

		if ("updeteOneOrderMaster".equals(action)) {
			try {
				String ord_id_update = req.getParameter("ord_id_update");
				boolean openModal_update = true;
				req.setAttribute("ord_id_update", ord_id_update);
				req.setAttribute("openModal_update", openModal_update);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/ordermaster/listAllOrderMasterByShip.jsp");
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}
	
	
	if ("confirmOrderMaster".equals(action)) {
		try {

			Integer masterstatus = Integer.parseInt(req.getParameter("masterstatus"));
			String ord_id_confirm = req.getParameter("ord_id_confirm");
			OrderMasterService orderMasterSVc = new OrderMasterService();
			OrderMasterVo masterVo = orderMasterSVc.update(masterstatus, ord_id_confirm);
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/ordermaster/listAllOrderMasterByShip.jsp");
			successView.forward(req, res);
	
		} catch (Exception e) {
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
			successView.forward(req, res);
		}
	}
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();

		if ("getOneOrderDetail".equals(action)) {
			try {
				String ord_id = req.getParameter("ord_id");
				boolean openModal = true;
				req.setAttribute("ord_id", ord_id);
				req.setAttribute("openModal", openModal);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/ordermaster/listAllOrderMasterByShip.jsp");
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}

		if ("getMyOrderDetail".equals(action)) {
			try {
				String ord_id = req.getParameter("ord_id");
				boolean openModal = true;
				req.setAttribute("ord_id", ord_id);
				req.setAttribute("openModal", openModal);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/ordermaster/listMyOrderMaster.jsp");
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}

		if ("updeteOneOrderMaster".equals(action)) {
			try {
				String ord_id_update = req.getParameter("ord_id_update");
				boolean openModal_update = true;
				req.setAttribute("ord_id_update", ord_id_update);
				req.setAttribute("openModal_update", openModal_update);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/ordermaster/listAllOrderMasterByShip.jsp");
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}

		if ("confirmOrderMaster".equals(action)) {
			try {

				Integer masterstatus = Integer.parseInt(req.getParameter("masterstatus"));
				String ord_id_confirm = req.getParameter("ord_id_confirm");
				OrderMasterService orderMasterSVc = new OrderMasterService();
				OrderMasterVo masterVo = orderMasterSVc.update(masterstatus, ord_id_confirm);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/ordermaster/listAllOrderMasterByShip.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}
		
		if ("changeAllOk".equals(action)) {
			String[] take_ord_id = req.getParameterValues("take_ord_id");
	
			try {
				if (take_ord_id == null || take_ord_id.length == 0) {
					out.println("<script>alert('沒有選擇任何訂單喔!');window.location.href='back-end/ordermaster/listAllOrderMasterByShip.jsp'</script>");
				return;
				}
				String condition = "";
				for (int i = 0; i < take_ord_id.length; i++) {
					if (i == 0)
						condition = "" +"'"+take_ord_id[i]+"'";
					else
						condition += "," +"'"+take_ord_id[i]+"'";
				}
				String SQL ="UPDATE ORDER_MASTER SET ord_status =2"+"WHERE ord_id IN("+condition+")";
			    OrderMasterService orderMasterSVc = new OrderMasterService();
				orderMasterSVc.doneom(SQL);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/ordermaster/listAllOrderMasterByShip.jsp");
				successView.forward(req, res);
			    
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}
		
		if ("changeAllTakeDone".equals(action)) {
			String[] take_ord_id = req.getParameterValues("take_ord_id");
		   try {
				if (take_ord_id == null || take_ord_id.length == 0) {
					out.println("<script>alert('沒有選擇任何訂單喔!');window.location.href='back-end/ordermaster/listAllOrderMasterByShip.jsp'</script>");
				return;
				}
				String condition = "";
				for (int i = 0; i < take_ord_id.length; i++) {
					if (i == 0)
						condition = "" +"'"+take_ord_id[i]+"'";
					else
						condition += ","+"'"+take_ord_id[i]+"'";
				}
				String SQL ="UPDATE ORDER_MASTER SET ord_status =3"+"WHERE ord_id IN("+condition+")";
			    OrderMasterService orderMasterSVc = new OrderMasterService();
				orderMasterSVc.doneom(SQL);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/ordermaster/listAllOrderMasterByShip.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}
		
		
		if ("changeAllDone".equals(action)) {
			String[] ord_id = req.getParameterValues("ord_id");
			try {
				if (ord_id == null || ord_id.length == 0) {
					out.println("<script>alert('沒有選擇任何訂單喔!');window.location.href='back-end/ordermaster/listAllOrderMasterByShip.jsp'</script>");
				return;
				}
				String condition = "";
				for (int i = 0; i < ord_id.length; i++) {
					if (i == 0)
						condition = "" +"'"+ord_id[i]+"'";
					else
						condition += "," +"'"+ord_id[i]+"'";
				}
				String SQL ="UPDATE ORDER_MASTER SET ord_status =3"+"WHERE ord_id IN("+condition+")";
			    OrderMasterService orderMasterSVc = new OrderMasterService();
				orderMasterSVc.doneom(SQL);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/ordermaster/listAllOrderMasterByShip.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}
		
		if ("changeAllShip".equals(action)) {
			String[] ord_id = req.getParameterValues("ord_id");
			try {
				if (ord_id == null || ord_id.length == 0) {
					out.println("<script>alert('沒有選擇任何訂單喔!');window.location.href='back-end/ordermaster/listAllOrderMasterByShip.jsp'</script>");
				return;
				}
				String condition = "";
				for (int i = 0; i < ord_id.length; i++) {
					if (i == 0)
						condition = "" +"'"+ord_id[i]+"'";
					else
						condition += "," +"'"+ord_id[i]+"'";
				}
     			String SQL ="UPDATE ORDER_MASTER SET ord_status =1"+"WHERE ord_id IN("+condition+")";
			    OrderMasterService orderMasterSVc = new OrderMasterService();
				orderMasterSVc.shipom(SQL);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/ordermaster/listAllOrderMasterByShip.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}

		if ("disOrderMaster".equals(action)) {
			String[] ord_id = req.getParameterValues("ord_id");
			String man_acc_id = req.getParameter("man_acc_id");
		
			try {
				if (ord_id == null || ord_id.length == 0) {
					out.println("<script>alert('沒有選擇任何訂單喔!');window.location.href='back-end/ordermaster/listAllOrderMasterByShip.jsp'</script>");
				return;
				}
				String condition = "";
				for (int i = 0; i < ord_id.length; i++) {
					if (i == 0)
						condition = "" +"'"+ord_id[i]+"'";
					else
						condition += "," +"'"+ord_id[i]+"'";
				}
				String SQL ="UPDATE ORDER_MASTER SET MAN_ACC_ID ="+"'"+man_acc_id+"'"+"WHERE ord_id IN("+condition+")";
			    OrderMasterService orderMasterSVc = new OrderMasterService();
				orderMasterSVc.disom(SQL);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/ordermaster/listAllOrderMasterByShip.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}

	}

}
