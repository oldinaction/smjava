package com.smalle.servlet;

import java.io.IOException;
import java.rmi.server.ObjID;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smalle.dao.comment.TmCommentDao;
import com.smalle.util.TmStringUtils;


public class CommentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取从index.jsp通过GET方式传来的log表中的id
		String id = req.getParameter("id");
		if(TmStringUtils.isNotEmpty(id) && id.matches("\\d")) { //不为空且匹配为数字
			//通过作用域中的userSession获取userId
			HashMap<String, Object> userMap = (HashMap<String, Object>)req.getSession().getAttribute("userSession");
			String userId = String.valueOf(userMap.get("id"));
			//通过log表中的id和userId在数据库中查询相应的日志内容
			HashMap<String, Object> logMap = TmCommentDao.getLog(Integer.parseInt(id), Integer.parseInt(userId));
			//把获取的日志对象写到作用域中
			req.setAttribute("logMap", logMap);
			
			//通过log表中的id和userId在数据库中获取相应的所有评论
			List<HashMap<String, Object>> commentMaps = TmCommentDao.findComments(Integer.parseInt(id), Integer.parseInt(userId));
			//把获取的日志评论集合对象写到作用域中
			req.setAttribute("commentMaps", commentMaps);
System.out.println("CommentServlet===" + "logMap===" + logMap + "commentMaps" + commentMaps);
			
			//请求分派到日志详情页面
			req.getRequestDispatcher("/WEB-INF/pages/comment.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("../error.html");
		}			
	}
	
}
