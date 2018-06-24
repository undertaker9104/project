<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.deposit_records.model.*"%>

<%Deposit_recordsVO deposit_recordsVO = (Deposit_recordsVO) request.getAttribute("deposit_recordsVO");%>
<%
    Deposit_recordsService deposit_recordsSvc = new Deposit_recordsService();
	List<Deposit_recordsVO> list = deposit_recordsSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有會員資料</title>

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
		 <h3>所有儲值紀錄</h3>
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
		<th>儲值編號</th>
		<th>會員編號</th>
		<th>儲值金額</th>
		<th>交易成立時間</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="deposit_recordsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
		<td>${deposit_recordsVO.dep_rec_id}</td>
		<td>${deposit_recordsVO.mem_id}</td>
		<td>${deposit_recordsVO.dep_cash}</td>
		<td>${deposit_recordsVO.dep_suss_date}</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>