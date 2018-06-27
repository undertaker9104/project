<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager_account.model.*"%>
<%@ page import="com.manager_account_authority.model.*"%>
<%@ page import="com.authority_class.model.*"%>
<%@ page import="com.manager_account.model.*"%>

<jsp:useBean id="manager_account_authoritySvc"
	class="com.manager_account_authority.model.Manager_account_authorityService" />
<%
Manager_accountVO manager_accountVO = (Manager_accountVO) session.getAttribute("manager_accountVO");
System.out.print(manager_accountVO.getMan_acc_id());
List<String> list1 = manager_account_authoritySvc.getOneManager_account_authority(manager_accountVO.getMan_acc_id());
if(!list1.contains("AC000001")){
	   out.println("<script language=\"javascript\">");
	   out.println("alert(\"您尚未有管理權限\");");
	   out.println("window.history.back(-1);");  
	   out.println("</script>");
}
%>
<%
	Manager_accountService manager_accountSvc = new Manager_accountService();
	List<Manager_accountVO> list = manager_accountSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="authority_classSvc"
	class="com.authority_class.model.Authority_classService" />

<html>
<head>
<title>所有員工資料</title>

<style>

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

 .button-error,.button-secondary {
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

 .button-error{
 background: red;
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

tr:nth-child(even) {background-color:#E8FFFF;}	
</style>
</head>
<body bgcolor='white'>
	<div class="container">
		<div class="row">
			<jsp:include page="/back-end/toolBar.jsp" />
			<jsp:include page="/back-end/breadBar.jsp" />
			<table id="table-1">
				<tr>
					<td>
						<h3><font style="font-family:微軟正黑體;font-weight:bold; font-weight:1.5em; font-size:2em" >所有員工資料</font></h3>
					</td>
				</tr>
			</table>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red ;">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<table>
				<tr style="
    height: 51.979166px;
">
					<th>員工編號</th>
					<th>員工姓名</th>
					<th>員工E-mail</th>
					<th>員工照片</th>
					<th>員工職稱</th>
					<th>修改</th>
				</tr>
				<%@ include file="pages/page1.file"%>
				<c:forEach var="manager_accountVO" items="${list}"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr
						${(manager_accountVO.man_acc_id==param.man_acc_id) ? 'bgcolor=#CCCCFF':''}>
						<!--將修改的那一筆加入對比色而已-->
						<td>${manager_accountVO.man_acc_id}</td>
						<td>${manager_accountVO.emp_name}</td>
						<td>${manager_accountVO.emp_email}</td>
						<td><img
							src="data:image/png;base64,${manager_accountVO.getImg(manager_accountVO.emp_img)}" /></td>

						<td><c:forEach var="manager_account_authorityVO"
								items="${manager_account_authoritySvc.all}">
								<c:if
									test="${manager_account_authorityVO.man_acc_id==manager_accountVO.man_acc_id}">
	                    ${authority_classSvc.getOneAuthority(manager_account_authorityVO.authority_id).authority_des}<br>
								</c:if>
							</c:forEach></td>

						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/Manager_account_authorityServlet"
								style="margin-bottom: 0px;">
								<input class="button-secondary" type="submit" value="修改職稱">
								<input type="hidden" name="man_acc_id" value="${manager_accountVO.man_acc_id}"> 
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="whichPage" value="<%=whichPage%>">
								<!--送出當前是第幾頁給Controller-->
								<input type="hidden" name="action" value="getOne_For_Display">
							</FORM>
							<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Manager_accountServlet" style="margin-bottom: 0px;">
			     <input class="button-error" type="submit" value="刪除">
			     <input type="hidden" name="man_acc_id"      value="${manager_accountVO.man_acc_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"     value="delete"></FORM>
			</td>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="pages/page2.file"%>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
</body>
</html>