<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.product.model.*"%>
<%
   ProductVo oneproVO = (ProductVo) request.getAttribute("oneproVO");
%>
<jsp:useBean id="iceSvc" scope="page" class="com.ice.model.IceService" />
<jsp:useBean id="sweetSvc" scope="page" class="com.sweet.model.SweetService" />
<html>
<head>
<style type="text/css">

.body {
	text-align: center;
	vertical-align: middle !important;
	font-size: 16px;
}
thumbnail.img {
	height: 250px;
	width: 250px;
	display: block;
    margin-left: auto;
    margin-right: auto;
}
</style>
</head>
<body>
<div class="container">
	  <div class="row">
            <div class="page-header">
                  <div class="h1">${oneproVO.product_name}</div>
               </div> 
           <div class="col-xs-12 col-sm-6">
				<div class="thumbnail">
				     <img src="data:image/jpg;base64,${oneproVO.getBase64Image()}">
			    <div class="caption">
			     </div>
		        </div>
		   </div>
		     ${oneproVO.product_price}<br>
	  </div>
</div>
</body>
</html>