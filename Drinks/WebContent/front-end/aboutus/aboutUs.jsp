<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.aboutus.model.*"%>

<%
	AboutUsService aboutUsSvc = new AboutUsService();
    List<AboutUsVO> list = aboutUsSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>關於我們</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/front.css" rel="stylesheet" type="text/css" />
</head>
<jsp:include page="/front-end/header.jsp" />
<body>
    <div id="content" class="container">
        <div class="row text-left">
            <div class="col-md-6 col-sm-12 text">
                <h3 class="title">| 關於我們 <small>About Us</small></h3>
                	<c:forEach var="aboutUsVO" items="${list}">
                		<p>${aboutUsVO.about_des}</p>
                	</c:forEach>
            </div>
            <div class="col-md-6 col-sm-12 pic">
                <img src="<%=request.getContextPath()%>/front-end/img/aboutus01.jpg" class="img-responsive">
            </div>
        </div>
        <div class="row text-center">
            <div class="col-md-6 col-sm-12 pic">
                <iframe width="585" height="400" frameborder="0" style="border:0"
                    src="https://www.google.com/maps/embed/v1/place?q=place_id:ChIJpTLHUOojaDQRSeyfbuZuXhs&key=AIzaSyBI21jRzEJsFR1_fd5meKzgw-dfZAarSuM" allowfullscreen>
                </iframe> 
            </div>
            <div class="col-md-6 col-sm-12 text">
                <h3 class="title">| 店家資訊 <small>Store Information</small></h3>
                <ul class="list">
                <c:forEach var="aboutUsVO" items="${list}">
                    <li>營業時間：${aboutUsVO.about_time}</li>
                    <li>電話：${aboutUsVO.about_phone}</li>
                    <li>客服電話：0800-000-000</li>
                    <li>地址：${aboutUsVO.about_add}</li>
                </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
<jsp:include page="/front-end/footer.jsp" />
</html>





















<!--     <div id="content"> -->
<!--         <div> -->
<!--             <p><h1 align="center">關於清涼一夏</h1></p> -->
<!--         </div> -->

<!--                 <div class="notice"> -->
<!--                     <h3>成功緣起</h3> -->

<!--                     <div class="content"> -->
<%--                        <c:forEach var="aboutUsVO" items="${list}"> --%>
<!-- 						<tr> -->
<%-- 							<h3><td>${aboutUsVO.about_des}</td> --%>
<!-- 						</tr> -->
<%-- 						</c:forEach> --%>
<!--                     </div> -->
<!--                                                                                                                                         </div> -->
<!--                 <div > -->
<!-- 					<img src="img/aboutus01.jpg"  > -->
<!--                 </div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               </ol> -->
<!--     </div> -->

<!-- <br/> -->
<!--     <div> -->
<!--                 <img src="img/aboutus02.jpg" width="50%" height="50%"> -->
                            
<!--     </div> -->

<!-- <iframe width="600" height="450" frameborder="0" style="border:0" -->
<!-- src="https://www.google.com/maps/embed/v1/place?q=place_id:ChIJpTLHUOojaDQRSeyfbuZuXhs&key=AIzaSyBI21jRzEJsFR1_fd5meKzgw-dfZAarSuM" allowfullscreen> -->
<!-- </iframe>  -->

<!-- <footer> -->
<!--     <div class="container"> -->

<!--         <p class="copy">Copyright © 2018 CoolSummer International Co., Ltd.</p> -->
<!--         <p class="address">地址：320桃園市中壢區中大路300號 </p> -->
<!--         <p class="addressM">客服電話：0800-000-000</p> -->
<!--     </div> -->
<!-- </footer>             -->

<!-- </body> -->
<!-- </html> -->