package com.ordermaster.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.g2.main.ImageUtil;
import com.google.gson.Gson;
import com.orderdetail.model.OrderDetailVo;
import com.orderdetail.model.OrderDetailService;
import com.ordermaster.model.CartVO;
import com.ordermaster.model.OrderMasterVo;
import com.ordermaster.model.OrderMasterService;
import com.product.model.ProductVo;
import com.product.model.ProductService;


public class OrderServlet_Android extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String contentType = getServletContext().getInitParameter("contentType");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		int imageSize = Integer.parseInt(req.getParameter("imageSize"));
		String mem_id = req.getParameter("mem_id");
		String outStr = "";
		OrderMasterService orderMasterSvc = new OrderMasterService();
		Gson gson = new Gson();
		if ("add".equals(action) && mem_id != null) {
			List<OrderDetailVo> orderDetailList = new ArrayList<>();
			String orderJson = req.getParameter("orderJson");
			OrderMasterVo order_masterVO = gson.fromJson(orderJson, OrderMasterVo.class);
			System.out.println("add_orderJson"+orderJson);
			List<CartVO> OrderProductList = order_masterVO.getOrderProductList();
			for (CartVO cartVO : OrderProductList) {
				OrderDetailVo orderDetailVO = new OrderDetailVo();
				orderDetailVO.setProduct_id(cartVO.getProduct_id());
				orderDetailVO.setIce_id(cartVO.getIce_id());
				orderDetailVO.setSweet_id(cartVO.getSweet_id());
				orderDetailVO.setOrd_price(cartVO.getPrice());
				orderDetailVO.setTol_cup(cartVO.getQuantity());
				orderDetailVO.setMem_id(order_masterVO.getMem_id());
				orderDetailList.add(orderDetailVO);
			}
			String ord_id = "-1";
			ord_id = orderMasterSvc.add(order_masterVO,orderDetailList);
			if (!"-1".equals(ord_id)) {
				OrderMasterVo orderMasterVO = orderMasterSvc.getOne(ord_id);
				orderMasterVO.setOrderProductList(OrderProductList);
				outStr = gson.toJson(orderMasterVO);
			}

		} else if ("getAll_By_MemId".equals(action) ) {
			OrderDetailService orderDetaiSvc = new OrderDetailService();
			ProductService productSvc = new ProductService();
			System.out.println("mem_id:"+mem_id);
			List<OrderMasterVo> orderList = orderMasterSvc.getAll_By_MemId(mem_id.trim());
			System.out.println("orderList:"+orderList);
			if (orderList != null && orderList.size() > 0) {
				System.out.println("�挾��銵�1");
				for (OrderMasterVo orderMasterVO : orderList) {
					System.out.println("�挾��銵�2");
					String ord_id = orderMasterVO.getOrd_id();
					System.out.println("ord_id : "+ord_id);
					List<OrderDetailVo> orederDetailList = orderDetaiSvc.findByOrderId(ord_id);
					List<CartVO> orderProductList = new ArrayList<CartVO>();
					for (OrderDetailVo orderDetailVO : orederDetailList) {
						CartVO cartVO = new CartVO();
						System.out.println("orderDetailVO.getProduct_id() : "+orderDetailVO.getProduct_id());
						ProductVo productVO = productSvc.getOneProduct(orderDetailVO.getProduct_id());
						cartVO.setProduct_name(productVO.getProduct_name());
						cartVO.setIce_id(orderDetailVO.getIce_id());
						cartVO.setSweet_id(orderDetailVO.getSweet_id());
						cartVO.setPrice(orderDetailVO.getOrd_price());
						cartVO.setQuantity(orderDetailVO.getTol_cup());
						orderProductList.add(cartVO);
					}
					orderMasterVO.setOrderProductList(orderProductList);
				}
				
			}
			outStr = gson.toJson(orderList);
			System.out.println("Detail = " + outStr);
		} else if ("getAll_By_ManId".equals(action) ) {
			OrderDetailService orderDetaiSvc = new OrderDetailService();
			ProductService productSvc = new ProductService();
			String man_acc_id = req.getParameter("man_acc_id");
			System.out.println("man_acc_id:"+man_acc_id);
			List<OrderMasterVo> orderList = orderMasterSvc.getAll_By_ManId(man_acc_id.trim());
			if (orderList != null && orderList.size() > 0) {
				for (OrderMasterVo orderMasterVO : orderList) {
					String ord_id = orderMasterVO.getOrd_id();
					List<OrderDetailVo> orederDetailList = orderDetaiSvc.findByOrderId(ord_id);
					List<CartVO> orderProductList = new ArrayList<>();
					for (OrderDetailVo orderDetailVO : orederDetailList) {
						CartVO cartVO = new CartVO();
						System.out.println("orderDetailVO.getProduct_id() : "+orderDetailVO.getProduct_id());
						ProductVo productVO = productSvc.getOneProduct(orderDetailVO.getProduct_id());
						cartVO.setProduct_name(productVO.getProduct_name());
						cartVO.setIce_id(orderDetailVO.getIce_id());
						cartVO.setSweet_id(orderDetailVO.getSweet_id());
						cartVO.setPrice(orderDetailVO.getOrd_price());
						cartVO.setQuantity(orderDetailVO.getTol_cup());
						orderProductList.add(cartVO);
					}
					orderMasterVO.setOrderProductList(orderProductList);
				}
				
			}
			outStr = gson.toJson(orderList);	
		} else if ("findByOrder".equals(action) && mem_id != null) {
			OrderDetailService orderDetaiSvc = new OrderDetailService();
			String ord_id = req.getParameter("ord_id");
			System.out.println("ord_id:"+ord_id);
			List<OrderDetailVo> orderDetailList = orderDetaiSvc.findByOrderId(ord_id);
			System.out.println("orderDetailList:"+orderDetailList);
			outStr = gson.toJson(orderDetailList);
		}else if ("update".equals(action)){
			String ord_id = req.getParameter("ord_id");
			Integer ord_status = Integer.parseInt(req.getParameter("ord_status"));
//			Order_MasterVO order_masterVO = gson.fromJson(orderJson, Order_MasterVO.class);
			outStr = String.valueOf(orderMasterSvc.update(ord_id,ord_status));
			//outStr = gson.toJson(orderDetailList);
		}

		res.setContentType(contentType);
		PrintWriter out = res.getWriter();
		System.out.println("outStr:"+outStr);
		out.print(outStr);
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
}
