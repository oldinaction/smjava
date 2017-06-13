package com.smalle.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smalle.dao.user.UserDao;
import com.smalle.util.TmStringUtils;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HashMap<String, Object> userMap = (HashMap<String, Object>) req.getSession().getAttribute("userSession");
System.out.println("LoginServlet===userMap===>" + userMap);
		if(null == userMap) {
			//如果session用户失效,那么就去cookie冲获取用户信息
			String account = null;
			Cookie[] cookies = req.getCookies();
			if(cookies!=null && cookies.length>0){
				for(Cookie cookie : cookies) {
					if(cookie.getName().equalsIgnoreCase("account")) {
						account = TmStringUtils.dencryption(cookie.getValue(), 5);
System.out.println("LoginServlet===account===>" + account);
					}
				}
				if(TmStringUtils.isNotEmpty(account)) {
					//根据账号去数据表中查询
					userMap = UserDao.findUser(account); //找一个java的加密和解密的两个方法
					if(null != userMap) {
						req.getSession().setAttribute("userSession", userMap);
System.out.println(req.getSession().getAttribute("userSession"));
						resp.sendRedirect("/servletBlog/index");
					}else {
						req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
					}
				}else {
					req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
				}
			}
			
		}else {
			resp.sendRedirect("/servletBlog/index");
		}	
	}
	
	
	
}
