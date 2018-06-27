<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.msgboard.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
      //留言板
      MsgBoardService msgSvc = new MsgBoardService();
      List<MsgBoardVo> listmsg = msgSvc.getFrontAll();
      request.setAttribute("listmsg",listmsg);
        
%>
<% 
    MemberVO memVO = (MemberVO) session.getAttribute("memVO");
    request.setAttribute("memVo",memVO);
%>
<jsp:useBean id="memSrc" scope="page" class="com.mem.model.MemberService" />
<html>
<head>
</head>
<body>
    <% if (memVO != null) { %>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				
				<c:forEach items="${listmsg}" var="msgVo">
					<div class="col-xs-12 col-sm-9 col-sm-offset-2">
						<div class="media">
							<div class="media-left">
							   <img src="data:image/jpg;base64,${memSrc.getOneMem_id(msgVo.mem_id).getBase64Image()}" class="media-object" style="width: 100px; height: 100px">
							</div>
							<div class="media-body">
							    <h4 class="media-heading" style="font-size: 32px"> <b>${memSrc.getOneMem_id(msgVo.mem_id).mem_name}</b></h4>
								<div style="font-size: 32px">${msgVo.msg_des}</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!--  -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12" style="
    height: 500px;
">
				<button type="submit" class="btn btn-warning" data-toggle="collapse"
					    href="#cc1" aria-expanded="false" aria-controls="#cc1">留言發表
					    <span class="glyphicon glyphicon-send"></span>
				</button>
				<div class="collapse" id="cc1">
					<form method="post" action="<%=request.getContextPath()%>/msg.do">
						<div class="form-group">
							<textarea class="form-control" name="msg_des" rows="3"></textarea>
						</div>
						<div class="text-right">
							<button type="submit" class="btn btn-info btn-lg btn-block">送出</button>
							<input type="hidden" name="action" value="add">
							<input type="hidden" name="mem_id" value="${memVo.mem_id}"> 
							<input type="hidden" name="product_id" value="P000001"> 
							<input type="hidden" name="msg_status" value=0>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%}%>
</body>
</html>