<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<%
   ProductVo proVO = (ProductVo) request.getAttribute("proVO"); 
%>
<!DOCTYPE html>
<html>
<head>
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
		<form class="well form-horizontal"
			  action="<%=request.getContextPath()%>/product.do" method="post"
			  id="contact_form" enctype="multipart/form-data">
			<fieldset>
			    <legend class="text-center" style="font-size: 20px;">
					<b>修改飲品</b>
				</legend>
				<div></div>
				<div class="form-group">
					<label class="col-md-4 control-label">飲料名稱</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
							  <i class="glyphicon glyphicon-comment"></i></span>
							  <input name="product_name" class="form-control" type="text" 	value="${proVO.product_name}"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">飲料類別</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"> 
							 <i class="glyphicon glyphicon-comment"></i></span>
							<jsp:useBean id="classSvc" scope="page" class="com.productclass.model.ProductClassService" />
							<select size="1" name="product_class" class="form-control">
								<c:forEach var="classVO" items="${classSvc.all}">
									<option value="${classVO.product_cl_id}" ${(classVO.product_cl_id == proVO.product_cl_id) ?'selected':''}>
										${ classVO.product_cl_name}
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">飲料價錢</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
							  <i class="glyphicon glyphicon-usd"></i></span> 
							 <input name="product_price" class="form-control" type="text" value="${proVO.product_price}">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">飲品描述</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-home"></i></span>
								 <input name="product_des" class="form-control" type="text" value="${proVO.product_des}">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<input type="hidden" name="action" value="update"> 
						<input type="hidden" name="product_id" value="${proVO.product_id}">
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