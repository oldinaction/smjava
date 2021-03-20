package cn.aezo.smjava.javaee.spring5.c01_ioc_flow.xml;

import cn.aezo.smjava.javaee.spring5.c01_ioc_flow.annotation.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author smalle
 * @date 2020-09-08 09:51
 */
public class AppXml {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring5/beans.xml");
        MyService myService = ac.getBean("myService", MyService.class);
        myService.doService();
    }

}
