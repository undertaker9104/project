<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.point_exc_cash.model.*"%>

<%
	Point_exc_cashVO point_exc_cashVO = (Point_exc_cashVO) request.getAttribute("point_exc_cashVO");
%>
<%
	Point_exc_cashService point_exc_cashSvc = new Point_exc_cashService();
	List<Point_exc_cashVO> list = point_exc_cashSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有會員點數兌換資料</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	table-layout: fixed;
	word-break: break-all;
	word-warp: break-word;
	border: 1px solid #CCCCFF;
}

th, td {
	width: 300px;
	padding: 5px;
	text-align: center;
}

.button-secondary {
	background: #3498db;
	background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
	background-image: -moz-linear-gradient(top, #3498db, #2980b9);
	background-image: -ms-linear-gradient(top, #3498db, #2980b9);
	background-image: -o-linear-gradient(top, #3498db, #2980b9);
	background-image: linear-gradient(to bottom, #3498db, #2980b9);
	-webkit-border-radius: 28;
	-moz-border-radius: 28;
	border-radius: 28px;
	font-family: Arial;
	color: #ffffff;
	font-size: 5px;
	padding: 10px 20px 10px 20px;
	text-decoration: none;
}

.button-secondary:hover {
	background: #3cb0fd;
	background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
	background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
	background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
	background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
	background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
	text-decoration: none;
}
</style>
</head>
<body bgcolor='white'>
	<div class="container">
		<div class="row">
			<jsp:include page="/back-end/toolBar.jsp" />
				<div class="col-xs-12 col-sm-9">
				<div class="page-header text-left">
					<div class="h1">
						管理介面<small>點數兌換資料管理</small>
					</div>
				</div>
				<table id="table-1">
					<tr>
						<td>
							<h3>所有點數兌換資料</h3>
						</td>
					</tr>
				</table>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<table>
					<tr>
						<th>兌換記錄編號</th>
						<th>會員編號</th>
						<th>兌換申請時間</th>
						<th>兌換現金</th>
						<th>銀行帳號</th>
						<th>申請狀態</th>
						<th>更改狀態</th>
					</tr>
					<%@ include file="pages/page1.file"%>
					<c:forEach var="point_exc_cashVO" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>${point_exc_cashVO.exc_rec_id}</td>
							<td>${point_exc_cashVO.mem_id}</td>
							<td>${point_exc_cashVO.exc_date}</td>
							<td>${point_exc_cashVO.exc_cash}</td>
							<td>${point_exc_cashVO.bank_acc}</td>
							<td class="A">${point_exc_cashVO.req_status}</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/Point_exc_cashServlet"
									style="margin-bottom: 0px;">
									<input class="button-secondary" type="submit" class="Q"
										value="修改"> <input type="hidden" name="exc_rec_id"
										value="${point_exc_cashVO.exc_rec_id}"> <input
										type="hidden" name="requestURL"
										value="<%=request.getServletPath()%>">
									<!--送出本網頁的路徑給Controller-->
									<input type="hidden" name="whichPage" value="<%=whichPage%>">
									<!--送出當前是第幾頁給Controller-->
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
		<%@ include file="pages/page2.file"%>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
		</div>
		</div>
		<script>
	$(function(){
		var text = $(".A").text();
		for(var i=0;i<text.length;i++){
			if(text.charAt(i)=='1'){
				$(".A").eq(i).next().children(":first-child").hide();  
			}
		}
	})
</script>
</div>
</body>
</html>