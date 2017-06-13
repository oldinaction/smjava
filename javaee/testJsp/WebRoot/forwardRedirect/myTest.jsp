<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>forward跳转和sendRedirect跳转区别</title>

  </head>
  <body>
	知识点：<br />
	&lt;jsp:forward&gt;发送了一次请求，服务器进行跳转。同一个request对象<br />
	&lt;% response.sendRedirect("url"); %&gt;发送了两次请求，客户端自己跳转。是不同的request对象<br /><br />

	获取参数a的值：
	<%
		//下面是进行编码转成，防止中文乱码
		String str = request.getParameter("a");//获取URL中的参数
		byte[] b = str.getBytes("ISO8859-1");//因为此时参数a的编码为ISO8859-1,获取此字符串的字节数组
		String a = new String(b, "UTF-8");//将此字节数组转换成UTF-8编码的字符串
		out.write(a);//输出
	%><br />
	获取参数att的值：<%=request.getAttribute("att") %><br />
	获取参数param的值：<%=request.getParameter("param") %><br />
	
  </body>
</html>
