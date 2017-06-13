<%@page import="cn.smalle.util.SmStringUtils"%>
<%@page import="cn.smalle.dao.content.ContentDao"%>
<%@page import="cn.smalle.bean.Content"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%
	String blogTitle = request.getParameter("title");
	String blogContent = request.getParameter("content");
	String tag = request.getParameter("tag");
	//System.out.println(blogTitle+"=="+blogContent+"=="+tag+"=="+updatetime);
	
	//判断标题和内容是否为空
	if(SmStringUtils.isNotEmpty(blogTitle) && SmStringUtils.isNotEmpty(blogContent)) {
		Content content = new Content();
		content.setTitle(blogTitle);
		content.setContent(blogContent);
		content.setUserId(1);
		content.setTag(tag);
		content.setStatus(1);
		content.setIsDelete(0);	
		
		boolean flag = ContentDao.saveContent(content);
		if(flag == true) {
			out.print("success");
		} else {
			out.print("fail");
		}
		
	}
%>



