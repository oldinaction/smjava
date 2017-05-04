package cn.aezo.web_element;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

//此方法不常用
public class WebAction4 extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	
	public String execute() {
		request.setAttribute("r1", "r11");
		session.setAttribute("s1", "s11");
		application.setAttribute("a1", "a11");
		return "success";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
		this.application = session.getServletContext();
	}
	

	
}
