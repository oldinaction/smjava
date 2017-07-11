package cn.aezo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/demo")
public class DemoServlet {
	@RequestMapping(method=RequestMethod.GET)//访问http://127.0.0.1:8080/testSpringMVC/demo.do
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("get...");
	}
	
	@RequestMapping(method=RequestMethod.POST)//表单提交
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("post...");
	}
}
