<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.manager_account.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%Manager_accountVO manager_accountVO = (Manager_accountVO) request.getAttribute("manager_accountVO");%>

<html>
<head>
<title>員工資料</title>

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
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>員工資料 </h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/select_emp.jsp">回查詢頁面</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>員工編號</th>
		<th>員工密碼</th>
		<th>員工權限</th>
		<th>員工E-mail</th>
		<th>員工姓名</th>
		<th>員工照片</th>
	</tr>
	<tr>
		<td>${manager_accountVO.man_acc_id}</td>
		<td><%=manager_accountVO.getAccpw()%></td>
		<td><%=manager_accountVO.getMan_acc_status()%></td>
		<td><%=manager_accountVO.getEmp_email()%></td>
		<td><%=manager_accountVO.getEmp_name()%></td>
	    <td><img id="output" src="<% out.print("data:image/png;base64, "+ new String(Base64.getEncoder().encode(manager_accountVO.getEmp_img())));%>"/></td>
	    <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Manager_accountServlet" style="margin-bottom: 0px;">
			     <input class="button-secondary" type="submit" value="修改"> 
			     <input type="hidden" name="man_acc_id" value="${manager_accountVO.man_acc_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
	</tr>

</table>

</body>
</html>