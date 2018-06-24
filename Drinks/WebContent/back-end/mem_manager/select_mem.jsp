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
   <tr><td><h3>會員 查詢/管理 專區</h3></td></tr>
   <tr><td> <h4><a href="<%=request.getContextPath()%>/back-end/index_back.jsp">回首頁</a></h4></td></tr>
</table>



<h3>會員資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<h3>會員 :<h3> 
<ul>  
   <li><a href='<%=request.getContextPath()%>/back-end/mem/listAllMem.jsp'>List</a> all Mems. <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" >
        <b>輸入會員編號 (如M00001):</b>
        <input type="text" name="mem_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

   <jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemberService" />
    <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" >
       <b>選擇會員編號:</b>
       <select size="1" name="mem_id">
         <c:forEach var="memberVO" items="${memberSvc.all}" > 
          <option value="${memberVO.mem_id}">${memberVO.mem_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
<li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" >
       <b>選擇會員姓名:</b>
       <select size="1" name="mem_id">
         <c:forEach var="memberVO" items="${memberSvc.all}" > 
          <option value="${memberVO.mem_id}">${memberVO.mem_name}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


 	
</body>
</html>