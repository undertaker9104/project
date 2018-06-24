package com.deposit_records.controller;

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

import com.deposit_records.model.Deposit_recordsService;
import com.deposit_records.model.Deposit_recordsVO;
import com.mem.model.MemberService;
import com.mem.model.MemberVO;

@WebServlet("/Deposit_recordsServlet")
public class Deposit_recordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Deposit_recordsServlet() {
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
		/*************************** �x���I�� ****************************************/

		if ("stored".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = request.getSession();
			MemberVO memVO1 = (MemberVO) session.getAttribute("memVO");

			try {
				String amount = request.getParameter("amount");
				if (amount == null || (amount.trim()).length() == 0) {
					errorMsgs.add("�п�J�z�n�x�Ȫ��I��");
				}
				try {
					new Integer(amount);
				} catch (Exception e) {
					errorMsgs.add("�x�Ȫ��B�u��L�Ʀr");
				}
				String cardNumber = request.getParameter("cardNumber");
				if (cardNumber == null || (cardNumber.trim()).length() == 0) {
					errorMsgs.add("�п�J�z���H�Υd�d��");
				}
			    try {
					new Integer(cardNumber);
				} catch (Exception e) {
					errorMsgs.add("�H�Υd�d���u��Ʀr");
				}
				String cardPassword = request.getParameter("cardPassword");
				if (cardPassword == null || (cardPassword.trim()).length() == 0) {
					errorMsgs.add("�п�J�z���H�Υd�K�X");
				}
				try {
					new Integer(cardPassword);
				} catch (Exception e) {
					errorMsgs.add("�H�Υd�K�X�u��Ʀr");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/storedPoint.jsp");
					failureView.forward(request, response);
					return;
				}

				String mem_id = memVO1.getMem_id();
//				String mem_id = request.getParameter("mem_id");
//				System.out.println(amount);
//				System.out.println(cardNumber);
//				System.out.println(cardPassword);
//				System.out.println(mem_id);
				java.util.Date now = new java.util.Date();
				java.sql.Date dep_suss_date = new java.sql.Date(now.getTime());
				Integer dep_cash = new Integer(amount);
				
				//�R 1000�e50
	
				Deposit_recordsService deposit_recordSvc = new Deposit_recordsService();
				deposit_recordSvc.addRecords(mem_id, dep_cash, dep_suss_date);
				MemberService memberSvc = new MemberService();

				MemberVO memVO = memberSvc.getOneMem_id(mem_id);
				//�R1000�e50
				Integer mem_point = memVO.getMem_point() + dep_cash+(dep_cash/1000)*50;
				memberSvc.updateMem_Point(mem_id, mem_point);
				memVO.setMem_point(mem_point);
				session.setAttribute("memVO", memVO);
				
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/mem/stored_success.jsp");
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("�x�ȥ��� �Э��s�x��");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/storedPoint.jsp");
				failureView.forward(request, response);
			}

		}

		/*************************** �x�Ȭ��� ****************************************/
		if ("Record_value".equals(action)) {
			try {
				String mem_id = request.getParameter("mem_id");
				Deposit_recordsService deposit_recordsSvc = new Deposit_recordsService();
				List<Deposit_recordsVO> deposit_records_valuelist = deposit_recordsSvc.getOneRecords_MemIdList(mem_id);
				request.getSession().setAttribute("deposit_records_valuelist", deposit_records_valuelist);
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/mem/listOneMemRecord_value.jsp"); // ���\���listOneEmp.jsp
				successView.forward(request, response);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/index.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String mem_id = request.getParameter("mem_id");
				if (mem_id == null || (mem_id.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
			
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_point.jsp");
					failureView.forward(request, response);
					return;// �{�����_
				}
				Deposit_recordsService deposit_recordsSvc = new Deposit_recordsService();
				List<Deposit_recordsVO> deposit_recordsVOList = deposit_recordsSvc.getOneRecords_MemIdList(mem_id);
				if (deposit_recordsVOList.size()==0) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_point.jsp");
					failureView.forward(request, response);
					return;// �{�����_
				}		
				request.getSession().setAttribute("deposit_recordsVOList", deposit_recordsVOList);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/mem/listOneMemRecord_value.jsp"); // ���\���listOneEmp.jsp
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_point.jsp");
				failureView.forward(request, response);
			}
		}

	}

}
