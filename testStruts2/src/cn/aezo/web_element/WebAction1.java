package cn.aezo.web_element;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

//action中访问web元素(request、session、application)
//一般使用此方法(DI/IoC),DI:dependency injection依赖注入,IoC:inverse of control控制反转
public class WebAction1 extends ActionSupport implements RequestAware,SessionAware,ApplicationAware {

	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	
	public String execute() {
		request.put("r1", "r11");
		session.put("s1", "s11");
		application.put("a1", "a11");
		return "success";
	}
	
	//由于实现了RequestAware等接口，所有struts2会自动调用setRequest等方法，里面的参数有Struts2传入(Struts2在拦截器获取了HttpRequest等的所有信息，将他放在相应Map中，此时拿出来传给setRequest即可)
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
	
}
