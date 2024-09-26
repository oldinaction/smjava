package cn.aezo.smjava.proguard.service.impl;

import cn.aezo.smjava.proguard.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	public String method1() {
		return "执行Method1方法";
	}

	public String getUserName(String param) {
		return null;
	}
}
