package cn.aezo.smjava.proguard.controller;

import java.lang.reflect.Method;

import cn.aezo.smjava.proguard.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private TestService testService;

	@RequestMapping("/test")
	public String test(){
		String Information="";
		// 反射调用私有方法测试
		try {
			Class cs = Class.forName("cn.aezo.smjava.proguard.entity.User");
			Method method= cs.getDeclaredMethod("information");
			method.setAccessible(true);
		    Information= (String)method.invoke(cs.newInstance());
			System.out.println(Information);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return Information;
	}

	@RequestMapping("/test2")
	public String test2() {
		return testService.method1();
	}
}
