package cn.aezo.smjava.javaee.spring5.bean.demo2;

public class MyBean {
    private String name;

    public void hello() {
        System.out.println("hello..." + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
