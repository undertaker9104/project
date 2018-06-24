package com.group_track.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_track.model.Group_trackService;
import com.group_track.model.Group_trackVO;
import com.mem.model.MemberVO;

public class Group_trackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("0".equals(action)) {
			try {
				String grou_id = req.getParameter("grou_id");
				String mem_id = req.getParameter("mem_id");
				Group_trackService gtSvc = new Group_trackService();
				gtSvc.insertGroup_track(mem_id, grou_id);
				
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		if ("1".equals(action)) {
			try {
				String grou_id = req.getParameter("grou_id");
				String mem_id = req.getParameter("mem_id");

				Group_trackService gtSvc = new Group_trackService();
				gtSvc.deleteGroup_track(mem_id, grou_id);
				
				List<Group_trackVO> group_trackVOList = gtSvc.getMem_id(mem_id);
				req.getSession().setAttribute("list", group_trackVOList);
				RequestDispatcher View = req.getRequestDispatcher("/front-end/groupart/MyTrackList.jsp");
				View.forward(req, res);

			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		if ("mem_idlist".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
   				try {
					MemberVO memVO1 = (MemberVO) req.getSession().getAttribute("memVO");
					String mem_id = memVO1.getMem_id();
					String str = memVO1.getMem_id();
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請勿空白");
					}
		
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/groupart/MyTrackList.jsp");
						failureView.forward(req, res);
						return;
					}
					
					Group_trackService gtSvc = new Group_trackService();
					List<Group_trackVO> group_trackVOList = gtSvc.getMem_id(mem_id);
					if (group_trackVOList.size() == 0) {
						errorMsgs.add("無此資料");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupart/MyTrackList.jsp");
						failureView.forward(req, res);
						return;
					}	
				req.getSession().setAttribute("list", group_trackVOList); 
				String url = "/front-end/groupart/MyTrackList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				}catch (Exception e) {
					errorMsgs.add("無此資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupart/MyTrackList.jsp");
					System.out.println("text");
					failureView.forward(req, res);
				}
				}
		
		if ("grou_idlist".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
   	  
				try {
					String str = req.getParameter("grou_id");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請勿空白");
					}
				
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("group_track_test.jsp");
						failureView.forward(req, res);
						return;
					}
									
					String grou_id = req.getParameter("grou_id");
				
					Group_trackService gtSvc = new Group_trackService();
					List<Group_trackVO> group_trackVOList = gtSvc.getGrou_id(grou_id);
					if (group_trackVOList.size() == 0) {
						errorMsgs.add("請勿空白");
					}
			
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("group_track_test.jsp");
						failureView.forward(req, res);
						return;
					}	

				req.getSession().setAttribute("list", group_trackVOList); 

				String url = "grou_idtrack.jsp";
			
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
		
				}catch (Exception e) {
					errorMsgs.add("查無此資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("group_track_test.jsp");
					failureView.forward(req, res);
				}
			}
	}
}
