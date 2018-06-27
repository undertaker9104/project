<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.point_exc_cash.model.*"%>
<%@ page import="com.manager_account.model.*"%>
<%@ page import="com.manager_account_authority.model.*"%>
<%
Manager_accountVO manager_accountVO = (Manager_accountVO) session.getAttribute("manager_accountVO");
System.out.print(manager_accountVO.getMan_acc_id());
Manager_account_authorityService manager_account_authoritySvc = new Manager_account_authorityService();
List<String> list1 = manager_account_authoritySvc.getOneManager_account_authority(manager_accountVO.getMan_acc_id());
if(!list1.contains("AC000001")){
	   out.println("<script language=\"javascript\">");
	   out.println("alert(\"您尚未有管理權限\");");
	   out.println("window.history.back(-1);");  
	   out.println("</script>");
}
%>
<%
	Point_exc_cashVO point_exc_cashVO = (Point_exc_cashVO) request.getAttribute("point_exc_cashVO");
%>
<%
	Point_exc_cashService point_exc_cashSvc = new Point_exc_cashService();
	List<Point_exc_cashVO> list = point_exc_cashSvc.getAll_Moderated();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有點數兌換待審核資料</title>



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

#table-1 {
	text-align: center;
	border:0px;
}

#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	display: inline;
}
</style>

</head>
<body bgcolor='white'>
<body>
	<div class="container">
		<div class="row">
			<jsp:include page="/back-end/toolBar.jsp" />
			<jsp:include page="/back-end/breadBar.jsp" />
			<div class="col-xs-12 col-sm-9">
				<div class="page-header text-left">
					<div class="h1">
						管理介面<small>點數兌換管理</small>
					</div>
				</div>
				<table id="table-1" align="center" >
					<tr>
						<td style="width: 451.979166px; border:0px">
							<h3><font style="font-family:微軟正黑體;font-weight:bold; font-weight:1.5em; font-size:1.5em" >點數兌換待審核資料</font>
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
						<th style="
    height: 51.979166px;
    width: 76.979166px;
">兌換記錄編號</th>
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
							<td>${point_exc_cashVO.req_status}</td>
							<td>
								<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/Point_exc_cashServlet"
									   style="margin-bottom: 0px;">
									<input class="button-secondary" type="submit" id="Q" value="修改">
									<input type="hidden" name="exc_rec_id" value="${point_exc_cashVO.exc_rec_id}"> 
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
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
				  <li><a href='<%=request.getContextPath()%>/back-end/mem/listAllcomplete.jsp'>List</a> all 已審核</li>
			</div>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery.js"></script>
</body>
</html>