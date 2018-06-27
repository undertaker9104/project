<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.point_exc_cash.model.*"%>
<%@ page import="java.util.*"%>

<%
Point_exc_cashVO point_exc_cashVO = (Point_exc_cashVO) request.getAttribute("point_exc_cashVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>點數兌換審核</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    table-layout:fixed;
  word-break:break-all;
  word-warp:break-word;
    border: 0px solid #CCCCFF;
  }
  th, td {
   width:100px;
    padding: 1px;
    
  }
  
  td{
	height:50px;
}

input[type="text"]{padding:5px 15px; border:2px black solid;
cursor:pointer;
-webkit-border-radius: 5px;
border-radius: 5px; }


 .button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 10px;
  cursor: pointer;
  text-align: center;   
  text-decoration: none;
  outline: none;
  color: #fff;
  background-color: blue;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
}

.button:hover {background-color: #3e8e41}

.button:active {
  background-color: blue;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}


</style>
<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>
</head>
<body bgcolor='white'>


	<div align="center" style="
    padding-top: 5%;
"><img src="<%=request.getContextPath()%>/front-end/pic/444.png" style="width: 50px; height: 50px"><font style="font-family:微軟正黑體;font-weight:bold;font-size:2em">點數兌換審核	</font></td>
</div>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Point_exc_cashServlet" style="
    padding-top: 5%;">
<table align="center">
	<tr>
		<td>兌換紀錄編號:</td>
		<td>${point_exc_cashVO.exc_rec_id}</td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td>${point_exc_cashVO.mem_id}</td>
	</tr>
	<tr>
		<td>兌換申請時間:</td>
		<td>${point_exc_cashVO.exc_date}</td>
	</tr>
	<tr>
		<td>兌換現金:</td>
		<td>${point_exc_cashVO.exc_cash}</td>
	</tr>
	<tr>
		<td>銀行帳號:</td>
		<td>${point_exc_cashVO.bank_acc}</td>
	</tr>
	<tr>
		<td>申請狀態:</td>
		<td><input type="TEXT" name="req_status" size="2"	value="${point_exc_cashVO.req_status}" />(0:待審核 1:已審核)</td>
	</tr>
	<tr>
	<td colspan="2">
<input type="hidden" name="action" value="update">
<input type="hidden" name="exc_rec_id"  value="<%=point_exc_cashVO.getExc_rec_id() %>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
<input class="button" type="submit" value="送出修改"></FORM>
</td>
</tr>
</table>
</body>

