package cn.aezo.smjava.javaee.spring5.bean.demo3;

import cn.aezo.smjava.javaee.spring5.bean.demo3.mapper.MyMapper;
import cn.aezo.smjava.javaee.spring5.bean.demo3.mybatis.AezoFactoryBean;
import cn.aezo.smjava.javaee.spring5.bean.demo3.mybatis.EnableAezo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// BeanFactory和FactoryBean区别 https://www.cnblogs.com/aspirant/p/9082858.html
@EnableAezo
public class App {
    public static void main( String[] args ) {
        // 上下文环境(管理Bean的工厂：AnnotationConfigApplicationContext->ApplicationContext->BeanFactory)
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(App.class);
        ctx.refresh();

        // 获取的是FactoryBean中getObject返回的对象
        MyMapper myMapper = (MyMapper) ctx.getBean("myMapper");
        myMapper.select(); // sql = select * from test

        // 获取的FactoryBean对象本身
        AezoFactoryBean aezoFactoryBean = (AezoFactoryBean) ctx.getBean("&myMapper");
        System.out.println("&myMapper = " + aezoFactoryBean); // &myMapper = cn.aezo.smjava.javaee.springarch.bean.demo3.mybatis.AezoFactoryBean@9a7504c

        System.out.println(ctx.getBean(AezoFactoryBean.class)); // cn.aezo.smjava.javaee.springarch.bean.demo3.mybatis.AezoFactoryBean@9a7504c

        // FactoryBean本身就是一个Bean
        MyMapper myMapper2 = (MyMapper) ctx.getBean("aezoFactoryBean2");
        myMapper2.select();
    }
}









