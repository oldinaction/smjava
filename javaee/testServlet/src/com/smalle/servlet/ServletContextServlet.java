package com.smalle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		PrintWriter pw = resp.getWriter();
		
		String encoding = application.getInitParameter("encoding");//获取web.xml中<context-param>中的相应参数
		resp.setCharacterEncoding(encoding);
		pw.println("encoding的值为" + encoding + " ， 此时可以显示中文说明获取encoding成功");
		String url = application.getRealPath("index.jsp");
		pw.println("index.jsp的路径为： " + url);
		
		Integer accessId = (Integer) application.getAttribute("accessId");
		if(accessId == null) {
			accessId = new Integer(0);
		} else {
			accessId = new Integer(accessId.intValue() + 1);
		}
		application.setAttribute("accessId", accessId);
		pw.println("您是第"+ accessId +"个访问此application");//可以通过不同的浏览器访问
	}

	@Override
	public void destroy() {
		System.out.println("我销毁了！");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("我创建了！");
	}
	
}

