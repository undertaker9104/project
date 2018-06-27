<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>
<%
	AnnouncementVO announcementVO = (AnnouncementVO) request.getAttribute("announcementVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>公告資料修改 </title></head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
<body>
	<br><br>
	<div class="container">
			<div class="row">
				<div class="container col-md-8 col-md-offset-2">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/announcement.do" enctype="multipart/form-data" name="form1">
						<div class="form-group">
							<td>公告編號:<font color=red><b>*</b></font></td>${announcementVO.ann_id}
						</div>
						<div class="form-group">
							<label>公告標題:</label>
							<input type="text" name="ann_title" size="45" value="${announcementVO.ann_title}" class="form-control">
						</div>
						<div class="form-group">
							<label>公告內容:</label>
							<input type="text" name="ann_des" size="45" value="${announcementVO.ann_des}" class="form-control">
						</div>
						<div class="form-group">
							<label>公告時間:</label>
							<input name="ann_date" id="f_date1" type="text" class="form-control">
						</div>
						<div class="form-group">
							<label>圖片:</label>
							<input type="file" name="ann_img" size="45" value="${announcementVO.ann_img}" class="form-control" onchange="showPreview(this.id,'portrait')" id="file">
							<%if(announcementVO != null){ %>
								  <img id="preview_progressbarTW_img" src="<%=request.getContextPath()%>/front-end/img/null.png">
							 <% } else { %>
							    <img id="preview_progressbarTW_img" src="#">
							 <% } %>							
						</div>
						<div class="form-group">
							<label>廣告狀態:</label>
							 <select size="1" name="ann_status" class="form-control">
							<option value="0"
								${(announcementVO.ann_status ==0 ) ? 'selected':''}>下架
							</option>
							<option value="1"
								${(announcementVO.ann_status ==1 ) ? 'selected':''}>上架
							</option>
							</select>
						</div>
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="ann_id" value="<%=announcementVO.getAnn_id()%>">
						<input type="submit" class="btn btn-success" value="送出修改">
					</FORM>
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
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
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
<link rel="stylesheet" type="text/css" href="../front-end/datetimepicker/jquery.datetimepicker.css" />
<script src="../front-end/datetimepicker/jquery.js"></script>
<script src="../front-end/datetimepicker/jquery.datetimepicker.full.js"></script>

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
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>
</body>
</html>
