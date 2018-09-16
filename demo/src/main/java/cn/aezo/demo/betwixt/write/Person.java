package cn.aezo.demo.betwixt.write;

import java.util.List;
import java.util.Map;

/**
 * Created by smalle on 2017/3/23.
 */
public class Person {

    private String name;
    private int age;
    private List hobbys;
    private Map feature;

    public Person() {}

    public Person(String name, int age, List hobbys, Map feature) {
        this.name = name;
        this.age = age;
        this.hobbys = hobbys;
        this.feature = feature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List getHobbys() {
        return hobbys;
    }

    public void setHobbys(List hobbys) {
        this.hobbys = hobbys;
    }

    public Map getFeature() {
        return feature;
    }

    public void setFeature(Map feature) {
        this.feature = feature;
    }

    // 可省略
    public String toString() {
        return "Person[name='" + name + "',age='" + age + "']";
    }
}
