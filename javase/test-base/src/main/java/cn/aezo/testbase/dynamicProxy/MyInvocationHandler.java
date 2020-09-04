package cn.aezo.testbase.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 动态代理：实现InvocationHandler接口，使用Porxy类的静态方法newProxyInstance生成代理对象
public class MyInvocationHandler implements InvocationHandler {
    // 目标对象(或叫被代理的对象,此案例中的UserServiceImpl对象)
    private Object target;

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    // @Override重写执行目标对象的方法
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        // 在目标对象的方法执行之前加入自己的逻辑
        System.out.println("------------------before------------------");

        // 执行目标对象(被代理对象)的方法  
        Object result = method.invoke(target, args);

        // 在目标对象的方法执行之后加入自己的逻辑    
        System.out.println("-------------------after------------------");

        return result;

    }

    // 获取目标对象的代理对象
    public Object getProxy() {

        /**
         * 参数一：目标对象(被代理对象)和代理对象要在同一个ClassLoader中
         * 参数二：产生的目标对象应该实现的接口
         * 参数三：指实现了InvocationHandler类的一个实例。当产生一个代理后用该handler进行处理
         */
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);

    }

}
