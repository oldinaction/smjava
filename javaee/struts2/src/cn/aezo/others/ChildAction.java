package cn.aezo.others;

import com.opensymphony.xwork2.ActionSupport;

public class ChildAction extends ActionSupport {
	private int id;

	public String execute() {
		if(id == 1) {
			return "success";
		} else if(id == 2) {
			return "error";
		} else {
			return "mainPage";
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
