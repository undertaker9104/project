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
      //商品總類別
      ProductClassService proclsSvc = new ProductClassService();
      List<ProductClassVo> proclslist =proclsSvc.getAllFront();
      pageContext.setAttribute("proclslist",proclslist);
%>
<% 
	  MemberVO memVO = (MemberVO) session.getAttribute("memVO");
    
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/base.css">
<jsp:useBean id="sweetSrc" scope="page" class="com.sweet.model.SweetService" />
<jsp:useBean id="iceSrc" scope="page" class="com.ice.model.IceService" />
<div class="col-xs-12 col-sm-12">
<div class="container ">
		<div class="page-header text-center">
			<div class="h1" style="color: red;background-image:url(/CA101G2/front-end/img/seasonBg.png);background-repeat: no-repeat;     background-position: 50%;">商品總覽</div>
		</div>
		<ul class="nav nav-pills nav-justified">
			<%for(int i= 0; i<proclslist.size(); i++) { %>
			<%String idname = proclslist.get(i).getProduct_cl_name();%>
			<li <%if(i==0){ %> class="active" <%}%>>
			   <a data-toggle="pill" href="#<%=idname%>"><%=idname%></a></li>
			<%}%>
		</ul>

		<div class="tab-content">
			<%for(int i= 0; i<proclslist.size(); i++) { %>
			<% 
			   String idname = proclslist.get(i).getProduct_cl_name();
			   String clid= proclslist.get(i).getProduct_cl_id();
			   ProductService probyclsSvc =new ProductService();
			   List<ProductVo> probyclslist = probyclsSvc.getAllBYCLASS(clid);
			%>
			<div id="<%=idname%>" <%if(i==0){ %> class="tab-pane fade in active"
				<%}else{%> class="tab-pane fade" <%}%>>
				<%for(int j= 0; j<probyclslist.size(); j++) {%>
				<% 
	              String pro_name =probyclslist.get(j).getProduct_name();
                  String pro_img = probyclslist.get(j).getBase64Image();
                  int pro_price =probyclslist.get(j).getProduct_price();
                  String pro_id =probyclslist.get(j).getProduct_id();
                  pageContext.setAttribute("pro_id",pro_id);
                %>
				<div class="col-sm-4 test">
					<div class="card getOneProduct">
						<div class="card-img">
							<img src="data:image/jpg;base64,<%=pro_img%>" style="width: 200px; height: 200px">
						</div>
						<div class="card-title">
							<span><%=pro_name%></span> 
							<span class="glyphicon glyphicon-usd"></span>
							<span><%=pro_price%></span>
						</div>
					</div>
					<input type="hidden" id="get_one" name="get_one" value="<%=pro_id%>" />
					<input type="hidden" id="action" name="action" value="getOneProduct" />
						
		           <% if (memVO != null) { %>
					<input type="hidden" name="shopCount" id="shopCount" value="1">
					<button class="btn btn-info cartButton my-btn" type="button">
						<span class="glyphicon glyphicon-shopping-cart" title="加入購物車"></span>
					</button>
					<input type="hidden" id="addProduct_id" name="addProduct_id" value="<%=pro_id%>" />
					<input type="hidden" id="action" name="action" value="addProduct_action" />
					<input type="hidden" id="buy_mem_id" name="buy_mem_id" value="${memVo.mem_id}" />
					
					 <select id="sweet_id" name="sweet_id" class="selectpicker">
						<c:forEach var="sweetVO" items="${ sweetSrc.all }">
							<option value="${ sweetVO.sweet_id }">${ sweetVO.sweet_des }
						</c:forEach>
					</select>
				    <select id="ice_id" name="ice_id" class="selectpicker">
						<c:forEach var="iceVo" items="${ iceSrc.all }">
							<option value="${ iceVo.ice_id }">${ iceVo.ice_des }
						</c:forEach>
					</select> 
					<select id="quenty" name="quenty" class="selectpicker">
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
                        request.setAttribute("memVo",memVO);
                      %>
                   	<c:if test="${empty protrlist}">
						<button class="btn btn-danger trackButton" type="submit" id="button">
							<span class="glyphicon glyphicon-heart-empty track" title="加入收藏"></span>
						</button>
						<input type="hidden" id="addFaver_id" name="addFaver_id" value="<%=pro_id%>" />
						<input type="hidden" id="action" name="action" value="0" />
						<input type="hidden" id="mem_id" name="mem_id" 	value="${memVo.mem_id}" />
					</c:if>
					<c:set var="isDone" value="0" scope="page"></c:set>
					<c:forEach var="protrVO" items="${protrlist}" varStatus="varStatus">
						<c:if test="${isDone == '0'}">
							<c:if test="${protrVO.product_id  eq pro_id}">
								<button class="btn btn-danger trackButton" type="submit">
									<span class="glyphicon glyphicon-heart track" title="取消收藏"></span>
								</button>
								<input type="hidden" id="addFaver_id" name="addFaver_id" value="<%=pro_id%>" />
								<input type="hidden" id="action" name="action" value="1" />
								<input type="hidden" id="mem_id" name="mem_id" 	value="${memVo.mem_id}" />
						<c:set var="isDone" value="1" scope="page"></c:set>
							</c:if>
							<c:if test="${varStatus.last}">
								<c:if test="${isDone == '0'}">
									<button class="btn btn-danger trackButton" type="submit">
										<span class="glyphicon glyphicon-heart-empty track" title="加入收藏"></span>
									</button>
									<input type="hidden" id="addFaver_id" name="addFaver_id" value="<%=pro_id%>" />
									<input type="hidden" id="action" name="action" value="0" />
									<input type="hidden" id="mem_id" name="mem_id" 	value="${memVo.mem_id}" />
								</c:if>
							</c:if>
						</c:if>
					</c:forEach>
						<%}%>
				</div>
				<%}%>
			</div>
		   <%}%>
		</div>
	</div>
	<div style="height:10%;"></div>
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
						console.log(response);
					},
					error:function(msg, status, thrown){
						console.log(status);
					}
				});
			});
		})
</script>
<script type="text/javascript">
		$(document).ready(function(){
			$(".getOneProduct").on("click",function(event){
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
		                     imageSize:'500x400',
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
<script>
        $('.test').flyto({
            item      : 'div',
            target    : '.abc',
            button    : '.my-btn'
        });
</script>
   
<script type="text/javascript">
function switchFavorite(e) {
	var heart = e.target;
 if (heart.title == "加入收藏") {
		heart.className ="glyphicon glyphicon-heart track";
		heart.title = "取消收藏";
		$(this).parent().next().next().val('0');
} else { 
		heart.className = "glyphicon glyphicon-heart-empty track";
		heart.title = "加入收藏";
		$(this).parent().next().next().val('1');
	}
}
function init() {
	var hearts = document.getElementsByClassName("track");
	for( var i=0; i< hearts.length; i++){
		hearts[i].onclick = switchFavorite;
	}
}
window.onload = init;

$(document).ready(function(){
	$(".trackButton").on("click",function(event){
		$.ajax({
			url:"/CA101G2/faver.do",
			data:{
				addFaver_id:$(this).next().val() ,
				action:$(this).next().next().val(),
				mem_id:$(this).next().next().next().val()
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
<script type="text/javascript">
function switchFavorite(e) {
	var heart = e.target;
 if (heart.title == "加入收藏") {
		heart.className ="glyphicon glyphicon-heart track";
		heart.title = "取消收藏";
		$(this).parent().next().next().val('0');
} else { 
		heart.className = "glyphicon glyphicon-heart-empty track";
		heart.title = "加入收藏";
		$(this).parent().next().next().val('1');
	}
}
function init() {
	var hearts = document.getElementsByClassName("track");
	for( var i=0; i< hearts.length; i++){
		hearts[i].onclick = switchFavorite;
	}
}
window.onload = init;

$(document).ready(function(){
	$(".trackButton").on("click",function(event){
		
	});
})
</script>
<% if (memVO == null) { %>
<script type="text/javascript">
document.getElementsByClassName("cartButton").attr('disabled', false);
</script>
<%}else{ %>
<script type="text/javascript">
document.getElementsByClassName("cartButton").attr('disabled', '');
</script>
<%}%>
<script src="<%=request.getContextPath()%>/front-end/js/flyto.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script> 

<!--  
<script type="text/javascript">
	function getCount(){ 
	  var xhr = new XMLHttpRequest();
	  //設定好回呼函數 
	  xhr.onreadystatechange = function (){
	    console.log("readyState",xhr.readyState);
	  }
	 
	  //===設定好回呼函數 
	  xhr.onload = function (){
	    console.log("loaded : ",xhr.readyState);
	      if(xhr.status == 200){
	        document.getElementById("Count").innerHTML = 
	        xhr.responseText;
	      }else{
	    	  alert(xhr.status);
       }
	  }
     //建立好Post連接
    var url = "PostResponseText.jsp";
    xhr.open("post", url, true);
    xhr.setRequestHeader("content-type","application/x-www-form-urlencoded")
    //送出請求
    var data_count="shopCount=" + document.getElementById("shopCount").value++;
    xhr.send(data_count);

   }//function 
   function init() {
	var shop = document.getElementsByClassName("my-btn");
	for( var i=0; i< shop.length; i++){
		shop[i].onclick = getCount;
	}
  }
  window.onload = init;
</script>
-->    






