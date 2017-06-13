package cn.aezo.hello;

import com.opensymphony.xwork2.Action;

//开发时，不使用此方法
public class Hello2 implements Action{
	
	@Override
	public String execute() {
		return "success";
	}
	
}
