package com.msgboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.msgboard.model.MsgBoardService;
import com.msgboard.model.MsgBoardVo;

public class MsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public MsgServlet() {
        super();
    
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");

		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String msg_des = req.getParameter("msg_des");
				String mem_id = req.getParameter("mem_id");
				String product_id = req.getParameter("product_id");
			    Integer msg_status = Integer.parseInt(req.getParameter("msg_status"));
			if (msg_des == null || msg_des.trim().length() == 0) {
				out.println("<script>alert('訊息請勿空白!!!');window.location.href='front-end/grouporderdetail/group_detail.jsp'</script>");
			}
	    	if (!errorMsgs.isEmpty()) {
	    		out.println("<script>alert('訊息請勿空白!!!');window.location.href='front-end/grouporderdetail/group_detail.jsp'</script>");
				return;
			}
			MsgBoardService msgSvc = new MsgBoardService();
			MsgBoardVo msgVo = msgSvc.addMsg(mem_id,product_id,msg_des,msg_status);
			RequestDispatcher failureView = req .getRequestDispatcher("front-end/grouporderdetail/group_detail.jsp");
			failureView.forward(req, res);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if ("getOne".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			String msg_id= req.getParameter("msg_id");
			MsgBoardService msgSvc = new MsgBoardService();
			MsgBoardVo msgVo = msgSvc.getOneMsg(msg_id);
			req.setAttribute("msgVo", msgVo);
			RequestDispatcher failureView = req .getRequestDispatcher("/back-end/msg/update_msg_input.jsp");
			failureView.forward(req, res);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			Integer msg_status1= Integer.parseInt(req.getParameter("msg_status"));
			String msg_id = req.getParameter("msg_id");
			MsgBoardService msgSvc = new MsgBoardService();
			MsgBoardVo msgUpdateVo = msgSvc.updateMsg(msg_id,msg_status1);
					req.setAttribute("msgUpdateVo", msgUpdateVo);
			RequestDispatcher failureView = req .getRequestDispatcher("/back-end/msg/listAllMSG.jsp");
			failureView.forward(req, res);
			
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
