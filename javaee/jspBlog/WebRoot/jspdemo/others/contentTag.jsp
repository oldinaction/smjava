<%@ page language="java" import="java.util.*,com.smalle.bean.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="sm" uri="/WEB-INF/tld/sm.tld" %>

<!DOCTYPE HTML>
<html>
  <head>  
    <title>业务的自定义标签获取表中的数据</title>
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
	<sm:contentTag var="content">
		${content_index} === ${content.title}
	</sm:contentTag>
		
  </body>
</html>
