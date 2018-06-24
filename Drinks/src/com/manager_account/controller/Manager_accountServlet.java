package com.manager_account.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.manager_account.model.Manager_accountService;
import com.manager_account.model.Manager_accountVO;
import com.manager_account_authority.model.Manager_account_authorityService;
import com.mem.model.MailService;

@WebServlet("/Manager_accountServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Manager_accountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Manager_accountServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		response.setContentType("text/html;charset=utf-8");

		/*************************** 新增員工 *****************************************/
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String emp_name = request.getParameter("emp_name");
				if (emp_name == null || (emp_name.trim()).length() == 0) {
					errorMsgs.add("請輸入員工姓名");
				}
				String emp_email = request.getParameter("emp_email");
				if (emp_email == null || (emp_email.trim()).length() == 0) {
					errorMsgs.add("請輸入E-mail");
				}
				byte[] emp_img = null;
				Part filePart = request.getPart("emp_img");
				InputStream in = null;
				if (filePart != null) {
					in = filePart.getInputStream();
					emp_img = new byte[in.available()];
					in.read(emp_img);
				} else {
					errorMsgs.add("請選擇上傳圖片");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/addManager_account.jsp");
					failureView.forward(request, response);
					return;
				}
			
				//亂數產生密碼
				int z;
			    StringBuilder sb = new StringBuilder();
			    int i;
			    for (i = 0; i < 8; i++) {
			      z = (int) ((Math.random() * 7) % 3);
			 
			      if (z == 1) { // 放數字
			        sb.append((int) ((Math.random() * 10) + 48));
			      } else if (z == 2) { // 放大寫英文
			        sb.append((char) (((Math.random() * 26) + 65)));
			      } else {// 放小寫英文
			        sb.append(((char) ((Math.random() * 26) + 97)));
			      }
			    }
				
			    String accpw = sb.toString();
			    
				
				Manager_accountService manager_accountSvc = new Manager_accountService();
				manager_accountSvc.addEmp( accpw , emp_name , emp_img , emp_email );
				
				String to = emp_email;
				String subject = "密碼通知";
				String ch_name = emp_name;
				String messageText = " Hello! 員工:" + ch_name +"您的員工帳號密碼為:"+accpw+"請謹記此密碼";
				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);
				
		       
				
				RequestDispatcher success = request.getRequestDispatcher("/back-end/backIndex.jsp");
				success.forward(request, response);
			} catch (Exception e) {
				System.out.println("新增失敗 請重新輸入");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/addManager_account.jsp");
				failureView.forward(request, response);
			}
		}

		/*************************** 查詢一筆員工 *****************************************/
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String man_acc_id = request.getParameter("man_acc_id");
				if (man_acc_id == null || (man_acc_id.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_emp.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				Manager_accountService manager_accountSvc = new Manager_accountService();
				Manager_accountVO manager_accountVO = manager_accountSvc.getOneEmp(man_acc_id);
				if (manager_accountVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_emp.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				request.setAttribute("manager_accountVO", manager_accountVO);
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_emp.jsp");
				failureView.forward(request, response);
			}
		}

		/*************************** 查詢員工並更新 *****************************************/
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			String requestURL = request.getParameter("requestURL");

			try {

				String man_acc_id = request.getParameter("man_acc_id");
				Manager_accountService manager_accountSvc = new Manager_accountService();
				Manager_accountVO manager_accountVO = manager_accountSvc.getOneEmp(man_acc_id);
				request.setAttribute("manager_accountVO", manager_accountVO);
				String url = "/back-end/emp/update_emp_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}

		/*************************** 查詢員工並更新 *****************************************/
		if ("getOne_For_Update2".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			String requestURL = request.getParameter("requestURL");

			try {

				String man_acc_id = request.getParameter("man_acc_id");
				Manager_accountService manager_accountSvc = new Manager_accountService();
				Manager_accountVO manager_accountVO = manager_accountSvc.getOneEmp(man_acc_id);
				request.setAttribute("manager_accountVO", manager_accountVO);
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}

		/*************************** 刪除員工 *****************************************/
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			try {
				String man_acc_id = request.getParameter("man_acc_id");
				Manager_account_authorityService manager_account_authoritySvc = new Manager_account_authorityService();
				manager_account_authoritySvc.delete(man_acc_id);
				Manager_accountService manager_accountSvc = new Manager_accountService();
				manager_accountSvc.getOneEmp(man_acc_id);
				manager_accountSvc.deleteEmp(man_acc_id);
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}

		/*************************** 更新資料 *****************************************/
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");

			Manager_accountService manager_accountSvc = new Manager_accountService();
			String man_acc_id = request.getParameter("man_acc_id").trim();
			try {
				String emp_name = request.getParameter("emp_name");
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				}
				Integer man_acc_status = null;
				try {
					man_acc_status = new Integer(request.getParameter("man_acc_status").trim());
					if (!(man_acc_status == 0) && !(man_acc_status == 1)) {
						errorMsgs.add("權限請填0(正常)或1(停權).");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("權限請填0(正常)或1(停權).");
				}
				String accpw = request.getParameter("accpw");
				if (accpw == null || accpw.trim().length() == 0) {
					accpw=manager_accountSvc.getOneEmp(man_acc_id).getAccpw();
					errorMsgs.add("請輸入密碼");
				}
				
				String emp_email = request.getParameter("emp_email");
				if (emp_email == null || emp_email.trim().length() == 0) {
					emp_email=manager_accountSvc.getOneEmp(man_acc_id).getEmp_email();
					errorMsgs.add("請輸入E-mail");
				}
				
				byte[] emp_img = null;
				Part filePart = request.getPart("emp_img");
				InputStream in = null;
				in = filePart.getInputStream();
				if (in.available() != 0) {
					emp_img = new byte[in.available()];
					in.read(emp_img);
					in.close();
				} else {
					
						emp_img = manager_accountSvc.getOneEmp(man_acc_id).getEmp_img();
		
				}
		
			
				
				Manager_accountVO manager_accountVO = new Manager_accountVO();
				manager_accountVO.setMan_acc_id(man_acc_id);
				manager_accountVO.setEmp_name(emp_name);
				manager_accountVO.setMan_acc_status(man_acc_status);
				manager_accountVO.setAccpw(accpw);
				manager_accountVO.setEmp_img(emp_img);
				manager_accountVO.setEmp_email(emp_email);
				request.setAttribute("manager_accountVO", manager_accountVO);
				

				if (!errorMsgs.isEmpty()) {
					
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
					failureView.forward(request, response);
					return; // 程式中斷
				}
				
				manager_accountVO = manager_accountSvc.updateEmp(accpw, emp_name, emp_img, man_acc_status, man_acc_id,emp_email);
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
				failureView.forward(request, response);
			}
		}

		/*************************** 後臺員工登入 *****************************************/
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String emp_name = request.getParameter("emp_name");
				if (emp_name == null || (emp_name.trim()).length() == 0) {
					errorMsgs.add("請輸入員工姓名");
				}
				String accpw = request.getParameter("accpw");
				if (accpw == null || (accpw.trim()).length() == 0) {
					errorMsgs.add("請輸入您的密碼");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Login.jsp");
					failureView.forward(request, response);
					return;
				}
				Manager_accountService manager_accountSvc = new Manager_accountService();
				Manager_accountVO manager_accountVO = manager_accountSvc.getOneEmp_name(emp_name);
				if (manager_accountVO == null) {
					errorMsgs.add("查無資料");
				} else if (!accpw.equals(manager_accountVO.getAccpw())) {
					errorMsgs.add("您輸入的密碼有誤請重新輸入");
				} else if ("1".equals(manager_accountVO.getMan_acc_status())) {
					errorMsgs.add("您輸入的帳號已停權");
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("manager_accountVO", manager_accountVO);
					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {
							session.removeAttribute("location");
							response.sendRedirect(location);
							return;
						}
					} catch (Exception ignored) {
						ignored.getMessage();
					}
					response.sendRedirect(request.getContextPath() + "/back-end/backIndex.jsp");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Login.jsp");
					failureView.forward(request, response);
					return;
				}
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Login.jsp");
				failureView.forward(request, response);
			}
		}

		/*************************** 判斷是否有無登入 *****************************************/
		if ("InOut".equals(action)) {
			HttpSession session = request.getSession();
			Manager_accountVO manager_accountVO = (Manager_accountVO) session.getAttribute("manager_accountVO");
			if (manager_accountVO == null) {
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Login.jsp");
				failureView.forward(request, response);
			} else {
				session.removeAttribute("manager_accountVO");
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Login.jsp");
				failureView.forward(request, response);
			}
		}
	}
}
