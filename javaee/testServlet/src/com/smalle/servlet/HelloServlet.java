package com.smalle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	//==>继承HttpServlet==>重写HttpServlet中的方法==>web.xml：注册
	
	private static final long serialVersionUID = 1L;
	
	public HelloServlet(){
		System.out.println("我构建了！");
	}
	
	//处理HTTP GET请求
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp); //默认从doGet开始调用,所以在此处调用下面的doPost方法
	}

	//处理HTTP POST请求
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8"); //将响应的数据设成utf-8
		PrintWriter pw = resp.getWriter(); //从相应对象中获取打印流
		pw.write("Hello Servlet!"); //内容中可以输入html标签，浏览器会做出相应的显示
		
		pw.flush();
		pw.close();
		
	}
	
	//销毁servlet实例对象的回调方法
	@Override
	public void destroy() {
		System.out.println("我销毁了！");
	}
	
	//初始化servlet的回调方法。只执行一次，第一次初始化时调用。建议重写这个有参数的init
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("我创建了！");
	}
	
	
	
}
