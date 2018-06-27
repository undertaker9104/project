package com.group_art.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.group_art.model.GroupService;
import com.group_art.model.Group_ArtVO;

@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Timer timer = null;
	
	public void destroy(){
		timer.cancel();
	}
	
	
	public void init(){
		timer = new Timer();
		GroupService groupSvc = new GroupService();
		TimerTask timerTask = new TimerTask(){
	    List<Group_ArtVO> group_ArtVOList = null;
			@Override
			public void run() {
				group_ArtVOList = groupSvc.getAll();
				for(Group_ArtVO group_ArtVO : group_ArtVOList){
					long time = System.currentTimeMillis();
					Instant firstInstant = Instant.ofEpochMilli(group_ArtVO.getExp_date().getTime());
					Instant secondInstant = Instant.ofEpochMilli(time);
					Duration between = Duration.between(secondInstant, firstInstant);
					if(0 == group_ArtVO.getGrou_status()){
						if(between.isNegative()){
							groupSvc.deleteGroup(group_ArtVO.getGrou_id());
							System.out.println("®Ï¥¡§È¥¡" + new Date());
						}
					}
				}
			}
			
		};
		timer.scheduleAtFixedRate(timerTask,0, 3000);
	}
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			String mem_id = req.getParameter("mem_id");
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			
				String ship_locat = req.getParameter("ship_locat").trim();
				String ship_locatbu = req.getParameter("ship_locatbt");
				if("0".equals(ship_locatbu)){
					ship_locat = "";
				}else{
					ship_locat = req.getParameter("ship_locat").trim();
					if(ship_locat == null || ship_locat.trim().length() == 0){
						errorMsgs.add("Ω–§≈™≈•’");
						if(ship_locat == null || ship_locat.trim().length() == 0){
							errorMsgs.add("Ω–§≈™≈•’");
							
						}
					}
				}
				String send_locat = req.getParameter("send_locat").trim();
				
				if(send_locat == null || send_locat.trim().length() == 0){
					errorMsgs.add("Ω–§≈™≈•’");
					
				}
				
				java.sql.Timestamp exp_date = null;
				
				try {
					exp_date = java.sql.Timestamp.valueOf(req.getParameter("exp_date").trim());
				} catch (IllegalArgumentException e) {
					exp_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("¨dµL¶π∏ÍÆ∆");
				}
				
			
				byte[] art_img = null;
				Part filePart = req.getPart("art_img");
				InputStream is = null;
				if(filePart != null){
					is  = filePart.getInputStream();
					art_img = new byte[is.available()];
					is.read(art_img);
				}
				
				String art_name = req.getParameter("art_name").trim();
				if(art_name == null || art_name.trim().length() == 0){
					errorMsgs.add("Ω–§≈™≈•’");
					
				}
				
				Integer grou_price = null;
				try{
					grou_price = new Integer(req.getParameter("grou_price").trim());
				}catch(Exception e){
					grou_price = 0;
					errorMsgs.add("ÆÊ¶°§£πÔ");
				}
				Group_ArtVO group_ArtVO = new Group_ArtVO();
				group_ArtVO.setMem_id(mem_id);
				group_ArtVO.setShip_locat(ship_locat);
				group_ArtVO.setSend_locat(send_locat);
				group_ArtVO.setExp_date(exp_date);
				group_ArtVO.setArt_img(art_img);
				group_ArtVO.setArt_name(art_name);
				group_ArtVO.setGrou_price(grou_price);
				
				if(!errorMsgs.isEmpty()){
					req.setAttribute("group_ArtVO",group_ArtVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupart/addGroupListForm.jsp");
					failureView.forward(req, res);
					return;
				}

				GroupService groupSvc = new GroupService();
				group_ArtVO = groupSvc.addGroup_Art(mem_id,ship_locat, send_locat, exp_date, art_img, art_name, grou_price);
			
				String url = "/front-end/groupart/grouplist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	
				
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupart/addGroupListFrom.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			

			try {
			
				String grou_id = req.getParameter("grou_id");
				GroupService grouSvc = new GroupService();
				grouSvc.deleteGroup(grou_id);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("¨dµL¶π∏ÍÆ∆:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupart/MyGroupList.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOneForUpdate".equals(action)){
			List<String> errorMsgs = new LinkedList<>();			
			req.setAttribute("errorMsgs", errorMsgs);			
			String requestURL = req.getParameter("requestURL");
			
			try{
				String grou_id = req.getParameter("grou_id");
				
			
				GroupService grouSvc = new GroupService();
				Group_ArtVO group_ArtVO = grouSvc.getOneArt(grou_id);
								
				
				req.setAttribute("group_ArtVO", group_ArtVO); 
				String url = "/front-end/groupart/UpdateGroupInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("¨dµL¶π∏ÍÆ∆:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if("getOneForUpdateForBackend".equals(action)){
			List<String> errorMsgs = new LinkedList<>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try{
				
				String grou_id = req.getParameter("grou_id");
				
				
				GroupService grouSvc = new GroupService();
				Group_ArtVO group_ArtVO = grouSvc.getOneArt(grou_id);
				req.setAttribute("group_ArtVO", group_ArtVO); 
				String url = "/back-end/groupart/UpdateGroupInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);


			} catch (Exception e) {
				errorMsgs.add("¨dµL¶π∏ÍÆ∆::"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		
		if("update".equals(action)){

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			String grou_id = req.getParameter("grou_id");
			String ord_id = req.getParameter("ord_id");
			try {
			
				String ship_locat = req.getParameter("ship_locat").trim();
				String ship_locatbu = req.getParameter("ship_locatbt");
				if("0".equals(ship_locatbu)){
					ship_locat = "";
				}else{
					ship_locat = req.getParameter("ship_locat").trim();
					if(ship_locat == null || ship_locat.trim().length() == 0){
						errorMsgs.add("Ω–§≈™≈•’");
						if(ship_locat == null || ship_locat.trim().length() == 0){
							errorMsgs.add("Ω–§≈™≈•’");
							
						}
					}
				}
				String send_locat = req.getParameter("send_locat").trim();
				
				if(send_locat == null || send_locat.trim().length() == 0){
					errorMsgs.add("Ω–§≈™≈•’");
					
				}
				java.sql.Timestamp exp_date = null;
				try {
					exp_date = java.sql.Timestamp.valueOf(req.getParameter("exp_date").trim());
				} catch (IllegalArgumentException e) {
					exp_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("Ω–§≈™≈•’");
				}
				byte[] art_img = null;
				Part filePart = req.getPart("art_img");
				InputStream is =filePart.getInputStream();
				if(is.available() != 0){
					art_img = new byte[is.available()];
					is.read(art_img);
				}else{
					GroupService groupSvc = new GroupService();
					art_img = groupSvc.getOneArt(grou_id).getArt_img();
				}
								
				String art_name = req.getParameter("art_name").trim();
				if(art_name == null || art_name.trim().length() == 0){
					errorMsgs.add("Ω–§≈™≈•’");
					
				}
				Integer grou_price = null;
				try{
					grou_price = new Integer(req.getParameter("grou_price").trim());
				}catch(Exception e){
					grou_price = 0;
					errorMsgs.add("ÆÊ¶°§£πÔ");
				}
			
				Group_ArtVO group_ArtVO = new Group_ArtVO();
				group_ArtVO.setShip_locat(ship_locat);
				group_ArtVO.setSend_locat(send_locat);
				group_ArtVO.setExp_date(exp_date);
				group_ArtVO.setArt_img(art_img);
				group_ArtVO.setArt_name(art_name);
				group_ArtVO.setGrou_price(grou_price);
				group_ArtVO.setGrou_id(grou_id);
				group_ArtVO.setOrd_id(ord_id);
				if(!errorMsgs.isEmpty()){
					req.setAttribute("group_ArtVO",group_ArtVO);
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				GroupService groupSvc = new GroupService();
				
				group_ArtVO = groupSvc.updateGrou(ship_locat, send_locat, exp_date, art_img, art_name, grou_price, grou_id,ord_id);
			
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				

			} catch (Exception e) {
				System.out.println(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if("getAllBySr".equals(action)){
			String art_name = req.getParameter("search");
			List<Group_ArtVO> group_ArtVOList = null;
			try{
				GroupService groupSvc = new GroupService();
				group_ArtVOList = groupSvc.getAllBySr(art_name);
				req.setAttribute("search",group_ArtVOList);
				
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/groupart/grouplist.jsp"); // ?ñ∞Â¢ûÊ?êÂ?üÂ?åË?â‰∫§addGroupListForm.jsp
				successView.forward(req, res);
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupart/grouplist.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
