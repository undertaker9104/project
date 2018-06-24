<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div id="carousel-id" class="carousel slide" data-ride="carousel" style="padding-top:-10%;">
		    <!-- 幻燈片小圓點區 -->
		    <ol class="carousel-indicators">
		        <li data-target="#carousel-id" data-slide-to="0" class=""></li>
		        <li data-target="#carousel-id" data-slide-to="1" class=""></li>
		        <li data-target="#carousel-id" data-slide-to="2" class="active"></li>
		    </ol>
		    <!-- 幻燈片主圖區 -->
		    <div class="carousel-inner">
		        <div class="item">
		            <img id="Q" src="<%=request.getContextPath()%>/front-end/img/images11.jpg" alt="" class="imgslide">
		        </div>
		        <div class="item">
		            <img id="Q" src="<%=request.getContextPath()%>/front-end/img/images12.jpg" alt="" class="imgslide">
		        </div>
		        <div class="item active">
		             <img id="Q" src="<%=request.getContextPath()%>/front-end/img/images13.jpg" alt="" class="imgslide">
		        </div>
		    </div>
		    <!-- 上下頁控制區 -->
		    <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
		    <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
   </div>

