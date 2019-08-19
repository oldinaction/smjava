package cn.aezo.smjava.javaee.spring5.bean.demo1.lifecycle;

public class MyBean2 {
    public MyBean2() {
        System.out.println("constructor MyBean2...");
    }

    public void init() {
        System.out.println("init MyBean2...");
    }

    public void destroy() {
        System.out.println("destroy MyBean2...");
    }
}
