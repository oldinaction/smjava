package com.smalle.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{ //实现接口时注意提示的说明，不要选错了Filter。要选javax.servlet.Filter，而不是java.util.logging.Filter
	
	private FilterConfig config;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 获取filter初始化的参数值
 		String encoding = config.getInitParameter("encoding");
		if (null != encoding && !"".equalsIgnoreCase(encoding)) {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
			System.out.println("从拦截器获得的参数encoding = " + encoding + ", 在servlet执行之前已经设置了字符编码哦。");
		}
		// 把请求和响应传给过滤链中的下一个调用者或servlet
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
	
}
