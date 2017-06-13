<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>testJsp测试目录</title>
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
    1、JSP简介<br />
    ==》实例：<a href="/testJsp/jspjianjie/1.jsp">获取本地时间</a> <!-- 此处testJsp注意大小写，连接地址识别目录的大小写 -->
    
    <br /><br />
    2、out对象实例<br />
    ==》实例1：
    <a href="/testJsp/out/a1.jsp">a1.jsp</a>
    <br />
    ==》实例2：利用out.flush()刷新一下
    <a href="/testJsp/out/b1.jsp">b1.jsp</a>
    <br />
    ==》实例3：设置buffer="none"
    <a href="/testJsp/out/c1.jsp">c1.jsp</a>
    <br />
    ==》实例4：
    <a href="/testJsp/out/d1.jsp">d1.jsp</a>
    
    <br /><br />
    3、pageContext对象实例<br />
    ==》实例1：pageContext对象只针对本页面
    <a href="/testJsp/pagecontext/a1.jsp">a1.jsp</a>
    <a href="/testJsp/pagecontext/a2.jsp">a2.jsp</a> 
    <br />
    ==》实例2：pageContext操作ServletRequest域对象
    <a href="/testJsp/pagecontext/b1.jsp">先访问b1.jsp把对象放到域中</a>
    <a href="/testJsp/pagecontext/b2.jsp">然后访问b2.jsp获取</a> 
    <br />
    ==》实例3：pageContext的findAttribute方法
    <a href="/testJsp/pagecontext/c1.jsp">方式一：重定向访问</a>
    <a href="/testJsp/pagecontext/c2.jsp">方式二：转发访问</a>
    
    <br /><br />
    4、jsp动作include实例
    ==》实例：<a href="/testJsp/others/a1.jsp">动态包含传递参数</a>
    
    <br /><br />
    5、jsp:useBean实例(javaBean)
    ==》实例：<a href="/testJsp/others/javaBean.jsp">jsp:Bean实例</a>
    
    <br /><br />
    6、EL表达式
    ==》实例：<a href="/testJsp/others/el.jsp">EL表达式</a>
    
    <br /><br />
    7、自定义标签
    ==》实例1：<a href="/testJsp/taglib/a1.jsp">函数的自定义标签</a><br />
    ==》实例2：<a href="/testJsp/taglib/b1.jsp">业务的自定义标签</a><br />
    ==》实例3：<a href="/testJsp/taglib/c1.jsp">单标签</a><br />
    ==》实例4：<a href="/testJsp/taglib/d1.jsp">BodyTagSupport</a><br />
    ==》实例5：<a href="/testJsp/taglib/c.jsp">c标签</a><br />
        
    <br /><br />
    8、forward和sendRedirect跳转 ：<a href="/testJsp/forwardRedirect/forward.jsp?a=1">forward跳转</a>、<a href="/testJsp/forwardRedirect/redirect.jsp?a=1">sendRedirect跳转</a>
    
    <br /><br />
    9、JSP和Servlet的通信：<a href="/testJsp/jspAndServlet/jspToServlet.jsp">JSP跳转到Servlet</a>、<a href="/testJsp/servlet/servletToJsp">Servlet跳转到JSP</a>
     
    
     
  </body>
</html>
