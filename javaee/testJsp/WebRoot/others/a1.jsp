<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>JSP动态包含实例</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<%-- JSP动态包含可以传递参数 --%>
  	先把user=小易传给a2.jsp,那么a2.jsp中的EL表达式输出的结果就是:(下面的数据是a2.jsp从a1.jsp中获取的数据)<br />
	<%request.setCharacterEncoding("utf-8");%><%-- 无此行则获得的"小易"出现乱码 --%>
    <jsp:include page="a2.jsp">
    	<jsp:param value="小易" name="user"/>
    </jsp:include>
  </body>
</html>
