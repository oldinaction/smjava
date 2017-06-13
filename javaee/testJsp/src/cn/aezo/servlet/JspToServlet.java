package cn.aezo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspToServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8"); //将响应的数据设成utf-8
		PrintWriter pw = resp.getWriter(); //从相应对象中获取打印流
		pw.write("JSP跳转到了Servlet了"); //内容中可以输入html标签，浏览器会做出相应的显示
		
		pw.flush();
		pw.close();
	}

}
