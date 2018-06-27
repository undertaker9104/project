<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>
<%
	AnnouncementService announcementSvc2 = new AnnouncementService();
	List<AnnouncementVO> list2 = announcementSvc2.getAllAd();
	pageContext.setAttribute("list2", list2);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>最新消息</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/front.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<script src="<%=request.getContextPath()%>/front-end/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/jquery-1.6.2.min.js"></script>

</head>
<body>
<c:forEach var="announcementVO" items="${list2}">
<!-- 廣告CSS -->
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;text-decoration:none;}
body{font:12px/180% Arial, Helvetica, sans-serif;}
*html .advbox${announcementVO.ann_id}{position:absolute;top:expression(eval(document.documentElement.scrollTop));}
/* 廣告置左left:0% */
.advbox${announcementVO.ann_id}{width:250px;position:relative;display:none;left:0%;top:0;} 
/* 每個廣告都有關閉按鈕 */
.advbox${announcementVO.ann_id} .advpic${announcementVO.ann_id}{position:relative;overflow:hidden;} 
.advbox${announcementVO.ann_id} .advpic${announcementVO.ann_id} .closebtn${announcementVO.ann_id}{display:block;width:60px;height:22px;line-height:26px;font-size:12px;color:#000;text-indent:12px;overflow:hidden;position:absolute;right:-10px;top:5px;z-index:99;}
</style>
<!--  	廣告關閉 -->
	<script type="text/javascript">
		$(document).ready(function(){
			$(".advbox${announcementVO.ann_id}").show();
			$(".advbox${announcementVO.ann_id}").animate({top:"50%"},1000);
			$(".closebtn${announcementVO.ann_id}").click(function(){
				$(".advbox${announcementVO.ann_id}").fadeOut(500);
			})
		})
	</script>

	<div class="container-fluid">
		<div class="row">
				<div class="list-group advbox${announcementVO.ann_id}">
					<div class="thumbnail advpic${announcementVO.ann_id}">
						<c:if test="${empty announcementVO.ann_img}">
							<img src="<%=request.getContextPath()%>/front-end/img/null.png">
						</c:if> 
						<c:if test="${! empty announcementVO.ann_img}">
							<img src="data:image/png;base64,${announcementVO.getBase64Image()}"  width="70%" height="70%"/>
						</c:if>
						<a href="javascript:void(0);" class="closebtn${announcementVO.ann_id}">關閉</a>
						<div class="caption text-center">
							<h2>${announcementVO.ann_title}</h2>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/announcementFront.do">
								<input type="submit" value="詳情" class="btn btn-danger" >
								<input type="hidden" name="ann_id" value="${announcementVO.ann_id}">
								<input type="hidden" name="action" value="getOne_For_Detail">
							</FORM>
						</div>
					</div>	
				</div>	
		</div>
	</div>
</c:forEach> 	
	
</body>
</html>