<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.msgboard.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<%
	MsgBoardService msgSvr = new MsgBoardService();
	List<MsgBoardVo> list = msgSvr.getAll();
	request.setAttribute("list", list);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/base.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
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
				<div class="page-header text-left">
					<div class="h1">管理介面<small>留言板管理</small></div>
				</div>
				<div class="text-left"><%@ include file="/back-end/page1.file"%></div>
				<table class="table table-hover table-bordered table-striped table-condensed ">
					<thead>
						<tr class="info">
							<th>留言編號</th>
							<th>會員編號</th>
							<th>商品編號</th>
							<th>留言</th>
							<th>狀態</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="msg" begin="<%=pageIndex%>"
							       end="<%=pageIndex+rowsPerPage-1%>">
							<tr bgcolor="#FFFFFF">
								<td>${ msg.msg_board_id }</td>
								<td>${ msg.mem_id }</td>
								<td>${ msg.product_id }</td>
								<td>${ msg.msg_des }</td>
								<td>${ msg.msg_status==0 ?'正常':'刪除'}</td>
								<td>
									<form method="post" action="<%=request.getContextPath()%>/msg.do">
										<input type="hidden" name="msg_id" value="${ msg.msg_board_id }"> 
										<input type="hidden" name="action" value="getOne"> 
										<input type="submit" class="btn btn-primary btn-lg" value="修改">
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
</html>
