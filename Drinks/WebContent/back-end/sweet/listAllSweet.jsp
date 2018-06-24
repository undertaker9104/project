<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sweet.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>

<%
    SweetService  sweetSvc = new SweetService();
    List<SweetVo> list  =sweetSvc.getAll();
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

<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<style type="text/css">
body, td, th, input {
	font-size: 12px;
	text-align: center;
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
				<div class="page-header text-left">
					<div class="h1">管理介面<small>冰塊管理</small></div>
				</div>
				<div class="text-left"><%@ include file="/back-end/page1.file"%></div>
				<table 	class="table table-hover table-bordered table-striped table-condensed ">
					<thead>
						<tr class="info">
							<th>冰塊編號</th>
							<th>冰塊名稱</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="sweetVo" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr bgcolor="#FFFFFF">
							    <td>${ sweetVo.sweet_id }</td>
								<td>${ sweetVo.sweet_des }</td>
							    <td>
									<form method="post" action="<%=request.getContextPath()%>/sweet.do">
										<input type="hidden" name="sweet_id"  value="${ sweetVo.sweet_id }"> 
										<input type="hidden" name="sweet_des" value="${ sweetVo.sweet_des }"> 
										<input type="hidden" name="action" value="getOne">
										<input type="submit" class="btn btn-warning btn-lg" value="修改">
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
	<script>
	$(document).ready(function() {
		$("tr:odd").css("background-color", "#f0f2f4");});
    </script>
</body>
</html>
