package com.smalle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Session就相当于特殊的Cookie，Cookie中的JSESSIONID就是此session的ID
 * <BR>
 * SetSession <BR>
 * @author oldinaction
 * @date 2014年10月22日-下午4:51:42
 * @version 1.0.0
 *
 */
public class SetSession extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//访问的话，直接在浏览器中输入 http://127.0.0.1:8080/testServlet/setSession?name=smalle
		String name = req.getParameter("name");
		HttpSession session = req.getSession(); //获取HttpSession对象
		session.setAttribute("name", name); //写入session,实际上时写在内存中了，在cookie保存相应的ID
		/** 跳转到getSession查看保存的值。
		 *  此处的href必须为/testServlet/getSession或者为getSession才会跳转到http://127.0.0.1:8080/testServlet/getSession
		 *  带/表示绝对路径，不带/表示相对路径(相对于根目录 http://127.0.0.1:8080/项目名)
		 */	
		resp.getWriter().write("<a href='getSession'>click view</a>");
		//resp.getWriter().write("<a href="+ resp.encodeURL("getSession") +">click view</a>");//将sessionID附在URL后面
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
