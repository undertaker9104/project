package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import org.json.JSONObject;

import com.mem.model.MailService;
import com.mem.model.MemberService;
import com.mem.model.MemberVO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

@WebServlet("/MemberServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
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

		/*************************** 登入 ****************************************/
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String email = request.getParameter("email");
				if (email == null || (email.trim()).length() == 0) {
					errorMsgs.add("請輸入您的Email");
				}
				String password = request.getParameter("password");
				if (password == null || (password.trim()).length() == 0) {
					errorMsgs.add("請輸入您的密碼");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/Login.jsp");
					failureView.forward(request, response);
					return;
				}
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMem(email);
				if (memVO == null) {
					errorMsgs.add("您尚未註冊");
				} else if (!password.equals(memVO.getMem_pwd())) {
					errorMsgs.add("您輸入的密碼有誤請重新輸入");
				} else if ("1".equals(memVO.getMem_acc_status())) {
					errorMsgs.add("您輸入的帳號已停權，如有問題請聯絡客服");
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("memVO", memVO);
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
					response.sendRedirect(request.getContextPath() + "/front-end/index.jsp");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/Login.jsp");
					failureView.forward(request, response);
					return;
				}
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/Login.jsp");
				failureView.forward(request, response);
			}
		}
		/*************************** 判斷是否有無登入狀態 ****************************************/
		if ("InOut".equals(action)) {
			HttpSession session = request.getSession();
			MemberVO memVO = (MemberVO) session.getAttribute("memVO");
			if (memVO == null) {
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/Login.jsp");
				failureView.forward(request, response);
			} else {
				session.removeAttribute("memVO");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/index.jsp");
				failureView.forward(request, response);
			}
		}

		/*************************** 註冊 ****************************************/
if ("Register".equals(action)) {
			
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			PrintWriter out = response.getWriter();
			Boolean isDone=true;
			try {
				String mem_name = request.getParameter("username");
				if (mem_name == null || (mem_name.trim()).length() == 0) {
					errorMsgs.add("請輸入使用者名稱");
				}
				
				String mem_email = request.getParameter("email");
				if (mem_email == null || (mem_email.trim()).length() == 0) {
					errorMsgs.add("請輸入您的Email");
				}
				System.out.println(mem_email);
				String mem_pwd = request.getParameter("password");
				if (mem_pwd == null || (mem_pwd.trim()).length() == 0) {
					errorMsgs.add("請輸入您的password");
				}
			
				String mem_sex = request.getParameter("sex");
				if (mem_sex == null || (mem_sex.trim()).length() == 0) {
					errorMsgs.add("請輸入您的性別");
				}
				
				
				java.sql.Date mem_birth = null;
				try {
					mem_birth = java.sql.Date.valueOf(request.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					mem_birth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
			
				String mem_phone = request.getParameter("phone");
				String phoneReg = "^09[0-9]{8}$";
				if (mem_phone == null || (mem_phone.trim()).length() == 0) {
					errorMsgs.add("請輸入您的手機");
				} else if (!mem_phone.trim().matches(phoneReg)) {
					errorMsgs.add("請輸入您的手機格式錯誤");
				}
				
				String mem_ads = request.getParameter("address");
				if (mem_ads == null || (mem_ads.trim()).length() == 0) {
					errorMsgs.add("請輸入您的地址");
				}
				
				
				byte[] mem_pic = null;
				Part filePart = request.getPart("file");
				InputStream in = null;
				if (filePart != null) {
					in = filePart.getInputStream();
					mem_pic = new byte[in.available()];
					in.read(mem_pic);
				} else {
					errorMsgs.add("請選擇上傳圖片");
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
					out.print(obj);
					out.flush();
					out.close();
					//RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/register.jsp");
					//failureView.forward(request, response);
					return;
				}
				
				String details = "Member_name :"+ mem_name ;
				String details2 = new String(details.getBytes("UTF-8"),"ISO-8859-1");
				ByteArrayOutputStream bos = QRCode.from(details2).to(ImageType.PNG).withSize(500, 500).stream();
				byte[] mem_qrcode =  bos.toByteArray();
				
				
				
				MemberService memSvc = new MemberService();
				MemberVO memVO1 = memSvc.getOneMem(mem_email);
				if (memVO1 != null) {
					errorMsgs.add("信箱已被註冊，請重新輸入");
				}
				
				MemberVO memVO = new MemberVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_email(mem_email);
				memVO.setMem_pwd(mem_pwd);
				memVO.setMem_sex(mem_sex);
				memVO.setMem_birth(mem_birth);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_ads(mem_ads);
				memVO.setMem_pic(mem_pic);
				memVO.setMem_qrcode(mem_qrcode);
				if (!errorMsgs.isEmpty()) {
					
					JSONObject obj = new JSONObject();
					isDone = false;
					try{
						obj.put("errorMsgs",errorMsgs);
						obj.put("isDone",isDone);
					}catch(Exception e){}
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					out.print(obj);
					out.flush();
					out.close();
					//RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/register.jsp");
					//failureView.forward(request, response);
					return;
				}
				memSvc.addMem(mem_name, mem_email, mem_pwd, mem_sex, mem_birth, mem_phone, mem_ads, mem_pic , mem_qrcode);
				JSONObject obj = new JSONObject();
				out.print(obj);
				//RequestDispatcher success = request.getRequestDispatcher("/front-end/mem/register_success.jsp");
				//success.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("註冊失敗 請重新註冊");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/register.jsp");
				failureView.forward(request, response);
			}
		}

		/*************************** 修改 ****************************************/
		if ("Modify".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = request.getSession();
			MemberVO memVO1 = (MemberVO) session.getAttribute("memVO");
			try {
				String mem_pwd = request.getParameter("password");
				if (mem_pwd == null || (mem_pwd.trim()).length() == 0) {
					errorMsgs.add("請輸入您的password");
				}
				String mem_phone = request.getParameter("phone");
				String phoneReg = "^09[0-9]{8}$";
				if (mem_phone == null || (mem_phone.trim()).length() == 0) {
					errorMsgs.add("請輸入您的手機");
				} else if (!mem_phone.trim().matches(phoneReg)) {
					errorMsgs.add("請輸入您的手機格式錯誤");
				}
				String mem_ads = request.getParameter("address");
				if (mem_ads == null || (mem_ads.trim()).length() == 0) {
					errorMsgs.add("請輸入您的地址");
				}
				byte[] mem_pic = null;
				Part filePart = request.getPart("mem_pic");
				InputStream in = null;
				in = filePart.getInputStream();
				if (in.available() != 0) {
					mem_pic = new byte[in.available()];
					in.read(mem_pic);
				} else {
					mem_pic = memVO1.getMem_pic();
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/modify.jsp");
					failureView.forward(request, response);
					return;
				}
				MemberService memSvc = new MemberService();
				memSvc.updateMem(mem_pwd, mem_phone, mem_ads, mem_pic, memVO1.getMem_email());
				MemberVO memVO = memSvc.getOneMem(memVO1.getMem_email());
				session.setAttribute("memVO", memVO);
				RequestDispatcher success = request.getRequestDispatcher("/front-end/mem/modify_success.jsp");
				success.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("更新失敗  請重新更新");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/modify.jsp");
				failureView.forward(request, response);
			}
		}

		/*************************** 取得一筆資料 *****************************************/
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String mem_id = request.getParameter("mem_id");
				if (mem_id == null || (mem_id.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_mem.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMem_id(mem_id);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_mem.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				request.setAttribute("memberVO", memberVO);
				String url = "/back-end/mem/listOneMem.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/select_mem.jsp");
				failureView.forward(request, response);
			}
		}
		/*************************** 刪除一筆資料 *****************************************/
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			try {
				String mem_id = request.getParameter("mem_id");
				MemberService memberSvc = new MemberService();
				memberSvc.getOneMem(mem_id);
				memberSvc.deleteMem(mem_id);
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}

		/*************************** 取得一筆資料更新 *****************************************/
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			try {
				String mem_id = request.getParameter("mem_id");
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMem_id(mem_id);
				request.setAttribute("memberVO", memberVO);
				String url = "/back-end/mem/update_mem_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}

		/*************************** 更新 *****************************************/
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = new MemberVO();
			try {
				String mem_id = request.getParameter("mem_id").trim();
				Integer mem_acc_status = Integer.parseInt(request.getParameter("mem_acc_status").trim());
				if (!(mem_acc_status.equals("0")) && !(mem_acc_status.equals("1"))) {
					errorMsgs.add("權限請填0(正常)或1(停權).");
				}
				Integer mem_point = null;
				try {
					mem_point = new Integer(request.getParameter("mem_point").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請填入數字");
				}
				Integer mem_int = null;
				try {
					mem_int = new Integer(request.getParameter("mem_int").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請填入數字");
				}
				byte[] mem_pic = null;
				Part filePart = request.getPart("mem_pic");
				InputStream in = null;
				in = filePart.getInputStream();
				if (in.available() != 0) {
					mem_pic = new byte[in.available()];
					in.read(mem_pic);
					in.close();
				} else {
					mem_pic = memberSvc.getOneMem_id(mem_id).getMem_pic();
				}
				memberVO = memberSvc.getOneMem_id(mem_id);
				memberVO.setMem_id(mem_id);
				memberVO.setMem_point(mem_point);
				memberVO.setMem_int(mem_int);
				memberVO.setMem_pic(mem_pic);
				memberVO.setMem_acc_status(mem_acc_status);
				request.setAttribute("memberVO", memberVO);
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/mem/update_mem_input.jsp");
					failureView.forward(request, response);
					return;
				}
				memberVO = memberSvc.updateMem_Manager(mem_id, mem_point, mem_int, mem_acc_status, mem_pic);
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/mem/update_mem_input.jsp");
				failureView.forward(request, response);
			}
		}

		/*************************** 找回密碼 *****************************************/
		if ("forgetPwd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				System.out.println(1);
				String mem_email = request.getParameter("email");
				if (mem_email == null || (mem_email.trim()).length() == 0) {
					errorMsgs.add("請輸入您的Email");
				}
				String mem_phone = request.getParameter("phone");
				String phoneReg = "^09[0-9]{8}$";
				if (mem_phone == null || (mem_phone.trim()).length() == 0) {
					errorMsgs.add("請輸入您的手機");
				} else if (!mem_phone.trim().matches(phoneReg)) {
					errorMsgs.add("請輸入您的手機格式錯誤");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/forgetPwd.jsp");
					failureView.forward(request, response);
					return;
				}
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMem(mem_email);
				if (memberVO == null) {
					errorMsgs.add("Email查無資料");
				} else if (!mem_phone.equals(memberVO.getMem_phone())) {
					errorMsgs.add("電話號碼輸入錯誤");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/forgetPwd.jsp");
					failureView.forward(request, response);
					return;
				}

				String to = memberVO.getMem_email();
				String subject = "密碼通知";
				String ch_name = memberVO.getMem_name();
				String passRandom = memberVO.getMem_pwd();
				String messageText = " Hello! " + ch_name + " 請謹記此密碼: " + passRandom;
				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);

				RequestDispatcher successView = request.getRequestDispatcher("/front-end/mem/forgetPwd_success.jsp");
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("發生不明錯誤,請重新常識或聯絡客服");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mem/forgetPwd.jsp");
				failureView.forward(request, response);
			}
		}

	}
}
