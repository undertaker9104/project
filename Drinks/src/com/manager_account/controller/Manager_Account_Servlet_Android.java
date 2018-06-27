package com.manager_account.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.authority_class.model.Authority_classService;
import com.authority_class.model.Authority_classVO;
import com.g2.main.ImageUtil;
import com.google.gson.Gson;
import com.manager_account.model.Manager_accountVO;
import com.manager_account.model.Manager_accountService;
import com.manager_account_authority.model.Manager_account_authorityService;

public class Manager_Account_Servlet_Android extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		req.setCharacterEncoding("UTF-8");
		
		Manager_accountService managerSvc = new Manager_accountService();
		Manager_account_authorityService manager_account_authoritySvc = new Manager_account_authorityService();
		Authority_classService authority_classSvc = new Authority_classService();
		Gson gson = new Gson();
		String outStr = "";

		String action = req.getParameter("action");
		System.out.println(action);
		
		if ("isManager".equals(action)) {
			String man_acc_id = req.getParameter("man_acc_id");
			String accpw = req.getParameter("accpw");
			outStr = String.valueOf(managerSvc.isManager(man_acc_id, accpw)) ;
		} else if ("findById".equals(action)) {
			String man_acc_id = req.getParameter("man_acc_id");
			Manager_accountVO manager = managerSvc.getOneEmp(man_acc_id);
			byte[] img = ImageUtil.shrink(manager.getEmp_img(),1);
			String imgStr = Base64.getMimeEncoder().encodeToString(img);
			manager.setImgbase64(imgStr);
			outStr = gson.toJson(manager);
		} else if("isUpdate".equals(action)){
			String man_acc_id = req.getParameter("man_acc_id");
			Integer man_acc_status = Integer.parseInt(req.getParameter("man_acc_status"));
			outStr = String.valueOf(managerSvc.update(man_acc_id, man_acc_status));
		} else if("isSender".equals(action)){
			String man_acc_id = req.getParameter("man_acc_id");
			List<String> authorityList = manager_account_authoritySvc.getOneManager_account_authority(man_acc_id);
			boolean isSender = false;
			for(int i=0;i<authorityList.size();i++){
				Authority_classVO authorityVO = authority_classSvc.getOneAuthority(authorityList.get(i));
				if("¥~°e©±­û".equals(authorityVO.getAuthority_des())){
					isSender=true;					
				}
			}
			System.out.println("isSender:"+isSender);
			outStr = String.valueOf(isSender);
		}

		res.setContentType(contentType);
		PrintWriter out = res.getWriter();
		System.out.println(outStr);
		out.print(outStr);
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

}
