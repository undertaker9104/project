<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.productclass.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.ice.model.*"%>
<%@ page import="com.sweet.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.producttrack.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
      //商品總個數
      ProductService productSvc =new ProductService();
      List<ProductVo> prolist = productSvc.getRank();
      pageContext.setAttribute("prolist",prolist);
      int rank =prolist.size();
%>
<% 
	  MemberVO memVO = (MemberVO) session.getAttribute("memVO");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/base.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script> 

<jsp:useBean id="sweetSrc" scope="page" class="com.sweet.model.SweetService" />
<jsp:useBean id="iceSrc" scope="page" class="com.ice.model.IceService" />
<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService" />
<div class="col-xs-12 col-sm-12">
	<div class="container ">
	<% if (prolist != null && (prolist.size() > 0)) { %>
		<div class="page-header text-center">
			<div class="h1" style="color: red;background-image:url(/CA101G2/front-end/img/seasonBg.png);background-repeat: no-repeat;    background-position: 50%;" >熱門商品</div>
		</div>
		<c:forEach items="${prolist}" var="product" begin="0" end="2" varStatus="s">
			<div class="col-sm-4 test">
			<div class="card getHotProduct">
					<div class="card-img">
					    <c:if test="${s.index==0}" >
				   	      <img src="<%=request.getContextPath()%>/front-end/img/1.jpg"style="width: 70px; height: 70px" align="left" >
				        </c:if> 
				        <c:if test="${s.index==1}" >
				   	      <img src="<%=request.getContextPath()%>/front-end/img/2.jpg"style="width: 70px; height: 70px" align="left">
				        </c:if> 
				        <c:if test="${s.index==2}" >
				   	      <img src="<%=request.getContextPath()%>/front-end/img/3.jpg"style="width: 70px; height: 70px" align="left">
				        </c:if> 
				          <img src="data:image/jpg;base64,${proSvc.getOneProduct(product.product_id).getBase64Image()}"style="width: 200px; height: 200px">
					</div>
					<div class="card-title">
							<span>${proSvc.getOneProduct(product.product_id).product_name}</span>
							<span class="glyphicon glyphicon-usd"></span>
							<span>${proSvc.getOneProduct(product.product_id).product_price}</span>
					</div>
				</div>
				<input type="hidden" id="get_one" name="get_one" value="${product.product_id}" />
				<input type="hidden" id="action" name="action" value="getOneProduct" />
				<% if (memVO != null) { %>
				<input type="hidden" name="shopCount" id="shopCount" value="1">
				<button class="btn btn-info cartButton my-btn" type="submit" name="Submit">
					 <span class="glyphicon glyphicon-shopping-cart " title="加入購物車"></span>
				</button>
				<input type="hidden" id="addProduct_id" name="addProduct_id" value="${proSvc.getOneProduct(product.product_id).product_id}" />
				<input type="hidden" id="action" name="action" value="addProduct_action" /> 
				<input type="hidden" id="buy_mem_id" name="buy_mem_id" value="${memVo.mem_id}" />
			    <select size="1" id="sweet_id" name="sweet_id" class="selectpicker">
					<c:forEach var="sweetVO" items="${ sweetSrc.all }">
						<option value="${ sweetVO.sweet_id }">${ sweetVO.sweet_des }
					</c:forEach>
			    </select>
			    <select size="1" id="ice_id" name="ice_id" class="selectpicker">
					<c:forEach var="iceVo" items="${ iceSrc.all }">
						<option value="${ iceVo.ice_id }">${ iceVo.ice_des }
					</c:forEach>
				</select> 
				<select size="1" id="quenty" name="quenty" class="selectpicker">
					<c:forEach var="quantity" begin="1" end="10" step="1">
						<option value="${quantity}">飲料杯數 ${quantity}
					</c:forEach>
				</select>
			
				
				<% 
                    ///取得當前會員的追蹤飲品
                   ProductTrackService  protrSvc =new ProductTrackService();
                   String mem_id = memVO.getMem_id();
                   List<ProductTrackVo>  protrlist = protrSvc.getMyAll(mem_id);
                   pageContext.setAttribute("protrlist", protrlist);
                   session.setAttribute("memVo",memVO);
                %>
				<c:if test="${empty protrlist}">
					<button class="btn btn-danger trackButton" type="submit" id="button">
						<span class="glyphicon glyphicon-heart-empty track" title="加入收藏"></span>
					</button>
					<input type="hidden" id="addFaver_id" name="addFaver_id" value="${proSvc.getOneProduct(product.product_id).product_id}" />
					<input type="hidden" id="action" name="action" value="0" />
					<input type="hidden" id="mem_id" name="mem_id" value="${memVo.mem_id}" />
				</c:if>
				<c:set var="isDone" value="0" scope="page"></c:set>
				<c:forEach var="protrVO" items="${protrlist}" varStatus="varStatus">
					<c:if test="${isDone == '0'}">
						<c:if test="${ proSvc.getOneProduct(product.product_id).product_id eq protrVO.product_id}">
							<button class="btn btn-danger trackButton" type="submit">
								<span class="glyphicon glyphicon-heart track" title="取消收藏"></span>
							</button>
							<input type="hidden" id="addFaver_id" name="addFaver_id" value="${proSvc.getOneProduct(product.product_id).product_id}" />
							<input type="hidden" id="action" name="action" value="1" />
							<input type="hidden" id="mem_id" name="mem_id" value="${memVo.mem_id}" />
							<c:set var="isDone" value="1" scope="page"></c:set>
						</c:if>
						<c:if test="${varStatus.last}">
							<c:if test="${isDone == '0'}">
								<button class="btn btn-danger trackButton" type="submit">
									<span class="glyphicon glyphicon-heart-empty track" title="加入收藏"></span>
								</button>
								<input type="hidden" id="addFaver_id" name="addFaver_id" value="${proSvc.getOneProduct(product.product_id).product_id}" />
								<input type="hidden" id="action" name="action" value="0" />
								<input type="hidden" id="mem_id" name="mem_id" value="${memVo.mem_id}" />
							</c:if>
						</c:if>
					</c:if>
				</c:forEach>
				<%}%>
			</div>
		</c:forEach>
		<%}else{%>
	           <div class="col-xs-12 col-sm-10 col-sm-offset-1">
		         <div class="page-header text-left">
		         <div class="h1">還沒商品喔!!!!!!!!!!!!!</div>
	            </div>
              </div>
         <%}%>
	</div>
</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/flyto.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			$(".getHotProduct").on("click",function(event){
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

