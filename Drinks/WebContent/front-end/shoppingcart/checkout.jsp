<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.shoppingcart.model.*"%>
<%@ page import="com.ice.model.*"%>
<%@ page import="com.sweet.model.*"%>
<%@ page import="java.util.*"%>
<%
    Integer amount = (Integer) request.getAttribute("amount");
    Integer distotal = (Integer) request.getAttribute("distotal");
    Integer totalCups = (Integer) request.getAttribute("totalCups");
    Integer disCup = (Integer) request.getAttribute("disCup");
    Integer disPrice = (Integer) request.getAttribute("disPrice");
  
    request.setAttribute("amount", amount);
    request.setAttribute("distotal", distotal);
    request.setAttribute("totalCups", totalCups);
    request.setAttribute("disCup", disCup);
    request.setAttribute("disPrice", disPrice);
 
%>
<%
	List<ShoppingCartVO> list = (List<ShoppingCartVO>) request.getAttribute("buylist");
	request.setAttribute("detaillist", list);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
body, td, th, input {
	text-align: center;
}

.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
}
</style>
</head>
<body>
<jsp:include page="/front-end/header.jsp"/>
	<jsp:useBean id="sweetSvc" scope="page"	class="com.sweet.model.SweetService" />
	<jsp:useBean id="iceSvc" scope="page" class="com.ice.model.IceService" />
	<div class="container-fulled">
	<div class="row">
	<% if (list != null && (list.size() > 0)) { %>
 		  
			<div class="row">
				<div class="col-xs-12 col-sm-8  col-sm-offset-2">
				<div class="page-header text-left">
			    <img src="<%=request.getContextPath()%>/front-end/img/detail.png" style="width: 100px; height: 100px"/>
		        </div>
					<table class="myTable table table-hover ">
						<thead>
							<tr>
								<th style="text-align: center;font-size: 20px">商品名稱</th>
								<th style="text-align: center;font-size: 20px">商品價格</th>
								<th style="text-align: center;font-size: 20px">商品敘述</th>
								<th style="text-align: center;font-size: 20px">商品圖片</th>
								<th style="text-align: center;font-size: 20px">甜度</th>
								<th style="text-align: center;font-size: 20px">冰塊</th>
								<th style="text-align: center;font-size: 20px">數量</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${detaillist}" var="order">
								<tr>
									<td style="text-align: center;font-size: 20px">${order.product_name}</td>
									<td style="text-align: center;font-size: 20px">${order.product_price}</td>
									<td style="text-align: center;font-size: 20px;width:35%">${order.product_des}</td>
									<td><img src="data:image/jpg;base64,${order.getBase64Image()}" style="width: 150px; height: 150px"></td>
									<td style="text-align: center;font-size: 20px">${sweetSvc.getOneSweet(order.sweet_id).sweet_des}</td>
									<td style="text-align: center;font-size: 20px">${iceSvc.getOneIce(order.ice_id).ice_des}</td>
									<td style="text-align: center;font-size: 20px">${order.quantity}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<div class="col-xs-12 col-sm-8 col-sm-offset-2">
			       <h2 class="text-left">
				       <%if(amount>=disPrice || totalCups>=disCup) {%>
						<img src="<%=request.getContextPath()%>/front-end/img/total.png" style="width: 80px; height: 80px"/>:
						<s>${amount}</s>&nbsp;&nbsp;&nbsp;
						<img src="<%=request.getContextPath()%>/front-end/img/dis.png" style="width: 80px; height: 80px"/>
						<span class="glyphicon glyphicon-usd"></span>:${distotal} 
						<%}else{%>
						<img src="<%=request.getContextPath()%>/front-end/img/total.png" style="width: 80px; height: 80px"/>:${amount}
						<%}%>
						&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/front-end/img/list.png" style="width: 80px; height: 80px"/>:${totalCups}
					</h2>
					<h2 class="text-left">
				     	<a href="<%=request.getContextPath()%>/front-end/index.jsp"> 
						<font size="+3"> 是 否 繼 續 購 物</font></a>
				    </h2>
				</div>
			</div>
	</div>
	<%}else{%>
		<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="page-header text-left">
					<div class="h1">還沒有訂單明細喔!!!!!!!!!!!!!</div>
				</div>
		</div>
    <%}%>
    </div>

</body>
<script src="https://code.jquery.com/jquery.js"></script>
</html>

