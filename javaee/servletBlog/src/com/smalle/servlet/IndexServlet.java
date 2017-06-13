package com.smalle.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smalle.dao.log.TmLogDao;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * 4:在IndexServlet中调用查询用户发表的日记 findLogs()
		 * 注意是req.getSession().getAttribute() 而不是req.getAttribute()
		 */
		HashMap<String, Object> map = (HashMap<String, Object>)req.getSession().getAttribute("userSession");
System.out.println("IndexServlet===" + map);
		if(null == map) {
			resp.sendRedirect("login");
		}else {			
			List<HashMap<String, Object>> logMaps = TmLogDao.findLogs(Integer.parseInt(String.valueOf(map.get("id")))); //根据user表中的id查询
				//5:将查询出来的日记信息放入到作用域中req.setAttribute()
				req.setAttribute("logMaps", logMaps);
				//${作用域的key}===${logMaps}
				req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
		}	
	}
	
		
}
