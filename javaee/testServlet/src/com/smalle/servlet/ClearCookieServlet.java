package com.smalle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearCookieServlet extends HttpServlet{
	
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
		pw.println("清除Cookie测试");
		Cookie cookie = new Cookie("username", null);
		cookie.setMaxAge(0);
		//cookie.setPath("/");
		resp.addCookie(cookie);
		/**
		 * 如果需要注销所有的cookie
		 * Cookie[] cookies = request.getCookies();
		 * for (Cookie cookie : cookies) {
		 * 	cookie2.setMaxAge(0);
		 * 	//cookie.setPath("/");
		 * 	response.addCookie(cookie);
		 * }
		 */
		pw.println("Cookie已经清除，关闭浏览器之后将不会再有此cookie了");
		
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
