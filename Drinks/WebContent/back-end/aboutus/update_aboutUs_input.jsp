<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.aboutus.model.*"%>
<%
	AboutUsVO aboutUsVO = (AboutUsVO) request.getAttribute("aboutUsVO");	 
    request.setAttribute("aboutUsVO", aboutUsVO);
%>
<html>
<head>
<title>關於我們修改 </title></head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
</head>
<body>
	<br><br>
	<div class="container">
			<div class="row">
				<div class="container col-md-8 col-md-offset-2">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/aboutUs.do" name="form1">
						<div class="form-group">
							<td>關於我們編號:<font color=red><b>*</b></font></td>${aboutUsVO.about_id}
						</div>
						<div class="form-group">
							<label>關於我們敘述:</label>
							<textarea name="about_des" value="${aboutUsVO.about_des}" class="form-control" rows="10">${aboutUsVO.about_des}</textarea>
						</div>
						<div class="form-group">
							<label>營業時間:</label>
							<input type="text" name="about_time" size="45" value="${aboutUsVO.about_time}" class="form-control">
						</div>
						<div class="form-group">
							<label>電話:</label>
							<input type="text" name="about_phone" size="45" value="${aboutUsVO.about_phone}" class="form-control">
						</div>
						<div class="form-group">
							<label>地址:</label>
							<input type="text" name="about_add" size="45" value="${aboutUsVO.about_add}" class="form-control">
						</div>
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="about_id" value="${aboutUsVO.about_id}">
						<input type="submit" class="btn btn-success" value="送出修改">
					</FORM>
				</div>
			</div>
		</div>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>


</body>
</html>
