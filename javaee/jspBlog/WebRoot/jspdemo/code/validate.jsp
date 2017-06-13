<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	//验证
	String rand = (String)session.getAttribute("code"); //获取numCode.jsp产生的数字验证码
	String stringRand = (String)session.getAttribute("stringCode"); //获取stringCode.jsp产生的数字验证码
	String countRand = (String)session.getAttribute("countCode"); //获取stringCode.jsp产生的数字验证码
	String input = request.getParameter("code");
	if(rand.equalsIgnoreCase(input) || stringRand.equalsIgnoreCase(input) || countRand.equalsIgnoreCase(input)){ 
		out.print("success"); 
	} else{ 
		out.print("<script>alert('请输入正确的验证码！');location.href='test.jsp';</script>"); 
	} 
%> 
