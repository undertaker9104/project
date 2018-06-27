<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<%
    ProductService proSvr =new ProductService();
    List<ProductVo> list  =proSvr.getAll();
	request.setAttribute("list", list);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<style type="text/css">
body, td, th, input {
	font-size: 12px;
	text-align: center;
}
img {
	height: 100px;
	width: 100px;
}
.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
	font-size: 16px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<jsp:include page="/back-end/toolBar.jsp" />
			<jsp:include page="/back-end/breadBar.jsp" />
	    <div class="col-xs-12 col-sm-9">
	   <jsp:include page="/back-end/product/listUploadProduct.jsp" />
		    <div class="page-header text-left">
				<div class="h1">
					管理介面<small>商品管理</small>
				</div>
			</div>
			<div class="text-left"><%@ include file="/back-end/page1.file"%></div>
			<table 	class="table table-hover">
				<thead>
					<tr class="info">
						<th>商品編號</th>
						<th>商品類別編號</th>
						<th>商品名稱編號</th>
						<th>商品金額</th>
						<th>商品敘述</th>
						<th>商品圖片</th>
						<th>狀態</th>
						<th>操作</th>
						<th>上傳圖片</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="product" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr bgcolor="#FFFFFF">
							<td>${ product.product_id }</td>
							<td>${ product.product_cl_id }</td>
							<td>${ product.product_name }</td>
							<td>${ product.product_price }</td>
							<td>${ product.product_des}</td>
							<td><img src="data:image/jpg;base64,${product.getBase64Image()}">
							<td><c:if test="${product.product_status==0}">           
								              下架
				                    </c:if> 
				                    <c:if test="${product.product_status==1}">
				                                                    上架
				                    </c:if></td>
							<td>
								<form method="post"
									  action="<%=request.getContextPath()%>/product.do">
									<input type="hidden" name="prono" value="${ product.product_id }">
									<input type="hidden" name="action" value="getOne"> 
									<input type="submit" class="btn btn-warning btn-lg" value="修改">
								</form>
							</td>
							<td>
								<form method="post"
									action="<%=request.getContextPath()%>/product.do">
									<input type="hidden" name="prono" value="${ product.product_id }">
									<input type="hidden" name="action" value="upPhoto">
									<input type="submit" class="btn btn-primary btn-lg" value="上傳">
								</form>
							</td>
							<td>
								<form method="post"
									action="<%=request.getContextPath()%>/product.do">
									<input type="hidden" name="prono" value="${ product.product_id }">
									<input type="hidden" name="action" value="down">
									<input type="hidden" name="product_status" value="0">
									<input type="submit" class="btn btn-primary btn-lg" value="下架">
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="text-right"><%@ include file="/back-end/page2.file"%></div>
		</div>
	</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
</body>
<script>
	$(document).ready(function() {
		$("tr:odd").css("background-color", "#f0f2f4");});
    </script>
</html>
