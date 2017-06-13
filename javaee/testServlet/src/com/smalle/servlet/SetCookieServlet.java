package com.smalle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCookieServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {	
		
		PrintWriter pw = resp.getWriter();	
		pw.println("写入Cookie测试");
		
		Cookie cookie = new Cookie("username", "smalle"); //创建一个Cookie实例
		cookie.setMaxAge(60); //单位是秒,设置有效时间为1分钟。最大可以设置Integer.MAX_VALUE
		resp.addCookie(cookie); //往响应流中写入Cookie
	
		pw.println("设置有效时间为1分钟，如果不清除cookie，即使关闭了浏览器，只要在60内访问仍可以访问到");	
		pw.flush();
		pw.close();	
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}
	
}
