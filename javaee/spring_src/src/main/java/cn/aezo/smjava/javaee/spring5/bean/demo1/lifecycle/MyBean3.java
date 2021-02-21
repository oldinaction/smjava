package cn.aezo.smjava.javaee.spring5.bean.demo1.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MyBean3 implements InitializingBean, DisposableBean {
    public MyBean3() {
        System.out.println("constructor MyBean3...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy MyBean3...");
    }

    // Bean创建完成，且属性赋值完成后调用
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet MyBean3...");
    }
}
