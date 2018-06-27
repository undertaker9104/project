package com.announcement.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.announcement.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class AnnouncementServlet extends HttpServlet {
    
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);

			try {
			
				String ann_title = req.getParameter("ann_title").trim();
				if (ann_title == null || ann_title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				
				String ann_des = req.getParameter("ann_des").trim();
				if (ann_des == null || ann_des.trim().length() == 0) {
					errorMsgs.add("敘述請勿空白");
				}
				
				java.sql.Date ann_date = null;
				try {
					ann_date = java.sql.Date.valueOf(req.getParameter("ann_date").trim());
				} catch (IllegalArgumentException e) {
					ann_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
//				byte[] ann_img = null;
//				Part filePart = req.getPart("ann_img");
//				InputStream in = null;
//				if (filePart != null) {
//					in = filePart.getInputStream();
//					ann_img = new byte[in.available()];
//					in.read(ann_img);
//				} else {
//					errorMsgs.add("請選擇上傳圖片");
//				}

				byte[] ann_img = null;
				Part filePart = req.getPart("ann_img");
				InputStream is = null;
				if (filePart != null) {
					is = filePart.getInputStream();
					ann_img = new byte[is.available()];
					is.read(ann_img);
				}
				
				Integer ann_status = Integer.parseInt(req.getParameter("ann_status"));
				
				AnnouncementVO announcementVO = new AnnouncementVO();
				announcementVO.setAnn_title(ann_title);
				announcementVO.setAnn_des(ann_des);
				announcementVO.setAnn_date(ann_date);
				announcementVO.setAnn_img(ann_img);
				announcementVO.setAnn_status(ann_status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("announcementVO", announcementVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/announcementAdd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementVO = announcementSvc.addAnnouncement(ann_title, ann_des, ann_date, ann_img, ann_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/announcement/announcementList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交announcementList.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/announcementAdd.jsp");
				failureView.forward(req, res);
			}
		}
		
        
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String ann_id = new String(req.getParameter("ann_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				AnnouncementVO announcementVO = announcementSvc.getOneAnnouncement(ann_id);

				/**************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("announcementVO", announcementVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/announcement/update_announcement_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/announcement/announcementList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String ann_id = new String(req.getParameter("ann_id").trim());

				String ann_title = req.getParameter("ann_title").trim();
				if (ann_title == null || ann_title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}

				String ann_des = req.getParameter("ann_des").trim();
				if (ann_des == null || ann_des.trim().length() == 0) {
					errorMsgs.add("敘述請勿空白");
				}
				
				java.sql.Date ann_date = null;
				try {
					ann_date = java.sql.Date.valueOf(req.getParameter("ann_date").trim());
				} catch (IllegalArgumentException e) {
					ann_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
//				byte[] ann_img = null;
//				Part filePart = req.getPart("ann_img");
//				InputStream in = null;
//				if (filePart != null) {
//					in = filePart.getInputStream();
//					ann_img = new byte[in.available()];
//					in.read(ann_img);
//				} else {
//					errorMsgs.add("請選擇上傳圖片");
//				}
				
				byte[] ann_img = null;
				Part filePart = req.getPart("ann_img");
				InputStream is =filePart.getInputStream();
				if(is.available() != 0){
					ann_img = new byte[is.available()];
					is.read(ann_img);
				}else{
					AnnouncementService announcementSvc = new AnnouncementService();
					ann_img = announcementSvc.getOneAnnouncement(ann_id).getAnn_img();
				}
				
				Integer ann_status = Integer.parseInt(req.getParameter("ann_status"));
								
				AnnouncementVO announcementVO = new AnnouncementVO();
				announcementVO.setAnn_id(ann_id);
				announcementVO.setAnn_title(ann_title);
				announcementVO.setAnn_des(ann_des);
				announcementVO.setAnn_date(ann_date);
				announcementVO.setAnn_img(ann_img);
				announcementVO.setAnn_status(ann_status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("announcementVO", announcementVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/announcement/update_announcement_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementVO = announcementSvc.updateAnnouncement(ann_id, ann_title, ann_des, ann_date, ann_img, ann_status);
				
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("announcementVO", announcementVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/announcement/announcementList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/announcement/update_announcement_input.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("delete".equals(action)) { // 來自listAllEmp.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String ann_id = new String(req.getParameter("ann_id").trim());
				/***************************2.開始刪除資料***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementSvc.deleteAnnouncement(ann_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/announcement/announcementList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/announcementList.jsp");
				failureView.forward(req, res);
			}
		}
       
	}
}
