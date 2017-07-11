<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>upload</title>
   

  </head>
  
  <body>

	<form action="upload.do"  method="post" enctype="multipart/form-data">
		<input type="file" name="file" />
		<input type="submit" />
	</form>

  </body>
</html>
