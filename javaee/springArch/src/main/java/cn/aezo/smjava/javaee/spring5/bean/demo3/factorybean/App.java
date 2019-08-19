package cn.aezo.smjava.javaee.spring5.bean.demo3.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class App {

    public static void main( String[] args ) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);

        // 获取的是FactoryBean中getObject返回的对象
        MyBean myBean = (MyBean) ctx.getBean("myFactoryBean");
        System.out.println(myBean);

        // 获取的是FactoryBean本身
        MyFactoryBean myFactoryBean = (MyFactoryBean) ctx.getBean("&myFactoryBean");
        System.out.println(myFactoryBean);
    }

    @Bean
    public MyFactoryBean myFactoryBean() {
        return new MyFactoryBean();
    }
}
