package cn.aezo.smjava.javaee.spring5.bean.demo2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableAezo // 加此注解，会动态把MyBean加入到IOC容器中
public class App {
    public static void main( String[] args ) {
        // 上下文环境
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(App.class);
        ctx.refresh(); // 刷新(只能执行一次)

        MyBean myBean = (MyBean) ctx.getBean("myBean");
        myBean.hello();
    }

    // @Bean // 静态加入MyBean
    // public MyBean myBean() {
    //     MyBean myBean = new MyBean();
    //     return myBean;
    // }
}









