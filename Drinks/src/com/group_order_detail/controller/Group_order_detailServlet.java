package com.group_order_detail.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.addgroup.model.AddGroupService;
import com.addgroup.model.AddGroupVO;
import com.discount.model.DiscountService;
import com.discount.model.DiscountVO;
import com.group_art.model.GroupService;
import com.group_art.model.Group_ArtVO;
import com.group_order_detail.model.Group_order_detailService;
import com.group_order_detail.model.Group_order_detailVO;
import com.mem.model.MemberService;
import com.mem.model.MemberVO;
import com.orderdetail.model.OrderDetailService;
import com.product.model.ProductService;
import com.product.model.ProductVo;


public class Group_order_detailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
        res.setContentType("text/html;charset=UTF-8");
		if ("enter".equals(action)) {
			try {							
				String grou_id = req.getParameter("grou_id");
				String mem_id = req.getParameter("mem_id");
				
				Group_ArtVO groupVO = new Group_ArtVO();
				groupVO.setGrou_id(grou_id);
				groupVO.setMem_id(mem_id);
				
				req.getSession().setAttribute("groupVO",groupVO); 
				String url = "/front-end/grouporderdetail/group_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		
		if ("delete".equals(action)) {
			try {
				String grou_id = req.getParameter("grou_id");
				String mem_id = req.getParameter("mem_id");
				String product_id = req.getParameter("product_id");
				String sweet_id = req.getParameter("sweet_id");
				String ice_id = req.getParameter("ice_id");
				String ord_id = req.getParameter("ord_id");
								
				Group_order_detailService godSvc = new Group_order_detailService();
				godSvc.deleteGroup_order_detail(grou_id,mem_id,product_id,sweet_id,ice_id,ord_id);
				List<Group_order_detailVO> group_order_detailVO = godSvc.getGrou_id(grou_id);
				req.setAttribute("group_order_detailVO", group_order_detailVO); 
				String url = "/front-end/grouporderdetail/group_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		
		if("cancelgroup".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
				

			try {
				String grou_id = req.getParameter("grou_id");
				String mem_id = req.getParameter("mem_id");

				GroupService grouSvc = new GroupService();
				grouSvc.deleteGroup(grou_id);

				AddGroupVO addgroupVO = new AddGroupVO();
				AddGroupService addSvc = new AddGroupService();
				addgroupVO.setGrou_id(grou_id);
				addgroupVO.setMem_id(mem_id);
				addSvc.LeaveGroup(addgroupVO);
				
			String url = "/front-end/groupart/grouplist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************嚙踝���□�嚙踝�蕭嚙踝���嚙踐嚙踝蕭**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupart/grouplist.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("addgroup".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();			
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String mem_id = req.getParameter("mem_id");
				String grou_id = req.getParameter("grou_id");
				
				AddGroupVO addgroupVO = new AddGroupVO();
				addgroupVO.setMem_id(mem_id);
				addgroupVO.setGrou_id(grou_id);
				
				AddGroupService addSvc = new AddGroupService();
				addgroupVO = addSvc.add(mem_id, grou_id);
								
				req.setAttribute("addgroupVO", addgroupVO); 
				
				String url = "/front-end/grouporderdetail/group_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/grouporderdetail/group_detail.jsp");
				failureView.forward(req, res);
			}			
		}		
		if ("leavegroup".equals(action)) {
			try {				
				String mem_id = req.getParameter("mem_id");
				String grou_id = req.getParameter("grou_id");
				String requestURL = req.getParameter("requestURL");
				
				Group_order_detailService godSvc = new Group_order_detailService();
				godSvc.deleteByMem_id(mem_id);
				
				AddGroupVO addgroupVO = new AddGroupVO();
				AddGroupService addSvc = new AddGroupService();
				addgroupVO.setGrou_id(grou_id);
				addgroupVO.setMem_id(mem_id);
				addSvc.LeaveGroup(addgroupVO);
				
				List<Group_order_detailVO> group_order_detailVO = godSvc.getGrou_id(grou_id);
				req.setAttribute("group_order_detailVO", group_order_detailVO); 
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �����������蕭 listOneEmp.jsp
				successView.forward(req, res);			
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
				
		if ("update".equals(action)) {
			try {
				String grou_id = req.getParameter("grou_id");
				String mem_id = req.getParameter("mem_id");
				String product_id = req.getParameter("product_id");
				String sweet_id = req.getParameter("sweet_id");
				String ice_id = req.getParameter("ice_id");
				String ord_id = req.getParameter("ord_id");
				
				Group_order_detailService godSvc = new Group_order_detailService();
				godSvc.deleteGroup_order_detail(grou_id,mem_id,product_id,sweet_id,ice_id,ord_id);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
						
		if ("add1".equals(action)) {							
				String grou_id = req.getParameter("grou_id");
			    MemberService mSvc = new MemberService();
				Group_order_detailService godSvc = new Group_order_detailService();
				List<Group_order_detailVO> group_order_detaillist = godSvc.getGrou_id(grou_id);	
			    List<Group_order_detailVO> allmemlist = godSvc.getMem_id(grou_id);
			
				String mem_id = req.getParameter("mem_id");
				String product_id = req.getParameter("product_id");
				String sweet_id = req.getParameter("sweet_id");
				String ice_id = req.getParameter("ice_id");	
				String ord_id = req.getParameter("ord_id");	
				Integer group_ord_price =  new Integer(req.getParameter("product_price"));
				Integer group_tol_cup = new Integer(req.getParameter("group_tol_cup"));
				Group_order_detailVO group_order_detailVO1 = new Group_order_detailVO(grou_id, mem_id, product_id, sweet_id, ice_id,ord_id,group_ord_price,group_tol_cup);
				MemberVO mempoint = mSvc.getImg(mem_id);
				Integer havepoint = mempoint.getMem_point();
				
				PrintWriter out = res.getWriter();
				
				    if(group_order_detaillist != null){					    				    	    
				    	Integer paypoint =0;
				    	List<Group_order_detailVO> memlist = godSvc.getPrice(mem_id, grou_id);
						
						for (int j = 0; j <memlist.size(); j++) {										
							Group_order_detailVO needpoint = memlist.get(j);
							Integer point = needpoint.getGroup_ord_price();
							Integer cup = needpoint.getGroup_tol_cup();
							paypoint += point*cup;
						}
									if(havepoint >= paypoint+(group_ord_price*group_tol_cup)){
									    if(group_order_detaillist.contains(group_order_detailVO1)){
										Group_order_detailVO innerdetailVO = group_order_detaillist.get(group_order_detaillist.indexOf(group_order_detailVO1));
										godSvc.updateGroup_order_detail(grou_id, mem_id, product_id, sweet_id, ice_id,ord_id,group_ord_price ,new Integer(innerdetailVO.getGroup_tol_cup()+group_order_detailVO1.getGroup_tol_cup()));
										}else{				
										group_order_detaillist.add(group_order_detailVO1);					
					                    godSvc.insertGroup_order_detail(grou_id,mem_id,product_id,sweet_id,ice_id,ord_id,group_ord_price,group_tol_cup);
									    }
									    req.setAttribute("group_order_detailVO", group_order_detaillist); 
										String url = "/front-end/grouporderdetail/group_detail.jsp";
										RequestDispatcher successView = req.getRequestDispatcher(url); 
										successView.forward(req, res);
								    }
									if(havepoint < paypoint+(group_ord_price*group_tol_cup)){
										try {
											Thread.sleep(3000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										out.println("<script>");
										out.println("alert('點數不足,請儲值點數');");
										out.println("</script>");
																				
										RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/storedPoint.jsp");
										failureView.forward(req, res);										
									}
				        }				    	    							
		}									        				    															
		if ("checkout".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();			
			req.setAttribute("errorMsgs", errorMsgs); 
			    
				try {		
					String grou_id = req.getParameter("grou_id");
					Group_order_detailService godSvc = new Group_order_detailService();
					List<Group_order_detailVO> group_order_detailVO = godSvc.getGrou_id(grou_id);								
					
					GroupService gSvc = new GroupService();
					Group_ArtVO group_ArtVO = gSvc.getOneArt(grou_id);
				    MemberService mSvc = new MemberService();
					OrderDetailService odSvc = new OrderDetailService();
					MailService mailSvc = new MailService();															
					DiscountService disSvc = new DiscountService();
					List<DiscountVO> dislist = disSvc.getAll();
					DiscountVO disVO = dislist.get(0);					
                    MemberVO memvo = mSvc.getMem_id(group_ArtVO.getMem_id());
                    String to = memvo.getMem_email();
                    String subject = "揪團訂單明細";
                    String messageText = "您的揪團訂單已經成立，請稍後至取貨地點取貨。";
                    
                    Integer total = 0;
					Integer cup = 0;
					Group_order_detailVO ggg = null;
			    	List<Group_order_detailVO> nowlist = godSvc.getGrou_id(grou_id);
					for (int i = 0; i <group_order_detailVO.size(); i++) {
						Group_order_detailVO gg = group_order_detailVO.get(i);
						
						Integer price = gg.getGroup_ord_price();
						Integer quantity = gg.getGroup_tol_cup();
						
						cup += quantity;
						total += (price * quantity);						
					}	
					if(cup >= disVO.getDis_cup()){
						total = (int) (total*disVO.getDis_cup_rate());
						for (int i = 0; i <nowlist.size(); i++) {
							 ggg = nowlist.get(i);	
							 Integer drinkprice = (int)(ggg.getGroup_ord_price()*disVO.getDis_cup_rate());
							 godSvc.updateGroup_order_detail(ggg.getGrou_id(),ggg.getMem_id(),ggg.getProduct_id(),ggg.getSweet_id(),ggg.getIce_id(),ggg.getOrd_id(),drinkprice,ggg.getGroup_tol_cup());
						    }
					}
					if(total >= disVO.getDis_price()){
						total = (int) (total*disVO.getDis_price_rate());
						for (int i = 0; i <nowlist.size(); i++) {
							 ggg = nowlist.get(i);	
							 Integer drinkprice = (int)(ggg.getGroup_ord_price()*disVO.getDis_price_rate());
							 godSvc.updateGroup_order_detail(ggg.getGrou_id(),ggg.getMem_id(),ggg.getProduct_id(),ggg.getSweet_id(),ggg.getIce_id(),ggg.getOrd_id(),drinkprice,ggg.getGroup_tol_cup());
						    }
					}
					Integer totalprice = 0;
					Integer totalcup = 0;					
			    	List<Group_order_detailVO> totallist = godSvc.getGrou_id(grou_id);
					for (int i = 0; i <totallist.size(); i++) {
						Group_order_detailVO gg = totallist.get(i);						
						Integer everyprice = gg.getGroup_ord_price();
						Integer everycup = gg.getGroup_tol_cup();						
						totalcup += everycup;
						totalprice += (everyprice * everycup);						
					}
											
					req.setAttribute("totalcup", totalcup);	
					req.setAttribute("totalprice", totalprice);
                    req.setAttribute("group_order_detailVO", group_order_detailVO); 

					mailSvc.sendMail(to, subject, messageText);
					
                    odSvc.insertgroup(group_order_detailVO);										

					String url = "/front-end/grouporderdetail/detail.jsp";			
					RequestDispatcher successView = req.getRequestDispatcher(url); // �����������蕭 listOneEmp.js
					successView.forward(req, res);
				
				}catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/grouporderdetail/detail.jsp");
					failureView.forward(req, res);
				}
			}	
		if ("detail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();			
			req.setAttribute("errorMsgs", errorMsgs); 
			    
				try {		
					String grou_id = req.getParameter("grou_id");
					Group_order_detailService godSvc = new Group_order_detailService();
					List<Group_order_detailVO> group_order_detailVO = godSvc.getGrou_id(grou_id);								
					
					GroupService gSvc = new GroupService();
					Group_ArtVO group_ArtVO = gSvc.getOneArt(grou_id);
				    MemberService mSvc = new MemberService();
					OrderDetailService odSvc = new OrderDetailService();
					MailService mailSvc = new MailService();															
					DiscountService disSvc = new DiscountService();
					List<DiscountVO> dislist = disSvc.getAll();
					DiscountVO disVO = dislist.get(0);					
                    
                    Integer total = 0;
					Integer cup = 0;
					Group_order_detailVO disdetail = null;
			    	List<Group_order_detailVO> nowlist = godSvc.getGrou_id(grou_id);
					for (int i = 0; i <group_order_detailVO.size(); i++) {
						Group_order_detailVO gg = group_order_detailVO.get(i);						
						Integer price = gg.getGroup_ord_price();
						Integer quantity = gg.getGroup_tol_cup();
						
						cup += quantity;
						total += (price * quantity);						
					}	
					if(cup >= disVO.getDis_cup()){
						total = (int) (total*disVO.getDis_cup_rate());						
					}					
					if(total >= disVO.getDis_price()){
						total = (int) (total*disVO.getDis_price_rate());						
					}
					req.setAttribute("cup", cup);	
					req.setAttribute("total", total);				
				
					String url = "/front-end/grouporderdetail/order_detail.jsp";								
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				
				}catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/grouporderdetail/order_detail.jsp");
					failureView.forward(req, res);
				}
			}			
		
		if ("sendEvaluation".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
 		    
				try {		
					String grou_id = req.getParameter("grou_id");
					Group_order_detailService godSvc = new Group_order_detailService();					
					List<Group_order_detailVO> emailList = godSvc.getMemEmail(grou_id);
					
					Group_order_detailVO god = new Group_order_detailVO();
				    MemberService mSvc = new MemberService();
					MailService mailSvc = new MailService();
					GroupService gSvc = new GroupService();
					Group_ArtVO group_ArtVO = gSvc.getOneArt(grou_id);
													
					for(int i=0;i<emailList.size();i++){
						god = emailList.get(i);																	
						if(god.getMem_id() != group_ArtVO.getMem_id()){   
						MemberVO memvo = mSvc.getMem_id(god.getMem_id());
	                    String to = memvo.getMem_email();
	                    String subject = "參加揪團訂單";
	                    String messageText = "您參加的揪團訂單已經成立並送出，請至取貨地點取貨，並請幫主揪者及本網站評價。";
	                    mailSvc.sendMail(to, subject, messageText);
						}
					}                                       				
					String url = "/front-end/groupart/grouplist.jsp";								
					RequestDispatcher successView = req.getRequestDispatcher(url);					
					successView.forward(req, res);
				
				}catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupart/grouplist.jsp");
					failureView.forward(req, res);
				}
		}				
	}
	}
