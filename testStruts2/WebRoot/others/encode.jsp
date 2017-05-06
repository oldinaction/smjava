<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编码问题</title>

  </head>
  
  <body>

	<form action="/testStruts2/others/encodeForm" method="post">
		姓名(可输入中文)：<input type="text" name="name" />
		<input type="submit" value="提交">请在后台查看参数值
	</form>
	
  </body>
</html>
