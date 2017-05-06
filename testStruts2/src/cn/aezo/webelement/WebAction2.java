package cn.aezo.webelement;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

//此方法不常用
public class WebAction2 extends ActionSupport {
	private Map request;
	private Map session;
	private Map application;

	public WebAction2() {
		request = (Map)ActionContext.getContext().get("request");
		session = ActionContext.getContext().getSession();
		application = ActionContext.getContext().getApplication();
	}
	
	public String execute() {
		request.put("r1", "r11");
		session.put("s1", "s11");
		application.put("a1", "a11");
		return "success";
	}
}
