package com.smalle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//注销session
		req.getSession().invalidate();
		//清除Cookie
		/**
		 * 如果需要注销所有的cookie
		 * Cookie[] cookies = request.getCookies();
		 * for (Cookie cookie : cookies) {
		 * 	cookie2.setMaxAge(0);
		 * 	response.addCookie(cookie);
		 * }
		 */
		Cookie cookie = new Cookie("account", null);
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		//页面跳转
		resp.sendRedirect("/servletBlog/login");
	}
	
	
}
