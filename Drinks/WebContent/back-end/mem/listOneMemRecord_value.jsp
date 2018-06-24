<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.deposit_records.model.*"%>
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
</style>

</head>

<body bgcolor='white'>
	<jsp:useBean id="deposit_recordsVOList" scope="session"
		type="java.util.List<Deposit_recordsVO>" />
	<table id="table-1">
		<tr>
			<td>
				<h3>儲值紀錄</h3>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>儲值編號</th>
			<th>會員編號</th>
			<th>儲值金額</th>
			<th>交易成立時間</th>
		</tr>
		<%@ include file="pages/page1_deposit_recordsVOList.file"%>
		<tr>
			<c:forEach var="deposit_recordsVO" items="${deposit_recordsVOList}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${deposit_recordsVO.dep_rec_id}</td>
					<td>${deposit_recordsVO.mem_id}</td>
					<td>${deposit_recordsVO.dep_cash}</td>
					<td>${deposit_recordsVO.dep_suss_date}</td>
				</tr>
			</c:forEach>
		</tr>
	</table>
	<%@ include file="pages/page2.file"%>

</body>
</html>