<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.int_exc.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%Int_excVO int_excVO = (Int_excVO) request.getAttribute("int_excVO");%>

<html>
<head>
<title>會員積分兌換資料</title>

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

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>單一會員積分兌換紀錄 </h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>積分兌換記錄編號</th>
		<th>會員編號</th>
		<th>積分兌換申請時間</th>
		<th>兌換積分</th>
	</tr>
	<tr>
		<td>${int_excVO.int_exc_rec_id}</td>
		<td>${int_excVO.mem_id}</td>
		<td>${int_excVO.int_exc_date}</td>
		<td>${int_excVO.integral}</td>
	</tr>

</table>

</body>
</html>