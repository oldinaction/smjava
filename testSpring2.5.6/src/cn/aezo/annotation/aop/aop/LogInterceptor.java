package cn.aezo.annotation.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component//注意一定要批注Component，Spring才会帮忙实例化，从而进行组装(自动代理)
public class LogInterceptor {
	@Pointcut("execution(* cn.aezo.annotation.aop.dao..*.*(..))")//当执行包cn.aezo.annotation.aop.dao或其子包下的任意类的返回任意值得任意方法时织入myMethod()方法
	public void myMethod() {}
	
	@Before("myMethod()")//当执行方法myMethod()开始前织入
	public void before() {
		System.out.println("method before");
	}
	
	@Around("myMethod()")//环绕方法织入
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("method around start");
		pjp.proceed();//相当于doFilter里面的chain方法，表示执行链条的下一级
		System.out.println("method around end");
	}
	
	@AfterReturning("myMethod()")//方法执行完成后织入
	public void after() {
		System.out.println("method after returning");
	}
	
	@AfterThrowing("myMethod()")//当遇到异常后织入，在UserDAOImpl类中抛一个异常就可以看到效果
	public void throwing() {
		System.out.println("method throwing");
	}
}
