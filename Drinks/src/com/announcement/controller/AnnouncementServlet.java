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
		
		
        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);

			try {
			
				String ann_title = req.getParameter("ann_title").trim();
				if (ann_title == null || ann_title.trim().length() == 0) {
					errorMsgs.add("���D�ФŪť�");
				}
				
				String ann_des = req.getParameter("ann_des").trim();
				if (ann_des == null || ann_des.trim().length() == 0) {
					errorMsgs.add("�ԭz�ФŪť�");
				}
				
				java.sql.Date ann_date = null;
				try {
					ann_date = java.sql.Date.valueOf(req.getParameter("ann_date").trim());
				} catch (IllegalArgumentException e) {
					ann_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
//				byte[] ann_img = null;
//				Part filePart = req.getPart("ann_img");
//				InputStream in = null;
//				if (filePart != null) {
//					in = filePart.getInputStream();
//					ann_img = new byte[in.available()];
//					in.read(ann_img);
//				} else {
//					errorMsgs.add("�п�ܤW�ǹϤ�");
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
					req.setAttribute("announcementVO", announcementVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/announcementAdd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementVO = announcementSvc.addAnnouncement(ann_title, ann_des, ann_date, ann_img, ann_status);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/announcement/announcementList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����announcementList.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/announcementAdd.jsp");
				failureView.forward(req, res);
			}
		}
		
        
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String ann_id = new String(req.getParameter("ann_id"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				AnnouncementVO announcementVO = announcementSvc.getOneAnnouncement(ann_id);

				/**************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("announcementVO", announcementVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/announcement/update_announcement_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/announcement/announcementList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				String ann_id = new String(req.getParameter("ann_id").trim());

				String ann_title = req.getParameter("ann_title").trim();
				if (ann_title == null || ann_title.trim().length() == 0) {
					errorMsgs.add("���D�ФŪť�");
				}

				String ann_des = req.getParameter("ann_des").trim();
				if (ann_des == null || ann_des.trim().length() == 0) {
					errorMsgs.add("�ԭz�ФŪť�");
				}
				
				java.sql.Date ann_date = null;
				try {
					ann_date = java.sql.Date.valueOf(req.getParameter("ann_date").trim());
				} catch (IllegalArgumentException e) {
					ann_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
//				byte[] ann_img = null;
//				Part filePart = req.getPart("ann_img");
//				InputStream in = null;
//				if (filePart != null) {
//					in = filePart.getInputStream();
//					ann_img = new byte[in.available()];
//					in.read(ann_img);
//				} else {
//					errorMsgs.add("�п�ܤW�ǹϤ�");
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
					req.setAttribute("announcementVO", announcementVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/announcement/update_announcement_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementVO = announcementSvc.updateAnnouncement(ann_id, ann_title, ann_des, ann_date, ann_img, ann_status);
				
				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("announcementVO", announcementVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/announcement/announcementList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/announcement/update_announcement_input.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String ann_id = new String(req.getParameter("ann_id").trim());
				/***************************2.�}�l�R�����***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementSvc.deleteAnnouncement(ann_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/announcement/announcementList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/announcementList.jsp");
				failureView.forward(req, res);
			}
		}
       
	}
}
