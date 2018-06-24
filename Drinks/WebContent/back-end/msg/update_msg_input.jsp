<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.msgboard.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<%
   MsgBoardVo msgVo = (MsgBoardVo) request.getAttribute("msgVo");
   request.setAttribute("msgVo",msgVo);
%>
<!DOCTYPE html>
<html>
<head>
<title>Title Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
</head>

<body>
	<div class="container" style="margin-top: 10%;">
		<form class="well form-horizontal" action="<%=request.getContextPath()%>/msg.do" method="post">
			<fieldset>
				<legend class="text-center" style="font-size: 20px;">
					<b>修改留言板</b>
				</legend>
				<div></div>
				<div class="form-group">
					<label class="col-md-4 control-label">商品狀態</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"> 
							<i class="glyphicon glyphicon-comment"></i>
							</span>
							<select size="1" name="msg_status" class="form-control">
								<option value=0 ${(msgVo.msg_status ==0 ) ? 'selected':''}>
									正常
								</option>
								<option value=1 ${(msgVo.msg_status ==1 ) ? 'selected':''}>
									刪除
								</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<input type="hidden" name="action" value="update"> 
						<input type="hidden" name="msg_id" value="${ msgVo.msg_board_id }"> 
				  	    <button type="submit" class="btn btn-warning">
							修改確認<span class="glyphicon glyphicon-send"></span>
						</button>
					</div>
				</div>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</fieldset>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
</body>
</html>