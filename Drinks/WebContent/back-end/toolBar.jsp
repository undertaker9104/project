<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.mem.model.*"%>
<%@page import="com.manager_account.model.*"%>
<%@page import="com.manager_account_authority.model.*"%>
<%@page import="com.authority_class.model.*"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
<style type="text/css">
body, td, th, input {
	font-size: 12px;
	text-align: center;
}
.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
	font-size: 16px;
}
</style>
</head>
<%
 Manager_accountVO manager_accountVO = (Manager_accountVO) session.getAttribute("manager_accountVO");
 String man_id=manager_accountVO.getMan_acc_id();
 request.setAttribute("man_id", man_id); 
 Manager_account_authorityService manSvc = new Manager_account_authorityService();
 List<String> manalist =manSvc.getOneManager_account_authority(man_id);
%>

<body>
	<div class="col-xs-12 col-sm-2">
		 <img src="data:image/jpg;base64,${manager_accountVO.getImg(manager_accountVO.getEmp_img())}"
				 class="img-circle" style="width: 100px; height:100px;">
				 <br>
		 <a><%=(manager_accountVO == null) ? "" : "歡迎管理員:<font color=red>" + manager_accountVO.getEmp_name() + "</font>您好"%></a>
                    <form action="<%=request.getContextPath()%>/Manager_accountServlet" method="post">
                            <input style="background-color: transparent; border: 0px; width: 106px; height: 52px;"
                                   type="submit" value="<%=(manager_accountVO == null) ? "登入" : "登出"%>">
                            <input type="hidden" name="action" value="InOut">
                    </form>
		<div class="list-group">
	   <%if(manalist.contains("AC000001")){ %>
	          <a href="<%=request.getContextPath()%>/back-end/mem/listAllMem.jsp" 
	             class="list-group-item"><h2>會員&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a> 
	          <a href="<%=request.getContextPath()%>/back-end/emp/listAllEmp_Auth.jsp" 
	             class="list-group-item"><h2>員工&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a> 
	          <a href="<%=request.getContextPath()%>/back-end/groupart/MyGroupList.jsp" 
	             class="list-group-item"><h2>揪團&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a>
	          <a href="<%=request.getContextPath()%>/back-end/discount/discountList.jsp" 
	             class="list-group-item"><h2>優惠&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a> 
	          <a href="<%=request.getContextPath()%>/back-end/productclass/listAllProductClass.jsp"
				 class="list-group-item"><h2>類別&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a>
	          <a href="<%=request.getContextPath()%>/back-end//product/listAllProduct.jsp"
				 class="list-group-item"><h2>商品&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a>
			  <a href="<%=request.getContextPath()%>/back-end/ordermaster/listAllOrderMasterByShip.jsp"
				 class="list-group-item"><h2>訂單&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a>
			  <a href="<%=request.getContextPath()%>/back-end/msg/listAllMSG.jsp"
				 class="list-group-item"><h2>留言&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a>
			  <a href="<%=request.getContextPath()%>/back-end/sweet/listAllSweet.jsp"
				 class="list-group-item"><h2>甜度&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a>
			  <a href="<%=request.getContextPath()%>/back-end/ice/listAllIce.jsp"
				 class="list-group-item"><h2>冰塊&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a>
			  <a href="<%=request.getContextPath()%>/back-end/mem/listAllModerated.jsp"
				 class="list-group-item"><h2>點數&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a>
			  <a href="<%=request.getContextPath()%>/back-end/aboutus/aboutUsList.jsp"
				 class="list-group-item"><h2>我們&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a>
			  <a href="<%=request.getContextPath()%>/back-end/announcement/announcementList.jsp"
			     class="list-group-item"><h2>消息&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a>
	       <%}else{ %>
	          <a href="<%=request.getContextPath()%>/back-end/ordermaster/listAllOrderMasterByShip.jsp"
			  class="list-group-item"><h2>訂單&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-wrench" ></span></h2></a>
		   <%} %>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
</body>
</html>