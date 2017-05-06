package cn.aezo.register.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.aezo.register.dto.UserRegisterInfo;
import cn.aezo.register.model.User;
import cn.aezo.register.service.UserManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component("u")//如果使用struts产生action的bean，则不需要写。如果使用spring产生action的bean时，自己批注component起名时不能和其他的bean冲突，此时有个User模型，所有起名不能为user
@Scope("prototype")//如果使用struts产生的action默认就是prototype类型的。如果使用spring产生action的bean时，就要注明prototype。prototype每次创建新的对象。spring默认创建的bean是singleton单例，这样只会创建一个Action对象，每次访问都是同一个Action对象，数据不安全
public class UserAction extends ActionSupport implements ModelDriven<UserRegisterInfo> {
	private UserManager userManager;
	private UserRegisterInfo info;//dto数据转化层中的UserRegisterInfo对象就减少了Action中有过多的页面传过来的属性。
	private List<User> users;
	private User user;
	
	//实现ModelDriven必须重写的方法。Struts2把页面传过来的属性值通过set方法注入到info这个数据模型中，而注入的过程Struts2首先要拿到这个模型
	@Override
	public UserRegisterInfo getModel() {
		if(info == null){
			info = new UserRegisterInfo();
		}
		return info;
	}
	
	public String execute() throws Exception {
		User user  = new User();
		user.setUsername(info.getUsername());
		user.setPassword(info.getPassword());
		boolean exists = userManager.exists(user);
		if(!exists) {
			userManager.add(user);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	public String list() {
		this.users = this.userManager.getUsers();
		return "list";
	}
	
	public String load() {
		this.user = this.userManager.loadById(info.getId());
		return "load";
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	
	@Resource(name="userManager")
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public UserRegisterInfo getInfo() {
		return info;
	}

	public void setInfo(UserRegisterInfo info) {
		this.info = info;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
