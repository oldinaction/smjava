<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head> 
    <title>jsp获取本地时间</title>      
  </head>
  
  <body>
   现在的时间是：
  <%--
  	这个注释不会被翻译到.java的文件中
  --%>
  <!--此注释在.java中使用out.write()输出，在html源码中会显示-->  
  <% 
  Date now = new Date(); 
  //这个注释会被翻译到.java的文件中，在html中源码不显示

  /*
   *这个注释也会被翻译到.java的文件中，在html中源码不显示
   */
  %>
  
  <%=now.toLocaleString()%> <!-- 其中不能加分号，因为最终会被翻译成out.print(now.toLocaleString()); -->
  
  </body>
</html>
