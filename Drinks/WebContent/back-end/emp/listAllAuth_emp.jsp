<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager_account.model.*"%>
<%@ page import="com.manager_account_authority.model.*"%>
<%@ page import="com.authority_class.model.*"%>

<%
	Manager_accountService manager_accountSvc = new Manager_accountService();
	List<Manager_accountVO> list = manager_accountSvc.getAll();
	pageContext.setAttribute("list", list);

	List<String> list1 = (List<String>) request.getAttribute("list1");

	String authority_id = (String) request.getAttribute("authority_id");
	Authority_classService authority_classSvc = new Authority_classService();
	Authority_classVO authority_classVO = authority_classSvc.getOneAuthority(authority_id);
%>
<jsp:useBean id="manager_account_authoritySvc"
	class="com.manager_account_authority.model.Manager_account_authorityService" />


<html>
<head>
<title>職稱員工資料</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
<script src="https://code.jquery.com/jquery.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<jsp:include page="/back-end/toolBar.jsp" />
			<jsp:include page="/back-end/breadBar.jsp" />
			<div class="col-xs-12 col-sm-9">
				<div class="page-header text-left">
					<div class="h1">
						<small>管理介面</small>
					</div>
				</div>
				<table id="table-1" align="center">
					<tr>
						<td>
							<h3>
								所有<%=authority_classVO.getAuthority_des()%></h3>
							<h4>
								<a href="<%=request.getContextPath()%>/back-end/backIndex.jsp">回查詢頁面</a>
							</h4>
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
				<table align="center">
					<tr>
						<th>員工編號</th>
						<th>員工姓名</th>
						<th>員工E-mail</th>
						<th>員工照片</th>
					</tr>
					<c:forEach var="manager_accountVO" items="${list}">
						<c:if test="${list1.contains(manager_accountVO.man_acc_id)}">
							<td>${manager_accountVO.man_acc_id}</td>
							<td>${manager_accountVO.emp_name}</td>
							<td>${manager_accountVO.emp_email}</td>
							<td><img src="data:image/png;base64,${manager_accountVO.getImg(manager_accountVO.emp_img)}" /></td>
						</c:if>
						<tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	</body>
</html>