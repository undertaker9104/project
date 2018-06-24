<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.point_exc_cash.model.*"%>

<%Point_exc_cashVO point_exc_cashVO = (Point_exc_cashVO) request.getAttribute("point_exc_cashVO");%>
<%
    Point_exc_cashService point_exc_cashSvc = new Point_exc_cashService();
	List<Point_exc_cashVO> list = point_exc_cashSvc.getAll_Complete();
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有點數兌換完畢資料</title>

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
    table-layout:fixed;
  word-break:break-all;
  word-warp:break-word;
    border: 1px solid #CCCCFF;
  }
  th, td {
  width:300px;
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>所有點數兌換完成資料</h3>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
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
	
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="point_exc_cashVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
		<td>${point_exc_cashVO.exc_rec_id}</td>
		<td>${point_exc_cashVO.mem_id}</td>
		<td>${point_exc_cashVO.exc_date}</td>
		<td>${point_exc_cashVO.exc_cash}</td>
		<td>${point_exc_cashVO.bank_acc}</td>
		<td>${point_exc_cashVO.req_status}</td>
	
	</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>