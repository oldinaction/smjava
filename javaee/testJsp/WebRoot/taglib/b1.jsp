<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/sm.tld" prefix="sm" %>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>业务的自定义标签</title>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<%
  		List<String> strs = new ArrayList<String>();
  		strs.add("abc");
  		strs.add("def");
  		strs.add("ghi");
  		pageContext.setAttribute("strs", strs);
  	%>
  	
  	<sm:loop var="strs" items="${strs}">
  		索引：${strs_index} === 值：${strs}<br />	
  	</sm:loop>
  
  </body>
</html>
