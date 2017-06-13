package cn.aezo.webelement;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//此方法不常用（注意：包名不能有下划线）
public class WebAction3 extends ActionSupport {

	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	
	public WebAction3() {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		application = session.getServletContext();
	}
	
	public String execute() {
		request.setAttribute("r1", "r11");
		session.setAttribute("s1", "s11");
		application.setAttribute("a1", "a11");
		return "success";
	}
	
}
