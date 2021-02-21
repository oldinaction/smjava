package cn.aezo.smjava.javaee.spring5.bean.c01_ioc_flow.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("cn.aezo.smjava.javaee.spring5.bean.c01_ioc_flow.annotation")
public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        System.out.println("创建IOC完成...");
        MyService myService = (MyService) ctx.getBean("myService");
        myService.doService();
    }
}
