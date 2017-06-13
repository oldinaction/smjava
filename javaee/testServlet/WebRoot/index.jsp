<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br><br>
    
    <br>
  	下面是关于web.xml中url-pattern和jsp中的href的测试<br>
  	<input type="button" value="javascript:location.href='pages/page.html'"  onclick="javascript:location.href='pages/page.html'" />
  	<input type="button" value="javascript:location.href='/testServlet/pages/page.html'"  onclick="javascript:location.href='/testServlet/pages/page.html'" /><br>
  	<input type="button" value="widows:location.href='pages/page.html'"  onclick="widows:location.href='pages/page.html'" /><br>
  	点击这个页面会被web.xml拦截
  	<input type="button" value="widows:location.href='pages/urlPatternPage.html'"  onclick="widows:location.href='pages/urlPatternPage.html'" /><br>
  	
  	<br>
	下面是GetParameterServlet的测试<br>
    <form method="post" action="parameter">
	    <input type="text" value="小易" name="username" />
	    <input type="password" value="123456" name="password" /><br />
	    <input type="checkbox" value="寒冰" name="lol" />寒冰
	    <input type="checkbox" value="流浪" name="lol" />流浪
	    <input type="checkbox" value="盖伦" name="lol" />盖伦<br />
	    <input type="submit" value="提交" />
    </form>
	
	<br>
	下面是TargetURLServlet的测试<br>
    <form method="post" action="targetURL">
	    密码：<input type="password" value="" name="password1" />（正确的为123）
	    <input type="submit" value="提交" />
	</form>
   
   	<br>
	下面是CookieServlet的测试<br>
	<input type="button" value="写入Cookie测试"  onclick="javascript:location.href='set'" /><br>
	<input type="button" value="读取Cookie测试"  onclick="javascript:location.href='get'" /><br>
	<input type="button" value="清除Cookie测试"  onclick="javascript:location.href='clear'" /><br>
	
	<br>
	下面是WEB-INF下面的文件安全性的测试<br>
	1、在浏览器的地址栏中输入http://127.0.0.1:8080/testServlet/WEB-INF/inf.jsp时，显示不了此页面<br>
	2、<input type="button" value="直接写href也不能调用"  onclick="javascript:location.href='WEB-INF/inf.jsp'" /><br>
	3、只能写个servlet通过服务器去调用此页面：
	<input type="button" value="写servlet通过服务器调用"  onclick="javascript:location.href='inf'" /><br>
	4、jsp页面的话可以使用在web.xml中写jsp-file的属性，且只能是打开jsp文件，如果为html文件则会出现乱码 
	<input type="button" value="jsp-File"  onclick="javascript:location.href='webinf'" /><br>
  
  	<br>
	下面是监听器的测试<br>
   <input type="button" value="在线人数"  onclick="javascript:location.href='index.html'" />
   
  </body>
</html>
