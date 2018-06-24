<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<%
   ProductVo proVO = (ProductVo) request.getAttribute("proVO"); 
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<style type="text/css">
img {
	height: 300px;
	hidth: 150px;
}
</style>
</head>
<body>
	<div class="container" style="margin-top:10%;">
		<form class="well form-horizontal"  action="<%=request.getContextPath()%>/product.do"
		      method="post"  id="contact_form" enctype="multipart/form-data">
			<fieldset>
				<legend class="text-center" style="font-size: 20px;">
					<b>上傳商品圖檔</b>
				</legend>
				<div></div>
				<div class="form-group">
					<label class="col-md-4 control-label">飲料圖片</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
							 <i class="glyphicon glyphicon-picture"></i></span>
							  <input name="product_img" class="form-control" type="File"
								     onchange="readURL(this)" targetID="preview_progressbarTW_img"
								     accept="image/gif, image/jpeg, image/png" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">預覽圖片</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-picture"></i></span> 
							 <%if(proVO != null){ %>
								  <img id="preview_progressbarTW_img" src="<%=request.getContextPath()%>/front-end/img/null.png">
							 <% } else { %>
							    <img id="preview_progressbarTW_img" src="#">
							 <% } %>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<input type="hidden" name="action" value="updatePhoto"> 
						<input type="hidden" name="product_id" value="${proVO.product_id}">
						<button type="submit" class="btn btn-warning">
							上傳確認<span class="glyphicon glyphicon-send"></span>
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
	</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script>
        function readURL(input){
             if(input.files && input.files[0]){
               var imageTagID = input.getAttribute("targetID");
               var reader = new FileReader();
               reader.onload = function (e) {
               var img = document.getElementById(imageTagID);
               img.setAttribute("src", e.target.result)
       }
    reader.readAsDataURL(input.files[0]);
  }
 }
</script>
</body>
</html>