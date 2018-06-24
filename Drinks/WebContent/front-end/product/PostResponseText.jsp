<%@page contentType="text/html;charset=utf-8"%>
<%
  Integer shopCount = Integer.parseInt(request.getParameter("shopCount"));
  request.getSession().setAttribute("shopCount", shopCount);
%>	 
${shopCount}

