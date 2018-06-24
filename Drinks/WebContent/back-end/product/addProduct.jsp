<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productclass.model.ProductClassVo"%>
<%@ page import="com.product.model.ProductVo"%>
<%@ page import="com.productclass.model.ProductClassDao"%>
<%@ page import="com.productclass.model.ProductClassService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<%
	ProductVo proVO = (ProductVo) request.getAttribute("proVO");
    request.setAttribute("proVO", proVO);
%>
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
<style type="text/css">
img {
	height: 200px;
	hidth: 150px;
}
</style>
<script>
		function readURL(input) {
			if (input.files && input.files[0]) {
				var imageTagID = input.getAttribute("targetID");
				var reader = new FileReader();
				reader.onload = function(e) {
					var img = document.getElementById(imageTagID);
					img.setAttribute("src", e.target.result)
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
</script>
</head>
<body>
	<div class="container" style="margin-top:10%;">
		<form class="well form-horizontal" 
		      action="<%=request.getContextPath()%>/product.do"
		      method="post" id="contact_form" enctype="multipart/form-data">
			<fieldset>
				<legend class="text-center" style="font-size: 20px;">
					<b>新增飲品</b>
				</legend>
				<div class="form-group">
					<label class="col-md-4 control-label">飲料名稱</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-comment"></i>
							</span> 
							<input 	name="product_name" placeholder="飲料名稱" 
							        class="form-control" type="text" value="${proVO.product_name}">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">飲料類別</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-comment"></i>
							</span>
							<jsp:useBean id="productClsrc" scope="page" class="com.productclass.model.ProductClassService" />
							<select size="1" name="product_class" class="form-control">
								<option value="">請選擇商品類別
									<c:forEach var="productClassVO" items="${productClsrc.all}">
										<option value="${productClassVO.product_cl_id}" 
										${(proVO.product_cl_id==productClassVO.product_cl_id)? 'selected':'' }>${productClassVO.product_cl_name}
									</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">飲料價錢</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-usd"></i></span> 
							<input name="product_price" placeholder="飲料價錢" 
							      class="form-control" type="text" value="${proVO.product_price}">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">飲品描述</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-home"></i></span>
							<input name="product_des" placeholder="飲品描述" 
							       class="form-control" type="text"
								   value="${proVO.product_des}">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">飲料圖片</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-picture"></i></span>
							<input name="product_img" class="form-control" 
							       type="File" onchange="readURL(this)" 
							       targetID="preview_progressbarTW_img"
								   accept="image/gif, image/jpeg, image/png"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">預覽圖片</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-picture"></i></span> 
								<%if(proVO == null){ %>
								<img id="preview_progressbarTW_img" src="<%=request.getContextPath()%>/front-end/img/null.png">
								<%}else{%>
							    <img id="preview_progressbarTW_img" src="#">
							    <%}%>
							</div>
						</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<input type="hidden" name="action" value="add">
						<input type="hidden" name="product_status" value="0">
						<button type="submit" class="btn btn-warning">
							新增<span class="glyphicon glyphicon-send"></span>
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
</body>
</html>