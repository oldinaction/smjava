package cn.aezo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//推荐使用此url类型
@Controller//点击该类会发现，Controller的类上有个@org.springframework.stereotype.Component的批注，所有相当于有个@Component
@RequestMapping("/user")//省略也可以直接使用/add访问到add()方法
public class UserController {
	
	@RequestMapping("/add")//访问http://127.0.0.1:8080/testSpringMVC/user/add.do
	public void add() {
		System.out.println("add...");
	}
	
	@RequestMapping("/del")
	public void del() {
		System.out.println("del...");
	}
}
