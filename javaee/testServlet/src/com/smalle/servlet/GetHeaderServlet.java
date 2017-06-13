package com.smalle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 得到请求头信息
 * <BR>
 * GetHeaderServlet <BR>
 * 创建人:oldinaction <BR>
 * 时间：2014年9月30日 下午9:30:20 <BR>
 * @version 1.0.0
 *
 */
public class GetHeaderServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp); //默认首先进入到doGet，如果在表单中设置method="post"则会直接进入到doPost方法
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8");
		//resp.setContentType("text/html;charset=utf-8"); //这是以html的形式写到浏览器，默认是以txt格式写到浏览器
		resp.setCharacterEncoding("utf-8");
		
		PrintWriter out = resp.getWriter();
		
		//http://localhost:8080/myBlog/head?username=smalle&password=123
		//获取请求的链接地址的相对路径URI
		String url = req.getRequestURI();
		//获取请求的链接地址的绝对路径URL
		String url2 = req.getRequestURL().toString();
		//获取URL参数的值
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		out.println("getRequestURI()获取请求的链接地址的相对路径 === " + url);
		out.println("getRequestURL()获取请求的链接地址的绝对路径 === " + url2);
		out.println("getParameter()获取URL参数username的值=== " + username);
		out.println("getParameter()获取URL参数password的值=== " + password);
		
		//根据名称获取请求头中的某个信息
		//获取主机域名和端口，localhost:8080
		String host = req.getHeader("host");
		String connection = req.getHeader("connection");
		//是否缓存，max-age=0
		String cache_control = req.getHeader("cache-control");
		//请求接收的文件格式，text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
		String accept = req.getHeader("accept");
		//获取客户端浏览器的信息，Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36
		String user_agent = req.getHeader("user-agent");
		//获取客户端的请求的编码，gzip,deflate,sdch
		String accept_encoding = req.getHeader("accept-encoding");
		//获取客户端的语言，zh-CN,zh;q=0.8
		String accept_language = req.getHeader("accept-language");
		out.println("host === " + host);
		out.println("connection === " + connection);
		out.println("cache_control === " + cache_control);
		out.println("accept === " + accept);
		out.println("user_agent === " + user_agent);
		out.println("accept_encoding === " + accept_encoding);
		out.println("accept_language === " + accept_language);
		
		//获取IP地址
		String ipAddress = req.getLocalAddr();
		out.println("ipAddress === " + ipAddress);
		//端口号
		int  sport = req.getServerPort();
		out.println("sport === " + sport);
		//主机
		String hostname = req.getRemoteHost();
		out.println("hostname === " + hostname);
		//获取服务器的根目录
		String path = req.getRealPath("/");
		out.println("path === " + path);
		
		//获取我们的浏览器文本的大小
		int contentLength = req.getContentLength();
		out.println("浏览器文本的大小 === "+contentLength);
		
		out.flush();
        out.close();
        
//		Enumeration<String> headNames = req.getHeaderNames();
//		while(headNames.hasMoreElements()){
//			System.out.println(headNames.nextElement());
//		}
		/*上面的结果为
		host
		connection
		cache-control
		accept
		user-agent
		accept-encoding
		accept-language
		*/
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
