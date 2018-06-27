<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>
<%
AnnouncementVO announcementVO = (AnnouncementVO) request.getAttribute("announcementVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>增加公告</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
</head>
<body>
<div class="container">
	<div class="row">
				<div class="container col-md-8 col-md-offset-2">
	<br><br>
	<form class="form-horizontal"   METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/announcement.do" enctype="multipart/form-data" name="form1">
		
		
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">公告標題:</label>
			<div class="col-sm-6">
				<input type="TEXT" class="form-control" name="ann_title" size="45" value="${announcementVO.ann_title}" />
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">公告內容:</label>
			<div class="col-sm-6">
				<input type="TEXT" class="form-control" name="ann_des" size="45" value="${announcementVO.ann_des}" />
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">公告時間:</label>
			<div class="col-sm-6">
					<input name="ann_date" id="f_date1" type="text">
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">圖片:	</label>
			<div class="col-sm-6">
				<input type="file" class="form-control" name="ann_img" size="45" value="${announcementVO.ann_img}" onchange="showPreview(this.id,'portrait')" id="file"/>
				<img src="" id="portrait" style="width: 100%; height: 100%; display: block;" />	
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">廣告狀態:	</label>
			<div class="col-sm-6">
				<select size="1" name="ann_status" class="form-control">
					<option value="0" ${(announcementVO.ann_status ==0 ) ? 'selected':''}>
									下架
					</option>
					<option value="1" ${(announcementVO.ann_status ==1 ) ? 'selected':''}>
									上架
					</option>
				</select>
			</div>
		</div>
		
		<input type="hidden" name="action" value="insert">
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<input type="submit" class="btn btn-success" value="送出新增">			
			</div>
		</div>
	</form>
</div>
</div>
</div>
<script type="text/javascript">
		/* 图片预览 */
		function showPreview(fileId, imgId) {
			var file = document.getElementById(fileId);
			var ua = navigator.userAgent.toLowerCase();
			var url = '';
			if (/msie/.test(ua)) {
				url = file.value;
			} else {
				url = window.URL.createObjectURL(file.files[0]);
			}
			document.getElementById(imgId).src = url;
		}
	</script>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date ann_date = null;
  try {
	  ann_date = announcementVO.getAnn_date();
   } catch (Exception e) {
	   ann_date = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=ann_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>
</body>
</html>