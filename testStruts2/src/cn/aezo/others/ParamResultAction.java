package cn.aezo.others;

import com.opensymphony.xwork2.ActionSupport;

//用Action属性接收url中的参数
public class ParamResultAction extends ActionSupport{
	
	private int id;
	
	public String execute() {
		return "success";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
