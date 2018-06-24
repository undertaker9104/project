<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body bgcolor="black">
<HR noShade SIZE=1 width=600>

<CENTER>
<BR><BR>
<TABLE border=2 cellPadding=0 cellSpacing=0 width=400>

  <TR>
      <TD align="center" background="<%=request.getContextPath()%>/front-end/img/blue.gif" height="250" width="400">
        <FONT color="#9c0000" size="+2">系統忙線中，請稍後再撥。</FONT>
        <BR>      
        <a href="<%=request.getContextPath()%>/front-end/index.jsp" target="parent">
           <INPUT type="submit" name="回去" value="回去重試">
        </a>
     </TD>
  </TR>

</TABLE>
<BR><BR>
 <HR noShade SIZE=1 width=600>
</CENTER>
</body>
</html>