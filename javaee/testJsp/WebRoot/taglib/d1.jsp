<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/sm.tld" prefix="sm" %>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>BodyTagSupport标签示例</title>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 	<sm:LoopBody count="1">abc</sm:LoopBody><br />
 	<sm:LoopBody count="1" flag="0">abc</sm:LoopBody><br />
 	<sm:LoopBody count="2" >abc</sm:LoopBody><br />
  </body>
</html>
