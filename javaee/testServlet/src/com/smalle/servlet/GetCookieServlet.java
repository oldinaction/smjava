package com.smalle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCookieServlet extends HttpServlet{
	
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
		pw.println("读取Cookie测试");
		
		Cookie[] cookies =  req.getCookies(); //获取请求Cookie数组
		if(cookies != null) {
			for(Cookie cookie : cookies) { //遍历cookie信息
				pw.println("Cookie名: " + cookie.getName());
				pw.println("Cookie值: " + cookie.getValue());
			}		
			pw.println("从客户端只能获取key和value，利用getMaxAge()等方法是获取不到相应的信息的");	
		}else {
			pw.println("您还没有Cookie值");
		}
		
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
