package cn.aezo.token;

import com.opensymphony.xwork2.ActionSupport;

public class InputAction extends ActionSupport {
	public String username;
	
	public String execute() {
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
