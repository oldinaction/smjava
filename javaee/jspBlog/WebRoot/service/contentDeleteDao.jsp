<%@page import="cn.smalle.util.SmStringUtils"%>
<%@page import="cn.smalle.dao.content.ContentDao"%>
<%@page import="cn.smalle.bean.Content"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%
	String id = request.getParameter("id");
	if(SmStringUtils.isNotEmpty(id)) {		
		boolean flag = ContentDao.deleteContent(Integer.parseInt(id));
		if(flag == true) {
			out.print("success");
		} else {
			out.print("fail");
		}
		
	}
%>



