<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %><!-- 导入struts2标签，标签前缀为s -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>带参数的结果</title>

  </head>
  
  <body>
	一次request只有一个值栈valueStack；以forward的形式跳转(dispatcher/chain)时，request没变，因此valueStack不变。而客户端跳转则值栈改变<br /><br />
	valueStack是request对象中的相关信息，而此时是客户端跳转过来的，由于直接跳转的是jsp没有再经过action，所以值栈为空，
    url上的带的参数在request域对象中是取不到到。只能在上下文中通过parameters获取<br /><br />
	
	from ValueStack: <s:property value="t" /><br />
	from StackContext: <s:property value="#parameters.t"/><br />
	
	<s:debug></s:debug>
	
  </body>
</html>
