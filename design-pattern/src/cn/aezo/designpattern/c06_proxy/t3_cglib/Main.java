package cn.aezo.designpattern.c06_proxy.t3_cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理
 *
 * @author smalle
 * @date 2020-06-09 22:32
 */
public class Main {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("pre...");
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("post...");
                return result;
            }
        });
        Dog dog = (Dog) enhancer.create();
        dog.move();
    }
}
