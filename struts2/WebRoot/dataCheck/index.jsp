<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>数据校验</title>

  </head>
  
  <body>

	<form action="/testStruts2/check/check" method="post">
		姓名：<input type="text" name="name" />
		<input type="submit" value="提交">(正确的用户名是admin，输入其他查看错误信息)
	</form>
	
  </body>
</html>
