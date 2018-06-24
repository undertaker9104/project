<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.orderdetail.model.OrderDetailService"%>
<%@ page import="com.orderdetail.model.OrderDetailVo"%>
<%@ page import="java.util.List"%>
<%
	String ord_id = (String) request.getAttribute("ord_id");
	OrderDetailService detailSvc = new OrderDetailService();
	List<OrderDetailVo> detaillist = detailSvc.getOne(ord_id);
	request.setAttribute("detaillist", detaillist);
%>
<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="iceSvc" scope="page" class="com.ice.model.IceService" />
<jsp:useBean id="sweetSvc" scope="page" class="com.sweet.model.SweetService" />
<html>
<head>
<title>listOneOrderDetail</title>

<style type="text/css">
.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
	font-size: 16px;
}
</style>

</head>
<body>
    <div class="page-header text-center">
		  <div class="h1">訂單明細</div>
    </div>
	<table class="myTable table table-hover table-bordered table-striped table-condensed">
				<thead>
					<tr bgcolor=#DDDDDD>
						<th>訂單編號</th>
						<th>產品編號</th>
						<th>冰塊編號</th>
						<th>甜度編號</th>
						<th>商品價錢</th>
						<th>訂購杯數</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${detaillist}" var="detail">
						<tr>
							<td> ${detail.ord_id} </td> 
							<td> ${proSvc.getOneProduct(detail.product_id).product_name} </td>
							<td> ${iceSvc.getOneIce(detail.ice_id).ice_des} </td>
							<td> ${sweetSvc.getOneSweet(detail.sweet_id).sweet_des} </td>
							<td> ${detail.ord_price} </td>
							<td> ${detail.tol_cup} </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
</body>
<script>
	$(document).ready(function() {
		$("tr:odd").css("background-color", "#f0f2f4");});
    </script>
</html>