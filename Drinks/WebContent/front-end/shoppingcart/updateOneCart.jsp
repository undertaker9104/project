<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.shoppingcart.model.*"%>
<%@ page import="com.mem.model.*"%>
<%
   ShoppingCartVO editOneCart = (ShoppingCartVO) session.getAttribute("editOneCart");
%>

<%
  Integer editIndex = (Integer) request.getAttribute("editIndex");
  request.setAttribute("editIndex", editIndex);
%>
<% 
    MemberVO memVO = (MemberVO) session.getAttribute("memVO");
    request.setAttribute("memVo",memVO);
%>
<html>
<head>

<title>listOneOrderMaster</title>

<style type="text/css">
.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
	font-size: 16px;
}
</style>
</head>
<body>
    <div class="page-header text-center">
		<div class="h1">購物清單</div>
    </div>
    <form method="post" action="<%=request.getContextPath()%>/cart.do">
	<table class="myTable_update table table-hover table-bordered table-striped table-condensed">
				<thead>
     				<tr bgcolor=#DDDDDD>
						<th>商品名稱</th>
						<th>商品價格</th>
						<th>商品敘述</th>
						<th>商品圖片</th>
						<th>甜度</th>
						<th>冰塊</th>
						<th>數量</th>
					</tr>
				</thead>
				<tbody>
				 	<tr>
						<td> ${editOneCart.product_name} </td>
						<td> ${editOneCart.product_price} </td>
						<td style="text-align: left;font-size: 18px;width:20% "> ${editOneCart.product_des} </td>
						<td>
						     <img src="data:image/jpg;base64,${editOneCart.getBase64Image()}">
						</td>
						<td>
						   <jsp:useBean id="sweetSvc" scope="page" class="com.sweet.model.SweetService" />
                              <select size="1" name="option_sweet" class="form-control">
							    <c:forEach var="sweetVO" items="${sweetSvc.all}">
								  <option value="${sweetVO.sweet_id}"
									${(sweetVO.sweet_id == editOneCart.sweet_id) ? 'selected':'' }>
									${ sweetVO.sweet_des}
									</c:forEach>
						      </select> 
						</td>
						<td>
						   <jsp:useBean id="iceSvc" scope="page" class="com.ice.model.IceService" /> 
							    <select size="1" name="option_ice"  class="form-control">
								   <c:forEach var="iceVo" items="${ iceSvc.all }">
									   <option value="${ iceVo.ice_id }"
										${(iceVo.ice_id == editOneCart.ice_id) ?'selected':''}>
										${ iceVo.ice_des }
									   </c:forEach>
							    </select>
						</td>
						<td>${editOneCart.quantity}</td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="action"  value="confirmCart">
	    	<input type="hidden" name="option_id"  value="${ editOneCart.addProduct_id }">
			<input type="hidden" name="editIndex"  value="${ editIndex }">
			<input type="hidden" name="option_quenty"  value="${editOneCart.quantity}">
			<input type="hidden" name="mem_id"  value="${memVo.mem_id}">
		    <input type="submit" class="btn btn-info btn" value="確認修改">
	</form>
	   
</body>
</html>