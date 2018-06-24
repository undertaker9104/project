<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.point_exc_cash.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>



<html>
<head>
<title>儲值紀錄</title>

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

<%
List<Point_exc_cashVO> point_exc_cashlist = (List<Point_exc_cashVO>) session.getAttribute("point_exc_cashlist");
%>

<table id="table-1">
	<tr><td>
		 <h3>提取紀錄 </h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/mem/withdrawalPoint.jsp">回提取頁面</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>兌換記錄編號</th>
		<th>會員編號</th>
		<th>兌換申請時間</th>
		<th>兌換金額</th>
		<th>銀行帳號</th>
		<th>申請狀態</th>
	</tr>
	<%@ include file="pages/page1_point_exc_cashlistVO.file"%>
	<tr>
	<c:forEach var="point_exc_cashlistVO" items="${point_exc_cashlist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td>${point_exc_cashlistVO.exc_rec_id}</td>
		<td>${point_exc_cashlistVO.mem_id}</td>
		<td>${point_exc_cashlistVO.exc_date}</td>
		<td>${point_exc_cashlistVO.exc_cash}</td>
		<td>${point_exc_cashlistVO.bank_acc}</td>
		<td>${point_exc_cashlistVO.req_status}</td>
	</tr>
	</c:forEach>
	</tr>
</table>
<%@ include file="pages/page2.file"%>

</body>
</html>