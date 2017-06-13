package cn.aezo.others;

import com.opensymphony.xwork2.ActionSupport;

//用Action属性接收url中的参数
public class DynamicResultAction extends ActionSupport{
	
	private int id;
	private String result;
	
	public String execute() {
		if(id == 1) {
			result = "/others/success.jsp";
		} else {
			result = "/others/error.jsp";
		}
		return "success";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
