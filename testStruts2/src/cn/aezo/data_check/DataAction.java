package cn.aezo.data_check;

import com.opensymphony.xwork2.ActionSupport;

//用Action属性接收url中的参数
public class DataAction extends ActionSupport{
	
	private String name = null;
	
	public String getData() {
		if(name == null || !(name.equals("admin"))) {
			this.addFieldError("myError", "name error1");//添加字段错误信息,在jsp页面用struts2标签获取错误信息
			this.addFieldError("myError", "name error2");
			return "error";
		}
		return "success";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
