<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.List"%>
<%
    MemberVO memVO = (MemberVO) session.getAttribute("memVO");
    String mem_id = memVO.getMem_id();
    OrderMasterService ordSvc = new OrderMasterService();
    List<OrderMasterVo> ordlist= ordSvc.getMyOrderMaster(mem_id);
    request.setAttribute("ordlist", ordlist);
%>
<html>
<head>
<style type="text/css">
body, td, th, input {
	font-size: 20px;
	text-align: center;
}
img {
	height: 100px;
	width: 100px;
}
.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
	font-size: 20px;
}
</style>
</head>
<body>
	<jsp:include page="/front-end/header.jsp" />
     <div class="container">
		<div class="row">
				<% if (ordlist != null && (ordlist.size() > 0)) { %>
				<br><br>
			<div class="col-xs-12 col-sm-8 col-sm-offset-2">
				<div class="page-header text-left">
					<div class="h1">我的訂單</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
	      <div class="col-xs-12 col-sm-10 col-sm-offset-2">
		<table 	class="table table-hover ">
			<thead>
				<tr bgcolor="white">
					<th style="text-align: center; font-size: 24px">訂單編號</th>
					<th style="text-align: center; font-size: 24px">揪團</th>
					<th style="text-align: center; font-size: 24px">外送地址</th>
					<th style="text-align: center; font-size: 24px">選擇</th>
					<th style="text-align: center; font-size: 24px">狀態</th>
					<th style="text-align: center; font-size: 24px">訂單明細</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ordlist}" var="order">
					<tr>
						<td style="text-align: center; font-size: 24px">${ order.ord_id }</td>
						<td style="text-align: center; font-size: 24px"><c:if test="${ order.grou == 0}">無 </c:if> <c:if
								test="${ order.grou == 1}">
								<img src="<%=request.getContextPath()%>/front-end/img/group.jpg">
							</c:if></td>
						<td style="text-align: center; font-size: 24px">${ (null == order.oute_add) ?'無':order.oute_add }</td>
						<td style="text-align: center; font-size: 24px"><c:if test="${ order.ship_option == 0}">
								<img src="<%=request.getContextPath()%>/front-end/img/take.jpg">
							</c:if> <c:if test="${ order.ship_option == 1}">
								<img src="<%=request.getContextPath()%>/front-end/img/out.jpg">
							</c:if></td>
						<td style="text-align: center; font-size: 24px"><c:choose>
								<c:when test="${order.ord_status == 0}">準備中</c:when>
								<c:when test="${order.ord_status == 1}">出貨中</c:when>
								<c:when test="${order.ord_status == 2}">可取貨</c:when>
								<c:when test="${order.ord_status == 3}">交易完成</c:when>
								<c:otherwise>移除</c:otherwise>
							</c:choose></td>
						<td>
							<form method="post" action="<%=request.getContextPath()%>/ordermaster.do">
								<input type="hidden" name="ord_id" value="${order.ord_id}">
								<input type="hidden" name="action" value="getMyOrderDetail">
								<input type="submit" class="btn btn-primary btn-lg" value="訂單明細">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<% } else {%>
		<br><br><br>
		<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="page-header text-left">
					<div class="h1">還沒有我的訂單喔!!!!!!!!!!!!!</div>
				</div>
		</div>
	  <% }%>
	</div>

	<c:if test="${openModal!=null}">
		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<div class="modal-title" id="myModalLabel">
							<div id="table-1" class="myTable text-left"></div>
						</div>

						<div class="modal-body">
							<jsp:include page="/back-end/orderdetail/listOneOrderDetail.jsp" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								    data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
	<script>$("#basicModal").modal({show: true});</script>
	<script>
	$(document).ready(function() {
		$("tr:odd").css("background-color", "#f0f2f4");});
    </script>
</body>
</html>