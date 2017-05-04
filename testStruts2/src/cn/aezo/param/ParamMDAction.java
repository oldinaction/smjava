package cn.aezo.param;

import cn.aezo.param.model.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//使用模型驱动ModelDriven接收参数,实际中很少使用
public class ParamMDAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();
	
	public String getParam() {
		System.out.println(user.getName());//不需要实例化，Struts2会自动实例化
		return "success";
	}
	
	@Override
	public User getModel() {
		return user;
	}
	
}
