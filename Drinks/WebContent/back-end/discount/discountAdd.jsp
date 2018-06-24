<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.discount.model.*"%>
<%
DiscountVO discountVO = (DiscountVO) request.getAttribute("discountVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加優惠方案</title>
<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>

<div class="container">
	<%@include file="/front-end/header.jsp"%>
	<br><br>
	<form class="form-horizontal" METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/discount.do" name="form1">
		
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">好康優惠敘述:</label>
			<div class="col-sm-6">
					<input type="TEXT" class="form-control" name="dis_des" size="45" value="${discountVO.dis_des}優惠方案" />
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">條件杯數:</label>
			<div class="col-sm-6">
					<select size="1" name="dis_cup">			         
					   <option value="0">0
					   <option value="10">10
					   <option value="20">20
 			        </select>
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">杯數折扣:</label>
			<div class="col-sm-6">
					<select size="1" name="dis_cup_rate">
					   <option value="0">0			         
					   <option value="1">1
					   <option value="2">2					   
 			        </select>
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">條件金額:</label>
			<div class="col-sm-6">
					<select size="1" name="dis_price">
					   <option value="0">0			         
					   <option value="500">500
					   <option value="1000">1000				   
 			        </select>
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">金額折扣:</label>
			<div class="col-sm-6">
					<select size="1" name="dis_price_rate">	
					   <option value="1.0">1.0		         
					   <option value="0.9">0.9
					   <option value="0.8">0.8
 			        </select>
			</div>
		</div>
		
		<input type="hidden" name="action" value="insert">
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<input type="submit" class="btn btn-success" value="送出新增">			
			</div>
		</div>
	</form>
</div>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</body>
</html>