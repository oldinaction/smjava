<%@page import="org.apache.struts2.json.JSONUtil"%>
<%@ page language="java" import="java.util.*,com.smalle.province.*" pageEncoding="UTF-8"%>

<% 
	//获取ajax请求发送过来的省份id参数
	String cityId = request.getParameter("cityId");
	if(null != cityId && !cityId.equals("")) {
		List<HashMap<String, Object>> areas = ProvinceDao.findAreas(Integer.parseInt(cityId));
		/*
		 * JSONUtil.serialize(要转换的对象)将集合对象转换成json格式,如果是单个就是转成json字符串，如果是多个就是json字符串数组
		 * List的样式 [{String=Object,String=Object},{},{}]
		 * Json字符串样式 {String:Object,String:Object}
		 * Json字符串数组样式 [{String:Object,String:Object},{},{}] 这样利于以对象访问属性的方式使用它
		 */
		out.print(JSONUtil.serialize(areas));		
	} else{
		out.print("fail");
	}
%>
