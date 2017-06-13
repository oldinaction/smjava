package com.smalle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smalle.dao.user.UserDao;
import com.smalle.util.TmStringUtils;

/**
 * 利用Ajax实现登陆
 * <BR>
 * JqueryAjaxServlet <BR>
 * 创建人:oldinaction <BR>
 * 时间：2014年10月21日-下午9:55:26 <BR>
 * @version 1.0.0
 *
 */
public class LoginCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String account = req.getParameter("account"); //获取登陆框的账号	
		String password = req.getParameter("password"); //获取登陆框的账号
		
		if(TmStringUtils.isNotEmpty(account) && TmStringUtils.isNotEmpty(password)) {
			//根据获取的数据查询表中的数据
			HashMap<String, Object> map = UserDao.findUser(account, password);
			if(null == map) {
System.out.println("LoginServlet=== ajax fail");
				out.print("fail");	
			}else {
System.out.println("LoginServlet=== ajax success");
				out.print("success"); //如果此处用out.println("success");在login.jsp中获取的数据就会多个换行符
				/**
				 * 写入数据到Session
				 * 在jsp页面可以通过EL表达式获取${userSession.account}
				 */
				HttpSession httpSession =  req.getSession();
				httpSession.setAttribute("userSession", map);
				
				//写入数据到Cookie(如果标示符为1)
				String cookieMark = req.getParameter("cookieMark");
				if(TmStringUtils.isNotEmpty(cookieMark) && cookieMark.equals("1")) {
					Cookie cookie = new Cookie("account", TmStringUtils.encryption(account, 5));
					cookie.setMaxAge(7*24*60*60); //设置存放时效是7天
					resp.addCookie(cookie);
				}
			}		
		}else {
			out.print("fail");
		}
		
		out.flush();
		out.close();
		
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
