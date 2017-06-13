package com.smalle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smalle.dao.comment.TmCommentDao;
import com.smalle.util.TmStringUtils;

public class CommentSavaServlet extends HttpServlet {

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
		
		//表现层只需要传递content和logId两个参数即可
		String content = req.getParameter("content");
		String logId = req.getParameter("logId");
		
		if(TmStringUtils.isNotEmpty(content) && TmStringUtils.isNotEmpty(logId)) {
			HashMap<String, Object> userMap = (HashMap<String, Object>) req.getSession().getAttribute("userSession");
			int userId = Integer.parseInt(String.valueOf(userMap.get("id")));
			int $logId = Integer.parseInt(logId);
			
			TmCommentDao.saveComment(content, userId, $logId);
			
			out.print("success");
		} else {
			out.print("fail");
		}
	}
	
	
}
