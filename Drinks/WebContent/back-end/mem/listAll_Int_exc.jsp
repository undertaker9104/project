<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.int_exc.model.*"%>

<%
    Int_excService int_excSvc = new Int_excService();
	List<Int_excVO> list = int_excSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有積分兌換資料</title>

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
		 <h3>所有積分兌換資料</h3>
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
		<th>積分兌換記錄編號</th>
		<th>會員編號</th>
		<th>積分兌換申請時間</th>
		<th>兌換積分</th>
	
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="int_excVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
		<td>${int_excVO.int_exc_rec_id}</td>
		<td>${int_excVO.mem_id}</td>
		<td>${int_excVO.int_exc_date}</td>
		<td>${int_excVO.integral}</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>