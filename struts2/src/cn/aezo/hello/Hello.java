package cn.aezo.hello;

import com.opensymphony.xwork2.ActionSupport;

//开发时，使用此方法
public class Hello extends ActionSupport{
	
	@Override
	public String execute() {
		return "success";
	}
	
}
