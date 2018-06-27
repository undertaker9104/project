package com.product.controller;

import java.io.*;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.product.model.ProductService;
import com.product.model.ProductVo;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ProductServlet() {
		super();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		
		if ("getOneProduct".equals(action)) {
			List<String> errorMsgs;
			errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			req.getSession().removeAttribute("oneProVO");
				
			String get_one = req.getParameter("get_one");
		 try {
		        ProductService proSvc =new ProductService();
		        ProductVo oneProVO  =proSvc.getOneProduct(get_one);
		        String pro_name= oneProVO.getProduct_name();
		        String pro_des= oneProVO.getProduct_des();
		        String pro_img= oneProVO.getBase64Image();
			 
		        out.print("{\"pro_name\":\"" + pro_name + "\",\"pro_des\":\""+pro_des+"\",\"pro_img\":\""+pro_img+"\"}");  
		        out.flush();  
		        out.close();  

			} catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
		
			}
		}
		
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String product_name = req.getParameter("product_name");
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				String product_class = req.getParameter("product_class");
				if (product_class == null || product_class.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				Integer product_price = null;
				try {
					product_price = Integer.parseInt(req.getParameter("product_price"));
					
				} catch (NumberFormatException e) {
					product_price = 0;
					errorMsgs.add("格式不對");
				}

				String product_des = req.getParameter("product_des");
				if (product_des == null || product_des.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}

				Integer product_status = Integer.parseInt(req.getParameter("product_status"));

				byte[] product_img = null;
				Part filePart = req.getPart("product_img");
				InputStream is = null;
				if (filePart != null) {
					is = filePart.getInputStream();
					product_img = new byte[is.available()];
					is.read(product_img);
				}

				ProductVo productVo = new ProductVo();
				productVo.setProduct_cl_id(product_class);
				productVo.setProduct_name(product_name);
				productVo.setProduct_price(product_price);
				productVo.setProduct_des(product_des);
				productVo.setProduct_img(product_img);
				productVo.setProduct_status(product_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", productVo);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}

				ProductService productSvc = new ProductService();
				productVo = productSvc.addProduct(product_name, product_class, product_price, product_des, product_img,
						product_status);
				RequestDispatcher View = req.getRequestDispatcher("/back-end//product/listAllProduct.jsp");
				View.forward(req, res);
			} catch (Exception e1) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/error.jsp");
				successView.forward(req, res);
			}
		}

		if ("getOne".equals(action)) {
			List<String> errorMsgs;
			errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String find_one = req.getParameter("prono");
			try {
				ProductService proSvc = new ProductService();
				ProductVo proVO = proSvc.getOneProduct(find_one);
				req.setAttribute("proVO", proVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/update_pro_input.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				 
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	
		if ("getOnePic".equals(action)) {
			List<String> errorMsgs;
			errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String find_one = req.getParameter("find_one");
			try {
				ProductService proSvc = new ProductService();
				ProductVo proVO = proSvc.getOneProduct(find_one);
				req.setAttribute("proVO", proVO);
                  System.out.println(action+find_one);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/websocket/Servertest.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("upPhoto".equals(action)) {
			List<String> errorMsgs;
			errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String find_one = req.getParameter("prono");
		try {
			    ProductService proSvc = new ProductService();
			    ProductVo proVO = proSvc.getOneProduct(find_one);
			    req.setAttribute("proVO", proVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/upload_product_photo.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("upload".equals(action)) {
			List<String> errorMsgs;
			errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String product_id = req.getParameter("product_id");
			Integer product_status =Integer.parseInt(req.getParameter("product_status"));
		try {
			    ProductService proSvc = new ProductService();
			    ProductVo proVO = proSvc.upload(product_status, product_id);
			    RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("down".equals(action)) {
			List<String> errorMsgs;
			errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String prono = req.getParameter("prono");
			Integer product_status =Integer.parseInt(req.getParameter("product_status"));
		try {
			    ProductService proSvc = new ProductService();
			    ProductVo proVO = proSvc.down(product_status, prono);
			    RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String product_id = req.getParameter("product_id");
			String product_class = req.getParameter("product_class");
			if (product_class == null || product_class.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			String product_name = req.getParameter("product_name");
			if (product_name == null || product_name.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}

			Integer product_price = null;
			try {
				product_price = Integer.parseInt(req.getParameter("product_price"));
			} catch (NumberFormatException e) {
				product_price = 0;
				errorMsgs.add("格式不對");
			}

			String product_des = req.getParameter("product_des");
			if (product_des == null || product_des.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}


			try {
				ProductVo productVo = new ProductVo();
				productVo.setProduct_id(product_id);
				productVo.setProduct_cl_id(product_class);
				productVo.setProduct_name(product_name);
				productVo.setProduct_price(product_price);
				productVo.setProduct_des(product_des);
		
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", productVo);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/update_pro_input.jsp");
					failureView.forward(req, res);
					return;
				}

				ProductService productSvc = new ProductService();
				productVo = productSvc.update(product_id, product_class, product_name, product_price, product_des);
				RequestDispatcher View = req.getRequestDispatcher("/back-end//product/listAllProduct.jsp");
				View.forward(req, res);
				

			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/update_pro_input.jsp");
				failureView.forward(req, res);
			}

		}

		if ("updatePhoto".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	    	String product_id = req.getParameter("product_id");
			byte[] product_img = null;
			Part filePart = req.getPart("product_img");
			InputStream is = null;
			if (filePart != null) {
				is = filePart.getInputStream();
				product_img = new byte[is.available()];
				is.read(product_img);
			}

			try {
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/upload_product_photo.jsp");
					failureView.forward(req, res);
					return;
				}
				ProductService productSvc = new ProductService();
				ProductVo proVo = new ProductVo();
				proVo = productSvc.updatePhoto(product_id,product_img);
				RequestDispatcher View = req.getRequestDispatcher("/back-end//product/listAllProduct.jsp");
				View.forward(req, res);
	
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/upload_product_photo.jsp");
				failureView.forward(req, res);
			}

		}

	}

}
