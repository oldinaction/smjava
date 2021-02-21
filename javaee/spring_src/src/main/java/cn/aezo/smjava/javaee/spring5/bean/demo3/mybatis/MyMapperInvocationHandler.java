package cn.aezo.smjava.javaee.spring5.bean.demo3.mybatis;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyMapperInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String sql = null;
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if(annotation instanceof Select) {
                sql = ((Select) annotation).value();
                break;
            }
        }

        System.out.println("sql = " + sql);
        return null;
    }
}
