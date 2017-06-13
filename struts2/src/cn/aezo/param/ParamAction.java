package cn.aezo.param;

import com.opensymphony.xwork2.ActionSupport;

//用Action属性接收url中的参数
public class ParamAction extends ActionSupport{
	
	private String name;
	
	public String getParam() {
		System.out.println(name);
		return "success";
	}
	
	//访问"http://localhost:8080/testStruts2/others/param?name=smalle"时相当于调用了setName/getName方法，注意此时写的参数name和getName()中name的对应
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
