<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/sm.tld" prefix="sm" %>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>单标签-小写转大写</title>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	abc转成大写为 === <sm:toUpper str="abc"/>(这是直接在类中输出的)<br />
 	abc转成大写为 === ${string}(这是放在作用域中的)
  </body>
</html>
