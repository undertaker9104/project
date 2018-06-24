package com.manager_account_authority.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manager_account.model.Manager_accountService;
import com.manager_account.model.Manager_accountVO;
import com.manager_account_authority.model.Manager_account_authorityService;
import com.manager_account_authority.model.Manager_account_authorityVO;


@WebServlet("/Manager_account_authorityServlet")
public class Manager_account_authorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Manager_account_authorityServlet() {
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
				String man_acc_id = request.getParameter("man_acc_id");
				if (man_acc_id == null || (man_acc_id.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_page.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				Manager_accountService manager_accountSvc = new Manager_accountService();
				Manager_accountVO manager_accountVO = manager_accountSvc.getOneEmp(man_acc_id);
				if (manager_accountVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_page.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
	
				Manager_account_authorityService manager_account_authoritySvc = new Manager_account_authorityService();
				List<String> list = manager_account_authoritySvc.getOneManager_account_authority(man_acc_id);
			
				request.setAttribute("list", list);
				request.setAttribute("manager_accountVO", manager_accountVO);
				String url = "/back-end/emp/update_empAuth_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_page.jsp");
				failureView.forward(request, response);
			}
		}
	
		if ("update".equals(action)) {
			
			String requestURL = request.getParameter("requestURL");
			try{
				
				String[] Auth = request.getParameterValues("auth");
				String man_acc_id = request.getParameter("man_acc_id");
				Manager_account_authorityService Manager_account_authoritySvc = new Manager_account_authorityService();
				if(Auth.length != 0){
					
					Manager_account_authoritySvc.delete(man_acc_id);
					for(int i=0 ; i<Auth.length;i++){
						Manager_account_authoritySvc.addManager_account_authority(man_acc_id, Auth[i]);
					}
				}else{
					Manager_account_authoritySvc.delete(man_acc_id);
				}
				Manager_accountService manager_accountSvc = new Manager_accountService();
				Manager_accountVO manager_accountVO = manager_accountSvc.getOneEmp(man_acc_id);
				Manager_account_authorityService manager_account_authoritySvc = new Manager_account_authorityService();
				List<String> list = manager_account_authoritySvc.getOneManager_account_authority(man_acc_id);
			
				request.setAttribute("list", list);
				request.setAttribute("manager_accountVO", manager_accountVO);
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				System.out.println(1);
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/update_empAuth_input.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		if ("getOneAuth_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String authority_id = request.getParameter("authority_id");
				if (authority_id == null || (authority_id.trim()).length() == 0) {
					errorMsgs.add("請輸入職位");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_page.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				
				Manager_account_authorityService manager_account_authoritySvc = new Manager_account_authorityService();
				List<String> list1 =manager_account_authoritySvc.getAllEmpAuthority(authority_id);
				request.setAttribute("list1", list1);
				String url = "/back-end/emp/listAllAuth_emp.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_page.jsp");
				failureView.forward(request, response);
			}
		}
	
	}
}
