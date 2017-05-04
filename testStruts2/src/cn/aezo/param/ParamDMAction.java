package cn.aezo.param;

import cn.aezo.param.model.User;

import com.opensymphony.xwork2.ActionSupport;

//使用域模型DomainModel接收参数,实际中一般使用这种方法
public class ParamDMAction extends ActionSupport {

	private User user;
	
	public String getParam() {
		System.out.println(user.getName());//不需要实例化，Struts2会自动实例化
		return "success";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
