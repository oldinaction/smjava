<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>类型转换(实际是struts2内置了拦截器)</title>

  </head>
  
  <body>

	id：<s:property value="id"/><br /><!-- 访问http://127.0.0.1:8080/testStruts2/others/dataformat?id=1 -->
	date：<s:date name="date" format="yyyy-MM-dd HH:mm:ss"/><br /><!-- 访问http://127.0.0.1:8080/testStruts2/others/dataformat?date=2015-11-11 11:11:11 -->
	interests：<s:property value="interests" /><br /><!-- 访问http://127.0.0.1:8080/testStruts2/others/dataformat?interests=paint&interests=math -->
	map：<s:property value="map" /><br /><!-- 访问http://127.0.0.1:8080/testStruts2/others/dataformat?map['username']=smalle -->

  </body>
</html>
