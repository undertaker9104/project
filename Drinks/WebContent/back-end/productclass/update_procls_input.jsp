<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productclass.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<%
   ProductClassVo proclsVO = (ProductClassVo) request.getAttribute("proclsVO"); 
%>
<!DOCTYPE html>
<html lang="">
<head>
<title>Title Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<style type="text/css">
img {
	height: 100px;
	hidth: 150px;
}
</style>
</head>
<body>
	<div class="container" style="margin-top:10%;">
		<form class="well form-horizontal" action="<%=request.getContextPath()%>/productclass.do" method="post" >
			<fieldset>
				<legend class="text-center" style="font-size: 20px;">
					<b>修改飲品</b>
				</legend>
				<div></div>
				<div class="form-group">
					<label class="col-md-4 control-label">類別名稱</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-comment"></i></span>
							<input name="product_cls_name" class="form-control" type="text" value="${proclsVO.product_cl_name}" />
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<input type="hidden" name="action" value="update"> 
						<input type="hidden" name="product_cls_name" value="${proclsVO.product_cl_name}">
						<input type="hidden" name="product_cls_id" value="${proclsVO.product_cl_id}">
						<button type="submit" class="btn btn-warning">
							修改確認<span class="glyphicon glyphicon-send"></span>
						</button>
					</div>
				</div>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</fieldset>
		</form>
	</div>
<script src="https://code.jquery.com/jquery.js"></script>
</body>
</html>