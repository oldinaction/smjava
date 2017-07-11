package cn.aezo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/struts1")
public class Struts1Controller {
	
	@RequestMapping(params="method=add")//访问http://127.0.0.1:8080/testSpringMVC/struts1.do?method=add
	public void add() {
		System.out.println("struts1 add...");
	}
	
	@RequestMapping(params="method=del")
	public void del() {
		System.out.println("struts1 del...");
	}
}
