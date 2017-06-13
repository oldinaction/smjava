<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String age = request.getParameter("age");
	String sex = request.getParameter("sex");
	
	StringBuffer hobbysBuf = new StringBuffer();
	String[] hobbys = request.getParameterValues("hobbys");
	for(String str : hobbys){
		hobbysBuf.append(str + " ");
	}
	
	String description = request.getParameter("description");
	
	//String weibo = request.getParameter("weibo");
	
	StringBuffer formdata = new StringBuffer();
	formdata.append("用户名===" + username);
	formdata.append("密码===" + password);
	formdata.append("年龄===" + age);
	formdata.append("性别===" + sex);
	formdata.append("兴趣===" + hobbysBuf);
	formdata.append("个人说明===" + description);
	
	//formdata.append("微博===" + weibo);

System.out.println(formdata);
	//out.print(formdata);
	out.print("success");
%>

