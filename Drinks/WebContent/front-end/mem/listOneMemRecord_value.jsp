<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.deposit_records.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>



<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>儲值紀錄</title>
<style type="text/css">
th{
background: #BDBDBD;
}

h3 {
	color:#660099;
	font-size: 4rem;
}

</style>

</head>
<body bgcolor='white'>
<jsp:useBean id="deposit_records_valuelist" scope="session"
		type="java.util.List<Deposit_recordsVO>" />

<table align="center" id="table-1">
	<tr><td >
		 <h3><img src="<%=request.getContextPath()%>/front-end/img/logo-01.png" style="width: 60px; height: 60px"><font style="font-family:微軟正黑體;font-weight:bold;font-size:1em">儲值紀錄 </font></h3>
		 <h4 align="center"><a href="<%=request.getContextPath()%>/front-end/mem/storedPoint.jsp">回儲值頁面</a></h4>
	</td></tr>
</table>
	<br>
<table class="table table-hover table-bordered table-striped table-condensed">
	<thead>
	<tr>
		<th>儲值編號</th>
		<th>會員編號</th>
		<th>儲值金額</th>
		<th>交易成立時間</th>
	</tr>
	</thead>
	<%@ include file="pages/page1_deposit_records_valuelist.file"%>
	<br>
	<br>
	<tr>
	<c:forEach var="deposit_recordsVO" items="${deposit_records_valuelist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tbody>
	<tr>
		<td>${deposit_recordsVO.dep_rec_id}</td>
		<td>${deposit_recordsVO.mem_id}</td>
		<td>${deposit_recordsVO.dep_cash}</td>
		<td>${deposit_recordsVO.dep_suss_date}</td>
	</tr>
	</c:forEach>
	</tr>
	<tbody>
</table>
<%@ include file="pages/page2.file"%>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>