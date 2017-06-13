package com.smalle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetSession extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(); //获取HttpSession对象;req.getSession(true);获取session,如果没有就创建一个
		String name = (String)session.getAttribute("name");
		resp.getWriter().write("保存的值为===" + name);
		//这两个\n都能显示换行
		resp.getWriter().write('\n'+ "\nID为===" + session.getId() + "===他就是Cookie下的JSESSIONID的值");
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
