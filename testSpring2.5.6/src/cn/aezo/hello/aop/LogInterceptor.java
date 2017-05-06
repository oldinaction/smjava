package cn.aezo.hello.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogInterceptor {
	
	public void before() {
		System.out.println("method before");
	}
	
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("method around start");
		pjp.proceed();//相当于doFilter里面的chain方法，表示执行链条的下一级
		System.out.println("method around end");
	}
	
	public void after() {
		System.out.println("method after returning");
	}
	
	public void throwing() {
		System.out.println("method throwing");
	}
}
