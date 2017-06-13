package com.smalle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebInf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * 此时注意不要掉了WEB-INF
		 * 不能使用resp.sendRedirect("WEB-INF/inf.html");
		 * 重定向相当于直接在浏览器输入地址http://127.0.0.1:8080/testServlet/WEB-INF/inf.html
		 */
		req.getRequestDispatcher("WEB-INF/inf.html").forward(req,resp);
		
	}	
}
