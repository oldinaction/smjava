package cn.aezo.others;

import com.opensymphony.xwork2.ActionSupport;

//Action级别的国际化，properties文件前缀要是此Action的类名
//包级别的，properties文件前缀要是package
//全局级别的，前缀随便取
public class TestI18nAction extends ActionSupport {
	private String username;
	
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
