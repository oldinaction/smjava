<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %><!-- 导入struts2标签，标签前缀为s -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户名输入产生错误</title>

  </head>
  
  <body>

	用户名输入产生错误<br />
	<s:fielderror fieldName="myError"/><!-- 使用struts2标签,获取自己设定的错误信息 -->
	<br /><br />
	
	<s:property value="errors.myError[1]"/><!-- 获取debug模式中的Value Stack和Stack Context中的值 -->
	
	<s:debug></s:debug><!-- 使用debug模式 -->
	
  </body>
</html>
