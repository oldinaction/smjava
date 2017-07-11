package cn.aezo.others;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.aezo.po.User;

//获取表单参数 ：通过方法参数的动态注入
@Controller
public class ParamController {
	
	//Controller是一个单例
	
	//法一：直接获取
	@RequestMapping("/param/login")//访问http://127.0.0.1:8080/testSpringMVC/param/login.do?n=smalle&p=123
	public void login(@RequestParam("n") String name, @RequestParam("p") String pwd) {
		System.out.println("login==" + name + "---" + pwd);
	}
	
	//法二：使用po
	@RequestMapping("/param/user")//访问http://127.0.0.1:8080/testSpringMVC/param/user.do?name=smalle&pwd=123
	public void addUser(User user) {
		System.out.println("addUser==" + user.getName());
	}
	
	//发三：http restFul风格
	@RequestMapping("/param/{id}/del")//访问http://127.0.0.1:8080/testSpringMVC/param/1/del.do
	public void del(@PathVariable("id") Integer id) {
		System.out.println("del==" + id);
	}
}
