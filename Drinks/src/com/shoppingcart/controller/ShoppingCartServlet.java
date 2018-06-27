package com.shoppingcart.controller;

import java.io.*;

import java.util.List;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.*;
import com.discount.model.DiscountService;
import com.discount.model.DiscountVO;
import com.ice.model.IceService;
import com.mem.model.*;
import com.ordermaster.model.OrderMasterService;
import com.product.model.OrderMailService;
import com.product.model.ProductService;
import com.product.model.ProductVo;
import com.shoppingcart.model.*;
import com.sweet.model.SweetService;

public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		String calcelIndex = req.getParameter("calcelIndex");
		HttpSession session = req.getSession();

		List<ShoppingCartVO> buylist = (List<ShoppingCartVO>) session.getAttribute("list");

		if ("minus".equals(action)) {
			String minusIndex = req.getParameter("minusIndex");
			req.getSession().setAttribute("minusIndex", minusIndex);
			Integer addMinusIndex = Integer.parseInt(minusIndex) - 1;
			ShoppingCartVO innerCart = (ShoppingCartVO) buylist.get(addMinusIndex);
			int count = innerCart.getQuantity();
			if (count - 1 > 0) {
				innerCart.setQuantity(innerCart.getQuantity() - 1);
				String addPro_id = innerCart.getAddProduct_id();
				ProductService proSvc = new ProductService();
				ProductVo proVo = proSvc.getOneProduct(addPro_id);
				innerCart.setProduct_price(innerCart.getProduct_price() - proVo.getProduct_price());
			}
			try {
				int total = 0;
				int totalCups = 0;
				for (int i = 0; i < buylist.size(); i++) {
					ShoppingCartVO order = (ShoppingCartVO) buylist.get(i);
					Integer price = order.getProduct_price();
					Integer quantity = order.getQuantity();
					total += price;
					totalCups += quantity;
				}

				DiscountService disSvc = new DiscountService();
				List<DiscountVO> disVo = disSvc.getAll();
				int disCup = disVo.get(0).getDis_cup();
				Double disCupRate = disVo.get(0).getDis_cup_rate();
				int disPrice = disVo.get(0).getDis_price();
				Double disPriceRate = disVo.get(0).getDis_price_rate();
				Integer amount = total;

				if (total >= disPrice) {
					total = (int) (total * disPriceRate);
				}
				if (totalCups >= disCup) {
					total = (int) (total * disCupRate);
				}
				Integer distotal = total;
				req.setAttribute("distotal", distotal);
				req.setAttribute("amount", amount);
				req.setAttribute("totalCups", totalCups);
				req.setAttribute("distotal", distotal);
				req.setAttribute("disCup", disCup);
				req.setAttribute("disPrice", disPrice);
				session.setAttribute("list", buylist);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/shoppingcart/shoppingcart.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}

		}

		if ("plus".equals(action)) {
			String plusIndex = req.getParameter("plusIndex");
			req.getSession().setAttribute("plusIndex", plusIndex);
			Integer addPlusIndex = Integer.parseInt(plusIndex) - 1;
			ShoppingCartVO innerCart = (ShoppingCartVO) buylist.get(addPlusIndex);
			String addPro_id = innerCart.getAddProduct_id();
			ProductService proSvc = new ProductService();
			ProductVo proVo = proSvc.getOneProduct(addPro_id);
			innerCart.setQuantity(innerCart.getQuantity() + 1);
			innerCart.setProduct_price(innerCart.getProduct_price() + proVo.getProduct_price());

			try {
				int total = 0;
				int totalCups = 0;
				for (int i = 0; i < buylist.size(); i++) {
					ShoppingCartVO order = (ShoppingCartVO) buylist.get(i);
					Integer price = order.getProduct_price();
					Integer quantity = order.getQuantity();
					total += price;
					totalCups += quantity;
				}

				DiscountService disSvc = new DiscountService();
				List<DiscountVO> disVo = disSvc.getAll();
				int disCup = disVo.get(0).getDis_cup();
				Double disCupRate = disVo.get(0).getDis_cup_rate();
				int disPrice = disVo.get(0).getDis_price();
				Double disPriceRate = disVo.get(0).getDis_price_rate();
				Integer amount = total;

				if (total >= disPrice) {
					total = (int) (total * disPriceRate);
				}

				if (totalCups >= disCup) {
					total = (int) (total * disCupRate);
				}

				Integer distotal = total;
				req.setAttribute("distotal", distotal);
				req.setAttribute("amount", amount);
				req.setAttribute("totalCups", totalCups);
				req.setAttribute("distotal", distotal);
				req.setAttribute("disCup", disCup);
				req.setAttribute("disPrice", disPrice);
				session.setAttribute("list", buylist);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/shoppingcart/shoppingcart.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}

		if ("confirmCart".equals(action)) {
			String option_id = req.getParameter("option_id");
			String mem_id = req.getParameter("mem_id");
			ProductService proSve = new ProductService();
			ProductVo proVo = proSve.getOneProduct(option_id);
			String product_id = proVo.getProduct_id();
			String product_name = proVo.getProduct_name();
			String product_des = proVo.getProduct_des();
			byte[] product_img = proVo.getProduct_img();
			Integer product_price = proVo.getProduct_price();
			String option_sweet = req.getParameter("option_sweet");
			String option_ice = req.getParameter("option_ice");
			Integer option_quenty = Integer.parseInt(req.getParameter("option_quenty"));
			Integer editIndex = Integer.parseInt(req.getParameter("editIndex"));
			int d = editIndex;
			ShoppingCartVO confirmCart = new ShoppingCartVO(product_id, product_name, product_price * option_quenty,
					product_des, product_img, option_quenty, option_ice, option_sweet, mem_id);
			if (buylist.contains(confirmCart)) {
				buylist.remove(d);
				ShoppingCartVO innerCart = (ShoppingCartVO) buylist.get(buylist.indexOf(confirmCart));
				innerCart.setQuantity(innerCart.getQuantity() + confirmCart.getQuantity());
			} else {
				buylist.remove(d);
				buylist.add(confirmCart);
			}
			try {
				session.setAttribute("list", buylist);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/shoppingcart/shoppingcart.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}

		}

		if ("edit".equals(action)) {
			Integer editIndex = Integer.parseInt(calcelIndex) - 1;
			ShoppingCartVO editCart = (ShoppingCartVO) buylist.get(editIndex);
			String mem_id = req.getParameter("mem_id");
			String option_id = editCart.getAddProduct_id();
			String option_name = editCart.getProduct_name();
			Integer option_price = editCart.getProduct_price();
			String option_des = editCart.getProduct_des();
			byte[] option_img = editCart.getProduct_img();
			String sewwt_id = editCart.getSweet_id();
			String ice_id = editCart.getProduct_name();
			Integer option_quenty = editCart.getQuantity();
			boolean openModal = true;
			ShoppingCartVO editOneCart = new ShoppingCartVO(option_id, option_name, option_price, option_des,
					option_img, option_quenty, ice_id, sewwt_id, mem_id);
			try {
				session.setAttribute("editOneCart", editOneCart);
				req.setAttribute("openModal", openModal);
				req.setAttribute("editIndex", editIndex);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/shoppingcart/shoppingcart.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}

		}

		if ("checkout".equals(action)) {
			try {
				String mem_id = req.getParameter("mem_id");
				Integer grou = Integer.parseInt(req.getParameter("grou"));
				String oute_add = req.getParameter("oute_add");
				Integer ship_option = Integer.parseInt(req.getParameter("ship_option"));
				Integer ord_starus = Integer.parseInt(req.getParameter("ord_starus"));
				int total = 0;
				int totalCups = 0;
				for (int i = 0; i < buylist.size(); i++) {
					ShoppingCartVO order = (ShoppingCartVO) buylist.get(i);
					Integer price = order.getProduct_price();
					Integer quantity = order.getQuantity();
					total += price;
					totalCups += quantity;
				}

				// System.out.println(mem_id);
				// System.out.println(grou);
				// System.out.println(oute_add);
				// System.out.println(ship_option);
				// System.out.println(ord_starus);
				// System.out.println(buylist);

				DiscountService disSvc = new DiscountService();
				List<DiscountVO> disVo = disSvc.getAll();
				int disCup = disVo.get(0).getDis_cup();
				Double disCupRate = disVo.get(0).getDis_cup_rate();
				int disPrice = disVo.get(0).getDis_price();
				Double disPriceRate = disVo.get(0).getDis_price_rate();
				Integer amount = total;

				if (total >= disPrice) {
					total = (int) (total * disPriceRate);
				}
				if (totalCups >= disCup) {
					total = (int) (total * disCupRate);
				}
				Integer distotal = total;
				// System.out.println(distotal);
				//
				MemberService memSvc = new MemberService();
				MemberVO memberVo = memSvc.getOneMem_id(mem_id);
				int point = memberVo.getMem_point();

				if (distotal > point) {

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
						successView.forward(req, res);
					}

					RequestDispatcher successView = req
							.getRequestDispatcher("/front-end/mem/storedPoint.jsp");
					successView.forward(req, res);

				} else {
					int pay = point - distotal;
					memSvc.updateMem_Point(mem_id, pay);

					OrderMasterService orderMasterSrv = new OrderMasterService();
					orderMasterSrv.addItem(mem_id, grou, oute_add, ship_option, ord_starus, buylist);
					req.setAttribute("amount", amount);
					req.setAttribute("distotal", distotal);
					req.setAttribute("totalCups", totalCups);
					req.setAttribute("disCup", disCup);
					req.setAttribute("disPrice", disPrice);
					req.setAttribute("buylist", buylist);
					session.removeAttribute("list");

					// orderdetail mail to costomer
					MemberVO memVo = memSvc.getOneMem_id(mem_id);
					String to = memVo.getMem_email();
					String subject = "飲料訂單";
					String message = "";
					StringBuffer outprintorder = null;

					for (int j = 0; j < buylist.size(); j++) {
						ShoppingCartVO printOrderlist = (ShoppingCartVO) buylist.get(j);
						String buyName = printOrderlist.getProduct_name();
						String buyDes = printOrderlist.getProduct_des();
						Integer buyPrice = printOrderlist.getProduct_price();

						String buySweet_id = printOrderlist.getSweet_id();
						SweetService sweetSvc = new SweetService();
						String buySweet = sweetSvc.getOneSweet(buySweet_id).getSweet_des();

						String buyIce_id = printOrderlist.getIce_id();
						IceService iceSvc = new IceService();
						String buyIce = iceSvc.getOneIce(buyIce_id).getIce_des();
						message += buyName + "," + buyDes + "," + buyPrice + "," + buySweet + "," + buyIce + "\n";
						outprintorder = new StringBuffer();
						outprintorder.append(message);
					}
					String messageText = "訂單如下" + "\n" + outprintorder + "總金額:" + total + "\n" + "總杯數" + totalCups;
					OrderMailService mailService = new OrderMailService();
					System.out.println(messageText);
					mailService.sendMail(to, subject, messageText);

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
						successView.forward(req, res);
					}
					RequestDispatcher successView = req.getRequestDispatcher("/front-end/shoppingcart/checkout.jsp");
					successView.forward(req, res);
				}

			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}

		if ("cancel".equals(action)) {
			try {
				System.out.println(action);
				int d = Integer.parseInt(calcelIndex) - 1;
				System.out.println(d);
				buylist.remove(d);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/shoppingcart/shoppingcart.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}

		if ("addProduct_action".equals(action)) {
			try{
			String addProduct_id = req.getParameter("addProduct_id");
			String sweet_id = req.getParameter("sweet_id");
			String buy_mem_id = req.getParameter("buy_mem_id");
			String ice_id = req.getParameter("ice_id");
			Integer quenty = Integer.parseInt(req.getParameter("quenty"));
			req.getSession().setAttribute("addProduct_id", addProduct_id);
			req.getSession().setAttribute("sweet_id", sweet_id);
			req.getSession().setAttribute("buy_mem_id", buy_mem_id);
			req.getSession().setAttribute("ice_id", ice_id);
			req.getSession().setAttribute("quantity", quenty);
			req.getSession().setAttribute("addProduct_action", action);

//			System.out.println("addProduct_id:" + addProduct_id);
//			System.out.println("sweet_id:" + sweet_id);
//			System.out.println("buy_mem_id:" + buy_mem_id);
//			System.out.println("ice_id:" + ice_id);
//			System.out.println("quenty:" + quenty);
//			System.out.println("action:" + action);

			ProductService proSve = new ProductService();
			ProductVo proVo = proSve.getOneProduct(addProduct_id);
			String product_id = proVo.getProduct_id();
			String product_name = proVo.getProduct_name();
			String product_des = proVo.getProduct_des();
			byte[] product_img = proVo.getProduct_img();
			Integer product_price = proVo.getProduct_price();
			Integer tolPrice = quenty * product_price;

			ShoppingCartVO cart = new ShoppingCartVO(product_id, product_name, tolPrice, product_des, product_img,
					quenty, ice_id, sweet_id, buy_mem_id);

			if (buylist == null) {
				buylist = new Vector<ShoppingCartVO>();
				buylist.add(cart);
			} else {
				if (buylist.contains(cart)) {
					ShoppingCartVO innerCart = (ShoppingCartVO) buylist.get(buylist.indexOf(cart));
					innerCart.setQuantity(innerCart.getQuantity() + cart.getQuantity());
					innerCart.setProduct_price(innerCart.getProduct_price() + cart.getProduct_price());
				} else {
					buylist.add(cart);
				}
			}
			try {
				int total = 0;
				int totalCups = 0;

				for (int i = 0; i < buylist.size(); i++) {
					ShoppingCartVO order = (ShoppingCartVO) buylist.get(i);
					Integer price = order.getProduct_price();
					Integer quantity = order.getQuantity();
					total += price;
					totalCups += quantity;
				}

				DiscountService disSvc = new DiscountService();
				List<DiscountVO> disVo = disSvc.getAll();
				int disCup = disVo.get(0).getDis_cup();
				Double disCupRate = disVo.get(0).getDis_cup_rate();
				int disPrice = disVo.get(0).getDis_price();
				Double disPriceRate = disVo.get(0).getDis_price_rate();
				Integer amount = total;

				if (total >= disPrice) {
					total = (int) (total * disPriceRate);
				}
				if (totalCups >= disCup) {
					total = (int) (total * disCupRate);
				}
				Integer distotal = total;

				req.setAttribute("distotal", distotal);
				req.setAttribute("amount", amount);
				req.setAttribute("totalCups", totalCups);
				req.setAttribute("distotal", distotal);
				req.setAttribute("disCup", disCup);
				req.setAttribute("disPrice", disPrice);
				session.setAttribute("list", buylist);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/shoppingcart/shoppingcart.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
			
			}catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}

		}
	}

}
