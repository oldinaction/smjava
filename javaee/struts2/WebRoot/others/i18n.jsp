<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>i18n国际化</title>

  </head>
  
  <body>
  	<a href="?request_locale=en_US&username=smalle">en</a>
  	<a href="?request_locale=zh_CN&username=smalle">cn</a><br />
  	
	修改浏览器语言即可展现不同的语言！<br />
	<s:property value="getText('welcome.string')"/><!-- 调用的Action的方法，实际中是ActionSupport的方法 -->

	<br />
	<s:property value="getText('global.string')"/>

	<br /><br />
	处理资源文件中的参数,访问的链接中带参数(或者通过表单传值)<br />
	<s:text name="param.string">
		<s:param value="username"></s:param>
	</s:text>
	
	
  </body>
</html>
