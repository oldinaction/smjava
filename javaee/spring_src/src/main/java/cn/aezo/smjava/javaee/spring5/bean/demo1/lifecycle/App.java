package cn.aezo.smjava.javaee.spring5.bean.demo1.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class App {

    public static void main( String[] args ) {
        /**
         * constructor MyBean2...
         * init MyBean2...
         * 创建IOC完成...
         * destroy MyBean2...
         */
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        System.out.println("创建IOC完成...");

        ctx.close(); // 销毁容器
    }

    // 指定初始化和销毁方法
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public MyBean2 myBean2() {
        return new MyBean2();
    }

    // 实现接口
    @Bean
    public MyBean3 myBean3() {
        return new MyBean3();
    }

    @Bean
    public MyBean4 myBean4() {
        return new MyBean4();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
