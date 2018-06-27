package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.product.model.ProductService;

import com.product.model.ProductVo;

import com.g2.main.ImageUtil;

public class ProductServlet_Android extends HttpServlet{
//	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
//	@Override
//	public void init() throws ServletException {
//		super.init();
//		
//	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//req.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		Gson gson = new Gson();
		String action = req.getParameter("action");
		System.out.println("action :"+action);
		int imageSize = Integer.parseInt(req.getParameter("imageSize"));
		String outStr="";
		ProductService productSvc = new ProductService();
		if ("getAll".equals(action)){
			List<ProductVo> list = productSvc.getAll();		
			for(ProductVo productVO : list){
				byte[] img = ImageUtil.shrink(productVO.getProduct_img(), imageSize);
				productVO.setProduct_img(img);
				String imgStr = Base64.getMimeEncoder().encodeToString(img);
				productVO.setBase64(imgStr);
			}
			outStr = gson.toJson(list);
//		}else if ("findById".equals(action)) {
//			String product_id = req.getParameter("product_id");
//			ProductVO productVO = productSvc.findByPrimaryKey(product_id);
//			byte[] img =ImageUtil.shrink(productVO.getProduct_img(), imageSize);
//			productVO.setProduct_img(img);
//			String imgStr = Base64.getMimeEncoder().encodeToString(img);
//			productVO.setBase64(imgStr);
//			outStr = gson.toJson(productVo);
		} else if ("findByCategory".equals(action)) {
			System.out.println("findByCategory");
			String product_cl_id =req.getParameter("product_cl_id");
			System.out.println("product_cl_id");
			List<ProductVo> list = productSvc.getAllBYCLASS(product_cl_id);
			for (int i = 0; i < list.size(); i++) {
				ProductVo productVO = list.get(i);
				byte[] img =ImageUtil.shrink(productVO.getProduct_img(), imageSize);
				productVO.setProduct_img(img);
				String imgStr = Base64.getMimeEncoder().encodeToString(img);
				productVO.setBase64(imgStr);
			}
			outStr = gson.toJson(list);
		}	
			res.setContentType(contentType);
			PrintWriter out = res.getWriter();
			System.out.println("outStr"+outStr);
			out.println(outStr);
			out.close();
		
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

}
