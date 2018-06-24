<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.aboutus.model.*"%>
<%
AboutUsVO aboutUsVO = (AboutUsVO) request.getAttribute("aboutUsVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�W�[����ڭ�</title>
<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>

<div class="container">
<jsp:include page="/front-end/header.jsp" />

	<br><br>
	<form class="form-horizontal" METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/aboutUs.do" name="form1">
		
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">����ڭ̱ԭz:</label>
			<div class="col-sm-6">
					<textarea class="form-control" name="about_des" value="${aboutUsVO.about_des} cols="30" rows="10">Hello</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">��~�ɶ�:</label>
			<div class="col-sm-6">
					<input type="TEXT" class="form-control" name="about_time" size="45" value="${aboutUsVO.about_time}Hello" />					
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">�q��:</label>
			<div class="col-sm-6">
					<input type="TEXT" class="form-control" name="about_phone" size="45" value="${aboutUsVO.about_phone}Hello" />
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">�a�}:</label>
			<div class="col-sm-6">
					<input type="TEXT" class="form-control" name="about_add" size="45" value="${aboutUsVO.about_add}Hello" />
			</div>
		</div>
		
		<input type="hidden" name="action" value="insert">
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<input type="submit" class="btn btn-success" value="�e�X�s�W">			
			</div>
		</div>
	</form>
</div>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</body>
</html>