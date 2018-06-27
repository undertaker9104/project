<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.discount.model.*"%>
<%
	DiscountService discountSvc = new DiscountService();
	List<DiscountVO> list = discountSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
</head>
<jsp:include page="/front-end/header.jsp" />
<br><br><br><br><br>
<body>
	<div class="container">
		<div class="row">
			<jsp:include page="/back-end/toolBar.jsp" />
			<jsp:include page="/back-end/breadBar.jsp" />
			<div class="col-xs-12 col-sm-9">
				<div class="page-header text-left">
					<div class="h1">
						管理介面<small>好康管理</small>
					</div>
				</div>
				<table class="table table-bordered table-hover">
					<tr>
						<th width="1%">ID</th>
						<th width="20%">好康敘述</th>
						<th width="10%">條件杯數</th>
						<th width="10%">杯數折扣</th>
						<th width="10%">條件金額</th>
						<th width="10%">金額折扣</th>
						<th width="1%">修改</th>
					</tr>
					<c:forEach var="discountVO" items="${list}">
						<tr>
							<td>${discountVO.dis_id}</td>
							<td>${discountVO.dis_des}</td>
							<td>${discountVO.dis_cup}</td>
							<td>${discountVO.dis_cup_rate}</td>
							<td>${discountVO.dis_price}</td>
							<td>${discountVO.dis_price_rate}</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/discount.do">
									<input type="submit" value="修改" class="btn btn-primary">
									<input type="hidden" name="dis_id" value="${discountVO.dis_id}">
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
</body>
</html>