package cn.aezo.smjava.javaee.spring5.bean.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main( String[] args ) {
        // 上下文环境
        // ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        /**
         * 1
         * 2
         * 创建myBean...
         * 3
         * hello...smalle
         * 创建myBean2...
         * hello...aezocn
         */
        // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // System.out.println("1");
        // ctx.register(AppConfig.class);
        // System.out.println("2");
        // ctx.refresh(); // 刷新(只能执行一次)
        // System.out.println("3");

        /**
         * 创建myBean...
         * 创建IOC完成...
         * hello...smalle
         * 创建myBean2...
         * hello...aezocn
         */
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("创建IOC完成...");

        MyBean myBean = (MyBean) ctx.getBean("myBean");
        myBean.hello();

        MyBean myBean2 = (MyBean) ctx.getBean("myBean2");
        myBean2.hello();

        // 获取所有Bean名称
        String[] names = ctx.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
