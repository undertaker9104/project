<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>個人QR-code</title>
<body>
<jsp:include page="/front-end/header.jsp"/>
<div align="center" style="margin-top:90px";>
<img src="data:image/png;base64,${memVO.getImg2(memVO.mem_qrcode)}"/>
</div>	
  <jsp:include page="/front-end/footer.jsp"/>
</body>
</html>