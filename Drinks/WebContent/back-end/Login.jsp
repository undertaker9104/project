<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productclass.model.ProductClassVo"%>
<%@ page import="com.product.model.ProductVo"%>
<%@ page import="com.productclass.model.ProductClassDao"%>
<%@ page import="com.productclass.model.ProductClassService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/base.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
</head>
<body background="<%=request.getContextPath()%>/front-end/img/back.jpg">
	<div class="container col-sm-6 col-sm-offset-3"
		style="margin-top: 15%;">
		<form class="well form-horizontal"
			action="<%=request.getContextPath()%>/Manager_accountServlet"
			method="post">
			<fieldset>
				<legend class="text-center" style="font-size: 36px;">
					<b>員工登入頁面</b>
				</legend>
				<div class="form-group">
					<label class="col-md-6 control-label col-sm-offset-3"></label>
					<div class="col-md-6 inputGroupContainer col-sm-offset-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span> <input name="emp_name" id="emp_name" placeholder="員工姓名"
								class="form-control" type="text" onfocus="myFocus(this)" onblur="myBlur(this)">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-6 control-label col-sm-offset-3"></label>
					<div class="col-md-6 inputGroupContainer col-sm-offset-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-comment"></i>
							</span> <input name="accpw"  id="accpw" placeholder="密碼" class="form-control"
								    type="password" onfocus="myFocus(this)" onblur="myBlur(this)">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-6">
						<input type="hidden" name="action" value="login"> <a
							href="<%=request.getContextPath()%>/back-end/backIndex.jsp">
							<button type="submit" class="btn btn-warning">
								回首頁<span class="glyphicon glyphicon-home"></span>
							</button>
						</a>
						<button type="submit" class="btn btn-warning">
							登入<span class="glyphicon glyphicon-send"></span>
						</button>
					</div>
				</div>
			<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
		    </fieldset>
		</form>
		<button type="submit" class="btn" onClick="mananger()">管理者</button>
		<button type="submit" class="btn" onClick="worker()">櫃台員工</button>
	
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
</body>

<Script>
function mananger() {
document.getElementById("emp_name").value ="小一";
document.getElementById("accpw").value ="a1";
}
function worker(){
document.getElementById("emp_name").value ="小三";
document.getElementById("accpw").value ="a3";
}
</Script>
<script>
	function myFocus(e) {
		e.style.backgroundColor="#cccccc";
	}
	function myBlur(e) {
		
		e.style.backgroundColor="#ffffff";
	}
</script>
</html>