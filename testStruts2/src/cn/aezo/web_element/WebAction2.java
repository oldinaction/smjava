package cn.aezo.web_element;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//此方法不常用
public class WebAction2 extends ActionSupport {
	private Map request;
	private Map session;
	private Map application;
	
	public String execute() {
		request = (Map)ActionContext.getContext().get("request");
		session = ActionContext.getContext().getSession();
		application = ActionContext.getContext().getApplication();
		
		request.put("r1", "r11");
		session.put("s1", "s11");
		application.put("a1", "a11");
		return "success";
	}
}
