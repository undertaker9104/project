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

		/*************************** �s�W���u *****************************************/
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String emp_name = request.getParameter("emp_name");
				if (emp_name == null || (emp_name.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�m�W");
				}
				String emp_email = request.getParameter("emp_email");
				if (emp_email == null || (emp_email.trim()).length() == 0) {
					errorMsgs.add("�п�JE-mail");
				}
				byte[] emp_img = null;
				Part filePart = request.getPart("emp_img");
				InputStream in = null;
				if (filePart != null) {
					in = filePart.getInputStream();
					emp_img = new byte[in.available()];
					in.read(emp_img);
				} else {
					errorMsgs.add("�п�ܤW�ǹϤ�");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/addManager_account.jsp");
					failureView.forward(request, response);
					return;
				}
			
				//�üƲ��ͱK�X
				int z;
			    StringBuilder sb = new StringBuilder();
			    int i;
			    for (i = 0; i < 8; i++) {
			      z = (int) ((Math.random() * 7) % 3);
			 
			      if (z == 1) { // ��Ʀr
			        sb.append((int) ((Math.random() * 10) + 48));
			      } else if (z == 2) { // ��j�g�^��
			        sb.append((char) (((Math.random() * 26) + 65)));
			      } else {// ��p�g�^��
			        sb.append(((char) ((Math.random() * 26) + 97)));
			      }
			    }
				
			    String accpw = sb.toString();
			    
				
				Manager_accountService manager_accountSvc = new Manager_accountService();
				manager_accountSvc.addEmp( accpw , emp_name , emp_img , emp_email );
				
				String to = emp_email;
				String subject = "�K�X�q��";
				String ch_name = emp_name;
				String messageText = " Hello! ���u:" + ch_name +"�z�����u�b���K�X��:"+accpw+"���԰O���K�X";
				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);
				
		       
				
				RequestDispatcher success = request.getRequestDispatcher("/back-end/backIndex.jsp");
				success.forward(request, response);
			} catch (Exception e) {
				System.out.println("�s�W���� �Э��s��J");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/addManager_account.jsp");
				failureView.forward(request, response);
			}
		}

		/*************************** �d�ߤ@�����u *****************************************/
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String man_acc_id = request.getParameter("man_acc_id");
				if (man_acc_id == null || (man_acc_id.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_emp.jsp");
					failureView.forward(request, response);
					return;// �{�����_
				}
				Manager_accountService manager_accountSvc = new Manager_accountService();
				Manager_accountVO manager_accountVO = manager_accountSvc.getOneEmp(man_acc_id);
				if (manager_accountVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_emp.jsp");
					failureView.forward(request, response);
					return;// �{�����_
				}
				request.setAttribute("manager_accountVO", manager_accountVO);
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // ���\���listOneEmp.jsp
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_emp.jsp");
				failureView.forward(request, response);
			}
		}

		/*************************** �d�߭��u�ç�s *****************************************/
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
				errorMsgs.add("�ק��ƨ��X�ɥ���:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}

		/*************************** �d�߭��u�ç�s *****************************************/
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
				errorMsgs.add("�ק��ƨ��X�ɥ���:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}

		/*************************** �R�����u *****************************************/
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
				RequestDispatcher successView = request.getRequestDispatcher(url); // �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}

		/*************************** ��s��� *****************************************/
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");

			Manager_accountService manager_accountSvc = new Manager_accountService();
			String man_acc_id = request.getParameter("man_acc_id").trim();
			try {
				String emp_name = request.getParameter("emp_name");
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				}
				Integer man_acc_status = null;
				try {
					man_acc_status = new Integer(request.getParameter("man_acc_status").trim());
					if (!(man_acc_status == 0) && !(man_acc_status == 1)) {
						errorMsgs.add("�v���ж�0(���`)��1(���v).");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("�v���ж�0(���`)��1(���v).");
				}
				String accpw = request.getParameter("accpw");
				if (accpw == null || accpw.trim().length() == 0) {
					accpw=manager_accountSvc.getOneEmp(man_acc_id).getAccpw();
					errorMsgs.add("�п�J�K�X");
				}
				
				String emp_email = request.getParameter("emp_email");
				if (emp_email == null || emp_email.trim().length() == 0) {
					emp_email=manager_accountSvc.getOneEmp(man_acc_id).getEmp_email();
					errorMsgs.add("�п�JE-mail");
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
					return; // �{�����_
				}
				
				manager_accountVO = manager_accountSvc.updateEmp(accpw, emp_name, emp_img, man_acc_status, man_acc_id,emp_email);
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
				failureView.forward(request, response);
			}
		}

		/*************************** ��O���u�n�J *****************************************/
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String emp_name = request.getParameter("emp_name");
				if (emp_name == null || (emp_name.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�m�W");
				}
				String accpw = request.getParameter("accpw");
				if (accpw == null || (accpw.trim()).length() == 0) {
					errorMsgs.add("�п�J�z���K�X");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/Login.jsp");
					failureView.forward(request, response);
					return;
				}
				Manager_accountService manager_accountSvc = new Manager_accountService();
				Manager_accountVO manager_accountVO = manager_accountSvc.getOneEmp_name(emp_name);
				if (manager_accountVO == null) {
					errorMsgs.add("�d�L���");
				} else if (!accpw.equals(manager_accountVO.getAccpw())) {
					errorMsgs.add("�z��J���K�X���~�Э��s��J");
				} else if ("1".equals(manager_accountVO.getMan_acc_status())) {
					errorMsgs.add("�z��J���b���w���v");
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

		/*************************** �P�_�O�_���L�n�J *****************************************/
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
