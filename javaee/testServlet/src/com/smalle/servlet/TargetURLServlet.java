package com.smalle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TargetURLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8"); //将请求的数据设成utf-8
		String password = req.getParameter("password1");
		if(password.equals("123")){
			req.getRequestDispatcher("success.html").forward(req, resp); //请求分派
		}else{
			resp.sendRedirect("fail.html"); //重定向
		}
	
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

