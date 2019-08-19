package cn.aezo.smjava.javaee.spring5.bean.demo1.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyBean4 {
    public MyBean4() {
        System.out.println("constructor MyBean4...");
    }

    @PostConstruct
    public void init() {
        System.out.println("init MyBean4...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy MyBean4...");
    }

}
