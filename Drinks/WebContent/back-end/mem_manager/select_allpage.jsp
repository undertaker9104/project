<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
   <tr><td><h3>不要問那麼多</h3></td></tr>
</table>



<h3>資料查詢:</h3>
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


<h3>儲值紀錄:</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/mem/listAllMemRecord_value.jsp'>List</a> all Stored_value. <br><br></li>

  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Deposit_recordsServlet" >
       	<b>輸入會員編號 (如M00001):</b>
        <input type="text" name="mem_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  

    <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Deposit_recordsServlet" >
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
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Deposit_recordsServlet">
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

   <h3>兌換現金紀錄:</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/mem/listAllModerated.jsp'>List</a> all 待審核. <br><br></li>
  <li><a href='<%=request.getContextPath()%>/back-end/mem/listAllcomplete.jsp'>List</a> all 已審核完畢. <br><br></li>
  <li><a href='<%=request.getContextPath()%>/back-end/mem/listAllMemWithdraealPoint.jsp'>List</a> all withdrawal. <br><br></li> 
<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Point_exc_cashServlet" >
        <b>輸入會員編號 (如M00001):</b>
        <input type="text" name="mem_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

   <jsp:useBean id="point_exc_cashSvc" scope="page" class="com.point_exc_cash.model.Point_exc_cashService" />
    <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Point_exc_cashServlet" >
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
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Point_exc_cashServlet" >
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
<h3>積分兌換紀錄:</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/mem/listAll_Int_exc.jsp'>List</a> all Int_exc. <br><br></li>
<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Int_excServlet" >
        <b>輸入會員編號 (如M00001):</b>
        <input type="text" name="mem_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

   <jsp:useBean id="int_excSvc" scope="page" class="com.int_exc.model.Int_excService" />
    <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Int_excServlet" >
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
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Int_excServlet" >
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
<!--
<h3><font color=orange>部門管理</font></h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/auth/listAllAuth.jsp'>List</a> all Depts. </li>
</ul>
 -->
 	
</body>
</html>