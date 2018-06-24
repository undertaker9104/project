<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.mem.model.*"%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="col-xs-12 col-sm-10">
				<ol class="breadcrumb text-left">
					<li style="text-align: center; font-size: 24px"><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
					<li style="text-align: center; font-size: 24px"><a href="<%=request.getContextPath()%>/back-end/backIndex.jsp">管理介面</a></li>
				 	<li style="text-align: center; font-size: 24px"><a href="<%=request.getContextPath()%>/back-end/productclass/addProductClass.jsp">新增商品類別</a></li>
					<li style="text-align: center; font-size: 24px"><a href="<%=request.getContextPath()%>/back-end/product/addProduct.jsp">新增商品</a></li>
					<li style="text-align: center; font-size: 24px"><a href="<%=request.getContextPath()%>/back-end/sweet/addSweet.jsp">新增甜度</a></li>
					<li style="text-align: center; font-size: 24px"><a href="<%=request.getContextPath()%>/back-end/ice/addIce.jsp">新增冰塊</a></li>
					<li style="text-align: center; font-size: 24px"><a href="<%=request.getContextPath()%>/back-end/emp/addManager_account.jsp">新增員工</a></li>
  			        <li style="text-align: center; font-size: 24px"><a href="<%=request.getContextPath()%>/back-end/announcement/announcementAdd.jsp">新增消息</a></li>
				</ol>
	 </div>
	<script src="https://code.jquery.com/jquery.js"></script>
</body>
</html>