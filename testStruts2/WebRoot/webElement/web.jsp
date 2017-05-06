<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %><!-- 导入struts2标签，标签前缀为s -->


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>action中访问web元素(request、session、application)</title>

  </head>
  
  <body>
	
	<!-- 第一种方法是通过Struts2的标签获取debug模式中显示的Stack Context(又称ActionContext，在value中填写"#key")的值 -->
	<s:property value="#request.r1"/> | <%=request.getAttribute("r1") %><br />
	<s:property value="#session.s1"/> | <%=session.getAttribute("s1") %><br />
	<s:property value="#application.a1"/> | <%=application.getAttribute("a1") %><br />
	
	<s:debug/>

  </body>
</html>
