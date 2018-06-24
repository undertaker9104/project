<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.product.model.*"%>
<%@ page import="com.producttrack.model.*"%>
<%@ page import="com.ice.model.*"%>
<%@ page import="com.sweet.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.List"%>
<%
   MemberVO memVO = (MemberVO) session.getAttribute("memVO");
   String mem_id = memVO.getMem_id();
   ProductTrackService trSvc = new ProductTrackService();
   List<ProductTrackVo> mytracklist  =trSvc.getMyAll(mem_id);
   request.setAttribute("mytracklist", mytracklist);
   request.setAttribute("memVo",memVO);
%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/base.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/mycss.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script> 
</head>
<body>
	<jsp:include page="/front-end/header.jsp"/>
	<jsp:useBean id="productSrc" scope="page" class="com.product.model.ProductService" />
	<jsp:useBean id="iceSrc" scope="page" class="com.ice.model.IceService" />
	<jsp:useBean id="sweetSrc" scope="page" class="com.sweet.model.SweetService" />
	<div class="container">
		<div class="row">
			<% if (mytracklist != null && (mytracklist.size() > 0)) { %>
			<div class="col-xs-12 col-sm-10 col-sm-offset-1" style="padding-top: 20px;">
			<br>
				<div class="page-header text-left">
					<div class="h1">我的最愛</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<c:forEach items="${mytracklist}" var="product">
			<div class="col-sm-4 test">
			   <div class="card getMyProduct">
					<div class="card-img">
					    <img src="data:image/jpg;base64,${productSrc.getOneProduct(product.product_id).getBase64Image()}"style="width: 180px; height: 200px">
						<div class="card-title">
						   <span>${productSrc.getOneProduct(product.product_id).product_name}</span>
						   <span class="glyphicon glyphicon-usd"></span>
						   <span>${productSrc.getOneProduct(product.product_id).product_price}</span>
					    </div>
					    </div>
				</div>
				<input type="hidden" id="get_one" name="get_one" value="${product.product_id}" />
				<input type="hidden" id="action" name="action" value="getOneProduct" />
					<button class="btn btn-info cartButton my-btn" type="submit" name="Submit">
						<span class="glyphicon glyphicon-shopping-cart" title="加入購物車"> </span>
					</button>
					<input type="hidden" id="addProduct_id" name="addProduct_id" value="${product.product_id}" />
					<input type="hidden" id="action" name="action" value="addProduct_action" />
					<input type="hidden" id="buy_mem_id" name="buy_mem_id" value="${memVo.mem_id}" /> 
					<select size="1" id="sweet_id" name="sweet_id" class="selectpicker" >
						<c:forEach var="sweetVO" items="${ sweetSrc.all }">
							<option value="${ sweetVO.sweet_id }"
								${(sweetVO.sweet_id==sweetVO.sweet_id)?'selected':'' }>${ sweetVO.sweet_des }
						</c:forEach>
					</select> 
					<select size="1" id="ice_id" name="ice_id" class="selectpicker">
						<c:forEach var="iceVo" items="${ iceSrc.all }">
							<option value="${ iceVo.ice_id }"
								${(iceVo.ice_id==iceVo.ice_id)?'selected':'' }>${ iceVo.ice_des }
						</c:forEach>
					</select> 
					<select size="1" id="quenty" name="quenty" class="selectpicker">
						<c:forEach var="quantity" begin="1" end="10" step="1">
							<option value="${quantity}">飲料杯數 ${quantity}
						</c:forEach>
					</select>
					<label>
						<form method="post"	action="<%=request.getContextPath()%>/faver.do">
							<input type="hidden" name="mem_id" value="${product.mem_id}">
							<input type="hidden" name="product_id" value="${product.product_id}"> 
							<input type="hidden" name="action" value="deleteTrace">
							<input type="submit" class="btn btn-primary btn-sm" value="取消">
						</form>
					</label>
			
				</div>
		</c:forEach>
		<% } else {%>
		<br><br><br>
		<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="page-header text-left">
					<div class="h1">還沒有我的最愛喔!!!!!!!!!!!!!</div>
				</div>
		</div>
	   <% }%>
	</div>
   <script type="text/javascript">
		$(document).ready(function(){
			$(".cartButton").on("click",function(event){
				$.ajax({
					url:"/CA101G2/cart.do",
					data:{
						addProduct_id:$(this).next().val() ,
						action:$(this).next().next().val(),
						buy_mem_id:$(this).next().next().next().val(),
						sweet_id:$(this).next().next().next().next().val(),
						ice_id:$(this).next().next().next().next().next().val(),
						quenty:$(this).next().next().next().next().next().next().val()
					},
					cache:false,
					method:"POST",
					success:function(response){
					},
					error:function(msg, status, thrown){
						console.log(msg);
						console.log(status);
					}
				});
			});
		})
	</script>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/flyto.js"></script>
	<script>
        $('.test').flyto({
            item      : 'div',
            target    : '.abc',
            button    : '.my-btn'
        });
    </script>
    <script type="text/javascript">
		$(document).ready(function(){
			$(".getMyProduct").on("click",function(event){
				$.ajax({
					url:"/CA101G2/product.do",
					data:{
						get_one:$(this).next().val() ,
						action:$(this).next().next().val()
					},
					cache:false,
					method:"POST",
					dataType:"json",
				 	success:function(data){
				        swal({  
		            	     title:data.pro_name,
		            	     text:data.pro_des,
		                     imageUrl:'data:image/jpg;base64,'+data.pro_img,
		                     imageSize:'600x600',
		                     confirmButtonText:'確定',
		                     closeOnConfirm: false
		                    }            
		                  );
					
					},
					error:function(msg, status, thrown){
						console.log(status);
					}
				});
			});
		})
</script>
<jsp:include page="/front-end/footer.jsp" />