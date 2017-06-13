package com.smalle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取上下配置文件对象
        ServletConfig servletConfig = this.getServletConfig();
        String name = servletConfig.getInitParameter("name"); //获取名字
        String age = servletConfig.getInitParameter("age");  //获取年龄

        resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.write("name: " + name + " ; " + "age: " + age);
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

