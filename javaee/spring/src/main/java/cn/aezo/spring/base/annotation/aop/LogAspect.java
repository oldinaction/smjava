package cn.aezo.spring.base.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by smalle on 2017/6/3.
 */
@Aspect // 声明一个切面
@Component
public class LogAspect {
    // 法一：简单
    @Before("execution(* cn.aezo.spring.base.annotation.aop.DemoMethodService.*(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("方法规则式拦截[@Before-execution]：" + method.getName());
    }

    // 法二：需要自定义注解类并注解
    // 声明切点, 利于复用
    @Pointcut("@annotation(cn.aezo.spring.base.annotation.aop.Action)")
    public void pintCutMethod() {
        System.out.println("不会运行");
    }

    // 声明一个建言，并使用@Pointcut注解的切点
    @After("pintCutMethod()")
    // @After("@annotation(cn.aezo.spring.base.annotation.aop.Action)")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截[@After-annotation]：" + action.name());
    }
}
