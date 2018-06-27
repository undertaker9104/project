<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<%
	ProductService proSvr = new ProductService();
	List<ProductVo> listunload = proSvr.getAllUnload();
	request.setAttribute("unload", listunload);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<link rel="stylesheet" 	href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
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
<script src="https://code.jquery.com/jquery.js"></script>
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-9">
			<%if (listunload != null) {%>
				<div class="page-header text-left">
					<div class="h1">
					<small>未上架管理</small>
					</div>
				</div>
				<table class="table table-hover ">
				<tbody>
					<c:forEach items="${unload}" var="unload">
							<tr>
							 <jsp:useBean id="productClsrc" scope="page" class="com.productclass.model.ProductClassService" />
							    <td>${ productClsrc.getOne(unload.product_cl_id).product_cl_name }</td>
								<td>${ unload.product_name }</td>
								<td><img src="data:image/jpg;base64,${unload.getBase64Image()}">
								<td><c:if test="${unload.product_status==0}">           
								            下架
				                    </c:if>
				                     <c:if test="${unload.product_status==1}">
				                                                    上架
				                    </c:if>
				                </td>
								<td>
									<form method="post" action="<%=request.getContextPath()%>/product.do">
										<input type="hidden" name="product_status" value="1">
										<input type="hidden" name="product_id" value="${unload.product_id}">
										<input type="hidden" name="action" value="upload"> 
										<input type="submit" class="btn btn-warning btn-lg" value="上架">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			<% } %>
			</div>
		</div>
	</div>

</body>
<script>
	$(document).ready(function() {
		$("tr:odd").css("background-color", "#f0f2f4");});
    </script>
</html>
