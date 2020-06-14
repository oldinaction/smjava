package cn.aezo.designpattern.c06_proxy.t2_jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 *
 * @author smalle
 * @date 2020-06-09 22:32
 */
public class Main {

    public static void main(String[] args) {
        // 保存生产的代理类文件到本地
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); // jdk1.8，jdk12则为jdk.proxy.ProxyGenerator.saveGeneratedFiles

        Dog dog = new Dog();
        Movable movable = (Movable) Proxy.newProxyInstance(Dog.class.getClassLoader(), new Class[]{Movable.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("pre...");
                Object o = method.invoke(dog, args);
                System.out.println("post...");
                return o;
            }
        });
        movable.move();
    }
}
