package com.smalle.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class CharacterEncodingFilter implements Filter {
	private FilterConfig config; //定义一个FilterConfig对象来获取init-param里面的值
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {	
		// 获取filter初始化的参数值
		String encoding = config.getInitParameter("encoding");
		
		if (null != encoding && !"".equalsIgnoreCase(encoding)) {
			req.setCharacterEncoding(encoding);
			resp.setCharacterEncoding(encoding);		
System.out.println("CharacterEncodingFilter，字符编码已改成" + encoding +"===请求的地址是：" + ((HttpServletRequest)req).getRequestURI());
		}	
		//把请求和响应传给过滤链中的下一个调用者或servlet
		chain.doFilter(req, resp);
	}
	
	@Override
	public void destroy() {
		
	}

}
