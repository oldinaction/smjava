package com.smalle.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截页面，看session中是否有值 <BR>
 * 即是否登陆，如果没登陆就跳转到登陆页面，如果登陆就doFilter跳出此拦截器 <BR>
 * (若后面还有拦截器就会跳到下一个拦截器，若没有就正常访问页面) <BR>
 * <BR>
 * LoginFilter <BR>
 * @author oldinaction
 * @date 2014年10月23日-下午4:09:22
 * @version 1.0.0
 *
 */
public class LoginFilter implements Filter {
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//由于HttpServletRequest才能获取HttpSession，所以此处将req向下转型为request
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession httpSession = request.getSession();
		Object object = httpSession.getAttribute("user");
System.out.println("session的值" + object + "===请求地址：" + request.getRequestURI() + "===LoginFilter");
		if(null == object) {
System.out.println("session中无user===" + object + "===LoginFilter");
		/**
		 * 千万不能加response.sendRedirect("/WEB-INF/pages/login.jsp"); 
		 * 否则会出现 网页中包含重定向循环 的提示
		 * 过程解析：session有值-跳转到/pages/index.jsp-被CharacterEncodingFilter拦截-跳转到下个拦截器LoginFilter
		 * -session有值-跳转到/pages/index.jsp-被CharacterEncodingFilter拦截
		 * 由此看出出现了循环
		 */
		req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);	
		}else {
			chain.doFilter(request, response);
		}	
		
	}

	@Override
	public void destroy() {
		
	}
}

