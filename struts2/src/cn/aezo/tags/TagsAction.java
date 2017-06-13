package cn.aezo.tags;

import com.opensymphony.xwork2.ActionSupport;

public class TagsAction extends ActionSupport {
 
	private String username;
	private String password;
	
	public String execute() {
		this.addFieldError("myError", "myErrorMessage");
		return "success";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
