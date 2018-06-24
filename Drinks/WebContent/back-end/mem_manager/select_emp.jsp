<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager_account.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager_account_authority.model.*"%>
<% 
   Manager_accountVO manager_accountVO = (Manager_accountVO) session.getAttribute("manager_accountVO");
   System.out.print(manager_accountVO.getMan_acc_id());
   Manager_account_authorityService manager_account_authoritySvc = new Manager_account_authorityService();
   List<String> list = manager_account_authoritySvc.getOneManager_account_authority(manager_accountVO.getMan_acc_id());
   if(!list.contains("AC000001")){
	   out.println("<script language=\"javascript\">");
	   out.println("alert(\"您尚未有管理權限\");");
	   out.println("window.history.back(-1);");  
	   out.println("</script>");
   }
%>
<html>
<head>
<title>IBM Emp: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>員工 查詢/管理 頁面</h3></td></tr>
   <tr><td> <h4><a href="<%=request.getContextPath()%>/back-end/index_back.jsp">回首頁</a></h4></td></tr>
</table>



<h3>員工資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<h3>員工管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/emp/addManager_account.jsp'>Add</a> a new Emp.</li>
</ul>

<h3>員工 :<h3>
<ul>


  <li><a href='<%=request.getContextPath()%>/back-end/emp/listAllEmp.jsp'>List</a> all Emps. <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Manager_accountServlet" >
        <b>輸入員工編號 (如MA000001):</b>
        <input type="text" name="man_acc_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>  
  
  <jsp:useBean id="manager_accountSvc" class="com.manager_account.model.Manager_accountService" />
    <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Manager_accountServlet" >
       <b>選擇員工編號:</b>
       <select size="1" name="man_acc_id">
         <c:forEach var="manager_accountVO" items="${manager_accountSvc.all}" > 
          <option value="${manager_accountVO.man_acc_id}">${manager_accountVO.man_acc_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
<li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Manager_accountServlet" >
       <b>選擇員工姓名:</b>
       <select size="1" name="man_acc_id">
         <c:forEach var="manager_accountVO" items="${manager_accountSvc.all}" > 
          <option value="${manager_accountVO.man_acc_id}">${manager_accountVO.emp_name}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>
  
<h3>員工職稱 :<h3>
<ul>  
   <li><a href='<%=request.getContextPath()%>/back-end/emp/listAllEmp_Auth.jsp'>List</a> all Emps. <br><br></li>

<jsp:useBean id="authority_classSvc" scope="page" class="com.authority_class.model.Authority_classService" />
    <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Manager_account_authorityServlet">
       <b>選擇職稱:</b>
       <select size="1" name="authority_id">
         <c:forEach var="authority_classVO" items="${authority_classSvc.all}" > 
          <option value="${authority_classVO.authority_id}">${authority_classVO.authority_des}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOneAuth_For_Display">
    </FORM>
  </li>
</ul> 	
</body>
</html>