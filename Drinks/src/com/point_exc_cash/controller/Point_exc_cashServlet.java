package com.point_exc_cash.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.deposit_records.model.Deposit_recordsService;
import com.deposit_records.model.Deposit_recordsVO;
import com.mem.model.MailService;
import com.mem.model.MemberService;
import com.mem.model.MemberVO;
import com.point_exc_cash.model.Point_exc_cashService;
import com.point_exc_cash.model.Point_exc_cashVO;

@WebServlet("/Point_exc_cashServlet")
public class Point_exc_cashServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Point_exc_cashServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		response.setContentType("text/html;charset=utf-8");
		Boolean isDone=true;
		if ("Withdrawal".equals(action)) {
	
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String mem_id = request.getParameter("mem_id");
			
			try {
				String Balance = request.getParameter("Balance");
				if (Balance == null || (Balance.trim()).length() == 0) {
					errorMsgs.add("請輸入您要提取的金額");
				}
				try {
					new Integer(Balance);
				} catch (Exception e) {
					errorMsgs.add("金額只能數字");
				}
			
					
				String BankAccount = request.getParameter("BankAccount");
				if (BankAccount == null || (BankAccount.trim()).length() == 0) {
					errorMsgs.add("請輸入您的銀行帳戶");
				}
				
				try {
					new Integer(BankAccount);
				} catch (Exception e) {
					errorMsgs.add("銀行帳戶格式錯誤");
				}
				
				String mem_pwd = request.getParameter("mem_pwd");
				if (mem_pwd == null || (mem_pwd.trim()).length() == 0) {
					errorMsgs.add("請輸入您的支付密碼");
				}
				if (!errorMsgs.isEmpty()) {
					JSONObject obj = new JSONObject();
					isDone = false;
					try{
						obj.put("errorMsgs",errorMsgs);
						obj.put("isDone",isDone);
					}catch(Exception e){}
					
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.print(obj);
					out.flush();
					out.close();
					//RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/withdrawalPoint.jsp");
					//failureView.forward(request, response);
					return;
				}
	
				MemberService memberSvc = new MemberService();
				MemberVO memberVO=memberSvc.getOneMem_id(mem_id);
				if(!mem_pwd.equals(memberVO.getMem_pwd())){
					
					errorMsgs.add("支付密碼錯誤");
				}
				if(memberVO.getMem_point()<new Integer(Balance)){
					
					errorMsgs.add("點數餘額不足");
				}
				
				if (!errorMsgs.isEmpty()) {
					JSONObject obj = new JSONObject();
					isDone = false;
					try{
						obj.put("errorMsgs",errorMsgs);
						obj.put("isDone",isDone);
					}catch(Exception e){}
					
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.print(obj);
					out.flush();
					out.close();
					//RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/withdrawalPoint.jsp");
					//failureView.forward(request, response);
					return;
				}
	
				
				java.util.Date now = new java.util.Date();
				java.sql.Date exc_date = new java.sql.Date(now.getTime());
				
				Integer exc_cash = new Integer(Balance);
				Integer bank_acc = new Integer(BankAccount);
				
				Point_exc_cashService point_exc_cashSvc = new Point_exc_cashService();
				point_exc_cashSvc.addPoint_exc_cash(mem_id, exc_date, exc_cash, bank_acc);
				List<Point_exc_cashVO> point_exc_cashlist = point_exc_cashSvc.getOnePoint_exc_cash(mem_id);
				
				HttpSession session = request.getSession();
				session.setAttribute("point_exc_cashlist", point_exc_cashlist);
				JSONObject obj = new JSONObject();
				PrintWriter out = response.getWriter();
				out.print(obj);
				//RequestDispatcher successView = request.getRequestDispatcher("/front-end/mem/withdrawalPoint_success.jsp");
				//successView.forward(request, response);
				
				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/withdrawalPoint.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		
		if("Records".equals(action)){
			String mem_id = request.getParameter("mem_id");
			Point_exc_cashService point_exc_cashSvc = new Point_exc_cashService();
			List<Point_exc_cashVO> point_exc_cashlist = point_exc_cashSvc.getOnePoint_exc_cash(mem_id);
			HttpSession session = request.getSession();
			session.setAttribute("point_exc_cashlist", point_exc_cashlist);
			RequestDispatcher successView = request.getRequestDispatcher("/front-end/mem/listOneMemWithdrawalPoint.jsp");
			successView.forward(request, response);
		}
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String mem_id = request.getParameter("mem_id");
				if (mem_id == null || (mem_id.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_point.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				Point_exc_cashService point_exc_cashSvc = new Point_exc_cashService();
				List<Point_exc_cashVO> point_exc_cashlist= point_exc_cashSvc.getOnePoint_exc_cash(mem_id);
				if (point_exc_cashlist.size()==0) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_point.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}		
				request.getSession().setAttribute("point_exc_cashlist", point_exc_cashlist);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/mem/listOneMemWithdraealPoint.jsp"); // 成功轉交listOneEmp.jsp
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_point.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			try {
				String exc_rec_id = request.getParameter("exc_rec_id");
				Point_exc_cashService point_exc_cashSvc = new Point_exc_cashService();
				Point_exc_cashVO point_exc_cashVO = point_exc_cashSvc.getOnePoint_exc_cash_(exc_rec_id);
				request.setAttribute("point_exc_cashVO", point_exc_cashVO);
				String url = "/back-end/mem/update_withdraeal_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
		
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			String exc_rec_id = request.getParameter("exc_rec_id");
			try {
				String req_status = request.getParameter("req_status");
				if(req_status==null||req_status.length()==0){
					errorMsgs.add("請輸入(數字)審核狀態碼 0(未審核) 1(審核成功)");
				}
				
				try{
				new Integer(req_status);
				}catch (Exception e) {
					errorMsgs.add("請輸入(數字)審核狀態碼 0(未審核) 1(審核成功)");
				}
				if(new Integer(req_status)>=2||new Integer(req_status)<0){
					errorMsgs.add("請輸入(數字)審核狀態碼 0(未審核) 1(審核成功)");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/mem/update_withdraeal_input.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}	
				Point_exc_cashService point_exc_cashSvc = new Point_exc_cashService();
				
				if("1".equals(req_status)){
					
					Point_exc_cashVO point_exc_cashVO1 = point_exc_cashSvc.getOnePoint_exc_cash_(exc_rec_id);
					MemberService memberSvc = new MemberService();
					MemberVO memberVO = memberSvc.getOneMem_id(point_exc_cashVO1.getMem_id());
					Integer mem_point = memberVO.getMem_point()-point_exc_cashVO1.getExc_cash();
					memberSvc.updateMem_Point(point_exc_cashVO1.getMem_id(), mem_point);
					point_exc_cashSvc.updateEmp(point_exc_cashVO1.getExc_rec_id(), new Integer(req_status));
					String to = memberVO.getMem_email();
					String subject = "審核通知";
					String ch_name = memberVO.getMem_name();
					String messageText = " Hello! " + ch_name +","+ point_exc_cashVO1.getExc_cash()+"點數兌換現金已完成,"+point_exc_cashVO1.getExc_cash()*0.95+"已入您的帳戶,如有問題請聯絡客服進行處理";
					MailService mailService = new MailService();
					mailService.sendMail(to, subject, messageText);
					
				}
				
				List<Point_exc_cashVO> point_exc_cashlist = point_exc_cashSvc.getAll();
				request.getSession().setAttribute("point_exc_cashlist", point_exc_cashlist);
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("審核失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/mem/update_withdraeal_input.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		
		
		
		
	}

}
