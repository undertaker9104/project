<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>    
<%
   ProductVo productVo=(ProductVo)request.getAttribute("proVO");
   String image =productVo.getBase64Image();
   request.setAttribute("productVo", productVo);
   request.setAttribute("image", image);
%>
<html>
    <head>
        <title>Servertest</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    </head>
    <body >
      <img src="data:image/jpg;base64,${image}" style="width:250px ;height:250px">
	  ${productVo.product_name}
	  ${productVo.product_des}
    </body>
 </html>